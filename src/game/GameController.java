package game;

//import java.awt.Color;
//import java.awt.Image;
//import java.awt.Polygon;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import objects.BigBoomAtTheEnd;
import objects.BigExplosion;
import objects.Bomb;
import objects.Building;
import objects.BuildingType;
import objects.Bullet;
//import objects.Explosion;
import objects.GameObject;
import objects.Player;
import objects.Rain;

/**
 * The Class GameController mit inneren Klassen. Diese Klasse kontrolliert das Spiel.
 */
public class GameController {

	/** Singleton. Ein Instanz */
	private static GameController instance;

	/** Haelt Informationen ueber den Zustand des Spiels. */
	private GameState gameState;

	/**
	 * Zustaendig fuer die Darstellung und Entgegennahme von
	 * Benutzerinteraktionen.
	 */
	private GameFrame gameFrame;

	/** Generiert Bomb-Objekte am oberen Rand des GamePanels. */
	private BombCreatorThread bombCreatorThread;

	/**
	 * Enthaelt die eigentliche Logik (Kollisions-, Score- und
	 * Schadensberechnung sowie Levelverwaltung).
	 */
	private GameManagementThread gameManagementThread;

	/**
	 * Gets the single instance of GameController.
	 *
	 * @return single instance of GameController
	 */
	public static GameController getInstance() {
		if (instance == null) {
			instance = new GameController();
		}
		return instance;
	}

	/** The view scenario. */
	// 1: image
	// 2: polygon
	// 3: both
	public int viewScenario = 1;

	/** The rain thread. */
	// threadek
	private RainCreatorThread rainThread;
	/** The play storm sound thread. */
	private PlaySoundThread playStormSoundThread;

	/**
	 * Initialisiert das Spiel. Legt GameState sowie GameFrame an, fuellt und
	 * zeichnet die objectList mit saemtlichen Objekten, die zum Spielstart
	 * noetig sind.
	 */
	public void initiate() {
		this.gameState = new GameState();

		// emese, die Gebauden und der Spieler werden erzeugt
		this.createTown();
		this.createPlayer();
		this.gameFrame = new GameFrame();
	}

	/**
	 * Creates the player und fuegt zu das objektlist Vector zu
	 */
	public void createPlayer() {
		Player player = new Player();
		gameState.addGameObject(player);
	}

	/**
	 * Erstellt vorgegebene Anzahl an Gebaeuden von jedem Buildingtype. Diese
	 * werden lueckenlos am unteren Rand des GamePanels positioniert.
	 * Spielrelevante Gebaeude werden jeweils am Anfang und am Ende platziert.
	 * Dazwischen werden die restlichen Gebauede in zufaelliger Reihenfolge
	 * gesetzt. nur die zweit hauser ganz links und ganz rehcts sind
	 * spielrelevante objekte, also wenn sie sterben, GAMEOVER
	 */
	public void createTown() {
		// nur die zweit hauser ganz links und ganz rehcts sind spielrelevante
		// objekte, also wenn sie sterben, GAMEOVER

		// Create random list of game relevant buildings in the middle
		ArrayList<GameObject> rndBuildList = new ArrayList<>();
		for (int i = 0; i < GameSettings.churchNumber; i++) {
			Building b1 = new Building(BuildingType.CHURCH);
			rndBuildList.add(b1);
		}
		for (int i = 0; i < GameSettings.houseBlueNumber - 2; i++) {
			Building b2 = new Building(BuildingType.BLUEHOUSE);
			rndBuildList.add(b2);
		}
		for (int i = 0; i < GameSettings.houseYellowNumber; i++) {
			Building b3 = new Building(BuildingType.YELLOWHOUSE);
			rndBuildList.add(b3);
		}
		for (int i = 0; i < GameSettings.houseRedNumber; i++) {
			Building b4 = new Building(BuildingType.REDHOUSE);
			rndBuildList.add(b4);
		}
		// shuffle list to create random building order
		long seed = System.nanoTime();
		Collections.shuffle(rndBuildList, new Random(seed));

		// Create the real building order
		ArrayList<GameObject> buildingList = new ArrayList<>();
		// start building
		Building bHFirst = new Building(BuildingType.BLUEHOUSE);
		// spielrelevant
		bHFirst.setGameRelevant();
		buildingList.add(bHFirst);
		buildingList.addAll(rndBuildList);
		// end building
		Building bHLast = new Building(BuildingType.BLUEHOUSE);
		// spielrelevant
		bHLast.setGameRelevant();
		buildingList.add(bHLast);
		int x = 0;
		// iterieren und x,y hinzufuegen
		for (GameObject o : buildingList) {
			o.setX(0);
			o.setY(0);
			o.setdX(x);
			o.setdY(GameSettings.gamePanelHeight - o.getImage().getHeight(null));
			x = x + o.getImage().getWidth(null);
		}

		gameState.addList(buildingList);
	}

	/** Startet Spiel zum ersten Mal. Alle threads werden gestartet */
	public void startGame() {
		rainThread = new RainCreatorThread();
		rainThread.start();
		bombCreatorThread = new BombCreatorThread();
		bombCreatorThread.start();
		gameManagementThread = new GameManagementThread();
		gameManagementThread.start();
		playStormSoundThread = new PlaySoundThread("sounds/strom.wav");
		playStormSoundThread.start();
	}

	/**
	 * Reinitiate das Spiel.
	 */
	public void reinitiate() {
		this.gameState = new GameState();
		this.createTown();
		this.createPlayer();
	}

	/**
	 * Interrupt all game control threads, beim neustart
	 */
	public void interruptAllGameControlThreads() {
		while (rainThread.isAlive())
			rainThread.interrupt();
		while (bombCreatorThread.isAlive())
			bombCreatorThread.interrupt();
		while (gameManagementThread.isAlive())
			gameManagementThread.interrupt();
		while (playStormSoundThread.isAlive())
			playStormSoundThread.interrupt();
	}

	/** Startet das Spiel neu. */
	public void restartGame() {
		this.startGame();
	}

	/**
	 * Pausiert das Spiel, GameoverframeCreator wird auch hier aufgerufen, mit
	 * den aktuellen Punkten. Alle threads interrupted
	 */
	public void endGame() {
		// GameSettings.running = false;
		while (rainThread.isAlive())
			rainThread.interrupt();
		while (bombCreatorThread.isAlive())
			bombCreatorThread.interrupt();
		// GM thread called endgame, it will be interrupted after endgame was
		// called
		// while (gameManagementThread.isAlive())
		// gameManagementThread.interrupt();
		while (playStormSoundThread.isAlive())
			playStormSoundThread.interrupt();
		gameFrame.InterruptAllGameFrameThreads();
		gameFrame.createGameOverFrame((int) gameState.getScore());
		GameSettings.running = false;
	}

	/**
	 * Gets the game state.
	 *
	 * @return the game state
	 */
	public GameState getGameState() {
		return this.gameState;
	}

	/**
	 * Gets the game frame.
	 *
	 * @return the game frame
	 */
	public GameFrame getGameFrame() {
		return this.gameFrame;
	}

	/**
	 * The Class RainCreatorThread. Es regnet wahrend des Spiels, erzeugt
	 * raindrops
	 */
	private class RainCreatorThread extends Thread {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		public void run() {
			while (true) {
				try {
					if (GameSettings.running) {
						int randomXforRain = (int) (Math.random() * GameSettings.gamePanelWidth
								- GameSettings.playerWidth / 2 - GameSettings.playerWidth / 2)
								+ GameSettings.playerWidth / 2;
						Rain rain = new Rain(randomXforRain, -1 * GameSettings.rainCreatorYChange);
						GameController.getInstance().gameState.addGameObject(rain);
						Thread.sleep(30);
					} else {
						Thread.sleep(GameSettings.rainCreatorYChange);
					}
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}

	/**
	 * The Class BombCreatorThread. erzeugt Bomben Die Run-Methode der Klasse
	 * soll Bomb-Objekte an einer zufaelligen Stelle am oberen Rand des
	 * Gamepanels in einem vorgegebenen Zeitintervall abhaengig vom aktuellen
	 * Level generieren. Mit ansteigendem Level sollen die Pausenintervalle in
	 * konstanten Schritten bis zu einer vorgegeben Mindestgrenze abnehmen
	 * (siehe Klasse GameSettings).
	 */
	private class BombCreatorThread extends Thread {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		/*
		 * Die Run-Methode der Klasse soll Bomb-Objekte an einer zufaelligen
		 * Stelle am oberen Rand des Gamepanels in einem vorgegebenen
		 * Zeitintervall abhaengig vom aktuellen Level generieren. Mit
		 * ansteigendem Level sollen die Pausenintervalle in konstanten
		 * Schritten bis zu einer vorgegeben Mindestgrenze abnehmen (siehe
		 * Klasse GameSettings).
		 */
		public void run() {
			while (true) {
				try {
					if (GameSettings.running) {
						// create 1 new bomb
						int min = GameSettings.playerWidth / 2;
						int max = GameSettings.gamePanelWidth - GameSettings.playerWidth / 2 - min;
						int randomXforBomb = (int) (Math.random() * max) + min;
						Bomb bomb = new Bomb(randomXforBomb, -1 * GameSettings.bombCreatorYChange);
						GameController.getInstance().gameState.addGameObject(bomb);

						// reduce bomb creation interval per level
						if (GameController.getInstance().getGameState().getLevel() > 0) {
							int actualLevelForBombPause = GameController.getInstance().getGameState().getLevel() - 1;
							int actualPauseBetweenBombs = GameSettings.bombCreatorStartPause
									- actualLevelForBombPause * GameSettings.bombCreatorDecreaseNumber;
							if (actualPauseBetweenBombs > GameSettings.bombCreatorMinPause) {
								Thread.sleep(actualPauseBetweenBombs);
							} else {
								Thread.sleep(GameSettings.bombCreatorMinPause);
							}
						}
					} else {
						Thread.sleep(GameSettings.repaintIntervalInMS);
					}
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}

	/**
	 * Play sound bei alle Events (zum Biespiel small explosion, big explision,
	 * bullet)
	 *
	 * @param url
	 *            the url
	 */
	// SOUND
	synchronized public static void playSound(final String url) {
		new Thread(new Runnable() {
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(url));
					clip.open(inputStream);
					clip.start();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}

		}).start();
	}

	/**
	 * The Class GameManagementThread. MAIN Spiellogik Die Run-Methode soll die
	 * zentrale Logik des Spiels enthalten. 1. In jeder Iteration sollen alle
	 * Objekte auf Kollisionen untersucht und die entstehenden Schaeden
	 * zugewiesen werden. GameObjects, die sich zuvor selbst deaktiviert (active
	 * = 'false') haben, werden vom GameManagementThread aus der gameObjectList
	 * entfernt. Zudem soll das Spiel beendet werden (GameOver), wenn die
	 * vorgegebene Anzahl an spielrelevanten Objekten in der objectList
	 * unterschritten wird. 2. Nach Verstreichen eines vorgegebenen
	 * Zeitintervalls (levelTime) soll zusaetzlich das aktuelle Level
	 * hochgezaehlt und der Score um die Summe aller Rueckgabewerte der
	 * getScore()-Methoden, die Objekte mit dem Interface Scoreable
	 * implementieren, erhoeht werden. Das Pausieren des Spiels soll darauf
	 * keinen Einfluss haben.
	 */
	private class GameManagementThread extends Thread {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		/*
		 * Die Run-Methode soll die zentrale Logik des Spiels enthalten. 1. In
		 * jeder Iteration sollen alle Objekte auf Kollisionen untersucht und
		 * die entstehenden Schaeden zugewiesen werden. GameObjects, die sich
		 * zuvor selbst deaktiviert (active = 'false') haben, werden vom
		 * GameManagementThread aus der gameObjectList entfernt. Zudem soll das
		 * Spiel beendet werden (GameOver), wenn die vorgegebene Anzahl an
		 * spielrelevanten Objekten in der objectList unterschritten wird. 2.
		 * Nach Verstreichen eines vorgegebenen Zeitintervalls (levelTime) soll
		 * zusaetzlich das aktuelle Level hochgezaehlt und der Score um die
		 * Summe aller Rueckgabewerte der getScore()-Methoden, die Objekte mit
		 * dem Interface Scoreable implementieren, erhoeht werden. Das Pausieren
		 * des Spiels soll darauf keinen Einfluss haben.
		 */
		public void run() {
			long endLevelTimer = Calendar.getInstance().getTimeInMillis() + GameSettings.levelPause;
			long startPause = 0;

			while (true) {
				try {
					if (GameSettings.running) {
						// calculate Pause Duration wenn es notig ist
						if (startPause > 0) {
							long endPause = Calendar.getInstance().getTimeInMillis();
							long pauseDuration = endPause - startPause;
							endLevelTimer += pauseDuration;
							startPause = 0;
						}

						// increase Level if needed
						if (Calendar.getInstance().getTimeInMillis() >= endLevelTimer) {
							GameController.getInstance().getGameState().increaseLevel(1);
							endLevelTimer = Calendar.getInstance().getTimeInMillis() + GameSettings.levelPause;

							GameSettings.running = false;
							BBFrame frameLevelUp = new BBFrame();
							frameLevelUp.setFocusable(true);
							frameLevelUp.setAlwaysOnTop(true);
							frameLevelUp.toFront();
							frameLevelUp.requestFocus();
							frameLevelUp.repaint();
							Thread.sleep(1400);
							frameLevelUp.setVisible(false);
							GameSettings.running = true;

							// getScorable erhoehen //punkte berechnen
							long score = GameController.getInstance().getGameState().getScore();
							for (Building building : GameController.getInstance().getGameState().getBuildings()) {
								score += building.getScore();
							}
							GameController.getInstance().getGameState().setScore(score);
						}

						// move bombs
						for (Bomb b : GameController.getInstance().getGameState().getBombs()) {
							b.setdY(GameSettings.bombCreatorYChange);
						}
						// move rain
						for (Rain r : gameState.getRainDrops()) {
							r.setdY(GameSettings.rainCreatorYChange);
						}

						// remove inactive objects
						Vector<GameObject> v = GameController.getInstance().getGameState().getVectorWithGameObjects();
						for (int i = 0; i < v.size(); i++) {
							if (!v.elementAt(i).isActive()) {
								v.remove(i);
							}
						}

						// collision detection
						// A bomb_bullet
						for (Bomb bomb : GameController.getInstance().getGameState().getBombs()) {
							for (Bullet bullet : GameController.getInstance().getGameState().getBullets()) {
								if (bomb.collide(bullet)) {
									playSound("sounds/shotgun.wav");
									bomb.generateExplosion();
									bomb.setActive(false);
									bullet.setActive(false);
								}

							}
						}
						// B bomb_building
						for (Bomb bomb : GameController.getInstance().getGameState().getBombs()) {
							for (Building building : GameController.getInstance().getGameState().getBuildings()) {
								if (bomb.collide(building)) {
									playSound("sounds/buildingBomb.wav");
									bomb.generateExplosion();
									bomb.setActive(false);

									if (BuildingType.BLUEHOUSE.equals(building.getBuildingType())) {
										building.increaseDamage(1);
										if (building.getDamage() == GameSettings.houseBlueMaxDamage) {
											createBigExplosion(building);
										}
									}
									if (BuildingType.REDHOUSE.equals(building.getBuildingType())) {
										building.increaseDamage(1);
										if (building.getDamage() == GameSettings.houseRedMaxDamage) {
											createBigExplosion(building);
										}
									}
									if (BuildingType.YELLOWHOUSE.equals(building.getBuildingType())) {
										building.increaseDamage(1);
										if (building.getDamage() == GameSettings.houseYellowMaxDamage) {
											createBigExplosion(building);
										}
									}
									if (BuildingType.CHURCH.equals(building.getBuildingType())) {
										building.increaseDamage(1);
										if (building.getDamage() == GameSettings.churchMaxDamage) {
											createBigExplosion(building);
										}
									}
								}
							}
						}
						// C bomb player
						for (Bomb bomb : GameController.getInstance().getGameState().getBombs()) {
							Player player = GameController.getInstance().getGameState().getPlayer();
							if (bomb.collide(player)) {
								// das Leben von Player sich verringert
								player.subtractLife(1);
								// System.out.println("shot");
								bomb.generateExplosion();
								bomb.setActive(false);
								player.increaseDamage(1);
								playSound("sounds/flugzeugexpl.wav");

								// GAMEOVER_B
								if (player.getDamage() == GameSettings.playerMaximumDamage) {
									BigBoomAtTheEnd boom = new BigBoomAtTheEnd(0, (int) player.getdY());
									GameController.getInstance().gameState.addGameObject(boom);
									Thread.sleep(1000);
									player.setActive(false);
									endGame();
									throw new InterruptedException();
								}

							}
						}
						// feld und bomb
						for (Bomb bomb : GameController.getInstance().getGameState().getBombs()) {
							// wenn die Bombe triffts sich mit dem Boden..
							if (bomb.getY() >= GameSettings.gamePanelHeight - GameSettings.bombSize * 2) {
								bomb.generateExplosion();
								playSound("sounds/bombToGround.wav");
								bomb.setActive(false);
							}
						}

						// ueberprufen ob es noch spielrelevant objekte gibt
						if (gameState.getNumberOfGameRelevantAliveBuildings() == 0) {
							endGame();
							throw new InterruptedException();
						}

						Thread.sleep(GameSettings.repaintIntervalInMS);

					} else {
						if (startPause == 0) {
							startPause = Calendar.getInstance().getTimeInMillis();
						}
						Thread.sleep(GameSettings.repaintIntervalInMS);
					}
				} catch (InterruptedException e) {
					break;
				}

			}
		}
	}

	/**
	 * Creates the big explosion.
	 *
	 * @param building
	 *            the building
	 */
	private void createBigExplosion(Building building) {
		building.setActive(false);
		BigExplosion bigexpl = new BigExplosion((int) building.getX(),
				GameSettings.gamePanelHeight - GameSettings.churchHeight);
		GameController.getInstance().getGameState().addGameObject(bigexpl);
	}

}
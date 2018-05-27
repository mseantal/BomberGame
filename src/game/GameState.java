package game;

import java.util.ArrayList;
import java.util.Vector;

import objects.Bomb;
import objects.Building;
import objects.Bullet;
import objects.Explosion;
import objects.GameObject;
import objects.Player;
import objects.Rain;

/**
 * The Class GameState.
 */
public class GameState {

	/**
	 * Bestimmt Aktivitaet aller Thread-Objekte. Wenn 'false', ist das Spiel
	 * voruebergehend pausiert. Anmerkung: Im Gegensatz zum 'active'-Attribut
	 * der gameObjects selbst, fuehrt gameActive auf 'false' zu keinen
	 * Loeschungen.
	 */
	//meine befindet sich in gamesettings
	private boolean gameActive;
	/**
	 * Liste aller GameObjects, die in jedem Frame vom RepainterThread auf dem
	 * GamePanel gezeichnet werden sollen.
	 */
	private Vector<GameObject> objectList = new Vector<GameObject>();
	/**
	 * Bisheriger Punktestand, der vom Spieler erzielt wurde. Der Punktestand
	 * wird am Ende jedes Levels vom gameManagementThread um die Summe der
	 * Rueckgabewerte aller getScore()-Methoden, die Objekte mit dem Interface
	 * Scoreable implementieren, erhoeht.
	 */
	private long score;
	/**
	 * Aktuelles Level. Je hoeher das Level, desto weiter fortgeschritten das
	 * Spiel und desto kuerzer die Pausenzeiten zwischen der Generierung neuer
	 * Bomb-Objekte des bombCreatorThreads.
	 */
	private int level;

	/**
	 * Instantiates a new game state.
	 */
	public GameState() {
		this.objectList = new Vector<GameObject>();
		this.setGameActive(false);
		this.score = 0;
		this.level = 1;
	}

	/**
	 * Sets the score.
	 *
	 * @param score
	 *            the new score
	 */
	public void setScore(long score) {
		this.score = score;
	}

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public long getScore() {
		return this.score;
	}

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */

	public int getLevel() {
		return this.level;
	}

	/**
	 * Increase level.
	 *
	 * @param levelToIncrease
	 *            the level to increase
	 */
	public void increaseLevel(int levelToIncrease) {
		this.level += levelToIncrease;
	}

	/**
	 * Adds the game object to the vector
	 *
	 * @param ele
	 *            the ele
	 */
	// ich habe getters und setters geschrieben fuer alle objekttypen in
	// gameobjects fuer den vector
	public void addGameObject(GameObject ele) {
		objectList.addElement(ele);
	}

	/**
	 * Adds the list.
	 *
	 * @param list
	 *            the list
	 */
	public void addList(ArrayList<GameObject> list) {
		objectList.addAll(list);
	}

	/**
	 * Adds the element at a bestimmten Platz.
	 *
	 * @param ele
	 *            the ele
	 * @param k
	 *            the k
	 */
	public void addElementAt(GameObject ele, int k) {
		for (int i = 0; i < objectList.size(); i++) {
			if (i == objectList.size() - 1) {
				objectList.add(ele);
			}
		}
	}

	/**
	 * Sets the objects active.
	 *
	 * @param b
	 *            the new objects active
	 */
	public void setObjectsActive(boolean b) {
		for (GameObject o : objectList) {
			o.setActive(b);
		}
	}

	/**
	 * Removes the element from the vector.
	 *
	 * @param ele
	 *            the ele
	 */
	public void removeElement(GameObject ele) {
		objectList.remove(ele);
	}

	/**
	 * Removes the element at from the vector.
	 *
	 * @param i
	 *            the i
	 */
	public void removeElementAt(int i) {
		objectList.remove(i);
	}

	/**
	 * Gets the vector with game objects.
	 *
	 * @return the vector with game objects
	 */
	public Vector<GameObject> getVectorWithGameObjects() {
		return this.objectList;
	}

	/**
	 * Gets the player from the vector with gameobejcts.
	 *
	 * @return the player
	 */
	public Player getPlayer() {
		for (int i = 0; i < objectList.size(); i++) {
			if (objectList.elementAt(i) instanceof Player) {
				return (Player) objectList.elementAt(i);
			}
		}
		return null;
	}

	/**
	 * Gets the bombs from the vector with gameobjects.
	 *
	 * @return the bombs
	 */
	public ArrayList<Bomb> getBombs() {
		ArrayList<Bomb> bombs = new ArrayList<Bomb>();
		for (int i = 0; i < objectList.size(); i++) {
			if (objectList.elementAt(i) instanceof Bomb) {
				bombs.add((Bomb) objectList.elementAt(i));
			}
		}
		return bombs;
	}

	/**
	 * Gets the bullets from the vector with gameobjects..
	 *
	 * @return the bullets
	 */
	public ArrayList<Bullet> getBullets() {
		ArrayList<Bullet> bullets = new ArrayList<Bullet>();
		for (int i = 0; i < objectList.size(); i++) {
			if (objectList.elementAt(i) instanceof Bullet) {
				bullets.add((Bullet) objectList.elementAt(i));
			}
		}
		return bullets;
	}

	/**
	 * Gets the explosions.from the vector with gameobjects.
	 *
	 * @return the explosions
	 */
	public ArrayList<Explosion> getExplosions() {
		ArrayList<Explosion> explosions = new ArrayList<Explosion>();
		for (int i = 0; i < objectList.size(); i++) {
			if (objectList.elementAt(i) instanceof Explosion) {
				explosions.add((Explosion) objectList.elementAt(i));
			}
		}
		return explosions;
	}

	/**
	 * Gets the buildings.from the vector with gameobjects.
	 *
	 * @return the buildings
	 */
	public ArrayList<Building> getBuildings() {
		ArrayList<Building> buildings = new ArrayList<Building>();
		for (int i = 0; i < objectList.size(); i++) {
			if (objectList.elementAt(i) instanceof Building) {
				buildings.add((Building) objectList.elementAt(i));
			}
		}
		return buildings;
	}

	/**
	 * Gets the rain drops.from the vector with gameobjects.
	 *
	 * @return the rain drops
	 */
	public ArrayList<Rain> getRainDrops() {
		ArrayList<Rain> raindrops = new ArrayList<Rain>();
		for (int i = 0; i < objectList.size(); i++) {
			if (objectList.elementAt(i) instanceof Rain) {
				raindrops.add((Rain) objectList.elementAt(i));
			}
		}
		return raindrops;
	}

	/**
	 * Gets the number of game relevant alive buildings.
	 *
	 * @return the number of game relevant alive buildings
	 */
	public int getNumberOfGameRelevantAliveBuildings() {
		int counter = 0;
		for (Building build : this.getBuildings()) {
			if (build.isGameRelevant()) {
				counter++;
			}
		}
		return counter;
	}
	//is aktiv
	public boolean isGameActive() {
		return gameActive;
	}
	//set for gameactiv, meine befindet sich aber in gamesettings (running)
	public void setGameActive(boolean gameActive) {
		this.gameActive = gameActive;
	}
}

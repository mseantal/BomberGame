package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import objects.*;

// TODO: Auto-generated Javadoc
/**
 * The Class GameFrame, mit GamePanel und GameOverFrame und Updater inneren
 * Klassen
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	/** Darstellungsflaeche fuer das eigentliche Spielgeschehen. */
	private GamePanel gamePanel;

	/** Start- und Restart-Button. */
	private JButton startButton;

	/** Pause- und Fortsetzen-Button. */
	private JButton pauseButton;

	/** Anzeige der verbleibenden Lebenspunkte der Spielfigur. */
	private JLabel lifePoints;

	/** Anzeige des aktuellen Levels. */
	private JLabel level;

	/** Anzeige der bisher gesammelten Punkte. */
	private JLabel score;

	/** The view. */
	// emese
	private JButton view;

	/** The rep thread. */
	// emese
	private RepainterThread repThread;

	/** The game frame updater thread. */
	private GameFrameUpdater gameFrameUpdaterThread;

	/**
	 * Gets the life points.
	 *
	 * @return the life points
	 */
	public JLabel getLifePoints() {
		return this.lifePoints;
	}

	/**
	 * Sets the life points text.
	 *
	 * @param s
	 *            the new life points text
	 */
	// emese LifePoints
	public void setLifePointsText(String s) {
		this.lifePoints.setText(s);
	}

	/**
	 * Instantiates a new game frame.
	 */
	public GameFrame() {

		this.setTitle("GameFrame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();

		gamePanel = new GamePanel();

		c.add(gamePanel, BorderLayout.CENTER);
		c.add(createButtonPanel(), BorderLayout.SOUTH);

		this.setSize(GameSettings.gamePanelWidth + GameSettings.frameAdditionalWidth,
				GameSettings.gamePanelHeight + GameSettings.frameAdditionalHeight);

		// Fenster in der Mitte des Bildschirms positionieren
		final Dimension dimension = this.getToolkit().getScreenSize();
		this.setLocation((int) ((dimension.getWidth() - this.getWidth()) / 2),
				(int) ((dimension.getHeight() - this.getHeight()) / 2));

		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * Interrupt all game frame threads.
	 */
	public void InterruptAllGameFrameThreads() {
		while (repThread.isAlive()) {
			repThread.interrupt();
		}
		while (gameFrameUpdaterThread.isAlive()) {
			gameFrameUpdaterThread.interrupt();
		}
	}

	/**
	 * Erstellung eines Panels mit Start-, Pause-, View-Button und Anzeige von
	 * Level, Leben und Score.
	 *
	 * @return the j panel
	 */
	public JPanel createButtonPanel() {
		JPanel panel = new JPanel();

		this.startButton = new JButton("Start");
		this.startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
				JButton button = (JButton) e.getSource();
				if (button.getText().equals("Start")) {
					button.setText("Restart");
					repThread = new RepainterThread();
					repThread.start();
					gameFrameUpdaterThread = new GameFrameUpdater();
					gameFrameUpdaterThread.start();
					GameController.getInstance().startGame();
				} else {
					// wenn man nach dem gameover neustarten moechte
					GameSettings.running = true;

					// At restart interrupt all threads first
					InterruptAllGameFrameThreads();
					GameController.getInstance().interruptAllGameControlThreads();

					// Then restart the threads
					GameController.getInstance().reinitiate();
					repThread = new RepainterThread();
					repThread.start();
					gameFrameUpdaterThread = new GameFrameUpdater();
					gameFrameUpdaterThread.start();
					GameController.getInstance().restartGame();
				}
			}
		});

		// emese view Button, polygon, bild oder beide
		view = new JButton("Pol");
		this.view.addActionListener(new ActionListener() {
			public synchronized void actionPerformed(ActionEvent e) {

				JButton jButton = (JButton) e.getSource();
				if (jButton.getText().equals("Bild")) {
					jButton.setText("Pol");
					GameController.getInstance().viewScenario = 1;
				} else if (jButton.getText().equals("Pol")) {
					jButton.setText("Beide");
					GameController.getInstance().viewScenario = 2;
				} else if (jButton.getText().equals("Beide")) {
					jButton.setText("Bild");
					GameController.getInstance().viewScenario = 3;
				}
			}
		});

		this.pauseButton = new JButton("Anhalten");
		this.pauseButton.addActionListener(new ActionListener() {
			public synchronized void actionPerformed(ActionEvent e) {
				JButton jButton = (JButton) e.getSource();

				if (jButton.getText().equals("Anhalten")) {
					jButton.setText("Fortsetzen");
					// alle objekt setActivated false
					GameController.getInstance().getGameState().setObjectsActive(false);
					// alle thread wait
					GameSettings.running = false;

				} else if (jButton.getText().equals("Fortsetzen")) {
					jButton.setText("Anhalten");
					// alle Objekte wieder true bei setactivated
					GameController.getInstance().getGameState().setObjectsActive(true);
					// threads zuruecksetzen
					GameSettings.running = true;
				}
			}
		});

		lifePoints = new JLabel();
		lifePoints.setText("Leben: -");
		level = new JLabel();
		level.setText("Level: -");
		score = new JLabel();
		score.setText("Punkte -");

		panel.add(startButton);
		panel.add(pauseButton);
		panel.add(view);
		panel.add(lifePoints);
		panel.add(level);
		panel.add(score);

		return panel;
	}

	/**
	 * Generiert Frame mit GameOver-Meldung und erreichtem Punktestand.
	 *
	 * @param score
	 *            Endpunktestand nach verlorenem Spiel
	 */
	public void createGameOverFrame(int score) {
		// this.toBack();
		// this.setFocusable(false);
		GameOverFrame gf = new GameOverFrame(score); // setfocuable nincs benne
		gf.setFocusable(true);
		gf.setAlwaysOnTop(true);
		gf.toFront();
		gf.requestFocus();
		gf.repaint();
	}

	/**
	 * Gets the panel height.
	 *
	 * @return the panel height
	 */
	public int getPanelHeight() {
		return this.gamePanel.getHeight();
	}

	/**
	 * Gets the panel width.
	 *
	 * @return the panel width
	 */
	public int getPanelWidth() {
		return this.gamePanel.getWidth();
	}

	/**
	 * Repaint game panel.
	 */
	public void repaintGamePanel() {
		this.gamePanel.repaint();
	}

	/**
	 * The Class GamePanel.
	 */
	private class GamePanel extends JPanel {

		/** Breite des GamePanels. */
		private int width = GameSettings.gamePanelWidth;

		/** Hoehe des GamePanels. */
		private int height = GameSettings.gamePanelHeight;

		/** The cloud1. */
		private Cloud cloud1 = new Cloud(100, 50);

		/** The cloud2. */
		private Cloud cloud2 = new Cloud(150, 200);

		/** The cloud3. */
		private Cloud cloud3 = new Cloud(400, 120);

		/**
		 * Instantiates a new game panel.
		 */
		public GamePanel() {

			setPreferredSize(new Dimension(width, height));
			// emese right left listener, ich habe den listener zu dem Panel
			// hinzugefuegt
			this.addKeyListener(new ArrowListenerForThePlayer());
			this.setFocusable(true);
		}

		/**
		 * The Class ArrowListenerForThePlayer. Mit der Hilfe dieser Klasse wird
		 * der Player sich bewegen (mit Pfeilen auf der Tatstatur)
		 */
		// emese Listener zu den Player fuer die pfeilen
		public class ArrowListenerForThePlayer implements KeyListener, Runnable {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
			 */
			@Override
			public void keyPressed(KeyEvent e) {
				// ha gamesettings true, nem megy bele, es nem megy tovabb a
				// repcsi
				if (GameSettings.running) {
					Player p = GameController.getInstance().getGameState().getPlayer();
					int keyCode = e.getKeyCode();
					switch (keyCode) {
					case KeyEvent.VK_UP:
						Bullet bullet = new Bullet(p.getX() + GameSettings.playerWidth / 2, p.getY());
						GameController.getInstance().getGameState().addGameObject(bullet);
						GameController.playSound("sounds/shootBullets.wav");
						bullet.start();
						break;
					case KeyEvent.VK_DOWN:
						// handle down
						break;
					case KeyEvent.VK_LEFT:
						GameController.getInstance().getGameState().getPlayer().stepLeft(GameSettings.playerDX);
						break;
					case KeyEvent.VK_RIGHT:
						GameController.getInstance().getGameState().getPlayer().stepRight(GameSettings.playerDX);
						break;
					}
				}
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
			 */
			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
			 */
			@Override
			public void keyTyped(KeyEvent arg0) {
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Runnable#run()
			 */
			@Override
			public void run() {
			}
		}

		/**
		 * Zeichnet GamePanel.Wolken im Hintergrund, alle GameObjekte von
		 * gebauden bis explosion. Alle objekten werden immer wieder neumal
		 * gezeichnet, repaint() im Thread ruft paint() auf
		 * 
		 * @param g
		 *            Graphics
		 */
		public void paint(Graphics g) {

			Graphics2D graphics2D = (Graphics2D) g;
			graphics2D.setColor(GameSettings.gamePanelBackgroundColor);
			graphics2D.fillRect(0, 0, width, height);

			// cloud painten
			graphics2D.drawImage(cloud1.getImage(), cloud1.getX(), cloud1.getY(), null);
			graphics2D.drawImage(cloud2.getImage(), cloud2.getX(), cloud2.getY(), null);
			graphics2D.drawImage(cloud3.getImage(), cloud3.getX(), cloud3.getY(), null);

			Vector<GameObject> vectorWithGameObjects = GameController.getInstance().getGameState()
					.getVectorWithGameObjects();
			synchronized (this) {
				for (int i = 0; i < vectorWithGameObjects.size(); i++) {
					GameObject o = vectorWithGameObjects.elementAt(i);

					// Set coordinates that were changed
					o.setY(o.getY() + o.getdY());
					o.setX(o.getX() + o.getdX());

					// Move shape to the new place
					AffineTransform at = new AffineTransform();
					at.translate(o.getdX(), o.getdY());
					Shape s = at.createTransformedShape(o.getShape());

					// Set new shape and reset moving values
					o.setShape(s);
					o.setdX(0);
					o.setdY(0);

					// draw
					o.draw(graphics2D);
					// draw schaden
					graphics2D.setColor(Color.BLACK);
					graphics2D.setFont(new Font("Comic Sans MS", Font.BOLD, 10));
					if (o instanceof Building) {
						if (o.getDamage() == 1) {
							graphics2D.setColor(Color.ORANGE);
							graphics2D.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
						}
						if (o.getDamage() >= 2) {
							graphics2D.setColor(Color.RED);
							graphics2D.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
						}
						if (((Building) o).getBuildingType().equals(BuildingType.BLUEHOUSE))
							graphics2D.drawString(
									String.valueOf(o.getDamage()) + " / "
											+ String.valueOf(GameSettings.houseBlueMaxDamage),
									(int) o.getX(), (int) o.getY());
						if (((Building) o).getBuildingType().equals(BuildingType.REDHOUSE))
							graphics2D.drawString(
									String.valueOf(o.getDamage()) + " / "
											+ String.valueOf(GameSettings.houseRedMaxDamage),
									(int) o.getX(), (int) o.getY());
						if (((Building) o).getBuildingType().equals(BuildingType.YELLOWHOUSE))
							graphics2D.drawString(
									String.valueOf(o.getDamage()) + " / "
											+ String.valueOf(GameSettings.houseYellowMaxDamage),
									(int) o.getX(), (int) o.getY());
						if (((Building) o).getBuildingType().equals(BuildingType.CHURCH))
							graphics2D.drawString(
									String.valueOf(o.getDamage()) + " / "
											+ String.valueOf(GameSettings.churchMaxDamage),
									(int) o.getX(), (int) o.getY());
					}

					// set explosions inactive later (wait some frames)
					if (o instanceof Explosion) {
						Explosion e = (Explosion) o;
						if (e.framesUntilVisible == 0)
							o.setActive(false);
						else
							e.framesUntilVisible -= 1;
					}
					if (o instanceof BigExplosion) {
						BigExplosion e = (BigExplosion) o;
						if (e.framesUntilVisible == 0)
							o.setActive(false);
						else
							e.framesUntilVisible -= 1;
					}
				}
			}
			// sehen
			this.requestFocus();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.JComponent#getHeight()
		 */
		public int getHeight() {
			return this.height;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.JComponent#getWidth()
		 */
		public int getWidth() {
			return this.width;
		}
	}

	/**
	 * The Class GameOverFrame. Wenn der Spieler tot ist, oder es keine
	 * spielrelevante Objekte gibt, (zwei bluehauser) dann ist das Spiel beendet
	 */
	private class GameOverFrame extends JFrame {

		/**
		 * Generiert Frame mit GameOver-Meldung und erreichtem Punktestand.
		 *
		 * @param score
		 *            Endpunktestand nach verlorenem Spiel
		 */
		public GameOverFrame(int score) {

			this.setTitle("Game Over!");
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			Container c = getContentPane();

			JLabel gameOverLabel = new JLabel("Game Over!");
			gameOverLabel.setFont(new Font("Arial Bold", Font.ITALIC, GameSettings.gameOverLabelSize));
			gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);

			JLabel scoreLabel = new JLabel("Sie haben " + score + " Punkte erreicht.");
			scoreLabel.setFont(new Font("Arial Bold", Font.ITALIC, GameSettings.scoreLabelSize));
			scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GameOverFrame.this.dispose();
				}
			});

			c.add(gameOverLabel, BorderLayout.NORTH);
			c.add(scoreLabel, BorderLayout.CENTER);
			c.add(okButton, BorderLayout.SOUTH);

			this.setSize(GameSettings.gameOverFrameWidth, GameSettings.gameOverFrameHeight);

			final Dimension dimension = this.getToolkit().getScreenSize();
			this.setLocation((int) ((dimension.getWidth() - this.getWidth()) / 2),
					(int) ((dimension.getHeight() - this.getHeight()) / 2));

			this.setResizable(true);
			this.setVisible(true);

		}

	}

	/**
	 * The Class RepainterThread.hier wird alle Objekte immer wieder
	 * neugezeichnet werden (mti repaint)
	 */
	private class RepainterThread extends Thread {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		// TODO Run-Methode soll das GamePanel in kurzen Abstaenden neu zeichen.
		@Override
		public void run() {
			while (true) {
				try {
					if (GameSettings.running) {
						GameFrame.this.gamePanel.repaint();
						Thread.sleep(GameSettings.repaintIntervalInMS);
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
	 * The Class GameFrameUpdater. Run-Methode soll Informationsanzeigen der GUI
	 * (lifePoints, level, score) in kurzen Abstaenden mit den Daten aus der
	 * Klasse gameState aktualisieren.
	 */
	private class GameFrameUpdater extends Thread {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		/*
		 * Run-Methode soll Informationsanzeigen der GUI (lifePoints, level,
		 * score) in kurzen Abstaenden mit den Daten aus der Klasse gameState
		 * aktualisieren.
		 */
		synchronized public void run() {
			while (true) {
				try {
					// Leben
					if (GameController.getInstance().getGameState().getPlayer() != null) {
						GameFrame.this.lifePoints.setText(
								"Leben: " + GameController.getInstance().getGameState().getPlayer().getLeben());
					} else if (GameController.getInstance().getGameState().getPlayer() == null) {
						// der Player ist tot (nach setActive(false))
						GameFrame.this.score.setText("Punkte: " + 0);
					}
					// Level
					GameFrame.this.level.setText("Level: " + GameController.getInstance().getGameState().getLevel());
					// Punkte
					GameFrame.this.score.setText("Punkte: " + GameController.getInstance().getGameState().getScore());
					Thread.sleep(GameSettings.repaintIntervalInMS);
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}

}

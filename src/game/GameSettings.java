package game;

import java.awt.Color;

/**
 * Klasse, die alle Einstellungsmoeglichkeiten und weitere Konstanten
 * beinhaltet.
 */

public class GameSettings {

	/** The Constant repaintIntervalInMS. */
	// emese_fuer Threads
	public static final long repaintIntervalInMS = 20;

	/** The Constant bulletMovingPerRepaint. */
	public static final long bulletMovingPerRepaint = 10;

	/** The running.Allgemeine boolean fuer pausieren alle threads */
	public static boolean running = true;

	/** The Constant bombCreatorStartPause. */
	// bombCreator
	public static final int bombCreatorStartPause = 2000; // Pause, zwischen
															// Generierung
															// einzelner Bomben
															// zu Beginn/Level 1
															// (in ms) //2000
															// volt

	/** The Constant bombCreatorDecreaseNumber. */
	public static final int bombCreatorDecreaseNumber = 250; // Wert, um den die
																// Pausenzeit
																// pro
																// Levelanstieg
																// reduziert
																// wird (in ms)

	/** The Constant bombCreatorMinPause. */
	public static final int bombCreatorMinPause = 500; // Pausenzeitwert, der
														// nicht unterschritten
														// werden darf (in ms)

	/** The Constant bombSize. */
	public static final int bombSize = 25; // Hoehe und Breite der generierten
											// Bomben

	/** The Constant bombCreatorYChange. */
	// mse
	public static final int bombCreatorYChange = 2;

	/** The Constant cloudWidth. */
	public static final int cloudWidth = 200;

	/** The Constant cloudHeight. */
	public static final int cloudHeight = 100;

	/** The Constant cloudPieces. */
	public static final int cloudPieces = 6;

	/** The Constant eagleWidth. */
	public static final int eagleWidth = 60;

	/** The Constant eagleHeight. */
	public static final int eagleHeight = 100;

	/** The Constant smallExplosionWidth. */
	public static final int smallExplosionWidth = 90;

	/** The Constant smallExplosionHeight. */
	public static final int smallExplosionHeight = 60;

	/** The Constant bigExplosionWidth. */
	public static final int bigExplosionWidth = 80;

	/** The Constant bigExplosionHeight. */
	public static final int bigExplosionHeight = 150;

	/** The Constant finalExplosionWidth. */
	public static final int finalExplosionWidth = 600;

	/** The Constant finalExplosionHeight. */
	public static final int finalExplosionHeight = 600;

	/** The Constant rainWidth. */
	public static final int rainWidth = 1;

	/** The Constant rainHeight. */
	public static final int rainHeight = 5;

	/** The Constant rainCreatorYChange. */
	public static final int rainCreatorYChange = 25;

	/** The Constant playerWidth. */
	// player
	public static final int playerWidth = 60; // Breite der Spielfigur

	/** The Constant playerHeight. */
	public static final int playerHeight = 30; // Hoehe der Spielfigur

	/** The Constant playerMaximumDamage. */
	public static final int playerMaximumDamage = 4; // Maximaler Schaden, den
														// die Spielfigur
														// einstecken kann

	/** The Constant playerDX. */
	public static final double playerDX = 10; // X-Wert, um die Position der
												// Spielfigur bei Bewegung pro
												// Nutzereingabe verschoben wird

	/** The Constant bulletSize. */
	// player bullet
	public static final int bulletSize = 5; // Hoehe und Breite eines
											// Bullet-Objekts

	// buildings - Maximalschaden, Breite, Hoehe und Anzahl

	/** The Constant houseBlueMaxDamage. */
	public static final int houseBlueMaxDamage = 2;

	/** The Constant houseYellowMaxDamage. */
	public static final int houseYellowMaxDamage = 2;

	/** The Constant houseRedMaxDamage. */
	public static final int houseRedMaxDamage = 1;

	/** The Constant churchMaxDamage. */
	public static final int churchMaxDamage = 3;

	/** The Constant houseBlueWidth. */
	public static final int houseBlueWidth = 90;

	/** The Constant houseYellowWidth. */
	public static final int houseYellowWidth = 85;

	/** The Constant houseRedWidth. */
	public static final int houseRedWidth = 85;

	/** The Constant churchWidth. */
	public static final int churchWidth = 100;

	/** The Constant houseBlueHeight. */
	public static final int houseBlueHeight = 80;

	/** The Constant houseYellowHeight. */
	public static final int houseYellowHeight = 80;

	/** The Constant houseRedHeight. */
	public static final int houseRedHeight = 80;

	/** The Constant churchHeight. */
	public static final int churchHeight = 140;

	/** The Constant houseBlueNumber. */
	public static final int houseBlueNumber = 2;

	/** The Constant houseYellowNumber. */
	public static final int houseYellowNumber = 1;

	/** The Constant houseRedNumber. */
	public static final int houseRedNumber = 1;

	/** The Constant churchNumber. */
	public static final int churchNumber = 1;

	/** The Constant relevantBuildingsNumber. */
	public static final int relevantBuildingsNumber = 2; /*
															 * Anzahl
															 * spielrelevanter
															 * Gebaeude, die
															 * jeweils ganz
															 * links bzw. ganz
															 * rechts platziert
															 * werden, aus der
															 * Menge aller
															 * Gebauede
															 */

	/** The Constant gameOverFrameWidth. */
	// GameOverFrame (vorgegeben)
	public static final int gameOverFrameWidth = 310;

	/** The Constant gameOverFrameHeight. */
	public static final int gameOverFrameHeight = 170;

	/** The Constant gameOverLabelSize. */
	public static final int gameOverLabelSize = 45;

	/** The Constant scoreLabelSize. */
	public static final int scoreLabelSize = 15;

	/** The Constant levelPause. */
	// gameManager
	public static final int levelPause = 20000; // Dauer eines Levels (in ms)
												// //20000 volt

	/** The Constant numberRelevantGameObjects. */
	public static final int numberRelevantGameObjects = relevantBuildingsNumber
			+ 1; /*
					 * Anzahl spielrelevanter Objekte (relevante Gebauede +
					 * Player). Wenn eines der Objekte geloescht wird, gilt das
					 * Spiel als verloren.
					 */

	/** The Constant frameAdditionalWidth. */
	// GameFrame
	public static final int frameAdditionalWidth = 6; // zusaetzliche Breite
														// gegenueber dem
														// GamePanel

	/** The Constant frameAdditionalHeight. */
	public static final int frameAdditionalHeight = 77; // zusaetzliche Hoehe
														// gegenueber dem
														// GamePanel

	/** The Constant gamePanelWidth. */
	// GamePanel
	public static final int gamePanelWidth = houseBlueWidth * houseBlueNumber + houseYellowWidth * houseYellowNumber
			+ houseRedWidth * houseRedNumber + churchWidth * churchNumber; // Breite
																			// in
																			// Abhaengigkeit
																			// der
																			// vorhandenen
																			// Gebauede

	/** The Constant gamePanelHeight. */
	public static final int gamePanelHeight = 500; // Hoehe des GamePanel

	/** The game panel background color. */
	public static Color gamePanelBackgroundColor = new Color(0, 204, 255); // Hintergrundfarbe
																			// des
																			// GamePanels

	/** The Constant playerPolygonXValues. */
	// ich habe meinen eigenen Bilder, deshalb auch meine eingenen Koordianten
	// benutzt, da es keine hochgeladene Bilder gab, als ich mit dem Projekt
	// angefangen habe
	/*
	 * Polygone: X- und Y-Werte von Polygonen, die zu den bereitgestellten
	 * PNG-Bildern passen. Um die Polygone auf die gewollte Groesse zu
	 * skalieren, muessen die X-Werte mit der Breite und die Y-Werte mit der
	 * gewuenschten Hoehe multipliziert werden. Es gilt dabei, dass
	 * Seitenverhaeltnis der Bilddateien zu beachten.
	 */
	public static final double[] playerPolygonXValues = { 0.0, 0.03334, 0.53334, 0.5667, 0.7334, 0.7167, 0.7334, 0.7334,
			0.8, 0.8334, 0.8167, 0.8, 0.8167, 0.8334, 1.0, 1.0, 0.9667, 0.7667, 0.7834, 0.5333, 0.6167, 0.1834, 0.1334,
			0.0665, 0.05 };

	/** The Constant playerPolygonYValues. */
	public static final double[] playerPolygonYValues = { -0.6, -0.5666, -0.35, -0.3064, -0.3033, -0.2334, -0.0667,
			-0.0334, -0.0667, -0.1667, -0.2, -0.2667, -0.1667, -0.3667, -0.5, -0.6, -0.6634, -0.6667, -0.9434, -0.94,
			-0.6667, -0.6334, -0.8667, -0.8934, -0.6 };

	/** The Constant houseBluePolygonXValues. */
	public static final double[] houseBluePolygonXValues = { 0.088, 0.088, 0.0111, 0.1, 0.1556, 0.15556, 0.277, 0.277,
			0.3222, 0.3222, 0.267, 0.3333, 0.6766, 0.7211, 0.6666, 0.6666, 0.7333, 0.7333, 0.844, 0.844, 0.8988, 0.9977,
			0.9115, 0.9115 };

	/** The Constant houseBluePolygonYValues. */
	public static final double[] houseBluePolygonYValues = { 0.0, -0.4375, -0.45, -0.7375, -0.7375, -0.8, -0.8, -0.75,
			-0.75, -0.83, -0.83, -1.0, -1.0, -0.83, -0.825, -0.7375, -0.7375, -0.8, -0.8, -0.7375, -0.7375, -0.4675,
			-0.4625, 0.0 };

	/** The Constant houseYellowPolygonXValues. */
	public static final double[] houseYellowPolygonXValues = { 0.0585, 0.0585, 0.0, 0.0588, 0.1594, 0.1594, 0.2788,
			0.2788, 0.7258, 0.7258, 0.8435, 0.8435, 0.9258, 1.0, 0.9511, 0.9511 };

	/** The Constant houseYellowPolygonYValues. */
	public static final double[] houseYellowPolygonYValues = { 0.0, -0.65, -0.65, -0.9125, -0.9125, -1.0, -1.0, -0.9125,
			-0.9125, -1.0, -1.0, -0.9125, -0.9125, -0.65, -0.65, 0.0 };

	/** The Constant houseRedPolygonXValues. */
	public static final double[] houseRedPolygonXValues = { 0.0941, 0.0941, 0.0235, 0.0, 0.5058, 0.9885, 0.9885, 0.9059,
			0.9059 };

	/** The Constant houseRedPolygonYValues. */
	public static final double[] houseRedPolygonYValues = { 0.0, -0.625, -0.6, -0.675, -0.9625, -0.6625, -0.6125,
			-0.625, 0.0 };

	/** The Constant churchPolygonXValues. */
	public static final double[] churchPolygonXValues = { 0.0, 0.0, 0.72, 0.72, 0.68, 0.86, 1.0, 1.0, 1.0 };

	/** The Constant churchPolygonYValues. */
	public static final double[] churchPolygonYValues = { 0.0, -0.5438, -0.5438, -0.7714, -0.7714, -0.9857, -0.8142,
			-0.7714, 0.0 };

	/** The Constant bombPolygonXValues. */
	public static final double[] bombPolygonXValues = { 0.25, 0.65, 0.8, 0.8, 0.7, 0.55, 0.45, 0.35, 0.25, 0.1, 0.0,
			0.05 };

	/** The Constant bombPolygonYValues. */
	public static final double[] bombPolygonYValues = { 0.0, 0.0, -0.15, -0.4, -0.6, -0.65, -0.85, -0.85, -0.65, -0.6,
			-0.4, -0.15 };

}
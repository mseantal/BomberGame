package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Point2D;

import game.GameController;
import game.GameSettings;

/**
 * The Class GameObject.Behalter von allen Objekte.
 */
public abstract class GameObject extends Thread implements Explosive {

	/**
	 * Beendet bei 'false' die Aktivitaeten des Objekts, gibt es zur Entfernung
	 * aus der objectList durch den gameManagementThread frei und fuehrt damit
	 * zur Loeschung durch den Garbage Collector.
	 */
	protected boolean active;
	/**
	 * True, falls Existenz des Objekts relevant fuer die Weiterfuehrung des
	 * Spiels.
	 */
	protected boolean gameRelevant;
	/**
	 * Polygon des Objekts. Wird zur Darstellung und Kollisionsermittlung
	 * verwendet. Beinhaltet Positionsinformationen (Polygon jeweils um aktuelle
	 * Position verschoben). Falls null, sind keine Kollisionen moeglich.
	 */
	protected Polygon polygon;
	
	/**  Farbe des Polygons. */
	protected Color color;
	
	/**  Position des linken, unteren Eckpunkts des Objekts auf dem GamePanel. */
	protected Point2D.Double position;
	
	/**  Breite des Objekts. */
	protected int width;
	
	/**  Hoehe des Objekts. */
	protected int height;
	
	/**  Maximaler Schaden. */
	protected int maximumDamage;
	
	/**  Aktueller Schaden. */
	protected int damage;
	
	/**  Threadpause nach jeder Iteration. */
	protected int pause;
	
	/**  Positionsverschiebung in X- und Y-Richtung bei jeder Bewegung. */
	protected double dX, dY;

	/** The image. */
	// emese
	protected Image image;
	
	/** The shape. */
	protected Shape shape;
	
	/** The y. */
	protected double x, y;
	// emese

	/* (non-Javadoc)
	 * @see objects.Explosive#generateExplosion()
	 */
	//erzeugt Explosion und speichert sie in die GO Vector
	@Override
	public void generateExplosion() {
		Explosion smallExplosion = new Explosion((int) this.getX() - GameSettings.smallExplosionWidth / 2,
				(int) this.getY());
		GameController.getInstance().getGameState().addGameObject(smallExplosion);
	}

	/* (non-Javadoc)
	 * @see objects.Explosive#setSilent(boolean)
	 */
	@Override
	public void setSilent(boolean silent) {
		}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Gets the d x.
	 *
	 * @return the d x
	 */
	public double getdX() {
		return dX;
	}

	/**
	 * Sets the d x.
	 *
	 * @param dX the new d x
	 */
	public void setdX(double dX) {
		this.dX = dX;
	}

	/**
	 * Gets the d y.
	 *
	 * @return the d y
	 */
	public double getdY() {
		return dY;
	}

	/**
	 * Sets the d y.
	 *
	 * @param dY the new d y
	 */
	public void setdY(double dY) {
		this.dY = dY;
	}

	/**
	 * Instantiates a new game object.
	 */
	public GameObject() {
		this.damage = 0;
		this.active = true;
		this.gameRelevant = false;
	}

	/**
	 * Ermittelt Kollision zweier GameObjects.
	 *
	 * @param gameObjectToCheck            GameObject
	 * @return boolean Kollision
	 */
	public abstract boolean collide(GameObject gameObjectToCheck);

	/**
	 * Spiegelt Polygon horizontal.
	 */
	public abstract void flipPolygonHorizontally();

	/**
	 * Ermittelt, ob das gegebene Objekt noch im sichtbaren Bereich des
	 * GamePanels liegt.
	 * 
	 * @return boolean true, falls Objekt außerhalb des sichtbaren Bereichs
	 */
	public abstract boolean outOfView();

	/**
	 * Zeichnet Polygon des Objekts auf dem GamePanel (bzw. dessen Graphics
	 * Context)
	 * 
	 * @param graphics2D
	 *            Graphics2D
	 */
	public void draw(Graphics2D graphics2D) {
		if (GameController.getInstance().viewScenario == 1) {
			graphics2D.drawImage(this.getImage(), (int) this.getX(), (int) this.getY(), null);
		} else if (GameController.getInstance().viewScenario == 2) {
			graphics2D.setColor(Color.RED);
			if (this.getShape() != null)
				graphics2D.draw(this.getShape());
		} else if (GameController.getInstance().viewScenario == 3) {
			graphics2D.drawImage(this.getImage(), (int) this.getX(), (int) this.getY(), null);
			graphics2D.setColor(Color.RED);
			if (this.getShape() != null)
				graphics2D.draw(this.getShape());
		}
	}

	/**
	 * Setzt Aktivitaet des Objekts. Einmal deaktivierte Objekte werden vom
	 * gameManagementThread aus der objectList entfernt und durch den Garbage
	 * Collector geloescht.
	 * 
	 * @param active
	 *            boolean
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return this.active;
	}

	/**
	 * Gets the polygon.
	 *
	 * @return the polygon
	 */
	public Polygon getPolygon() {
		return this.polygon;
	}

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public Image getImage() {
		return this.image;
	}

	/**
	 * Gets the shape.
	 *
	 * @return the shape
	 */
	public Shape getShape() {
		return this.shape;
	}

	/**
	 * Sets the shape.
	 *
	 * @param s the new shape
	 */
	public void setShape(Shape s) {
		this.shape = s;
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Point2D.Double getPosition() {
		return this.position;
	}

	/**
	 * Gets the life points.
	 *
	 * @return the life points
	 */
	public int getLifePoints() {
		return (this.maximumDamage - this.damage + 1);
	}

	/**
	 * Gets the maximum damage.
	 *
	 * @return the maximum damage
	 */
	public int getMaximumDamage() {
		return this.maximumDamage;
	}

	/**
	 * Checks if is game relevant.
	 *
	 * @return true, if is game relevant
	 */
	public boolean isGameRelevant() {
		return this.gameRelevant;
	}

	/**
	 * Sets the game relevant.
	 */
	
	public void setGameRelevant() {
		this.gameRelevant = true;
	}

	/**
	 * Gets the damage.
	 *
	 * @return the damage
	 */
	public int getDamage() {
		return this.damage;
	}

}

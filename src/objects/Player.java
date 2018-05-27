package objects;

import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

import game.GameSettings;
import io.ImageLoader;
import io.ImageOutline;

/**
 * The Class Player.
 */
public class Player extends GameObject implements Damageable {

	/** The img left. */
	private Image imgLeft;

	/** The img right. */
	private Image imgRight;

	/** The shape left. */
	private Shape shapeLeft;

	/** The shape right. */
	private Shape shapeRight;

	/** The leben. */
	private int leben;

	/**
	 * Instantiates a new player.
	 */
	public Player() {
		imgLeft = ImageLoader.getPlayerLookingToLeftImage(GameSettings.playerWidth, GameSettings.playerHeight);
		imgRight = ImageLoader.getPlayerLookingToRightImage(GameSettings.playerWidth, GameSettings.playerHeight);
		image = imgRight;
		shapeLeft = ImageOutline.toShape(imgLeft);
		shapeRight = ImageOutline.toShape(imgRight);
		shape = shapeRight;
		polygon = ImageOutline.toPolygon(shape);
		// in die MItte zu sein
		this.setdX(GameSettings.gamePanelWidth / 2);
		this.setdY(GameSettings.gamePanelHeight / 2);
		this.setX(0);
		this.setY(0);
		leben = GameSettings.playerMaximumDamage;
	}

	/**
	 * Subtract life.
	 *
	 * @param i
	 *            the i
	 */
	public void subtractLife(int i) {
		this.leben -= i;
	}

	/**
	 * Gets the leben.
	 *
	 * @return the leben
	 */
	public int getLeben() {
		return this.leben;
	}

	/**
	 * Step left.
	 *
	 * @param toLeft
	 *            the to left
	 */
	public void stepLeft(double toLeft) {
		toLeft = Math.abs(toLeft);
		if ((x - toLeft) > 0) {
			this.setdX(toLeft * -1);
			this.image = imgLeft;
			this.shape = shapeLeft;
			AffineTransform at = new AffineTransform();
			at.translate(this.getX(), this.getY());
			this.shape = at.createTransformedShape(this.shape);
		}
	}

	/**
	 * Step right.
	 *
	 * @param toRight
	 *            the to right
	 */
	public void stepRight(double toRight) {
		toRight = Math.abs(toRight);
		if ((x + toRight) < GameSettings.gamePanelWidth - GameSettings.playerWidth) {
			this.setdX(toRight);
			this.image = imgRight;
			this.shape = shapeRight;
			AffineTransform at = new AffineTransform();
			at.translate(this.getX(), this.getY());
			this.shape = at.createTransformedShape(this.shape);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.GameObject#collide(objects.GameObject)
	 */
	// collesion ueberprufung mit der HIlfe von den Hinweisen im Project.
	// Instersect gibt true zueruck, wenn die zwei objekte sich ueberlappen,
	// sonst false
	@Override
	public boolean collide(GameObject gameObjectToCheck) {
		Area a1 = new Area(this.getShape());
		// System.out.println("bomb x: " + a1.getBounds().x);
		// System.out.println("bomb y: " + a1.getBounds().y);
		Area a2 = new Area(gameObjectToCheck.getShape());
		a1.intersect(a2);
		return !a1.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.GameObject#flipPolygonHorizontally()
	 */
	@Override
	public void flipPolygonHorizontally() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.GameObject#outOfView()
	 */
	@Override
	public boolean outOfView() {

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.Damageable#increaseDamage(int)
	 */
	@Override
	// erhoeht das damage
	public void increaseDamage(int damage) {
		this.damage += damage;

	}

}

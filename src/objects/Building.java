package objects;

import java.awt.geom.Area;

import game.*;
import io.ImageLoader;
import io.ImageOutline;

/**
 * The Class Building. Sie erbt von GO, kann damaged sein Der Benutzer bekommt
 * so viele Punkte, als die Punkten von noch stehenden Gebauden - in jeder neuen
 * Level (jetzt nach jewels nach 20 sec) Deshalb implementiert sie das Interface
 * Scorable
 */
public class Building extends GameObject implements Damageable, Scoreable {

	/** The building type. von ENUM */
	private BuildingType buildingType;

	/**
	 * Instantiates a new building.
	 *
	 * @param buildingType
	 *            the building type
	 */
	public Building(BuildingType buildingType) {
		this.buildingType = buildingType;
		this.createBuildingAttributes();
	}

	/**
	 * Creates the building attributes.
	 */
	public void createBuildingAttributes() {
		switch (buildingType) {
		case BLUEHOUSE:
			this.image = ImageLoader.getBlueHouseImage(GameSettings.houseBlueWidth, GameSettings.houseBlueHeight);
			break;
		case REDHOUSE:
			this.image = ImageLoader.getRedHouseImage(GameSettings.houseRedWidth, GameSettings.houseRedHeight);
			break;
		case CHURCH:
			this.image = ImageLoader.getChurchImage(GameSettings.churchWidth, GameSettings.churchHeight);
			break;
		case YELLOWHOUSE:
			this.image = ImageLoader.getYellowHouseImage(GameSettings.houseYellowWidth, GameSettings.houseYellowHeight);
			break;
		default:
			break;
		}

		// create and set Outline of the image
		this.shape = ImageOutline.toShape(this.image);
		polygon = ImageOutline.toPolygon(this.shape);
		this.buildingType.setPolygon(polygon);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.GameObject#collide(objects.GameObject)
	 */
	@Override
	public boolean collide(GameObject gameObjectToCheck) {
		Area a1 = new Area(this.getShape());
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
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.GameObject#outOfView()
	 */
	@Override
	public boolean outOfView() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.Damageable#increaseDamage(int)
	 */
	// aus Interface
	@Override
	public void increaseDamage(int damage) {
		this.damage += damage;
	}

	/**
	 * Gets the building type.
	 *
	 * @return the building type
	 */
	// emese
	public BuildingType getBuildingType() {
		return this.buildingType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.Scoreable#getScore()
	 */
	// emese scorable fuer den Punkten
	// Der Benutzer bekommt so viele Punkte, als die Punkten von noch stehenden
	// Gebauden - in jeder neuenLevel (jetzt nach jewels nach 20 sec) Deshalb
	// implementiert sie das InterfaceScorable
	@Override
	public int getScore() {
		int ergebnis = 0;
		if (this.getBuildingType().equals(BuildingType.BLUEHOUSE)) {
			ergebnis = GameSettings.houseBlueMaxDamage - this.damage;
		}
		if (this.getBuildingType().equals(BuildingType.REDHOUSE)) {
			ergebnis = GameSettings.houseRedMaxDamage - this.damage;
		}
		if (this.getBuildingType().equals(BuildingType.YELLOWHOUSE)) {
			ergebnis = GameSettings.houseYellowMaxDamage - this.damage;
		}
		if (this.getBuildingType().equals(BuildingType.CHURCH)) {
			ergebnis = GameSettings.churchMaxDamage - this.damage;
		}
		return ergebnis;
	}

}

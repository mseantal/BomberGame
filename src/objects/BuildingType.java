package objects;

import java.awt.Polygon;

/**
 * The Enum BuildingType mit Polygon
 */
public enum BuildingType {

	/** The bluehouse. */
	BLUEHOUSE(null),
	/** The redhouse. */
	REDHOUSE(null),
	/** The church. */
	CHURCH(null),
	/** The yellowhouse. */
	YELLOWHOUSE(null);

	/** The p. */
	Polygon p;

	/**
	 * Instantiates a new building type.
	 *
	 * @param p
	 *            the p
	 */
	private BuildingType(Polygon p) {
		this.p = p;
	}

	/**
	 * Sets the polygon.
	 *
	 * @param p
	 *            the new polygon
	 */
	public void setPolygon(Polygon p) {
		this.p = p;
	}

	/**
	 * Gets the polygon.
	 *
	 * @return the polygon
	 */
	public Polygon getPolygon() {
		return this.p;
	}
}

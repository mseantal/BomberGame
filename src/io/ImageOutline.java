package io;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;

/**
 * The Class ImageOutline.eigene Klasse fuer the outline von eigenen Images
 */

public class ImageOutline {

    /**
     * Gets the outline based of black.
     *
     * @param target the target
     * @param image the image
     * @return the outline
     */
    private static Area getOutline(Color target, BufferedImage image) {
        // construct the GeneralPath
        GeneralPath gp = new GeneralPath();

        boolean cont = false;
        int targetRGB = target.getRGB();
        for (int xx=0; xx<image.getWidth(); xx++) {
            for (int yy=0; yy<image.getHeight(); yy++) {
            	//testen andere farben
            	//int c = image.getRGB(xx,yy);
            	//int  red = (c & 0x00ff0000) >> 16;
            	//int  green = (c & 0x0000ff00) >> 8;
            	//int  blue = c & 0x000000ff;
            	// and the Java Color is ...
            	//Color color = new Color(red,green,blue);
            	//if (isIncluded(target, color, 0)){
                if (image.getRGB(xx,yy)==targetRGB) {
                    if (cont) {
                        gp.lineTo(xx,yy);
                        gp.lineTo(xx,yy+1);
                        gp.lineTo(xx+1,yy+1);
                        gp.lineTo(xx+1,yy);
                        gp.lineTo(xx,yy);
                    } else {
                        gp.moveTo(xx,yy);
                    }
                    cont = true;
                } else {
                    cont = false;
                }
            }
            cont = false;
        }
        gp.closePath();

        // construct the Area from the GP & return it
        return new Area(gp);
    }
    
    /**
     * Checks if is included.
     *
     * @param target the target
     * @param pixel the pixel
     * @param tolerance the tolerance
     * @return true, if is included
     */
    public static boolean isIncluded(Color target, Color pixel, int tolerance) {
        int rT = target.getRed();
        int gT = target.getGreen();
        int bT = target.getBlue();
        int rP = pixel.getRed();
        int gP = pixel.getGreen();
        int bP = pixel.getBlue();
        return(
            (rP-tolerance<=rT) && (rT<=rP+tolerance) &&
            (gP-tolerance<=gT) && (gT<=gP+tolerance) &&
            (bP-tolerance<=bT) && (bT<=bP+tolerance) );
    }
    
    /**
     * To polygon.
     *
     * @param shape the shape
     * @return the polygon
     */
    public static Polygon toPolygon(Shape shape) {
		Polygon p = new Polygon();
		PathIterator path = shape.getPathIterator(null);
		while (!path.isDone()) {
			toPolygon(path, p);
			path.next();
		}
		return p;
	}

	/**
	 * To polygon.
	 *
	 * @param p_path the p_path
	 * @param p the p
	 */
	private static void toPolygon(PathIterator p_path, Polygon p) {
		double[] point = new double[2];
		if (p_path.currentSegment(point) != PathIterator.SEG_CLOSE)
			p.addPoint((int) point[0], (int) point[1]);
	}

	/**
	 * To shape.
	 *
	 * @param img the img
	 * @return the shape
	 */
	public static Shape toShape(Image img) {
		Shape sh = null;
		if (img != null) {
			BufferedImage b_img = toBufferedImage(img);
			sh = getOutline(Color.BLACK, b_img);
		}
		return sh;
	}

	/**
	 * To buffered image.
	 *
	 * @param img the img
	 * @return the buffered image
	 */
	private static BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		// Return the buffered image
		return bimage;
	}
}

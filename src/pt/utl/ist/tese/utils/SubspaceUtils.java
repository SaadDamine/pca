/**
 * 
 */
package pt.utl.ist.tese.utils;

import javax.media.jai.RenderedOp;
import org.grlea.log.SimpleLogger;

import pt.utl.ist.tese.cbir.PixelVector;

import java.awt.image.BufferedImage;

public class SubspaceUtils {

private static final SimpleLogger log = new SimpleLogger(SubspaceUtils.class);

protected static final int RED = 0;
protected static final int GREEN = 1;
protected static final int BLUE = 2;
protected static final int SIZE = 3;


public static int[] generateSubspaceData(RenderedOp img , int img_size ) {
	BufferedImage bi = img.getAsBufferedImage();

	int[] rawPixel = bi.getRGB(0, 0, img_size , img_size , null, 0 , img_size );

	return rawPixel;
}

public static byte[] generateSubspaceData( byte[] original  , int resolution , int originalSize ) {
	// original resolution of the image
//	int originalSize = original.resolution();
	// number of windows that will be used to calculate the new vector
	int numWindows = resolution * resolution;
	// the position in the vector when jumping to the next line ( y + 1 )
	int incrementY = originalSize;
	// length of the side of each window
	int windowSize = originalSize / resolution;
	// pixels in each window
	float pixelsPerWindow = windowSize * windowSize;
	// new vector
	byte[] newVector = new byte[resolution * resolution * SIZE];

	byte[] originalVector = original;
	//SubspaceData newVector = new SubspaceData(resolution , SubspaceData.SUBSPACES[0] , 0 );
	// start of the window (x,y)
	int x = 0;
	int y = 0;
	// auxiliary variable that determines when each line ends (i.e. restart the x and increment y by 1)
	int cont = 0;

	int windowPos = 0;
	
	log.debug("numWindows: " + numWindows );
	log.debug("incrementY: " + incrementY );
	log.debug("windowSize: " + windowSize );
	log.debug("pixelsPerWindow: " + pixelsPerWindow );
	
	// calculate the mean rgb value of each window (the new vector pixel value)
	for ( int window = 0 ; window < numWindows && window < 2 ; window++ ) {
		// could use the remainder of x % 5, but this takes up less resources (no division needed)
		if ( cont == resolution ) {
			cont = 0;
			y += windowSize;
			x = 0;
		}
		// calculate the window starting position on the x axis
		x = cont * windowSize;
		cont++;

		// rgb mean values of the window
		float red = 0;
		float green = 0;
		float blue = 0;

		// vector position of the window (x,y) -> i
		int vectorPos = ( y * incrementY ) + x;

		log.ludicrous("window starting position: (" + x + "," + y + ")" );
		log.ludicrous("vector[" + (vectorPos*SIZE) + "]");

		// calculate the mean pixel value of the current window
		// instead of going through the whole window we will
		// use horizontal and vertical sweeps, for the first loop
		// the pixels in X will be calculated
		//
		// X X X X
		// X O O O
		// X O O O
		// X O O O
		for ( int m = 0 ; m < windowSize ; m++ ) {

			// position for the horiz and vert sweeps
			int xPos = vectorPos;
			int yPos = vectorPos;
			for ( int n = m ; n < windowSize ; n++) {
				log.ludicrous("\t pixels (xPos): ( " + (xPos*SIZE) + " ) ++ " + getRedValue(xPos , originalVector) );

				// horizontal sweep
				red		+= getRedValue(xPos , originalVector);
				green	+= getGreenValue(xPos , originalVector);
				blue	+= getBlueValue(xPos , originalVector);

				//log.ludicrous("red: " + red);

				// vertical sweep
				if ( n != m ) {
					log.ludicrous("\t pixels (yPos): ( " + (yPos*SIZE) + " ) ++ " + getRedValue(yPos , originalVector) );
					red		+= getRedValue(yPos , originalVector);
					green	+= getGreenValue(yPos , originalVector);
					blue	+= getBlueValue(yPos , originalVector);
				}

				// increment to the next horizontal and vertical pixels
				xPos++;
				yPos += incrementY;
			}
		
			// next sweep will start in this position (x + 1 , y + 1)
			vectorPos += 1 + incrementY;
		}

		log.ludicrous("\t sum (r,g,b): ( " + red + " , " + green + " , " + blue + " )");

		red = red / pixelsPerWindow;
		green = green / pixelsPerWindow;
		blue = blue / pixelsPerWindow;

		//log.ludicrous("(r,g,b): ( " + red + " , " + green + " , " + blue + " )");

		newVector[windowPos + RED] = (byte)Math.round( red   );
		newVector[windowPos + GREEN] = (byte)Math.round( green );
		newVector[windowPos + BLUE] = (byte)Math.round( blue  );
		windowPos += SIZE;


	}
	return newVector;
}

public static byte getRedValue  ( int pixel , byte[] rgb ) { return rgb[pixel * SIZE + RED]; }
public static byte getGreenValue( int pixel , byte[] rgb ) { return rgb[pixel * SIZE + GREEN]; }
public static byte getBlueValue ( int pixel , byte[] rgb ) { return rgb[pixel * SIZE + BLUE]; }


protected static byte convertColorToByte( int val ) {
	return (byte) (val - 128);
}
}

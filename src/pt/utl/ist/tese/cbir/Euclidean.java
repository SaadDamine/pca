/**
 * 
 */
package pt.utl.ist.tese.cbir;

import org.grlea.log.SimpleLogger;

/**
 * @author André
 *
 * This class contains static methods that determine the euclidean distance
 * between two objects (arrays, histograms, etc...)
 *
 */
public class Euclidean {
	/**
	 * logger variable
	 */
	private static final SimpleLogger log = new SimpleLogger(Euclidean.class);
	/**
	 * Calculates the euclidean distance between two byte arrays
	 * 
	 * @param array1 - first image
	 * @param array2 - second image
	 * @return the distance between the arrays
	 */	
	public static double calc( byte[] array1 , byte[] array2 ) {

		int distR = 0, distG = 0, distB = 0;
		int i;
		for ( i = 0 ; i < array1.length ;  ) {
			distR += Math.pow((double) array1[i+0] - array2[i+0], 2);
			distG += Math.pow((double) array1[i+1] - array2[i+1], 2);
			distB += Math.pow((double) array1[i+2] - array2[i+2], 2);
			i = i + 3;
		}
		return Math.sqrt(distR) + Math.sqrt(distG) + Math.sqrt(distB);
	}
	/**
	 * Calculates the euclidean distance between two double arrays
	 * 
	 * @param array1 - first array
	 * @param array2 - second array
	 * @return the distance between the arrays
	 */
	public static double calc( float[] array1 , float[] array2 ) {

		int dist = 0;
		if ( array1.length != array2.length )
			log.info("diff lens -- " + array1.length + " + " + array2.length );
		for ( int i = 0 ; i < array1.length ; i++  ) {
			dist += Math.pow((double) array1[i] - array2[i], 2);
		}
		
		return Math.sqrt(dist);
	}

}

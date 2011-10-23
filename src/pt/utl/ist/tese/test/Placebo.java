package pt.utl.ist.tese.test;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

import org.grlea.log.SimpleLogger;

import pt.utl.ist.tese.utils.MemoryUsage;
import pt.utl.ist.tese.utils.Timer;

public class Placebo {

	protected static final int[] subs = { 4 };
	
	private static final SimpleLogger log = new SimpleLogger(Placebo.class);
	
	private byte[][][] _vectors;
	
	public Placebo() {
		_vectors = new byte[subs.length][][];
		
		for ( int i = 0 ; i < subs.length ; i++ )
			_vectors[i] = createVector( subs[i] );
	}
	
	public byte get( int color , int pos , int index) {
		return _vectors[index][color][pos];
	}
	
	public int size( ) {
		return _vectors.length;
	}
	
	public int max( int index ) {
		return _vectors[index][0].length;
	}

	public static byte convertColorToByte( int val) {
		return (byte) (val - 128);
	}
	
	public double dimension( int index ) {
		return _vectors[index][0].length;
	}
	
	private static byte[][] createVector( int dime ) {
		byte rgb[][] = new byte[3][dime*dime];

		Random rand = new Random();
	
		for ( int j = 0 ; j < dime * dime ; j++ ) {
			rgb[0][j] = convertColorToByte(rand.nextInt(256));
			rgb[1][j] = convertColorToByte(rand.nextInt(256));
			rgb[2][j] = convertColorToByte(rand.nextInt(256));
		}
		
		return rgb;
	}
	
	private static ArrayList<Placebo> createVectors( int size ) {

		ArrayList <Placebo> list = new ArrayList<Placebo>();
		
		for ( int i = 0 ; i < size ; i++ ) {
			list.add( new Placebo() );	
		}
		return list;
	}
	
	public static void search( int num ) {
		NumberFormat nf = NumberFormat.getInstance();
		MemoryUsage mem = new MemoryUsage();
		Timer timer = new Timer();
		ArrayList <Placebo> v = createVectors( num );
		log.info("finished generating the placebo images in " + timer.toString() + " using " + mem);
		
		
		double dist = 0;
		timer.restartTimer();
		for ( int i = 0 ; i < v.get(0).size() ; i++ ) {
			dist += search( v.get( 0 ) , v , i , true);
			
		}
		log.info("it took " + timer.toString() + ", visited " + nf.format(dist) + " pixels and used " + mem);
	}
	
	public static double  search( Placebo test , ArrayList<Placebo> collec , int index , boolean sqroot ) {
		
		double dime = test.dimension(index);
		double val[] = new double[ collec.size() ];
		double cont = 0;
		int i = 0;

		for ( Placebo pla : collec ) {
			double distR = 0;
			double distG = 0;
			double distB = 0;
			for ( int j = 0 ; j < dime  ; j++ ) {
				cont++;
				distR += Math.pow(test.get( 0 , j , index) - pla.get( 0 , j , index) , 2);
				distG += Math.pow(test.get( 1 , j , index) - pla.get( 1 , j , index) , 2);
				distB += Math.pow(test.get( 2 , j , index) - pla.get( 2 , j , index) , 2);	
			}
			if ( sqroot )
				val[i++] = Math.sqrt(distR) + Math.sqrt(distG) + Math.sqrt(distB);
			else
				val[i++] = distR + distG + distB;
		}
		double dist = 0;
		for ( double d : val )
			dist += d;
		
		return cont;
	}
	
}


package pt.utl.ist.tese.test;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

import org.grlea.log.SimpleLogger;

import pt.utl.ist.tese.utils.MemoryUsage;
import pt.utl.ist.tese.utils.Timer;

public class PlaceboII  {

	private static final SimpleLogger log = new SimpleLogger(PlaceboII.class);
	
	private byte[][] _vectors;
	
	public PlaceboII() {
		_vectors = new byte[Placebo.subs.length][];
		
		for ( int i = 0 ; i < Placebo.subs.length ; i++ )
			_vectors[i] = createVector( Placebo.subs[i] );
	}
	
	public byte get( int pos , int index) {
		return _vectors[index][ pos ];
	}
	public int size( ) {
		return _vectors.length;
	}
	public double dimension( int index ) {
		return _vectors[index].length;
	}
	public static byte convertColorToByte( int val) {
		return (byte) (val - 128);
	}
	public static void setVal( int pos , byte v[] , byte val) {
		v[ pos ] = val;
	}
	private static byte[] createVector( int dime ) {
		byte rgb[] = new byte[dime*dime*3];

		Random rand = new Random();

		for ( int j = 0 ; j < dime*dime*3 ; j++ )
			setVal( j , rgb , convertColorToByte(rand.nextInt(256)) );

		return rgb;
	}
	
	private static ArrayList<PlaceboII> createVectors( int size ) {

		ArrayList <PlaceboII> list = new ArrayList<PlaceboII>();
		
		for ( int i = 0 ; i < size ; i++ ) {
			list.add( new PlaceboII() );
		}
		return list;
	}
	
	public static double characteristic( int num ) {
		NumberFormat nf = NumberFormat.getInstance();
		MemoryUsage mem = new MemoryUsage();
		Timer timer = new Timer();
		ArrayList <PlaceboII> v = createVectors( num );
		
		log.info("finished generating the placebo images in " + timer.toString() + " using " + mem);
		
		double dist = 0;
		timer.restartTimer();
		
		dist = characteristic( v );
		
		log.info("it took " + timer.toString() + ", visited " + nf.format(dist) + " pixels and used " + mem);
		
		return dist;
	}
	
	public static double characteristic( ArrayList<PlaceboII> collect ) {
		double dist = 0;
		int cont = 0;
		Timer timer = new Timer();
		for ( PlaceboII p : collect ) {
			if ( cont > 0 && cont % 100 == 0 )
				log.info("image " + cont + "/" + collect.size() + " in " + timer);
			cont++;
			for ( int i = 0 ; i < collect.get(0).size() ; i++ )
				dist += search( p , collect , i , true);
			if ( cont == 10 )
				break;
		}
		return dist;
	}
	
	public static void search( int num ) {
		NumberFormat nf = NumberFormat.getInstance();
		MemoryUsage mem = new MemoryUsage();
		Timer timer = new Timer();
		ArrayList <PlaceboII> v = createVectors( num );
		
		log.info("finished generating the placebo images in " + timer.toString() + " using " + mem);
		
		double dist = 0;
		timer.restartTimer();
		for ( int i = 0 ; i < v.get(0).size() ; i++ ) {
			dist += search( v.get( 0 ) , v , i , true);
		}
		log.info("it took " + timer.toString() + ", visited " + nf.format(dist) + " pixels and used " + mem);
	}
	
	public static double  search ( PlaceboII test , ArrayList<PlaceboII> collec , int index , boolean sqroot ) {
		double dime = test.dimension(index);
		double val[] = new double[ collec.size() ];
		double cont = 0;
		int i = 0;
		for ( PlaceboII pla : collec ) {
			double distR = 0;
			double distG = 0;
			double distB = 0;
			
			for ( int j = 0 ; j < dime - 1  ; ) {
				cont++;
				distR += Math.pow(test.get( j , index) - pla.get( j++ , index) , 2);
				distG += Math.pow(test.get( j , index) - pla.get( j++ , index) , 2);
				distB += Math.pow(test.get( j , index) - pla.get( j++ , index) , 2);	
			}
			if ( sqroot )
				val[i++] = Math.sqrt(distR) + Math.sqrt(distG) + Math.sqrt(distB);
			else
				val[i++] = distR + distG + distB;
		}
//		double dist = 0;
//		for ( double d : val )
//			dist += d;
		
		return cont;
	}
	
}


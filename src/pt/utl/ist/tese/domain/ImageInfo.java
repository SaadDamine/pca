package pt.utl.ist.tese.domain;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import org.grlea.log.SimpleLogger;

import pt.utl.ist.tese.cbir.PixelVector;
import pt.utl.ist.tese.cbir.ImageRawData;
import pt.utl.ist.tese.pca.PCA;
import pt.utl.ist.tese.utils.FileUtils;
import pt.utl.ist.tese.utils.MemoryUsage;

public class ImageInfo implements Serializable {

	private static final long serialVersionUID = 8249354302109102928L;

	@SuppressWarnings("unused")
	private static final SimpleLogger log = new SimpleLogger(ImageInfo.class);

	private static final long RANDOM_SEED = 5436982311431233698l;
	
	protected int _id;
	
	protected PixelVector[] _vectors;
	
	protected static final int FILES_PER_FOLDER = 1000;
	
	public static final int[] SUBSPACES = { 256, 128, 64, 32 , 16 , 8 , 4 };
	protected static final String PREFIX = System.getProperty( "user.dir") + File.separator + "artists" + File.separator + "var" + File.separator + "subspaces";

	public int id() { return _id; }
	
	public ImageInfo( int id ) {
		_id = id;
		_vectors = new PixelVector[ SUBSPACES.length ];
		int cont = 0;
		for ( int i : SUBSPACES ) {
			String pathPrefix = PREFIX + File.separator + i + File.separator + ( id / FILES_PER_FOLDER );
			String filename = "" + id + ".data";
			File f = new File( pathPrefix + File.separator + filename );
			_vectors[cont++] = new PixelVector(f , i);
		}
				
	}
	
	public ImageInfo( File f , int id , int resolution ) {
		_id = id;
		_vectors = new PixelVector[ 1 ];
		_vectors[0] = new PixelVector( f , resolution );
	}
	public ImageInfo( int id , int numberOfVectors ) {
		_id = id;
		_vectors = new PixelVector[ numberOfVectors ];
	}
	public void insertPixelVector( File f , int resolution , int index ) {
		_vectors[index] = new PixelVector( f , resolution );
	}
	public ImageInfo( File folder , int id ) {
		_id = id;
		_vectors = new PixelVector[ SUBSPACES.length ];
		
		for ( File f : folder.listFiles() ) {
			int j = 0;
			for ( int i : SUBSPACES ) {
				if ( f.getName().contentEquals("" + i + ".data" ) ) {
					String suffix = "" + i + File.separator + ( id / FILES_PER_FOLDER ); 
					String filename = id + ".data";
					//log.info("path for " + id + ": " + PREFIX + File.separator + suffix );
					
					_vectors[j] = new PixelVector( FileUtils.readPixelVectorData(f) );
					boolean status = _vectors[j].setFilePath( PREFIX + File.separator + suffix , filename);
					if ( !status )
						continue;
					status = _vectors[j].save();
				}
				j++;
			}
		
		}
	}
	
	public PixelVector getPixelVector( int index ) {
		if ( index < _vectors.length )
			return _vectors[index];
		else
			return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public static int loadPixelVectorsToMemory( int index , ArrayList<ImageInfo> map , ArrayList<byte[]> vectorList , int max ) {
		if ( MemoryUsage.freeMem() > (MemoryUsage.maxMem() * 0.20) ) {
			System.gc();
			System.gc();
			System.gc();
			System.gc();
		}
		ArrayList<ImageInfo> map2 = (ArrayList<ImageInfo>)map.clone();
		Random rand = new Random(RANDOM_SEED);
		for ( int i = 0 ; i < map.size() && i < max ; i++ ) {
			
			// reserve 5% of memory to other operations
			if ( MemoryUsage.freeMem() < (MemoryUsage.maxMem() * 0.20) )
				break;
			//else if ( vectorList.size() == 3 )	break;
			vectorList.add(map2.remove(rand.nextInt(map2.size())).getPixelVector(index).load());
			
			//log.info("id: " + map.get( i ).id() );
		}
		if ( vectorList.size() == 0 )
			return -1;
		return vectorList.size() - 1;
	}
	
	public static int loadPixelVectorsToMemory( int index , ArrayList<ImageInfo> map , int start , ArrayList<ImageRawData> vectorList , PCA pca) {
		int i = start;
		boolean yada = true;

		if ( MemoryUsage.freeMem() < (MemoryUsage.maxMem() * 0.20) ) {
			System.gc();
			System.gc();
			System.gc();
			System.gc();
		}
			
		for ( ; i < map.size() ; i++ ) {
			
			ImageRawData el = null;
			// reserve 10% of memory to other operations
			
			if ( i % 10000 == 0 )
				log.info(i + ".free: " + MemoryUsage.free() + " MB -- total: " + MemoryUsage.total() + " ++ max: " + MemoryUsage.max() + " -- usage: " + MemoryUsage.usage());
			
			if ( yada && MemoryUsage.freeMemRun() < (MemoryUsage.maxMem() * 0.25 ) ) {
				yada = false;
				System.gc();System.gc();System.gc();System.gc();
			}
			else if ( MemoryUsage.freeMem() < (MemoryUsage.maxMem() * 0.20) )
				break;

			byte[] vector = map.get( i ).getPixelVector(index).load();
			
			if ( pca == null ) el = new ImageRawData( vector , i );
			else el = new ImageRawData( pca.reduce( vector ) , i );
			
			vectorList.add( el );
			vector = null;
			el = null;
		}
		if ( vectorList.size() == 0 )
			return -1;
		return i - 1;
	}
	
	public static ArrayList<ImageInfo> loadImages() {
		
		ArrayList<ImageInfo> list = new ArrayList<ImageInfo>();
		for ( int i = 0 ; i < 8109 ; i++ ) {
			list.add( new ImageInfo( i ));
			//if ( i == 90 ) return list;
		}
		
		return list;
	}
	
	public static void findImages( String folderPath ) {

		File folder = new File ( folderPath );
		int i = 0;
		for ( File artist : folder.listFiles() ) {
			if ( artist.isDirectory() ) {
				for ( File f : artist.listFiles()) {
					if ( f.isDirectory() ) {
						new ImageInfo( f , i++ );
					}
				}
			}

		}
	}
	
}

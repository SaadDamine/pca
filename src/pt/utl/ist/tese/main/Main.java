package pt.utl.ist.tese.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.grlea.log.SimpleLogger;

import pt.utl.ist.tese.cbir.Characteristics;
import pt.utl.ist.tese.cbir.ImageRawData;
import pt.utl.ist.tese.cbir.PixelVector;
import pt.utl.ist.tese.domain.ImageInfo;
import pt.utl.ist.tese.pca.PCA;
import pt.utl.ist.tese.threads.CalculatePCACharactThread;
import pt.utl.ist.tese.threads.GeneratePCAThread;
import pt.utl.ist.tese.utils.FileUtils;
import pt.utl.ist.tese.utils.MemoryUsage;
import pt.utl.ist.tese.utils.SubspaceUtils;
import pt.utl.ist.tese.utils.Timer;
import pt.utl.ist.tese.utils.XMLDecoder;

public class Main {
	
	public static final String ARTIST_PATH = System.getProperty( "user.home") + File.separator + "artists";
	
	private static final SimpleLogger log = new SimpleLogger(Main.class);
	
	public static int pos( int x , int y , int dim ) {
		return ((y * dim) + x) * 3;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		log.entry("Main");
		
	
		MemoryUsage mem = new MemoryUsage();
		Timer timer = new Timer();

		int sample = 10 , index = 1;
		sample = 3000;

		//ArrayList<ImageInfo> list = XMLDecoder.read();
		//FileUtils.saveListToDisk(list);
		
		
		
		
		
		ArrayList<ImageInfo> list = FileUtils.loadListFromDisk();
		
		ImageInfo imd = list.get(0);
		
		//log.info("subs: " + imd.getPixelVector(0).filepath());
//
//		String sf = createPCA(list, index, sample);
		
//		int id = Integer.parseInt(sf);
		int id = 2;
		Characteristics c = new Characteristics( index , sample , list);
		while ( index >= 0 ) {
			c.characteristics();
			c.setIndex(index--);
			break;
		}
		
		
//		ArrayList<File> pcas = FileUtils.loadPCAFromDisk(id , ImageInfo.SUBSPACES[index]);
//		for ( int i = 0 ; i < pcas.size() ; i++) {
//			File f = pcas.get(pcas.size() - i - 1);
//					
//			PCA pca = readPCA(f);
//			log.info(pca.getReducedDimension() + "-" + pca.getSample() );
//			int pcaS = pca.getSample();
//
//			//if ( pcaS != 900 )
//			//	charactWithPCA(list, index, pca, sample, c);
//		}
		
				
		log.info("mem: " + mem + " in " + timer);
				
		log.exit("Main");
	}
	
	/**
	 * @param list
	 * @param index
	 * @param sample
	 * @param id
	 */
	public static String createPCA ( ArrayList<ImageInfo> list , int index , int sample ) {
		File f = FileUtils.createPCADirectory();
		ArrayList<Integer> a = yada( list.size() );
		//for ( int i = 0 ; i < ImageInfo.SUBSPACES.length ; i++ )
		//	PCAThreads( 1 , list, a , f);

		for ( int i : a ) {
			PCA pca = pca(list,index,i);
			savePCA(pca, index, i , f);
			if ( pca.getSample() == 3072 )
				break;
		}
		return f.getName();
	}
	
	/**
	 * @param list
	 * @param index64PixelVector
	 * @param id
	 */
	public static void create64PCA( ArrayList<ImageInfo> list , int index64PixelVector , int sample) {
		File f = FileUtils.createPCADirectory();
		
		PCA pca = pca(list, index64PixelVector, sample);
		savePCA(pca, index64PixelVector , sample , f);
	}
	
	public static void fullCharact( int sample , int startSubspaceIndex , int endSubspaceIndex , ArrayList<ImageInfo> list ) {
		
		Characteristics characteristics = new Characteristics( 0 , sample , list);

		for ( int i = startSubspaceIndex ; i <= endSubspaceIndex ; i++ ) {
			characteristics.setIndex(i);
			log.info("__________________________________");
			log.info("                                  |");
			log.info("                                  |");
			log.info("   Searching the " + ImageInfo.SUBSPACES[i] + "x" + ImageInfo.SUBSPACES[i] + " subspace   |");
			log.info("                                  |");
			log.info("__________________________________|");
			CharactPCAThreads( characteristics , 4);
			characteristics.characteristics();
			System.gc();
			System.gc();
			System.gc();
			System.gc();
		}
	}
	public static PCA getPCA( int i ) {
		ArrayList<File> pcas = FileUtils.loadPCAFromDisk(4 , ImageInfo.SUBSPACES[i]);
		return readPCA(pcas.get(0));
	}
	/**
	 * @param startIndex
	 * @param endIndex
	 * @param c
	 * @param pcaId
	 */
	public static void CharactPCAThreads( Characteristics characteristics , int pcaId ) {
	
		
		ArrayList<File> pcas = FileUtils.loadPCAFromDisk( pcaId , ImageInfo.SUBSPACES[characteristics.getIndex()] );
		CalculatePCACharactThread thread = null;
		CalculatePCACharactThread thread2 = null;

		thread = new CalculatePCACharactThread( characteristics , pcas , 0 , 2 );
		thread2 = new CalculatePCACharactThread( characteristics , pcas , 1 , 2 );
			
		thread.start();
		thread2.start();
			
		try {
			thread.join();
			thread2.join();
		} catch (InterruptedException e) {
			log.info("error: join -- " + e.getMessage());
		}
		System.gc();
		System.gc();
		System.gc();
		System.gc();

	}
	
	
	/**
	 * @param list
	 * @param index
	 * @param pca
	 * @param sample
	 */
	public static void charactWithPCA(ArrayList<ImageInfo> list , int index , PCA pca , int sample, Characteristics c) {
		
		//Characteristics c = new Characteristics( index , sample , list);
		System.gc();
		System.gc();
		System.gc();
		c.characteristics( pca );
		//System.exit(0);
		System.gc();
		System.gc();
		System.gc();
		//c.characteristics();
	}
	
	/**
	 * @param files
	 * @param prefix
	 * @return
	 */
	protected static ArrayList<File> findFilesInDir( File[] files , String prefix) {
		
		log.entry("findFilesInDir - searching for files with prefix: " + prefix );
		
		ArrayList<File> arrayOfFiles = new ArrayList<File>();
		
		for ( int i = 0 ; i < files.length ; i++  ){
			File file = files[i];
			arrayOfFiles.add(file);
		}
				
		ArrayList<File> listOfFiles = new ArrayList<File>();
		
		for ( int i = 0 ; i < arrayOfFiles.size() ; i++ ) {
			File file = arrayOfFiles.get(i);
						
			if ( file.isDirectory() ) {
				insertArrayInList( file.listFiles() , arrayOfFiles );
			}
			else if ( file.getName().startsWith(prefix) ) {
				listOfFiles.add(file);
			}
						
		}
		log.exit("findFilesInDir - found " + listOfFiles.size() + " files");

		return listOfFiles;
	}
	
	/**
	 * @param resolution
	 * @return
	 */
	public static ArrayList<File> findSubspaceData( int resolution) {
		
		String prefix = resolution + "-";
		String dirPath = "var" + File.separator + "artists";
		File dir = new File( System.getProperty( "user.dir") + File.separator + dirPath );
		
		ArrayList<File> files = findFilesInDir( dir.listFiles(), prefix );
		
		return files;
		
	}
	/**
	 * @param files
	 * @param list
	 * @return
	 */
	protected static ArrayList<File> insertArrayInList ( File[] files , ArrayList<File> list ) {
		if ( files == null )
			return null;
		for ( File file : files )
			list.add(file);
		return list;
	}	
	
	/**
	 * @param resolution
	 * @return
	 */
	public static ArrayList<PixelVector> loadSubspaceData( int resolution ) {
		ArrayList<File> files = findSubspaceData(resolution);
		ArrayList<PixelVector> vectors = new ArrayList<PixelVector>();
		
		int i = 0;
		for ( File f : files ) {
			PixelVector temp = new PixelVector( FileUtils.readPixelVectorData(f) );
			vectors.add( temp );
			temp = null;
			i++;
			//if ( i == 3 )
				//break;
		}

		return vectors;
	}
	
	
	
	/**
	 * @param list
	 * @param index
	 * @param sample
	 * @return
	 */
	public static PCA pca( ArrayList<ImageInfo> list , int index, int sample ) {
		return PCA.calculatePCA( list ,index , sample);
	}
	
	/**
	 * @param index
	 * @param list
	 * @param a
	 * @param f
	 */
	public static void PCAThreads( int index, ArrayList<ImageInfo> list , ArrayList<Integer> a , File f) {
		
		ArrayList<Integer> b = new ArrayList<Integer>();
		ArrayList<Integer> c = new ArrayList<Integer>();
		for ( int i = 0 ; i < a.size() ; i++ ) {
			if ( i % 2 == 0 ) 
				b.add( a.get( i ));
			else
				c.add( a.get(i));
		}
		
		GeneratePCAThread thread  = new GeneratePCAThread( index , list , b , f);
		GeneratePCAThread thread2 = new GeneratePCAThread( index , list , c , f);
		
		thread.start();
		thread2.start();
		
		try {
			thread.join();
			thread2.join();
		} catch (InterruptedException e) {
			log.info("error: join -- " + e.getMessage());
		}
		
	}

	/**
	 * @param f
	 * @return
	 */
	public static PCA readPCA( File f ) {
		PCA pca = null;
		try {
			FileInputStream os = new FileInputStream( f );
			ObjectInputStream oos = new ObjectInputStream( os );
			pca = (PCA) oos.readObject();
		} catch (FileNotFoundException e) {
			log.error("error: file not found -- " + e.getMessage());
		} catch (IOException e) {
			log.error("error: object -- " + e.getMessage() );
		} catch (ClassNotFoundException e) {
			log.error("error: class not found -- " + e.getMessage());
		}
		return pca;
	}
	
	/**
	 * @param id
	 * @param index
	 * @param sample
	 * @return
	 */
	public static PCA readPCA( int id , int index , int sample ) {
		File f = new File( FileUtils.PCA_PATH + id + File.separator + ImageInfo.SUBSPACES[index] + File.separator + sample + ".pca" );
		
		PCA pca = readPCA(f);
		
		return pca;
	}
	
	/**
	 * @param pca
	 * @param index
	 * @param id
	 * @param f
	 */
	public static void savePCA( PCA pca , int index , int id , File f ) {
		File file = new File( f.getAbsolutePath() + File.separator + ImageInfo.SUBSPACES[index] );
		file.mkdirs();
		file = new File ( file.getAbsolutePath() + File.separator + id + ".pca");
		try {
			FileOutputStream os = new FileOutputStream( file );
			ObjectOutputStream oos = new ObjectOutputStream( os );
			oos.writeObject( pca );
		} catch (FileNotFoundException e) {
			log.error("error: file not found -- " + e.getMessage());
		} catch (IOException e) {
			log.error("error: object -- " + e.getMessage() );
		}
		
	}

	/**
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> yada( int size ) {
		ArrayList<Integer> a = new ArrayList<Integer>();
	
		//for ( int i = 100 ; i < size && i < 1000 ; i += 100 ) {
		//	a.add( new Integer( i ));
		//}
		//if (true)
		//	return a;
		for ( int i = 1000 ; i < size ; i += 500 ) {
			a.add( new Integer( i ));
		}
		a.add( new Integer( size));
		return a;
	}
	
	
}

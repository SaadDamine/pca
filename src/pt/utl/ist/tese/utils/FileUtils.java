package pt.utl.ist.tese.utils;

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
import java.util.Date;

import org.grlea.log.SimpleLogger;

import pt.utl.ist.tese.cbir.ImageRawData;
import pt.utl.ist.tese.domain.ImageInfo;
import pt.utl.ist.tese.main.Main;

/**
 * A set of methods that interact with the file system creating files and 
 * saving data
 * 
 * @author André (54306)
 *
 */
public class FileUtils {
	/**
	 * logger variable
	 */
	private static final SimpleLogger log = new SimpleLogger(FileUtils.class);
	
	/**
	 * path to the characteristics folder
	 * TODO: save this information on the database, and only use this variable if 
	 * 		we cannot access the database
	 */
	private final static String CHARACTERISTIC_PATH = Main.ARTIST_PATH + 
						File.separator + "var" + File.separator + "charact" + File.separator;
	/**
	 * path to the PCA folder
	 * TODO: save this information on the database, and only use this variable if 
	 * 		we cannot access the database
	 */
	public final static String PCA_PATH = Main.ARTIST_PATH + File.separator + "pca" + File.separator;
	/**
	 * creates the characteristics directory to store the generated data
	 * the file structure of this directory is:<br>
	 * - (DIR) root directory<br>
	 * -- (DIR) container for each different execution<br>
	 * --- (DIR) resolution of the subspace<br>
	 * ---- (DIR) dimension of the subspace ("original" for the image subspace and for the PCA 
	 * generated characteristics the folder has the value of the reduced dimension
	 * 
	 * @return the id for the current execution
	 */
	public static int createCharacteristicDirectory( ) {
		
		int i = 0;
		File file = new File( CHARACTERISTIC_PATH );
		if ( file.exists() ) {
			File[] files = new File( CHARACTERISTIC_PATH ).listFiles();
			
			i = files.length - 1;
			do {
				file = new File( CHARACTERISTIC_PATH + ++i );
			} while ( file.exists() );
			
		} else {
			file = new File( CHARACTERISTIC_PATH + i );
		}
		try {
			log.info("characteristics directory: " + file.getAbsolutePath() );
			file.mkdirs();
		} catch (Exception e) {
			log.error("error when creating characteristic directory: " + e.getMessage());
			log.errorException(e);
			return (int)new Date().getTime();
		}
			
		return i;
	}
	/**
	 * creates the PCA directory to store the generated data
	 * the file structure of this directory is:<br>
	 * - (DIR) root directory<br>
	 * -- (DIR) container for each different execution<br>
	 * --- (DIR) resolution of the subspace<br>
	 * ---- (FILES) the different subspaces identified by the sample size and a 
	 * unique identifier for the folder
	 * 
	 * @return the id for the current execution
	 */
	public static File createPCADirectory( ) {
		
		int i = 0;
		File file = new File( PCA_PATH );
		
		if ( file.exists() ) {
			File[] files = new File( PCA_PATH ).listFiles();
			
			i = files.length;

			do {
				file = new File( PCA_PATH + i );
			} while ( file.exists() );
			
		} else
			file = new File( PCA_PATH + i );
		try {
			log.info("PCA directory: " + file.getAbsolutePath() );
			file.mkdirs();
		} catch (Exception e) {
			log.error("error when creating PCA directory: " + e.getMessage());
			log.errorException(e);
			return null;
		}
			
		return file;
	}
	/**
	 * This file writer will be used to save the characteristics data to disk
	 * @param id 			of the execution
	 * @param resolution 	of the original data
	 * @param filenumber 	of the file to be written (filename will be "file number.data")
	 * @return a buffered stream
	 */
	public static BufferedWriter getFileWriter( int id , int resolution , int filenumber ) {
		return getFileWriter( id , resolution, "original" , filenumber );
	}
	/**
	 * This file writer will be used to save the characteristics data to disk
	 * @param id 			of the execution
	 * @param resolution 	of the original data
	 * @param filenumber 	of the file to be written (filename will be "file number.data")
	 * 
	 * @return a buffered stream
	 */
	public static BufferedWriter getFileWriter( int id , int resolution , String dirName , int filenumber ) {
		String path = CHARACTERISTIC_PATH + id + File.separator + 
				resolution + File.separator + dirName + File.separator + resolution + "_" + filenumber + ".data";

		File file = new File( CHARACTERISTIC_PATH + id + File.separator + resolution + File.separator + dirName );
		if ( !file.exists() ) {
			try {
				file.mkdirs();
			} catch (Exception e) {
				log.error("error when creating characteristic directory: " + e.getMessage());
				log.errorException(e);
				return null;
			}
		}
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter( path ));
		}
		catch (IOException e) {
			log.error("error on writing string's data to disk: " + e.getMessage());
			log.errorException(e);
		}
		return out;
	}
	/**
	 * retrieves the PCA from disk without loading the actual content to memory 
	 * as some might be too big and slow this method's execution (depends on the
	 * dimension of the original data and the number of significant EigenVectors)
	 * 
	 * @param id 			of the folder
	 * @param resolution 	of the target PCAs
	 * 
	 * @return a list of files that contain the PCA information
	 */
	public static ArrayList<File> loadPCAFromDisk( int id , int resolution ) {
		
		File dir = new File( PCA_PATH + id + File.separator + resolution );
		
		ArrayList<File> pcas = new ArrayList<File>();
		
		if ( dir.isDirectory() )
			for ( File f : dir.listFiles() ) {
				pcas.add(f);
			}
		else
			log.error("error: pca directory is not a directory -- " + dir.getAbsolutePath());
		
		return pcas;
	}
	/**
	 * loads the pixel vector's data from the disk
	 * 
	 * @param file 		pointing to the pixel vector
	 * @param dimension of the pixel vector
	 * 	
	 * @return the pixel vector data
	 */
	public static byte[] loadPixelVector( File file , int dimension ) {
		FileInputStream is = null;
		byte[] pv = new byte[dimension];
		try {
			is = new FileInputStream( file );
			is.read(pv);
			is.close();
		} catch (FileNotFoundException fnf) {
			log.error("Cannot read pixel vector from disk: " + file.getAbsolutePath() );
			log.errorException(fnf);
		} catch (IOException e) {
			log.error("Cannot read pixel vector from disk: " + file.getAbsolutePath() );
			log.errorException(e);
		}
		
		return  pv;
	}
	/**
	 * saves the pixel vector to disk
	 * @param file 		to where the pixel vector's data will be saved
	 * @param vector 	data
	 * 
	 * @return a boolean which says whether the file was successfully saved
	 */
	public static boolean savePixelVector( File file , byte[] vector ) {
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream( file );
			fs.write( vector );
			fs.close();
		} catch (FileNotFoundException fnf) {
			log.error("Cannot write pixel vector to disk: " + file.getAbsolutePath() );
			return false;
		} catch (IOException e) {
			log.error("Cannot write pixel vector to disk: " + file.getAbsolutePath() );
			return false;
		}
		return true;
	}
	/**
	 * loads the pixel vector's data from the disk
	 * 
	 * @deprecated as the pixel vector's data does not save its data as a table of INT, 
	 * but as a byte array
	 *  
	 * @param file pointing to the pixel vector's data on disk
	 * 
	 * @return a table of INTS with the RGB information of the pixel vector
	 */
	public static int[][] readPixelVectorData( File file ) {
		ObjectInputStream os = null;
		FileInputStream is = null;
		try {
			is = new FileInputStream( file );
			os = new ObjectInputStream( is );
		} catch (FileNotFoundException fnf) {
			log.error("Cannot read pixel vector from disk: " + file.getAbsolutePath() );
		} catch (IOException e) {
			log.error("Cannot read pixel vector from disk: " + file.getAbsolutePath() );
		}

		int[][] pv = null;
		try {
			pv = (int[][])os.readObject();
			os.close();
		} catch ( IOException ioe ) {
			log.error("could not read pixel vector from disk");
		} catch (ClassNotFoundException e) {
			log.error("could not read pixel vector from disk");
		}
		
		return  pv;
	}
	/**
	 * loads the pixel vector's data from the disk
	 * 
	 * @deprecated as the pixel vector's data does not save its data as a table of INT, 
	 * but as a byte array
	 *  
	 * @param path of the pixel vector's data on disk
	 * 
	 * @return a table of INTS with the RGB information of the pixel vector
	 */
	public static int[][] readPixelVectorData( String path ) {
		return  readPixelVectorData(new File(path));
	}
	
	public static ArrayList<ImageInfo> loadListFromDisk () {
		File file = new File(Main.ARTIST_PATH +	File.separator + "var" + File.separator + "arrayList.data");
		ObjectInputStream os = null;
		FileInputStream is = null;
		try {
			is = new FileInputStream( file );
			os = new ObjectInputStream( is );
		} catch (FileNotFoundException fnf) {
			log.error("Cannot read pixel vector from disk: " + file.getAbsolutePath() );
		} catch (IOException e) {
			log.error("Cannot read pixel vector from disk: " + file.getAbsolutePath() );
		}

		ArrayList<ImageInfo> list = null;
		try {
			list = (ArrayList<ImageInfo>)os.readObject();
			os.close();
		} catch ( IOException ioe ) {
			log.error("could not array list of ImageInfo from disk");
		} catch (ClassNotFoundException e) {
			log.error("could not array list of ImageInfo from disk");
		}
		
		return list;

	}
	
	public static boolean saveListToDisk( ArrayList<ImageInfo> list ) {
		File file = new File(Main.ARTIST_PATH +	File.separator + "var" + File.separator + "arrayList.data");
		ObjectOutputStream os = null;
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream( file );
			os = new ObjectOutputStream( fs );
		} catch (FileNotFoundException fnf) {
			log.error("Cannot read pixel vector from disk: " + file.getAbsolutePath() );
		} catch (IOException e) {
			log.error("Cannot read pixel vector from disk: " + file.getAbsolutePath() );
		}
		boolean status = true;
		try {
			os.writeObject(list);
			os.close();
		} catch ( IOException ioe ) {
			status = false;
			log.error("could not write array list of ImageInfo to disk");
		}
		return  status;

	}
	
	public static ArrayList<ImageRawData> loadRawListFromDisk ( int i ) {
		File file = new File(Main.ARTIST_PATH +	File.separator + "var" + File.separator + "rawList_" + i + ".data");
		ObjectInputStream os = null;
		FileInputStream is = null;
		try {
			is = new FileInputStream( file );
			os = new ObjectInputStream( is );
		} catch (FileNotFoundException fnf) {
			log.error("Cannot read pixel vector from disk: " + file.getAbsolutePath() );
		} catch (IOException e) {
			log.error("Cannot read pixel vector from disk: " + file.getAbsolutePath() );
		}

		ArrayList<ImageRawData> list = null;
		try {
			list = (ArrayList<ImageRawData>)os.readObject();
			os.close();
		} catch ( IOException ioe ) {
			log.error("could not array list of ImageInfo from disk");
		} catch (ClassNotFoundException e) {
			log.error("could not array list of ImageInfo from disk");
		}
		
		return list;

	}
	
	public static boolean saveRawListToDisk( ArrayList<ImageRawData> list , int i ) {
		File file = new File(Main.ARTIST_PATH +	File.separator + "var" + File.separator + "rawList_" + i + ".data");
		ObjectOutputStream os = null;
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream( file );
			os = new ObjectOutputStream( fs );
		} catch (FileNotFoundException fnf) {
			log.error("Cannot read pixel vector from disk: " + file.getAbsolutePath() );
		} catch (IOException e) {
			log.error("Cannot read pixel vector from disk: " + file.getAbsolutePath() );
		}
		boolean status = true;
		try {
			os.writeObject(list);
			os.close();
		} catch ( IOException ioe ) {
			status = false;
			log.error("could not write array list of ImageInfo to disk");
		}
		return  status;

	}
}

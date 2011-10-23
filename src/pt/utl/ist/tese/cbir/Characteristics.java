package pt.utl.ist.tese.cbir;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

import org.grlea.log.SimpleLogger;

import pt.utl.ist.tese.domain.ImageInfo;
import pt.utl.ist.tese.pca.PCA;
import pt.utl.ist.tese.utils.FileUtils;
import pt.utl.ist.tese.utils.MemoryUsage;
import pt.utl.ist.tese.utils.Timer;

/**
 * Calculates the characteristics of a collection using a 
 * specified sample and subspace or a reduced subspace by PCA
 * 
 * @author André
 */
public class Characteristics {

	/**
	 * logger variable
	 */
	private static final SimpleLogger log = new SimpleLogger(Characteristics.class);	
	/**
	 * id of the folder where the results will be written
	 */
	protected int _id;
	/**
	 * index of the subspace that's being calculated
	 */
	protected int _index;
	/**
	 * number of images to be calculated
	 */
	protected int _sample;
	/**
	 * auxiliary array that keeps track of the work done
	 */
	protected ArrayList<ImageInfo> _collection;
	/**
	 * 
	 * constructor for the {@link Characteristics}
	 * 
	 * @param id 		 id of the folder where the results will be written
	 * @param index 	 index of the subspace that's being calculated
	 * @param sample 	 number of images to be calculated
	 * @param collection list where each element is an ImageInfo with the respective information
	 */
	public Characteristics(  int index , int sample , ArrayList<ImageInfo> collection ) {
		this._id = FileUtils.createCharacteristicDirectory();
		this._index = index;
		this._sample = sample;
		this._collection = collection;
		
		log.info("characterist: dir id (" + _id + ") -- sample (" + _sample + ") -- size of collection (" + _collection.size() + ") -- subspace (" + 
				ImageInfo.SUBSPACES[_index] + "x" + ImageInfo.SUBSPACES[_index] + ")");
	}
	/**
	 * setter method for the index variable
	 * @param _index
	 */
	public void setIndex(int _index) {
		this._index = _index;
		log.info("changing index of charachteristics to " + ImageInfo.SUBSPACES[_index] + "x" + ImageInfo.SUBSPACES[_index] );
	}
	/**
	 * getter method for the index variable
	 * @returns the index of the target subspace
	 */
	public int getIndex() {
		return this._index;
	}
	protected boolean calcCharacteristics( ImageRawData img1 , ImageRawData img2 , int[] work , double[][] results  ) {
		int a = img1.id() , b = img2.id();

		if ( work[a] == -1 || work[a] - 1 >= b ) {
			return false;
		}
		
		double dist = img1.euclidean(img2);
		results[a][b] = dist;
		if ( b < _sample ) {
			results[b][a] = dist;
			work[b] = a + 1;
		}
		
		work[a] = b + 1;
		return true;
	}
	/**
	 * 
	 * Compares a subspace against all others saving the work that's done to the disk
	 * and in memory, in order to avoid redundant work. 
	 * 
	 * @param imgId 	the image that is compared against the collection
	 * @param imgList 	a list containing colour information RGB of each image
	 * @param work 		auxiliary array that keeps track of the work done 
	 * @param results 	list in which the elements are an array with the colour information of each image
	 * @param os 		stream that has the output file opened
	 * 
	 * @return a count of the number of comparisons
	 */
	protected int calcCharacteristics ( int imgId , ArrayList<ImageRawData> imgList , int[] work , double[][] results ) {
		int cont = 0;
		for ( ImageRawData img2 : imgList ) {
			if ( img2.id() == imgId )
				continue;
			boolean status = calcCharacteristics( imgList.get( imgId ) , img2 , work, results);
			if ( status ) cont++;
		}
		
		return cont;
	}
	protected int calcCharacteristics( ImageRawData img1 , ArrayList<ImageRawData> imgList , int[] work , double[][] results ) {
		int cont = 0;
		for ( ImageRawData img2 : imgList ) {
			boolean status = calcCharacteristics( img1 , img2 , work, results );
			if ( status ) cont++;
		}
		
		return cont;
	}
	/**
	 * 
	 * Calculates the characteristic of a collection using a predetermined sample of images 
	 * and the subspace indicated by the _index variable
	 * 
	 * @return the number of images that were compared
	 */
	public int characteristics( PCA pca) {
		
		if ( pca == null ) log.entry("characteristic (" + ImageInfo.SUBSPACES[_index] + "x" + ImageInfo.SUBSPACES[_index] +  ")-- generic");
		else log.entry("characteristic (" + ImageInfo.SUBSPACES[_index] + "x" + ImageInfo.SUBSPACES[_index] +  ")\t-- pca with " + pca.getReducedDimension() + " dimensions");

		log.info("max: " + MemoryUsage.max());
		log.info("free: " + MemoryUsage.free());
		
		int cont = 0;
		int[] work = new int[_sample];
		double[][] results = new double[_sample][_collection.size()];
		
		log.info("free: " + MemoryUsage.free());
		
		Timer timer = new Timer();
		ArrayList<ImageRawData> subspace;
		NumberFormat nf = NumberFormat.getInstance();
		
		Timer timerBatch = new Timer() , timerSample = new Timer();
		
		int start = 0 , end;
		do {

			subspace = null;

			System.gc();
			System.gc();
			System.gc();

			subspace = new ArrayList<ImageRawData>();
			
			end = ImageInfo.loadPixelVectorsToMemory( _index , _collection , start , subspace , pca);
			log.info("size of collection: " + subspace.size());
			if ( subspace.size() == 0 ) break;
			
			int aux = 0;
			
			for ( int i = 0 ; i < _sample ; i++ ) {
				if ( i < start || i > end) {
					ImageRawData img = null;
					if ( pca != null )
						 img = new ImageRawData( pca.reduce(_collection.get(i).getPixelVector(_index).load()) , i );
					else
						img = new ImageRawData( _collection.get(i).getPixelVector(_index).load() , i );
					aux += calcCharacteristics(img, subspace, work, results );

				}
				else 
					aux += calcCharacteristics(i, subspace, work, results );
				
				
				if ( i % 1 == 0 && aux != 0 ) {
					log.info("" + i + " have been calculated in " + timerSample );
					timerSample.restartTimer();
				}
				
			}
			cont += aux;
			if ( subspace.size() != 0 ) {
				log.info("finished the " + nf.format(start) + "-" + nf.format(end) + 
						" subset (" + nf.format(end - start + 1) + " images) in " + timerBatch + 
						" (img calc'ed: " + nf.format(aux) + " and " + 
						nf.format(aux*ImageInfo.SUBSPACES[_index]*ImageInfo.SUBSPACES[_index]) + " pixels )");
				timerBatch.restartTimer();
				start = end + 1;
			}
		} while ( subspace.size() != 0 );
		
		subspace = null;

		System.gc();
		System.gc();
		System.gc();

		log.info("finished calculating the characteristics in " + timer + ", writting results to disk...");
		timerBatch.restartTimer();
		for ( int i = 0 ; i < _sample ; i++ ) {
			BufferedWriter os;
			if ( pca == null ) os = FileUtils.getFileWriter( _id , ImageInfo.SUBSPACES[_index] , i );
			else os = FileUtils.getFileWriter(_id, ImageInfo.SUBSPACES[_index], pca.getSample() + "-" + pca.getReducedDimension(), i);
			try { 
				int j;
				for ( j = 0 ; j < _collection.size() ; j++ )					
					os.write(String.valueOf(results[i][j]) + ",");
				os.close();
			} catch (IOException e1) { 
				log.error("error: could not write to bufferedwriter -- " + e1.getMessage()); 
			}
		}
		
		log.info("finished writing data to disk in " + timerBatch);
		
		if ( pca == null ) log.exit("characteristic -- generic -- " + 
				nf.format(cont) + "/"+ nf.format(_sample * _collection.size()) + " images visited " + " in " + timer);
		else log.exit("characteristic -- pca -- " + 
				nf.format(cont) + "/"+ nf.format(_sample * _collection.size()) + " images visited " + " in " + timer);
		return cont;
	}
	/**
	 * 
	 * Calculates the characteristic of a collection using a predetermined sample of images 
	 * and the subspace reduced by PCA indicated by the _index variable
	 * 
	 * @return the number of images that were compared
	 */
	public int characteristics( ) {
		return characteristics(null);
	}
}

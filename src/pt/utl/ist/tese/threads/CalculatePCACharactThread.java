package pt.utl.ist.tese.threads;

import java.io.File;
import java.util.ArrayList;

import org.grlea.log.SimpleLogger;

import pt.utl.ist.tese.cbir.Characteristics;
import pt.utl.ist.tese.domain.ImageInfo;
import pt.utl.ist.tese.main.Main;
import pt.utl.ist.tese.pca.PCA;

public class CalculatePCACharactThread extends Thread {
	
	/**
	 * logger variable
	 */
	@SuppressWarnings("unused")
	private static final SimpleLogger log = new SimpleLogger(CalculatePCACharactThread.class);
	/**
	 * 
	 */
	protected int incr;
	/**
	 * 
	 */
	protected int startIndex;
	/**
	 * 
	 */
	protected Characteristics charact;
	/**
	 * 
	 */
	protected ArrayList<File> pcas;
		
	/**
	 * @param charact
	 * @param pcas
	 * @param startIndex
	 * @param incr
	 */
	public CalculatePCACharactThread( Characteristics charact , ArrayList<File> pcas , int startIndex , int incr ) {
		this.startIndex = startIndex;
		this.incr = incr;
		this.pcas = pcas;
		this.charact = charact;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run ( ) {
		int resol = ImageInfo.SUBSPACES[charact.getIndex()];
		int dim = resol * resol * 3;
		boolean status = true;
		int cont = 0;

		for ( int i = startIndex ; i < pcas.size() ; i += incr ) {
			cont++;
			PCA pca = Main.readPCA(pcas.get(i));
			if ( pca.getReducedDimension() != dim || (status && pca.getReducedDimension() == dim) ) {
				
				if ( pca.getReducedDimension() == dim )
					status = false;
				log.info("\t\t-- calculating " + cont + "/" + (pcas.size()/2 - startIndex + 1 ) + " pca's characteristics");
				charact.characteristics( pca );
			}
			pca = null;
			System.gc();
			System.gc();
		}
	}


}


package pt.utl.ist.tese.threads;

import java.io.File;
import java.util.ArrayList;

import pt.utl.ist.tese.domain.ImageInfo;
import pt.utl.ist.tese.main.Main;
import pt.utl.ist.tese.pca.PCA;

public class GeneratePCAThread extends Thread {
	
	protected ArrayList<Integer> size;
	protected int index;
	protected ArrayList<ImageInfo> list;
	protected File f;
	
	/**
	 * @param index
	 * @param list
	 * @param size
	 * @param f
	 */
	public GeneratePCAThread( int index , ArrayList<ImageInfo> list , ArrayList<Integer> size , File f ) {
		this.index = index;
		this.list = list;
		this.size = size;
		this.f = f;
	}
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run ( ) {
		PCA pca = null;
		for ( int i : size) {
			pca = Main.pca(list, index, i);
			Main.savePCA(pca, index , i , f);
		}
	}

}

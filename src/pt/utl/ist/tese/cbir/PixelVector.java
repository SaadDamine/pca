package pt.utl.ist.tese.cbir;

import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;

import org.grlea.log.SimpleLogger;

//import org.grlea.log.SimpleLogger;

import pt.utl.ist.tese.utils.FileUtils;
import pt.utl.ist.tese.utils.Utils;

public class PixelVector implements java.io.Serializable{

	private static final long serialVersionUID = 8120988143129069983L;
	protected static final int RED = 0;
	protected static final int GREEN = 1;
	protected static final int BLUE = 2;
	protected static final int SIZE = 3;

	private static final String SUBSPACE_SUFFIX = "-vector" + ".data";
	
	protected byte[] _rgb;
	protected int _resolution;
	protected double _constant;

	protected File _file;
	
	private static final SimpleLogger log = new SimpleLogger(PixelVector.class);
	
	public PixelVector( File file , int resolution ) {
		_file = file;
		_resolution = resolution;
		_constant = 1;
	}
	public byte[] load( ) {
		return FileUtils.loadPixelVector(_file , _resolution * _resolution * SIZE );
	}
	public void unload( ) {
		_rgb = null;
	}
	public boolean save() {
		return FileUtils.savePixelVector( _file, _rgb );
	}
	public boolean setFilePath( String folderPath , String filename ) {
		File folder = new File( folderPath );
		File f;
		try {
			if ( folder.exists() && !folder.isDirectory() )
				return false;
			else if ( !folder.exists() )
				folder.mkdirs();
			f = new File( folderPath + File.separator + filename);
			if ( f.exists() && !f.createNewFile() )
				return false;
		} catch (IOException e) {
			log.error("error: could not create PixelVector file in disk: " + e.getMessage());
			return false;
		}
		_file = f;
		return true;
	}
	public PixelVector( int[][] vector) {
		_rgb = convert( vector );
		setResolution( (int) Math.sqrt( _rgb.length / 3 )  );
		_constant = 1;
	}
	
	public PixelVector( int resolution ) {
		int size = resolution * resolution;
		_rgb = new byte[size * SIZE];
		setResolution( resolution );
		_constant = 1;
	}
	
	public PixelVector( int resolution , int largestSubspaceResolution) {
		int size = resolution * resolution;
		_rgb = new byte[size * SIZE];
		setResolution( resolution );
		_constant = (double) (largestSubspaceResolution / resolution );
	}
	
	public PixelVector( byte[] vector , int resolution ) {
		_rgb = vector;
		_constant = 1;
		setResolution(resolution);
	}
	
	public PixelVector( int[] vector , int resolution ) {
		
		_rgb = new byte[vector.length];
		
		ColorModel cm = ColorModel.getRGBdefault();
		
		for ( int i = 0 ; i < vector.length ; i++ ) {
			setRedValue( i , convertColorToByte(cm.getRed( vector[i] )));
			setGreenValue( i , convertColorToByte(cm.getGreen( vector[i] )));
			setBlueValue( i , convertColorToByte(cm.getBlue( vector[i] )));
		}
		_constant = 1;
		setResolution( resolution );
	}
	
	public byte[] rgb() {
		return _rgb;
	}
	protected byte convertColorToByte( int val) {
		return (byte) (val - 128);
	}
	public void saveDataToDisk( String folderPath ) {
	//	Utils.writeToFile( _rgb , folderPath + resolution() + SUBSPACE_SUFFIX );
		_rgb = null;
	}
	public byte[] convert( int[][] vector ) {
		byte rgb[] = new byte[ SIZE * vector[0].length ];
		int len = vector[0].length;
		for ( int i = 0 ; i < len ; i++ ) {
			for ( int j = 0 ; j < SIZE ; j++ ) {
				rgb[i * SIZE + j] = convertColorToByte(vector[j][i]);
			}
		}
		return rgb;
	}
	public void setFile( File f ) { _file = f; }
	public String filepath() { return _file.getAbsolutePath(); }
	public void readDataToMemory( String folderPath ) {
		_rgb = convert(FileUtils.readPixelVectorData( folderPath + resolution() + SUBSPACE_SUFFIX ));
	}
	public boolean needsRetrieving( ) {
		return ( _rgb == null);
	}
	public void setRGB( byte[] newRGB ) {
		_rgb = newRGB;
	}
	public byte getRedValue( int pixel ) {
		return _rgb[pixel * 3 + RED];
	}
	public byte getGreenValue( int pixel ) {
		return _rgb[pixel * 3 + GREEN];
	}
	public byte getBlueValue( int pixel ) {
		return _rgb[pixel * 3 + BLUE];
	}
	public void setRedValue( int pixel , int value ) {
		_rgb[pixel * SIZE + RED] = convertColorToByte(value);
	}
	public void setGreenValue( int pixel , int value) {
		_rgb[pixel * SIZE + GREEN] = convertColorToByte(value);
	}
	public void setBlueValue( int pixel , int value) {
		_rgb[pixel * SIZE + BLUE] = convertColorToByte(value);
	}
	public int resolution() {
		return _resolution;
	}
	public double constant() {
		return _constant;
	}
	public void setResolution(int resolution) {
		this._resolution = resolution;
	}
}

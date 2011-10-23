package pt.utl.ist.tese.cbir;

import java.io.Serializable;

import org.grlea.log.SimpleLogger;

public class ImageRawData implements Serializable {

	private static final long serialVersionUID = -4937280394310231778L;

	/**
	 * logger variable
	 */
	private static final SimpleLogger log = new SimpleLogger(ImageRawData.class);
	
	protected static final boolean BYTE = true;
	protected static final boolean DOUBLE = false;
	
	protected int _id;
	
	protected boolean _isByteArray;
	
	protected byte[] _byteArray = null;
	protected float[] _doubleArray = null;
	
	public ImageRawData( byte[] array , int id ) {
		_byteArray = array;
		_id = id;
		_isByteArray = BYTE;
	}
	public ImageRawData( float[] array , int id ) {
		_doubleArray = array;
		_id = id;
		_isByteArray = DOUBLE;
	}
	
	public double euclidean( ImageRawData img2 ) {
		if ( _isByteArray )
			return Euclidean.calc( this._byteArray , img2._byteArray );
		else
			return Euclidean.calc( this._doubleArray , img2._doubleArray );
	}
	
	public int id() { return _id; }
	
	public byte[] byteArray() { return _byteArray; }
	
	public float[] doubleArray() { return _doubleArray; }
	
	
}

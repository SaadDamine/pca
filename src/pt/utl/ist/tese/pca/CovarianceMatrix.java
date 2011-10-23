package pt.utl.ist.tese.pca;

import java.util.ArrayList;

import org.grlea.log.SimpleLogger;

import pt.utl.ist.tese.exception.DifferentSizesException;
import pt.utl.ist.tese.exception.EmptyInputException;
import pt.utl.ist.tese.utils.MemoryUsage;

public class CovarianceMatrix {

	private static final SimpleLogger log = new SimpleLogger(CovarianceMatrix.class);

	protected double[][] _matrix;
	protected int _dimension;
	protected double[] _mean;

	public CovarianceMatrix( ArrayList<byte[]> input  , boolean isByte) throws EmptyInputException, DifferentSizesException {
		double[][] values = constructMatrix( input , isByte);
		int size = input.size();
		_dimension = input.get(0).length;
		_matrix = calcCoVarianceMatrix( values , size );	
	}
	
	public CovarianceMatrix( ArrayList<double[]> input ) throws EmptyInputException, DifferentSizesException {
		double[][] values = constructMatrix( input );
		int size = input.size();
		_dimension = input.get(0).length;
		_matrix = calcCoVarianceMatrix( values , size );
	}

	public double[][] m () { return _matrix; }
	
	public double[] mean() { return _mean; }
	
	protected double[] calculateMean( double[][] input ) {
		
		_mean = new double[input[0].length];
		for ( double[] vect : input ) {
			int k = 0;
			for ( double vectI : vect ) {
				_mean[k++] += vectI / input.length;
			}
		}
		return _mean;
	}
	
	protected double[][] dataAdjust( double[][] input ) {
				
		double mean[] = calculateMean(input);
		
		for ( double[] vect : input )
			for ( int k = 0 ; k  < vect.length ; k++  ) {
				vect[k] -= mean[k];
			}
				
		return input;
	}
	
	public int dimension() { return _dimension; }
	
	protected double[][] constructMatrix( ArrayList<byte[]> input, boolean isByte ) throws EmptyInputException,DifferentSizesException {

		int rows = input.size();
		if ( input == null && rows == 0 ) {
			throw new EmptyInputException("Cannot calculate PCA, input is empty");
		}
		int cols = input.get(0).length;

		double[][] constMatrix = new double[rows][cols];

		int k = 0;
		for ( byte[] vect: input ) {
			if ( vect.length != cols ) {
				throw new DifferentSizesException(
						"Cannot calculate PCA, input does not have a normalized size, " +
						"current is " + vect.length + " when it should be " + cols );
			}
			else {
				int i = 0;
				for ( byte val : vect ) {
					constMatrix[k][i] = (double)val;
					//log.info("constMatrix[" + k + "][" + i  + "] = " + constMatrix[k][i]);
					i++;
				}
			}
			k++;			
		}
		constMatrix = dataAdjust(constMatrix);
		
		return constMatrix;
	}
	
	protected double[][] constructMatrix( ArrayList<double[]> input ) throws EmptyInputException,DifferentSizesException {

		int rows = input.size();
		if ( input == null && rows == 0 ) {
			throw new EmptyInputException("Cannot calculate PCA, input is empty");
		}
		int cols = input.get(0).length;

		double[][] constMatrix = new double[rows][];

		int k = 0;
		for ( double[] vect: input ) {
			if ( vect.length != cols ) {
				throw new DifferentSizesException(
						"Cannot calculate PCA, input does not have a normalized size, " +
						"current is " + vect.length + " when it should be " + cols );
			}
			else
				constMatrix[k++] = vect;
		}
		
		constMatrix = dataAdjust(constMatrix);
		
		return constMatrix;
	}

	protected double[][] createMatrix(  ) {
		log.info("memory usage: " + MemoryUsage.total());
		log.info("max mem: " + MemoryUsage.max());
		double mat[][] = new double[_dimension][_dimension];
		log.info("memory usage: " + MemoryUsage.total());
		log.info("max mem: " + MemoryUsage.max());
		return mat;
		
		
		//return new double[(1 + _dimension) * (_dimension / 2)];
	}
	
	protected int pos( int x , int y ) {
		if ( x > y ) {
			int t = y;
			y = x;
			x = t;
		}
		
		int aux = 0;
		
		for ( int i = 0; i < x ; i++ ) {
			aux += _dimension - i;
		}
		
		return aux + (y-x);
	}
	public double get( int x , int y ) {
		return _matrix[x][y];
	}
	
	public void set( int x , int y , double val ) {
		try {
			_matrix[x][y] = val;
		}catch (ArrayIndexOutOfBoundsException e) {
			log.error("pos: " + x + "," + y);
			log.error("error: " + e.getMessage());
			System.exit(0);
		}
	}
	
	protected double[][] calcCoVarianceMatrix( double[][] adjustedInput ,  int rows ) {

		double[] m = new double[ _dimension ];

		// calculate the m values
		for ( int i = 0 ; i < _dimension ; i++ ) {
			double mAux = 0;

			for ( int j = 0 ; j < rows ; j++ )
				mAux += adjustedInput[j][i];

			mAux /= rows;

			m[i] = mAux;
		}
		
		_matrix = createMatrix();
		
		for ( int i = 0 ; i < _dimension ; i++ ) {
			for ( int j = i ; j < _dimension ; j++ ) {
				double val = 0;
				for ( int k = 0 ; k < rows ; k++ ) {
					val += ( adjustedInput[k][i] - m[i] ) * ( adjustedInput[k][j] - m[j] );					
				}
				val /= rows - 1;

				set(i, j, val);
				set(j, i, val);
			}
		}

		return _matrix;
	}

	@Override
	public String toString() {
		String s = "";
		for ( int i = 0 ; i < dimension() ; i++ ) {
			s += i + ": ";
			for ( int j = 0 ; j < dimension() ; j++) {
				s += "\t " + get(i, j);
			}
			s += "\n";
		}
		
		return s;
	}
	
	

}

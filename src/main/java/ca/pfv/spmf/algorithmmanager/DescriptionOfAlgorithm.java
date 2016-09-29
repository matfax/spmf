package ca.pfv.spmf.algorithmmanager;

import java.io.IOException;

/* This file is copyright (c) 2008-2013 Philippe Fournier-Viger
 * 
 * This file is part of the SPMF DATA MINING SOFTWARE
 * (http://www.philippe-fournier-viger.com/spmf).
 * 
 * SPMF is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * SPMF is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with
 * SPMF. If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * This class is used to describe an algorithm to be used in the SPMF library.
 * 
 * @see DescriptionOfParameter
 * @see AlgorithmManager
 * @author Philippe Fournier-Viger
 */
public abstract class DescriptionOfAlgorithm {
	
	/** get the  name of the author of the implementation */
	public abstract String getImplementationAuthorNames();

	/** get the name of the algorithm (e.g. "Rulegrowth") */
	public  abstract String getName();

	/** get the category of this algorithm (e.g. "sequential rule mining" */
	public  abstract String getAlgorithmCategory();

	/** get the URL providing a documentation of how to use this algorithm */
	public abstract String getURLOfDocumentation();

	/**
	 * Run the algorithm
	 * 
	 * @param algorithmName
	 *            the name of the algorithm
	 * @param inputFile
	 *            the ca.pfv.spmf.input file for the algorithm
	 * @param outputFile
	 *            the output file for the algorithm
	 * @param parameters
	 *            the parameters of the algorithm
	 * @throws IOException
	 *             exception if an error occurs
	 */
	public abstract void runAlgorithm(String[] parameters, String inputFile,
			String outputFile) throws Exception;
	
	/**
	 * Get a description of the algorithm's parameters
	 * @return a list of AlgorithmParameter objects describing the parameters of the algorithm.
	 */
	public abstract DescriptionOfParameter[] getParametersDescription();
	
	/**
	 * Get at list of file types (Strings) representing the ca.pfv.spmf.input file format taken as ca.pfv.spmf.input by the algorithm.
	 * @return a list of file types (Strings) or null if the algorithm does not take an ca.pfv.spmf.input file as ca.pfv.spmf.input.
	 */
	public abstract String[] getInputFileTypes();
	
//	/**
//	 * Get at list of special file types supported by this algorithm such as the ARFF file format.
//	 * @return a list of file types (Strings) or null if the algorithm does not accept any special ca.pfv.spmf.input file as ca.pfv.spmf.input.
//	 */
//	abstract String[] getSpecialInputFileTypes();
//	
	/**
	 * Get at list of file types (Strings) representing the  output file format taken as ca.pfv.spmf.input by the algorithm.
	 * @return a list of file types (Strings) or null if the algorithm does not output a file.
	 */
	public abstract String[] getOutputFileTypes();
	

	/**
	 * Method to convert a parameter given as a string to a double. For example,
	 * convert something like "50%" to 0.5.
	 * 
	 * @param value
	 *            a string
	 * @return a double
	 */
	protected static double getParamAsDouble(String value)  {
		if (value.contains("%")) {
			value = value.substring(0, value.length() - 1);
			return Double.parseDouble(value) / 100d;
		}
		return Double.parseDouble(value);
	}

	/**
	 * Method to transform a string to an integer
	 * 
	 * @param value
	 *            a string
	 * @return an integer
	 */
	protected static int getParamAsInteger(String value) {
		return Integer.parseInt(value);
	}
	
	/**
	 * Method to transform a string to an boolean
	 * 
	 * @param value         a string
	 * @return a boolean
	 */
	protected static boolean getParamAsBoolean(String value) {
		return Boolean.parseBoolean(value);
	}

	/**
	 * Method to get a parameter as a string. Note: this method just return the
	 * string taken as parameter.
	 * 
	 * @param value
	 *            a string
	 * @return a string
	 */
	protected static String getParamAsString(String value) {
		return value;
	}

}

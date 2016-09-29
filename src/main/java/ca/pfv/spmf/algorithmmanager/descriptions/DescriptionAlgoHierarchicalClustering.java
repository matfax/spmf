package ca.pfv.spmf.algorithmmanager.descriptions;

import java.io.IOException;

import ca.pfv.spmf.algorithmmanager.DescriptionOfAlgorithm;
import ca.pfv.spmf.algorithmmanager.DescriptionOfParameter;
import ca.pfv.spmf.algorithms.clustering.distanceFunctions.DistanceFunction;
import ca.pfv.spmf.algorithms.clustering.hierarchical_clustering.AlgoHierarchicalClustering;
/* This file is copyright (c) 2008-2016 Philippe Fournier-Viger
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
 * This class describes the hierarchical clustering algorithm parameters. 
 * It is designed to be used by the graphical and command line interface.
 * 
 * @see AlgoHierarchicalClustering
 * @author Philippe Fournier-Viger
 */
public class DescriptionAlgoHierarchicalClustering extends DescriptionOfAlgorithm {

	/**
	 * Default constructor
	 */
	public DescriptionAlgoHierarchicalClustering(){
	}

	@Override
	public String getName() {
		return "Hierarchical_clustering";
	}

	@Override
	public String getAlgorithmCategory() {
		return "CLUSTERING";
	}

	@Override
	public String getURLOfDocumentation() {
		return "http://www.philippe-fournier-viger.com/spmf/index.php?link=documentation.php#example10";
	}

	@Override
	public void runAlgorithm(String[] parameters, String inputFile, String outputFile) throws IOException {
		int maxDistance = getParamAsInteger(parameters[0]);
		String distanceFunctionName = getParamAsString(parameters[1]);
		DistanceFunction distanceFunction 
			= DistanceFunction.getDistanceFunctionByName(distanceFunctionName);
		
		// Apply the algorithm
		AlgoHierarchicalClustering algo = new AlgoHierarchicalClustering();
		algo.runAlgorithm(inputFile, maxDistance, distanceFunction);
		algo.printStatistics();
		algo.saveToFile(outputFile);
	}

	@Override
	public DescriptionOfParameter[] getParametersDescription() {
        
		DescriptionOfParameter[] parameters = new DescriptionOfParameter[2];
		parameters[0] = new DescriptionOfParameter("Max distance", "(e.g. 4)", Double.class, false);
		parameters[1] = new DescriptionOfParameter("Distance function", "(e.g. euclidian, cosine...)", String.class, false);
		return parameters;
	}

	@Override
	public String getImplementationAuthorNames() {
		return "Philippe Fournier-Viger";
	}

	@Override
	public String[] getInputFileTypes() {
		return new String[]{"Database of instances", "Database of double vectors"};
	}

	@Override
	public String[] getOutputFileTypes() {
		return new String[]{"Clusters", "Hierarchical clusters"};
	}
	
}

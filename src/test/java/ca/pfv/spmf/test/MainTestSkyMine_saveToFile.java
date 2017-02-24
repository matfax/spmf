package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.skymine.AlgoSkyMine;
import org.junit.Test;

/**
 * This is an example of how to run the Skymine algorithm
 * <p>
 * Copyright (c) 2015 Vikram Goyal, Ashish Sureka, Dhaval Patel, Siddharth
 * Dawar
 * <p>
 * This file is part of the SPMF DATA MINING SOFTWARE *
 * (http://www.philippe-fournier-viger.com/spmf).
 * <p>
 * SPMF is free software: you can redistribute it and/or modify it under the
 * * terms of the GNU General Public License as published by the Free
 * Software * Foundation, either version 3 of the License, or (at your
 * option) any later * version. SPMF is distributed in the hope that it will
 * be useful, but WITHOUT ANY * WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along
 * with SPMF. If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Vikram Goyal, Ashish Sureka, Dhaval Patel, Siddharth Dawar
 * @see AlgoSkyMine
 */
public class MainTestSkyMine_saveToFile {


    /**
     * Main method
     */

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // Input file paths
            String transactionFile = "SkyMineTransaction.txt";
            String utilityTableFile = "SkyMineItemUtilities.txt";

            // Output file path
            String outputFilePath = "output.txt";

            // This is to choose whether optimizations should be used by the algorithms or not
            // If they are set to false, it is the baseline algorithm.
            boolean usePreInsertingSingleAndPairs = true;
            boolean useRaisingUMinByNodeUtilities = true;

            // Create an instance of the algorithm
            AlgoSkyMine up = new AlgoSkyMine();

            // run the algorithm
            up.runAlgorithm(transactionFile, utilityTableFile, outputFilePath, usePreInsertingSingleAndPairs, useRaisingUMinByNodeUtilities);

            // print statistics about the algorithm execution
            up.printStats();
        });
    }
}

package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.associationrules.MNRRules.AlgoMNRRules;
import ca.pfv.spmf.algorithms.frequentpatterns.zart.AlgoZart;
import ca.pfv.spmf.algorithms.frequentpatterns.zart.TZTableClosed;
import ca.pfv.spmf.input.transaction_database_list_integers.TransactionDatabase;
import org.junit.Test;

/**
 * Example of how to generate minimal non redundant
 * association rules in source code.
 *
 * @author Philippe Fournier-Viger, 2008
 */
public class MainTestMNRRules_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            System.out.println("STEP 1 : EXECUTING THE ZART ALGORITHM TO FIND CLOSED ITEMSETS AND MINIMUM GENERATORS");
            String input = "contextZart.txt";
            String output = ".//output.txt";
//		
            double minsup = 0.6;  // minimum support
            double minconf = 0.6; // minimum confidence

            TransactionDatabase database = new TransactionDatabase();
            try {
                database.loadFile(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            database.printDatabase();

            // Applying the Zart algorithm
            AlgoZart zart = new AlgoZart();
            TZTableClosed results = zart.runAlgorithm(database, minsup);
            zart.printStatistics();

//		// PRINT RESULTS FROM THE ZART ALGORITHM
//		int countClosed=0;
//		int countGenerators=0;
//		System.out.println("===================");
//		for(int i=0; i< results.levels.size(); i++){
//			System.out.println("LEVEL : " + i);
//			for(Itemset closed : results.levels.get(i)){
//				System.out.println(" CLOSED : " + closed.toString() + "  supp : " + closed.getAbsoluteSupport());
//				countClosed++;
//				System.out.println("   GENERATORS : ");
//					for(Itemset generator : results.mapGenerators.get(closed)){
//						countGenerators++;
//						System.out.println("     =" + generator.toString());
//					}
//			}
//		}

            System.out.println("STEP 2 : CALCULATING MNR ASSOCIATION RULES");
            // Run the algorithm to generate MNR rules
            AlgoMNRRules algoMNR = new AlgoMNRRules();
            algoMNR.runAlgorithm(output, minconf, results, database.size());
        });
    }
}

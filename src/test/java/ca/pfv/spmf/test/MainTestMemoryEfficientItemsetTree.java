package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.itemsettree.AssociationRuleIT;
import ca.pfv.spmf.algorithms.frequentpatterns.itemsettree.HashTableIT;
import ca.pfv.spmf.algorithms.frequentpatterns.itemsettree.MemoryEfficientItemsetTree;
import ca.pfv.spmf.patterns.itemset_array_integers_with_count.Itemset;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Example of how to use the memory efficient itemset tree data structure.
 *
 * @author Philippe Fournier-Viger, 2012.
 */
public class MainTestMemoryEfficientItemsetTree {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "contextItemsetTree.txt"; // the database

            // Applying the algorithm to build the itemset tree
            MemoryEfficientItemsetTree itemsetTree = new MemoryEfficientItemsetTree();
            // method to construct the tree from a set of transactions in a file
            itemsetTree.buildTree(input);
            // print the statistics about the tree construction time and print the tree in the console
            itemsetTree.printStatistics();
            System.out.println("THIS IS THE TREE:");
            itemsetTree.printTree();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(itemsetTree);
            oos.close();
            System.out.println("REAL SIZE OF ITEMSET TREE: " + baos.size() / 1024d / 1024d + " MB");

//		
//		// It is also possible to add transactions manually. For example, we can add 
//		// the transaction{4 5} to the tree.
//		System.out.println("THIS IS THE TREE AFTER ADDING A NEW TRANSACTION {4,5}:");
            itemsetTree.addTransaction(new int[]{4, 5});
            itemsetTree.printTree();
//		
//		// After the three is built, we can query the tree.
//		
//		// Example query 1 :  what is the support of an itemset (e.g. {1 2 3})
            System.out.println("EXAMPLES QUERIES: FIND THE SUPPORT OF SOME ITEMSETS:");
            System.out.println("the support of 1 2 3 is : " +
                    itemsetTree.getSupportOfItemset(new int[]{1, 2, 3}));
            System.out.println("the support of 2 is : " +
                    itemsetTree.getSupportOfItemset(new int[]{2}));
            System.out.println("the support of 2 4 is : " +
                    itemsetTree.getSupportOfItemset(new int[]{2, 4}));
            System.out.println("the support of 1 2 is : " +
                    itemsetTree.getSupportOfItemset(new int[]{1, 2}));
//		
//		// Example query 2: get all itemsets that subsume an itemset (e.g. {1 2}) and their support
            System.out.println("EXAMPLE QUERY: FIND ALL ITEMSETS THAT SUBSUME {1 2}");
            HashTableIT result = itemsetTree.getFrequentItemsetSubsuming(new int[]{1, 2});
            for (List<Itemset> list : result.table) {
                if (list != null) {
                    for (Itemset itemset : list) {
                        System.out.println("[" + itemset.toString() + "]    supp:" + itemset.support);
                    }
                }
            }
//		
//		// Example query 3:  get all itemsets that subsume an itemset and have a support higher than minsup
            System.out.println("EXAMPLE QUERY: FIND ALL ITEMSETS THAT SUBSUME {1} and minsup >= 2");
            int minsup = 2;
            HashTableIT result2 = itemsetTree.getFrequentItemsetSubsuming(new int[]{1}, minsup);
            for (List<Itemset> list : result2.table) {
                if (list != null) {
                    for (Itemset itemset : list) {
                        System.out.println("[" + itemset.toString() + "]    supp:" + itemset.support);

//					checkResult(itemset);   // THIS IS FOR DEBUGING  ONLY, IGNORE THIS LINE
                    }
                }
            }
//		
//		// Example query 4 : generate all association rules with an itemset as antecedent and minsup and minconf
            System.out.println("EXAMPLE QUERY: FIND ALL ASSOCIATION RULE WITH AN ITEMSET {1} AS ANTECEDENT AND MINSUP >= 2 and minconf >= 0.1");
            minsup = 2;
            double minconf = 0.1;
            List<AssociationRuleIT> rules = itemsetTree.generateRules(new int[]{1}, minsup, minconf);
            for (AssociationRuleIT rule : rules) {
                System.out.println(rule);
            }

        });
    }
}

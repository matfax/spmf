package   ca.pfv.spmf.algorithms.frequentpatterns.lcm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.pfv.spmf.patterns.itemset_array_integers_with_count.Itemset;
import ca.pfv.spmf.patterns.itemset_array_integers_with_count.Itemsets;
import ca.pfv.spmf.tools.MemoryLogger;

/* This file is copyright (c) 2012-2014 Alan Souza
* 
* This file is part of the SPMF DATA MINING SOFTWARE
* (http://www.philippe-fournier-viger.com/spmf).
* 
* SPMF is free software: you can redistribute it and/or modify it under the
* terms of the GNU General Public License as published by the Free Software
* Foundation, either version 3 of the License, or (at your option) any later
* version.
* SPMF is distributed in the hope that it will be useful, but WITHOUT ANY
* WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
* A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with
* SPMF. If not, see <http://www.gnu.org/licenses/>.
*/

/**
 * This is an implementation of the LCMMax algorithm for
 * mining frequent maximal itemsets from a transaction database.
 * More information on the LCM algorithm can be found in papers by
 * T. Uno, such as: <br/><br/>
 * 
 * T. Uno, M. Kiyomi, and H. Arimura. Lcm ver. 2:
  * Efficient mining algorithms for
 * frequent/closed/maximal itemsets. In FIMI, 2004
 * 
 * This implementation of LCMMax was made by Alan Souza and was
 * modified by Philippe Fournier-Viger to add optimizations and 
 * support for LCMFreq/LCMMax (note LCMMax has been temporarily removed in the 
 * current version of SPMF). <br/>
 * 
 * The implementation is similar to LCM version 2 with some differences.
 * For example, transaction merging is not performed yet and
 * items in transactions are not sorted in descending order of frequency.
 *
 * @author Alan Souza <apsouza@inf.ufrgs.br>
 */
class AlgoLCMMax {

    private Itemsets itemsets;
    
	// object to write the output file
    private BufferedWriter writer = null;
	
	// the number of frequent itemsets found (for
	// statistics)
	private int frequentCount; 

	// the start time and end time of the last algorithm execution
    private long startTimestamp;
	private long endTimestamp;
	private int minsupRelative;
	
	// Buckets for occurence delivery 
	// Recall that each bucket correspond to an item
    // and contains the transactions where the items appears.
	private List<Transaction>[] buckets;

    public AlgoLCMMax() {
         
    }

    /**
     * Run the algorithm
     * @param minimumSupport  the minimum support threshold as percentage value between 0 and 1
     * @param dataset  the dataset
     * @param outputPath  the output file path to save the result or null if to be kept in memory
     * @return the itemsets or null if the user choose to save to file
     * @throws IOException if exception while reading/writing to file
     */
    public Itemsets runAlgorithm(double minimumSupport, Dataset dataset, String outputPath) throws IOException {
		// record the start time
		startTimestamp = System.currentTimeMillis();

		// if the user choose to save to file
		// create object for writing the output file
		if(outputPath != null) {
			writer = new BufferedWriter(new FileWriter(outputPath));
		}else {
			// if the user choose to save to memory
			writer = null;
	        this.itemsets = new Itemsets("Itemsets");
		}
		
		// reset the number of itemset found
		frequentCount = 0;
		
		// reset the memory usage checking utility
		MemoryLogger.getInstance().reset();
		
		// convert from an absolute minsup to a relative minsup by multiplying
		// by the database size
		this.minsupRelative = (int) Math.ceil(minimumSupport * dataset.getTransactions().size());
    	
        // Create the initial occurrence array for the dataset
        performFirstOccurenceDelivery(dataset);
        
        //======
        // Remove infrequent items from transactions by using support calculated using
        // the buckets. Recall that each bucket correspond to an item
        // and contains the transactions where the items appears.
    	for(Transaction transaction : dataset.getTransactions()) {
    		transaction.removeInfrequentItems(buckets, minsupRelative);
    	}
    	
    	//======
    	// Create the array of all frequent items.
    	List<Integer> allItems = new ArrayList<Integer>();
    	for(Integer item : dataset.getUniqueItems()) {
    		if(buckets[item].size() >= minsupRelative) {
    			allItems.add(item);
    		}
    	}
    	// Sort all items
    	Collections.sort(allItems);

    	//======
        // Call the recursive method witht the empty set as prefix.
        // Since it is the empty set, we will have all transactions and no frequency count
    	backtrackingLCMMax(null, dataset.getTransactions(), allItems, -1, -1);
       
		// record the end time
		endTimestamp = System.currentTimeMillis();
		//close the output file
		if(writer != null) {
			writer.close();
		}
		
		MemoryLogger.getInstance().checkMemory();
        
        return itemsets;
    }
    
    /**
     * Recursive method to find maximal itemsets
     * @param p  a prefix itemset P
     * @param transactionsOfP the transations containing P
	 * @param frequentItems the list of frequent items in the p-projected database
     * @param tailPosInP the tail item position in itemset P
     * @throws IOException if error writing to output file
     */
    private void backtrackingLCMMax(List<Integer> p, List<Transaction> transactionsOfP,
                                    List<Integer> frequentItems, int tailPosInP, Integer itemELastAddedToP) {
    	
    	throw new RuntimeException("This algorithm is unavailable in the current version of SPMF. \n");
    	/*
    	boolean foundOneMaxItemset = false;

        // ========  for each frequent item  e  =============
		for (int j = 0; j < frequentItems.size(); j++) {
			Integer e = frequentItems.get(j);
			System.out.println(" p : " + p + " \te: " + e);
			// if the item is not already in p  before the current tail position
			// we will consider it to form a new itemset
			if(e <= itemELastAddedToP || (p != null && containsByBinarySearch(p, e, tailPosInP))) {  //  #DIFF
				continue;
			}
			
			// Calculate transactions containing P U e 
			// At the same time truncate the transactions to keep what appears after "e"
			List<Transaction> transactionsPe = intersectTransactions(transactionsOfP, e); //ok
			
			//====== Check if PU{e...} is a ppc extension  ======
			if (isPPCExtension(p, transactionsPe, e)) {
				//  ======= Create a closed itemset using PU{e...}  =====
				// First add all items from PU{e}
		    	List<Integer> itemset = new ArrayList<Integer>();
		    	if(p != null) {
			        //add every item i of p  such that i < e to the  itemset
			        for (int m = 0; m < p.size() && p.get(m) < e; m++) {
			        	itemset.add(p.get(m));
			        }
		    	}
		    	itemset.add(e);
		    	int tailPositionInPe = itemset.size()-1;
		    
		    	for (int k = j+1; k < frequentItems.size(); k++) {
					Integer itemk = frequentItems.get(k);
		            // for every item i > e add if it is in all transactions of T(P U e)
					// ### FIRST DIFFERENCE WITH LCM ###
		            if(isItemInAllTransactions(transactionsPe, itemk)) {
		            	itemset.add(itemk);
		            }
		        }
		    	
		    	/// #### SECOND DIFFERENCE ####
//		    	int itemsAddedAfterECount = itemset.size() - (tailPositionInPe + 1);

		        // ===== save the frequent closed itemset
		    	int supportPe = transactionsPe.size();

				//==== perform database reduction ====
		    	anyTimeDatabaseReductionMax(transactionsPe, j, frequentItems, p, e);  // ##DIF
				
		    	// ================ Find frequent items in transactions containing P ============
		        // Get all frequent items e such that e > tailOfP  
		    	// (i.e. "e" appears after the position of the tail item in the list of all items)
				List<Integer> newFrequentItems = new ArrayList<Integer>();
		    	for (int k = 0; k < frequentItems.size(); k++) {   ///  ##### ANOTHER DIFFERENCE  START FROM K = 0
		        	Integer itemK =  frequentItems.get(k);
		        	int supportK = buckets[itemK].size();
		            if(supportK >= minsupRelative) {
		            	newFrequentItems.add(itemK);
		            }
		        }
		    	
		    	//  ===== ### SECOND DIFFERENCE WITH LCM #### =====
//		    	int itemsAddedAfterEcount = (itemset.size() - p.size());
		    	System.out.println(itemset);
		    	if(newFrequentItems.size() == itemset.size()) {
					output(itemset, supportPe);
					System.out.println("OUTPUT " + itemset);
					foundOneMaxItemset = true;
		    	}else {  // #DIFF : ELSE
		    		// === recursive call
		    		boolean found = backtrackingLCMMax(itemset, transactionsPe, newFrequentItems, tailPositionInPe, e);  // ### DIFF :  PASS E
		    		if(found == false) {  // ## DIFF
						System.out.println("OUTPUT2 " + itemset);
		    			output(itemset, supportPe);
		    		}
		    	}
			}
		}

		MemoryLogger.getInstance().checkMemory();
		return foundOneMaxItemset;  // ## DIFF
		*/
    }
    
    

    /**
	 * Perform the initial occurence delivery with the original dataset
	 * containing all items
	 * @param dataset
	 */
    private void performFirstOccurenceDelivery(Dataset dataset) {

		buckets = new List[dataset.getMaxItem() + 1]; 

		for (Integer item : dataset.uniqueItems) {
			buckets[item] = new ArrayList<Transaction>();
		}

		for (Transaction transaction : dataset.getTransactions()) {
			for (Integer item : transaction.getItems()) {
				// for each item get its bucket and add the current transaction
				buckets[item].add(transaction);
			}

		}
	}

    
    
    private void anyTimeDatabaseReductionMax(List<Transaction> transactionsPe, int j, List<Integer> frequentItems, List<Integer> itemset, Integer e) {

 		// We just reset the buckets for item  > e
 		// instead of all buckets
 		for (int i = 0; i < frequentItems.size(); i++) { //  ## DIF INSTEAD OF J+1
 			Integer item = frequentItems.get(i);
 			buckets[item] = new ArrayList<Transaction>();
 		}
 		
        // for each transaction
        for(Transaction transaction : transactionsPe) {
     	   // we consider each item I  of the transaction such that  itemI > 0 
     	   for(int i = transaction.getItems().length-1; i >= 0; i--) {  // #### DIF  i >=0
     		   Integer item = transaction.getItems()[i];
     		   if(frequentItems.contains(item)) {
 	    		   // we add the transaction to the bucket of the itemI
 	    		   buckets[item].add(transaction);
     		   }
         	}
         }
     }
    
    /**
     * Check if an item appears in this itemset
     * @param item  the item
     * @return true if it appears. Otherwise, false.
     */
	public boolean containsByBinarySearch(List<Integer> items, Integer item, int searchAfterPosition) {
		if(items.size() == 0 || item > items.get(items.size() -1)) {
			return false;
		}
		int low = searchAfterPosition +1;
		int high = items.size() - 1;

		while (high >= low) {
			int middle = ( low + high ) >>> 1; // divide by 2
			if (items.get(middle).equals(item)) {
				return true;
			}
			if (items.get(middle) < item) {
				low = middle + 1;
			}
			if (items.get(middle) > item) {
				high = middle - 1;
			}
		}
		return false;
	}
	
	private boolean containsByBinarySearch(List<Integer> items, Integer item) {
		if(items.size() == 0 || item > items.get(items.size() -1)) {
			return false;
		}
		int low = 0;
		int high = items.size() - 1;

		while (high >= low) {
			int middle = ( low + high ) >>> 1; // divide by 2
			if (items.get(middle).equals(item)) {
				return true;
			}
			if (items.get(middle) < item) {
				low = middle + 1;
			}
			if (items.get(middle) > item) {
				high = middle - 1;
			}
		}
		return false;
	}
	
    /**
     * Calculate the transactions of the union of an itemset "P" with an item "e".
     * @param transactionsOfP the transactions containing P
     * @param e  the item "e"
     * @return the transactions containing P U "e"
     */
    public List<Transaction> intersectTransactions(List<Transaction> transactionsOfP, Integer e) {
        List<Transaction> transactionsPe = new ArrayList<Transaction>();

        // transactions of P U e
        for(Transaction transaction : transactionsOfP) {
        	// we remember the position where e appears.
        	// we will call this position an "offset"
        	int posE = transaction.containsByBinarySearch(e);
            if (posE != -1) { // T(P U e)
                transactionsPe.add(new Transaction(transaction, posE));
            }
        }
        return transactionsPe;
    }

    
    /**
     * Check if a given itemset PUe is a PPC Max extension according to
     * the set of transactions containing PUe.
     * @param p the itemset p
     * @param e the item e
     * @param transactionsPe  the transactions containing P U e
     * @return true if it is a PPC extension
     */
    private boolean isPPCMaxExtension(List<Integer> p, Integer e, List<Transaction> transactionsPe) {

    	// We do a loop on each item i not in P U e
    	Transaction firstTrans = transactionsPe.get(0);
    	Integer[] firstTransaction = firstTrans.getItems();
        for (int i = 0; i < firstTransaction.length; i++) {
        	Integer item = firstTransaction[i];
            // if p does not contain item i < e and item i is present in all transactions, 
        	// then it PUe is not a ppc
        	if(item >= e) {
        		break;
        	}
            if((p == null || !containsByBinarySearch(p,item))
                    && isItemInAtLeastMinsupTransactionsWithoutFirst(transactionsPe, item)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Check if an item appears in at least minsup transactions 
     * @param transactions a list of transactions (without the first one)
     * @param item an item
     * @return true if the item appears in > minsup-1 transactions after the first one
     */
    private boolean isItemInAtLeastMinsupTransactionsWithoutFirst(List<Transaction> transactions, Integer item) {
    	int supCount = 1;
    	for(int i=1; i < transactions.size(); i++) {
            if(transactions.get(i).containsByBinarySearchOriginalTransaction(item)) {
            	supCount++;
            	if(supCount == minsupRelative) {
            		return true;
            	}
            }
        }
        return false;
    }
    
    /**
     * Check if an item appears in all transactions of a list of transactions
     * @param transactions a list of transactions
     * @param item an item
     * @return true if the item appears in all transactions 
     */
    private boolean isItemInAtLeastMinsupTransactions(List<Transaction> transactions, Integer item) {
    	int supCount = 0;
        for(Transaction transaction : transactions) {
            if(transaction.containsByBinarySearch(item) != -1) {
            	supCount++;
            	if(supCount == minsupRelative) {
            		return true;
            	}
            }
        }
        return false;
    }
    
    
    /**
     * Check if an item appears in all transactions of a list of transactions
     * @param transactions a list of transactions
     * @param item an item
     * @return true if the item appears in all transactions 
     */
    private boolean isItemInAllTransactions(List<Transaction> transactions, Integer item) {

        for(Transaction transaction : transactions) {
            if(transaction.containsByBinarySearch(item) == -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Save a frequent closed itemset to file or memory depending on what the user chose.
     * @param itemset the itemset
     * @throws IOException if error while writting to output file
     */
    private void output(List<Integer> itemset, int support) throws IOException {
    	// if not the empty set
        if(!itemset.isEmpty()) {
            frequentCount++;
            
        	// if save to memory
        	if(writer == null) {
        		// The following line is not too optimized since
        		// we convert an itemset as List<Integer> to int[]
        		// but this cost is still quite small, so we leave it like 
        		itemsets.addItemset(new Itemset(itemset, support), itemset.size());
        	}else {
        	// if save to file
    		// create a stringuffer
    		StringBuilder buffer = new StringBuilder();
    		// append items from the itemset to the StringBuilder
    		for (int i = 0; i < itemset.size(); i++) {
    			buffer.append(itemset.get(i));
    			if (i != itemset.size() - 1) {
    				buffer.append(' ');
    			}
    		}
    		// append the support of the itemset
    		buffer.append(" #SUP: ");
    		buffer.append(support);
    		// write the strinbuffer to file and create a new line
    		// so that we are ready for writing the next itemset.
    		writer.write(buffer.toString());
    		writer.newLine();
        	}
        }
    }


 
 
    /**
     * Print statistics about the latest execution of the algorithm.
     */
	public void printStats() {
		System.out.println("========== LCMMax - STATS ============");
		System.out.println(" Freq. maximal itemsets count: " + frequentCount);			
		System.out.println(" Total time ~: " + (endTimestamp - startTimestamp)
				+ " ms");
		System.out.println(" Max memory:" + MemoryLogger.getInstance().getMaxMemory());
		System.out.println("=====================================");
	}
}

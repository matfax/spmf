package ca.pfv.spmf.algorithms.sequentialpatterns.clospan_AGP;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import ca.pfv.spmf.algorithms.sequentialpatterns.clospan_AGP.items.Item;
import ca.pfv.spmf.algorithms.sequentialpatterns.clospan_AGP.items.Pair;
import ca.pfv.spmf.algorithms.sequentialpatterns.clospan_AGP.items.PseudoSequence;
import ca.pfv.spmf.algorithms.sequentialpatterns.clospan_AGP.items.PseudoSequenceDatabase;
import ca.pfv.spmf.algorithms.sequentialpatterns.clospan_AGP.items.abstractions.Abstraction_Generic;
import ca.pfv.spmf.algorithms.sequentialpatterns.clospan_AGP.items.abstractions.ItemAbstractionPair;
import ca.pfv.spmf.algorithms.sequentialpatterns.clospan_AGP.items.creators.AbstractionCreator;
import ca.pfv.spmf.algorithms.sequentialpatterns.clospan_AGP.items.creators.ItemAbstractionPairCreator;
import ca.pfv.spmf.algorithms.sequentialpatterns.clospan_AGP.items.patterns.Pattern;
import ca.pfv.spmf.algorithms.sequentialpatterns.clospan_AGP.items.trie.Trie;
import ca.pfv.spmf.algorithms.sequentialpatterns.clospan_AGP.items.trie.TrieNode;
import ca.pfv.spmf.algorithms.sequentialpatterns.clospan_AGP.savers.Saver;

/**
 * This is an the real execution of PrefixSpan algorithm.
 * The main methods of this class are called from class AlgoPrefixSpan_AGP, and
 * the main loop of the algorithm is executed here.
 * 
 * NOTE: This implementation saves the pattern  to a file as soon 
 * as they are found or can keep the pattern into memory, depending
 * on what the user choose.
 *
 * Copyright Antonio Gomariz Peñalver 2013
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
 *
 * You should have received a copy of the GNU General Public License along with
 * SPMF. If not, see <http://www.gnu.org/licenses/>.
 *
 * @author agomariz
 */
class RecursionCloSpan {

    /**
     * Abstraction creator
     */
    private AbstractionCreator abstractionCreator;
    /**
     * Saver, got from Class AlgoPrefixSpan where the user has already chosen
     * where he wants to keep the results.
     */
    private Saver saver;
    /**
     * absolute minimum support.
     */
    private long minSupportAbsolute;
    /**
     * Original pseudosequence database (without infrequent items)
     */
    private PseudoSequenceDatabase pseudoDatabase;
    /**
     * Map which match the frequent items with their appearances
     */
    private Map<Item, BitSet> mapSequenceID;
    /**
     * Number of frequent items found by PrefixSpan
     */
    private int numberOfFrequentPatterns = 0;
    /**
     * Map in which we store the different patterns in order to know which ones 
     * can be skipped because can be summarized by other ones (the closed patterns)
     */
    private Map<Integer, Map<Integer, List<Entry<Pattern, Trie>>>> matchingMap;
    
    /**
     * Trie root that starts with the empty pattern and from which we will be 
     * able to access to all the frequent patterns generated by CloSpan
     */
    private Trie generalTrie;
    
    /**
     * flag to indicate if we are interesting in only finding the closed sequences
     */
    private boolean findClosedPatterns;
    /**
     * flag to indicate if we are interesting in only finding the closed sequence 
     * through the postprocessing step
     */
    private boolean executePruningMethods;

    /**
     * Standard constructor
     * @param abstractionCreator the abstraction creator
     * @param saver The saver for correctly save the results where the user wants
     * @param minSupportAbsolute The absolute minimum support
     * @param pseudoDatabase The original pseudoSequence database (without 
     * frequent items)
     * @param mapSequenceID Map which match the frequent items with their 
     * appearances
     * @param findClosedPatterns flag to indicate if we are interesting in only 
     * finding the closed sequences
     */
    public RecursionCloSpan(AbstractionCreator abstractionCreator, Saver saver, long minSupportAbsolute, PseudoSequenceDatabase pseudoDatabase, Map<Item, BitSet> mapSequenceID, boolean findClosedPatterns, boolean executePruningMethods) {
        this.abstractionCreator = abstractionCreator;
        this.minSupportAbsolute = minSupportAbsolute;
        this.pseudoDatabase = pseudoDatabase;
        this.mapSequenceID = mapSequenceID;
        this.matchingMap = new LinkedHashMap<Integer, Map<Integer, List<Entry<Pattern, Trie>>>>();
        this.generalTrie = new Trie();
        this.findClosedPatterns = findClosedPatterns;
        this.executePruningMethods=executePruningMethods;
        this.saver=saver;
    }

    /**
     * It executes the actual CloSpan Algorithm.
     * @param verbose Flag for debugging purposes
     */
    public void execute(boolean verbose) {
        //We get all the frequent items and we sort them
        List<Item> keySetList = new ArrayList<Item>(mapSequenceID.keySet());
        Collections.sort(keySetList);
        if (verbose) {
            System.out.println(keySetList.size() + " frequent items");
        }

        int numberOfFrequentItems = keySetList.size();
        int cont = 0;
        //For each frequent item
        for (Item item : keySetList) {            
            cont++;            

            if (verbose) {
                System.out.println("Projecting item = " + item + " (" + cont + "/" + numberOfFrequentItems + ")");
            }
            // We make a projection in the original database
            PseudoSequenceDatabase projectedContext = makePseudoProjections(item, pseudoDatabase, abstractionCreator.CreateDefaultAbstraction(), true);

            // And we create a new 1-pattern with that frequent item
            ItemAbstractionPair pair = new ItemAbstractionPair(item, abstractionCreator.CreateDefaultAbstraction());
            Pattern prefix = new Pattern(pair);
            //And we insert it its appearances
            prefix.setAppearingIn(mapSequenceID.get(item));
            
            //We generate a new Trie for the new prefix's children
            Trie newTrie = new Trie();
            newTrie.setAppearingIn(prefix.getAppearingIn());

            /*
             * We create a new node to insert the new prefix and we point it out 
             * to their future children
             */
            TrieNode prefixNode = new TrieNode(pair, newTrie);
            /*
             * We add the new node in the root trie
             */
            generalTrie.addNode(prefixNode);

            /*
             * We update the number of frequent patterns
             */
            if (projectedContext != null) {
                //And we call the main loop
                cloSpanLoop(prefix, prefixNode, 2, projectedContext, verbose);
            }
        }
    }

    /**
     * Get the frequent patterns by means of executing a preorder traversal
     * in the general trie
     * @return the list of frequent patterns.
     */
    public List<Pattern> getFrequentPatterns() {
        List<Pattern> result;
        result = generalTrie.preorderTraversal(null);
        return result;
    }

    /**
     * It projects the database given as parameter
     * @param item The item from which we make the projection
     * @param database The database where we make the projection
     * @param firstTime Flag that points out if it the first time that
     * @return The new projected database
     */
    private PseudoSequenceDatabase makePseudoProjections(Item item, PseudoSequenceDatabase database, Abstraction_Generic abstraccion, boolean firstTime) {
        // The projected pseudo-database
        PseudoSequenceDatabase newProjectedDatabase = new PseudoSequenceDatabase();
        //Counter of number of projections done
        int numberOfProjectionsSum = 0;
        /* 
         * Counter of number of itemsets appearing in the projected database, 
         * only taking into account the appearing in the first projected database
         */
        int cumulativeSum = 0;
        //Counter of all the elements of the projected database
        int totalElementInProjectedDatabase = 0;
        /*
         * string for keeping all the element appearing in each projection, one 
         * by one, concatenating all those values
         */
        StringBuilder sb = new StringBuilder();

        List<PseudoSequence> pseudoSequences = database.getPseudoSequences();

        for (int sequenceIndex = 0; sequenceIndex < pseudoSequences.size(); sequenceIndex++) { // for each sequence
            PseudoSequence sequence = pseudoSequences.get(sequenceIndex);            
            /*Flag indicating if the current sequence has already been projected
             * for the new projected database
             */
            boolean alreadyProjected = false;
            //Initialization of the new projected sequence for the current one
            PseudoSequence newSequence = null;
            //Initialization of the number of projections done in the current sequence
            int numberOfProjections = 0;
            //Set keeping the projections already done
            Set<Integer> projectionsAlreadyMade = new HashSet<Integer>();
            //For all the existing projections in the current sequence
            for (int k = 0; k < sequence.numberOfProjectionsIncluded(); k++) {
                int sequenceSize = sequence.size(k);
                // for each itemset of the sequence
                for (int i = 0; i < sequenceSize; i++) {
                    // we get the index ofthe given item to project in current the itemset
                    int index = sequence.indexOf(k, i, item);
                    //If the item has been found and either is the first projection or the method compute is true
                    if (index != -1 && (firstTime || (abstraccion.compute(sequence, k, i)))) {
                        int itemsetSize = sequence.getSizeOfItemsetAt(k, i);
                        // if the found item is not the last item of the itemset
                        if (index != itemsetSize - 1) {
                            //If this sequence has not been yet projected
                            if (!alreadyProjected) {
                                //A new pseudosequence is created starting from the next point to the found item
                                newSequence = new PseudoSequence(sequence.getRelativeTimeStamp(i, k), sequence, i, index + 1, k);
                                //We keep the projection point
                                projectionsAlreadyMade.add(sequence.getFirstItemset(k) + i);
                                //If the new pseudosequence has more than one item
                                if (newSequence.size(numberOfProjections) > 0) {
                                    //we increase the number of projections
                                    numberOfProjections++;
                                    //And we add the new projected sequence to the new database
                                    newProjectedDatabase.addSequence(newSequence);
                                    //Finally we update all the counters used as hash keys in the pruning methods
                                    cumulativeSum += newSequence.size(0);
                                    int remainingElements = newSequence.length(newSequence.numberOfProjectionsIncluded() - 1);
                                    totalElementInProjectedDatabase += remainingElements;
                                    sb.append(remainingElements);
                                }
                                /*We set the flag to true, indicating that the 
                                 * current sequence has been already projected
                                 */
                                alreadyProjected = true;
                            } else {
                                /*If the sequence is already projected and the 
                                projection point has not been previously used*/
                                if (projectionsAlreadyMade.add(sequence.getFirstItemset(k) + i)) {
                                    /*We make another projection in the same 
                                     * sequence previously projected, adding a 
                                     * new projection point*/
                                    newSequence.addProjectionPoint(k, sequence.getRelativeTimeStamp(i, k), sequence, i, index + 1);
                                    //Finally we update all the counters used as hash keys in the pruning methods
                                    cumulativeSum += newSequence.size(newSequence.numberOfProjectionsIncluded()-1);
                                    int remainingElements = newSequence.length(newSequence.numberOfProjectionsIncluded() - 1);
                                    totalElementInProjectedDatabase += remainingElements;
                                    sb.append(remainingElements);
                                }
                            }
                            /* if the found item is the last item of the sequence
                             * and the item where it is, it is not the last itemset
                             * of the sequence*/
                        } else if ((i != sequenceSize - 1)) {
                            //and has not been yet projected
                            if (!alreadyProjected) {
                                /*We create a new projected sequence starting 
                                 * in the next itemset to where the item appeared*/
                                newSequence = new PseudoSequence(sequence.getRelativeTimeStamp(i, k), sequence, i + 1, 0, k);
                                //And we count the projection
                                projectionsAlreadyMade.add(sequence.getFirstItemset(k) + i);
                                //If there is any item in the new sequence
                                if (itemsetSize > 0 && newSequence.size(numberOfProjections) > 0) {
                                    //we increase the number of projections
                                    numberOfProjections++;
                                    //And we add the new projected sequence to the new database
                                    newProjectedDatabase.addSequence(newSequence);
                                    //Finally we update all the counters used as hash keys in the pruning methods
                                    cumulativeSum += newSequence.size(0);
                                    int remainingElements = newSequence.length(newSequence.numberOfProjectionsIncluded() - 1);
                                    totalElementInProjectedDatabase += remainingElements;
                                    sb.append(remainingElements);
                                }
                                /*We set the flag to true, indicating that the 
                                 * current sequence has been already projected
                                 */
                                alreadyProjected = true;
                            } else {
                                /*If the sequence is already projected and the 
                                projection point has not been previously used*/
                                if (projectionsAlreadyMade.add(sequence.getFirstItemset(k) + i)) {
                                    /*We make another projection in the same 
                                     * sequence previously projected, adding a 
                                     * new projection point*/
                                    newSequence.addProjectionPoint(k, sequence.getRelativeTimeStamp(i, k), sequence, i + 1, 0);
                                    //Finally we update all the counters used as hash keys in the pruning methods
                                    cumulativeSum += newSequence.size(newSequence.numberOfProjectionsIncluded()-1);
                                    int remainingElements = newSequence.length(newSequence.numberOfProjectionsIncluded() - 1);
                                    totalElementInProjectedDatabase += remainingElements;
                                    sb.append(remainingElements);
                                }
                            }
                        }
                    }
                }
            }
            if (newSequence != null) {
                numberOfProjectionsSum += newSequence.numberOfProjectionsIncluded();
            }
        }
        //We set all the counter values
        newProjectedDatabase.setCumulativeSum(cumulativeSum);
        newProjectedDatabase.setCumulativeSumNumberOfProjections(numberOfProjectionsSum);
        newProjectedDatabase.setNumberOfElementsProjectedDatabase(totalElementInProjectedDatabase);
        newProjectedDatabase.setElementsProjectedDatabase(sb.toString());
        return newProjectedDatabase;
    }

    /**
     * Method that executes the main loop of prefixSpan for all the patterns
     * with a size greater than 1
     * @param prefix prefix from which we made the projected database and where
     * the frequent items that we find will be added
     * @param prefixNode trieNode of the prefix in order to add the frequent 
     * items as its children
     * @param k size of patterns that are going to be generated
     * @param context prefix-projected databases
     * @param verbose flag for debuggin purposes
     */
    private void cloSpanLoop(Pattern prefix, TrieNode prefixNode, int k, PseudoSequenceDatabase context, boolean verbose) {
        //If we are interested in closed patterns        
        if (findClosedPatterns && executePruningMethods) {
            /* We check if the current prefix has been previously generated by 
             * means of a shorter or larger pattern
             */
            if (pruneByCheckingProjectedDBSize(prefix, context, prefixNode)) {
                //If we processed it before, we stop this branch of the search tree
                return;
            }
        }

        //We get the trie connected to prefix in its prefix node
        Trie currentTrie = prefixNode.getChild();
        //and update the number of frequent patterns
        numberOfFrequentPatterns++;

        /* If the current projected database has less than minSupport sequences,
         * we also stop the current branch of the search tree
         */
        if (context == null || context.size() < minSupportAbsolute) {
            return;
        }

        // we find frequent items that appear in the given pseudosequence database.
        Set<Pair> pairs = abstractionCreator.findAllFrequentPairs(context.getPseudoSequences());
        if (verbose) {
            StringBuilder tab = new StringBuilder();
            for (int i = 0; i < k - 2; i++) {
                tab.append('\t');
            }
            System.out.println(tab + "Projecting prefix = " + prefix);
            System.out.print(tab + "\tFound " + pairs.size() + " frequent items in this projection\n");
        }
        // For each pair found,
        for (Pair pair : pairs) {
            // if the item is frequent.
            if (pair.getSupport() >= minSupportAbsolute) {
                // create the new pattern
                Pattern newPrefix = prefix.clonePatron();
                ItemAbstractionPair newPair = ItemAbstractionPairCreator.getInstance().getItemAbstractionPair(pair.getPar().getItem(), abstractionCreator.createAbstractionFromAPrefix(prefix, pair.getPar().getAbstraction()));
                newPrefix.add(newPair);
                // build the projected database with respect to this frequent item (the item which forms the prefix)
                PseudoSequenceDatabase projection = makePseudoProjections(pair.getPar().getItem(), context, pair.getPar().getAbstraction(), false);

                //If the projection exists and has more sequences than the absolute minimum support
                if (projection != null) {
                    /*We make a new trie that will be connected to the current 
                    prefix and where its childrens will appear*/
                    Trie newTrie = new Trie();
                    //And we put its appearance list
                    newTrie.setAppearingIn(pair.getSequencesID());

                    //We create a new node that associate the new pair with the new trie
                    TrieNode newNodoPrefix = new TrieNode(newPair, newTrie);
                    //And we add it as a node in the current trie of the current prefix
                    currentTrie.addNode(newNodoPrefix);
                    //We make a recursive call to the main method
                    cloSpanLoop(newPrefix, newNodoPrefix, k + 1, projection, verbose); // r�cursion
                }
            }
        }
    }

    /**
     * It returns the number of frequent patterns
     * @return  the number of frequent patterns.
     */
    public int numberOfFrequentPatterns() {
        return numberOfFrequentPatterns;
    }

    /**
     * It clears the attributes of this class
     */
    public void clear() {
        if (pseudoDatabase != null) {
            pseudoDatabase.clear();
            pseudoDatabase = null;
        }
        if (mapSequenceID != null) {
            mapSequenceID.clear();
            mapSequenceID = null;
        }
        if (matchingMap != null) {
            matchingMap.clear();
            mapSequenceID = null;
        }
        if (generalTrie != null) {
            generalTrie.removeAll();
        }
    }

    /**
     * Method that checks if the prefix given as parameter can be skipped 
     * by means of prune methods backward subpattern or backward superpattern.
     * The method uses a map where the different patterns are kept in order to check both pruning methods.
     * The hash keys used can vary, and we give some approaches by the methods:
     * 
     * key_standard()
     * key_standardAndSupport()
     * key_standardAndSumIDs()
     * key_standardAndCumulativeSum()
     * Key_standardAndElements()
     * 
     * @param prefix Current pattern which is going to be checked
     * @param projection Projected database associated with prefix
     * @param trieNode Trie node associated with prefix
     * @return 
     */
    private boolean pruneByCheckingProjectedDBSize(Pattern prefix, PseudoSequenceDatabase projection, TrieNode trieNode) {
        //We get the trie associated with the current prefix
        Trie prefixTrie = trieNode.getChild();
        //We get the support of the pattern        
        int support = prefixTrie.getSupport();

        /* We get as a first key the sum of all sequences identifiers where the 
         * current prefix appear
         */
        int key1 = prefixTrie.getSumIdSequences();
        int prefixSize = prefix.size();
        
        /*
         * Different approaches for the key2, we use that one that is the sum of
         * the total of elements appearing in the projected database with the support
         */
        //int key2 = RecursionCloSpan.key_standardAndSumIDs(projection, prefixTrie);
        //int key2 = RecursionCloSpan.key_standardAndCumulativeSum(projection, prefixTrie);
        int key2 = RecursionCloSpan.key_standardAndSupport(projection, prefixTrie);
        //int key2=RecursionCloSpan.key_standard(projection);
        //int key2=RecursionCloSpan.Key_standardAndElements(projection,prefixTrie);
        
        
        /* 
         * Map where there appear all the patterns with the same key1 of the 
         * current prefix, that makes a correspondence between a value given by
         * key2 and all the patterns that have it
         */
        Map<Integer, List<Entry<Pattern, Trie>>> associatedMap = matchingMap.get(key1);
        /* 
         * We make a new entry associating the current prefix with its 
         * corresponding prefixTrie
         */
        //Entry<Pattern, Trie> newEntry = new PatternTrieEntry<Pattern, Trie>(prefix, prefixTrie);
        Entry<Pattern, Trie> newEntry = new AbstractMap.SimpleEntry<Pattern, Trie>(prefix, prefixTrie);
        /* If there is not any pattern with the same key2 value, we add the current
         * prefix as a new entry, and we also insert it in the matching map
         */
        if (associatedMap == null) {
            associatedMap = new LinkedHashMap<Integer, List<Entry<Pattern, Trie>>>();
            List entryList = new ArrayList<Entry<Pattern, Trie>>();
            entryList.add(newEntry);
            associatedMap.put(key2, entryList);
            matchingMap.put(key1, associatedMap);
        } else {
            /* 
             * If, conversely, there are some patterns with the same key2 value 
             * (and extensively with the same key1 value) we check if we can apply
             * backward subpattern or backward superpattern pruning
             */
            
            //We get the list of entries
            List<Entry<Pattern, Trie>> associatedList = associatedMap.get(key2);
            //If is still empty, we create one
            if (associatedList == null) {
                associatedList = new ArrayList<Entry<Pattern, Trie>>();
                associatedList.add(newEntry);
                associatedMap.put(key2, associatedList);
            } else {
                int i;
                int superPattern = 0;
                for (i = 0; i < associatedList.size(); i++) {
                    //For all the elements of the associated list
                    Entry<Pattern, Trie> storedEntry = associatedList.get(i);

                    //We get both pattern and trie from the entry
                    Pattern p = storedEntry.getKey();
                    Trie t = storedEntry.getValue();
                    //We keep the size of the pattern
                    int pSize = p.size();
                    //If the support of the current prefix and the p pattern are equal
                    if (support == t.getSupport()) {
                        if (pSize != prefixSize) {
                            //if the prefix size is less than the size of p
                            if (prefixSize < pSize) {
                                //and prefix is a subpattern of p
                                if (prefix.isSubpattern(abstractionCreator, p)) {
                                    /* 
                                     * We execute backward subpattern pruning and 
                                     * establish as new nodes the nodes of the trie
                                     * of p
                                     */
                                    prefixTrie.setNodes(t.getNodes());
                                    /*
                                     * We end the method since we have already 
                                     * done the prune
                                     */
                                    return true;
                                }
                            } else if (p.isSubpattern(abstractionCreator, prefix)) {
                                /*
                                 * if, conversely, the prefix size is greater than 
                                 * the size of p and prefix is a superpattern of p
                                 */
                                
                                //we update a counter of superpatterns
                                superPattern++;
                                /* 
                                 * and we make the prefix trie point to the nodes
                                 * of the trie of p
                                 */
                                prefixTrie.setNodes(t.getNodes());
                                /*
                                 * and we make null the nodes of t since p is 
                                 * included in prefix
                                 */
                                //t.setNodes(null);
                                //And we remove the entry of the list
                                associatedList.remove(i);
                                i--;
                            }
                        }
                    }
                }
                //In this point we add the new entry of the current prefix
                associatedList.add(newEntry);
                //If we found any superPattern
                if (superPattern > 0) {
                    /*if (superPattern > 1) {
                    System.out.println("We removed more than one pattern!!");
                    }*/
                    //We return the correspondent output
                    return true;
                }
            }
        }
        /* 
         * We did not find any subpattern or supperpattern in order to skip the 
         * generation of the current prefix
         */
        return false;
    }

    /**
     * One of the methods used by key2 in the method pruneByCheckingProjectedDBSize 
     * that return the number of elements that appear in the projected
     * database
     * @param projection projected database of the prefix to consider
     * @return 
     */
    private static int key_standard(PseudoSequenceDatabase projection) {
        return projection.getNumberOfElementsProjectedDatabase();
    }

    /**
     * One of the methods used by key2 in the method pruneByCheckingProjectedDBSize 
     * that return the addition of the number of elements that appear in 
     * the projected database and the support of the related prefix
     * @param projection projected database of the prefix to consider
     * @param prefix prefix to consider
     * @return 
     */
    private static int key_standardAndSupport(PseudoSequenceDatabase projection, Trie prefix) {
        return projection.getNumberOfElementsProjectedDatabase() + prefix.getSupport();
    }

    /**
     * One of the methods used by key2 in the method pruneByCheckingProjectedDBSize 
     * that return the addition of the number of elements that appear in 
     * the projected database and sum of the sequence identifiers where 
     * the given prefix appears
     * @param projection projected database of the prefix to consider
     * @param prefix prefix to consider
     * @return 
     */
    private static int key_standardAndSumIDs(PseudoSequenceDatabase projection, Trie prefix) {
        return (projection.getNumberOfElementsProjectedDatabase() + prefix.getSumIdSequences());
    }

    /**
     * One of the methods used by key2 in the method pruneByCheckingProjectedDBSize 
     * that return the addition of the number of elements that appear in 
     * the projected database and sum of itemsets that appear in all the 
     * projection of every sequence where the given prefix appears
     * @param projection projected database of the prefix to consider
     * @param prefix prefix to consider
     * @return 
     */
    private static int key_standardAndCumulativeSum(PseudoSequenceDatabase projection, Trie prefix) {
        int key = projection.getNumberOfElementsProjectedDatabase();
        key += projection.getCumulativeSum();
        return key;
    }
    
    /**
     * One of the methods used by key2 in the method pruneByCheckingProjectedDBSize 
     * that return the addition of the number of elements that appear in 
     * the projected database and sum of itemsets that appear in the first 
     * projection point of every sequence where the given prefix appears
     * @param projection projected database of the prefix to consider
     * @param prefix prefix to consider
     * @return 
     */
    private static int Key_standardAndElements(PseudoSequenceDatabase projection, Trie prefix) {
        int key = projection.getNumberOfElementsProjectedDatabase();
        key += projection.getElementsProjectedDatabase();
        return key;
    }
    
    
    
    /**
     * It removes the non closed patterns from the list of patterns given
     * as parameter
     * @param frequentPatterns List of patterns from which we want to remove the
     * non-closed patterns
     * @param keepPatterns Flag indicating if we want to keep the final output
     */
    void removeNonClosedPatterns(List<Pattern> frequentPatterns, boolean keepPatterns) {
        System.err.println("Before removing NonClosed patterns there are " + numberOfFrequentPatterns + " patterns");
        numberOfFrequentPatterns = 0;
        /* 
         * We make a map to match group of patterns linked by their addition of 
         * sequence identifiers
         */
        Map<Integer, List<Pattern>> totalPatterns = new LinkedHashMap<Integer, List<Pattern>>();
        //and we classify the patterns there by their sumIdSequences number
        for (Pattern p : frequentPatterns) {
            List<Pattern> patternList = totalPatterns.get(p.getSumIdSequences());
            if (patternList == null) {
                patternList = new ArrayList<Pattern>();
                totalPatterns.put(p.getSumIdSequences(), patternList);
            }
            patternList.add(p);
        }

        //For all the list associated with de different sumSequencesIDs values
        for (List<Pattern> list : totalPatterns.values()) {
            //For all their patterns
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    Pattern p1 = list.get(i);
                    Pattern p2 = list.get(j);
                    //If the patterns has the same support
                    if (p1.getSupport() == p2.getSupport()) {
                        if (p1.size() != p2.size()) {
                            /* 
                             * And one is subpattern of the other, we remove the 
                             * shorter pattern and keep the longer one
                             */
                            if (p1.size() < p2.size()) {
                                if (p1.isSubpattern(abstractionCreator, p2)) {
                                    list.remove(i);
                                    i--;
                                    break;
                                }
                            } else {
                                if (p2.isSubpattern(abstractionCreator, p1)) {
                                    list.remove(j);
                                    j--;
                                }
                            }
                        }
                    }
                }
            }
        }
        /*
         * We calcule the number of frequent patterns and we store in the chosen 
         * output if the flag is activated
         */
        for (List<Pattern> list : totalPatterns.values()) {
            numberOfFrequentPatterns += list.size();
            if (keepPatterns) {
                for (Pattern p : list) {
                    saver.savePattern(p);
                }
            }
        }
    }
}

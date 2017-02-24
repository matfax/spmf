package ca.pfv.spmf.test;


import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.AlgoCMSPADE;
import ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.candidatePatternsGeneration.CandidateGenerator;
import ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.candidatePatternsGeneration.CandidateGenerator_Qualitative;
import ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.dataStructures.creators.AbstractionCreator;
import ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.dataStructures.creators.AbstractionCreator_Qualitative;
import ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.dataStructures.database.SequenceDatabase;
import ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.idLists.creators.IdListCreator;
import ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.idLists.creators.IdListCreator_FatBitmap;
import org.junit.Test;

/**
 * Example of how to use the algorithm CMSPADE, saving the results in the
 * main  memory
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestCMSPADE_saveToMemory {

    /**
     */
    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Load a sequence database
            double support = 0.50;

            boolean keepPatterns = true;
            boolean verbose = false;

            AbstractionCreator abstractionCreator = AbstractionCreator_Qualitative.getInstance();
            boolean dfs = true;

            // if you set the following parameter to true, the sequence ids of the sequences where
            // each pattern appears will be shown in the result
            boolean outputSequenceIdentifiers = false;

            IdListCreator idListCreator = IdListCreator_FatBitmap.getInstance();

            CandidateGenerator candidateGenerator = CandidateGenerator_Qualitative.getInstance();

            SequenceDatabase sequenceDatabase = new SequenceDatabase(abstractionCreator, idListCreator);

            sequenceDatabase.loadFile("contextPrefixSpan.txt", support);

            System.out.println(sequenceDatabase.toString());

            AlgoCMSPADE algorithm = new AlgoCMSPADE(support, dfs, abstractionCreator);

            algorithm.runAlgorithm(sequenceDatabase, candidateGenerator, keepPatterns, verbose, null, outputSequenceIdentifiers);
            System.out.println("Minimum relative support = " + support + "  minimum absolute support: " + algorithm.minSupAbsolute);
            System.out.println(algorithm.getNumberOfFrequentPatterns() + " frequent patterns.");

            System.out.println(algorithm.printStatistics());
        });
    }
}

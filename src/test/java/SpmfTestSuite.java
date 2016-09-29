import ca.pfv.spmf.test.MainTestTRuleGrowth;
import ca.pfv.spmf.test.MainTest_estDec_saveToFile;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MainTest_estDec_saveToFile.class,
        MainTestTRuleGrowth.class
})
public class SpmfTestSuite {
    //normally, this is an empty class
}
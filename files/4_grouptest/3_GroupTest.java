package junit.grouptest;

import org.apache.log4j.Logger;
import junit.framework.*;

/**
 * Group Test provides an Object Oriented way in which many similar tests can
 * be called programatically (without writing a separate test method for each one).
 * I wrote this in order to test a server that had 500 similar XML testcases.
 * <br>created on 3/11/2003, last changed on $Date: 2003/12/11 00:10:36 $
 * <br>$Header: /data/cvsroot/ged-foundation/ged-foundation/src/java/com/db/foundation/test/GroupTest.java,v 1.1 2003/12/11 00:10:36 wilandre Exp $
 * @author Andrew Wilson, last changed by $Author: wilandre $
 * @version $Revision: 1.1 $
 */
public class GroupTest {
    private static final Logger sLogger = Logger.getLogger(GroupTest.class);

    private static int counter = 0;    // A counter for which test we are on.
    private static GroupTestable suit; // The test suite we are testing.

    public static Test getTestSuite(GroupTestable mySuit) {
        suit = mySuit;
        TestSuite ts = new TestSuite();
        int noOfTests = suit.getNumberOfTests();
        for(int i=0;i<noOfTests;i++) {
            ts.addTestSuite(GenericTestCase.class);
        }
        GroupTest.counter = 0;  // reset counter for running tests.
        return ts;
    }

    public static class GenericTestCase extends TestCase {
        /**
         * Constructor with correct name.
         */
        public GenericTestCase(String name) throws Exception {
            super(suit.getTestName(counter++));
         }

        /**
         * Override the run test method to call our test method.
         */
        public void runTest() throws Exception {
            try {
                String description = "test #" + counter + " : " + suit.getTestName(counter);
                sLogger.info("Running " + description);
                suit.runTest(counter++);
                sLogger.info("Succeeded for " + description);
            } finally {
                // See whether we should shut down for the last test.
                if(counter == suit.getNumberOfTests() ) {
                    sLogger.info("Calling tearDown");
                    suit.tearDown();
                }
            }
        }

        /**
         * Dummy to trick JUnit
         */
        public void testDummy() throws Exception {
            throw new Exception("This should not be called - it is a dummy method required to fool JUnit");
        }
    }
}

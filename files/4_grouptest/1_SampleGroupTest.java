package junit.grouptest;

import junit.framework.*;
import org.apache.log4j.Logger;

/**
 * Example implementation of the GenericTestSuite.
 * <ol>
 * <li>Test 1 will Pass
 * <li>Test 2 will Fail (Exception)
 * <li>Test 3 will Assert
 * </ol>
 * Real examples would probably parse a custom XML file, array or similar to define the tests.
 * <br>created on 3/11/2003, last changed on $Date: 2003/12/11 00:10:36 $
 * <br>$Header: /data/cvsroot/ged-foundation/ged-foundation/src/java/com/db/foundation/test/SampleGroupTest.java,v 1.1 2003/12/11 00:10:36 wilandre Exp $
 * @author Andrew Wilson, last changed by $Author: wilandre $
 * @version $Revision: 1.1 $
 */
public class SampleGroupTest extends TestSuite implements GroupTestable {
    private static final Logger sLogger = Logger.getLogger(SampleGroupTest.class);

    /** This is the method that JUnit will look for to run the tests */
    public static Test suite() {
        GroupTestable gt = new SampleGroupTest();
        return GroupTest.getTestSuite(gt);
    }

    /** Do some useful set up here */
    private SampleGroupTest() {
        sLogger.info("Calling constructor");
    }

    /** Normally this would get the number of tests from an XML file or array */
    public int getNumberOfTests() { return 3; }

    /** Normally this would get the name of tests from an XML file or array */
    public String getTestName(int i) { return "Test " + i; }

    /** Run each of the tests */
    public void runTest(int i) throws Exception {
        sLogger.info("Running test # " + i);
        if(i==0) {
            // Pass the first test.
        } else if(i==1) {
            throw new RuntimeException("Exception");
        } else if(i==2) {
            Assert.fail("Problem");
        }
    }

    /** Do some useful tear down here */
    public void tearDown() {
        sLogger.info("Calling tearDown");
    }
}
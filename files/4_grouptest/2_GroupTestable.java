package junit.grouptest;

/**
 * The interface that needs to be implemented by a Generic Test Suite
 * Setup information can be put in the constructor of the implementing class
 * <br>created on 3/11/2003, last changed on $Date: 2003/12/11 00:10:36 $
 * <br>$Header: /data/cvsroot/ged-foundation/ged-foundation/src/java/com/db/foundation/test/GroupTestable.java,v 1.1 2003/12/11 00:10:36 wilandre Exp $
 * @author Andrew Wilson, last changed by $Author: wilandre $
 * @version $Revision: 1.1 $
 */
public interface GroupTestable {
    /**
     * Find out how many tests there are.
     * Usually this would look at an array or an XML file to count the tests.
     * @return How many tests there are
     */
    public int getNumberOfTests();

    /**
     * Get the name of a particular test.
     * Again this would look at an array or an XML file to get the test name.
     * @param testNo Get the name of the test with the given number
     */
    public String getTestName(int testNo);

    /**
     * Run a particular test, this would probably be getting data out of an
     * array or an XML file to run the particular test.
     * @param testNo The number of the test to run
     */
    public void runTest(int testNo) throws Exception;

    /**
     * Do any tear down work required by the tests, setup can be done in the
     * constructor of the implementing class.
     * You could decide in the constructor what work to do at this point.
     */
    public void tearDown();
}

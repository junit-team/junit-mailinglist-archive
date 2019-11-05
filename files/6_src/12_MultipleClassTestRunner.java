package junit.textui;

import junit.textui.TestRunner;
import junit.framework.*;
import junit.runner.*;
import java.util.Vector;
import java.util.Enumeration;

/**
 * A command line based tool to run tests.
 * <pre>
 * java junit.textui.MultipleClassTestRunner [-wait] TestCaseClass+
 * </pre>
 * Same as the standard textui but handles multiple TestCaseClasses
 * TestRunner expects the name of a TestCase class as argument.
 * If this class defines a static <code>suite</code> method it 
 * will be invoked and the returned test is run. Otherwise all 
 * the methods starting with "test" having no arguments are run.
 * <p>
 * When the wait command line argument is given TestRunner
 * waits until the users types RETURN.
 * <p>
 * TestRunner prints a trace as the tests are executed followed by a
 * summary at the end. 
 */
public class MultipleClassTestRunner extends TestRunner 
{

	public MultipleClassTestRunner() {
		super(); 
	}

	/**
	 * Starts a test run. Analyzes the command line arguments
	 * and runs the given test suite.
	 */
	protected TestResult start(String args[]) throws Exception {
		Vector testCase= new Vector();
		boolean wait= false;
		
		for (int i= 0; i < args.length; i++) {
			if (args[i].equals("-wait"))
				wait= true;
			else if (args[i].equals("-c")) 
				testCase.add(extractClassName(args[++i]));
			else if (args[i].equals("-v"))
				System.out.println("JUnit "+Version.id()+" by Kent Beck and Erich Gamma");
			else {
   				for (; i<args.length; i++) {
					testCase.add(extractClassName(args[i]));
				}
			}
		}
		
		if (testCase.isEmpty()) 
			throw new Exception("Usage: MultipleClassTestRunner [-wait] testCaseName+, where name is the name of the TestCase class");

		try {
			TestSuite suite= new TestSuite();
     		for (Enumeration e= testCase.elements() ; e.hasMoreElements() ;) {
				suite.addTest(getTest(e.nextElement()));
			}
			return doRun(suite, wait);
		}
		catch(Exception e) {
			throw new Exception("Could not create and run test suite: "+e);
		}
	}
		
	public static void main(String args[]) {
		TestRunner aTestRunner= new MultipleClassTestRunner();
		try {
			TestResult r= aTestRunner.start(args);
			if (!r.wasSuccessful()) 
				System.exit(-1);
			System.exit(0);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			System.exit(-2);
		}
	}
}



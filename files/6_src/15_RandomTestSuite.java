package com.modeln.junitext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;

/**
 * A RandomTestSuite is a TestSuite that randomly shuffles all tests each
 * time it is run.  It requires JDK 1.2.  
 * <p>
 * There are two primary ways to use it--either replace your existing uses
 * of "TestSuite" with "RandomTestSuite", or create your TestSuite just as
 * before and then pass it to the RandomTestSuite constructor.  Either way
 * results in the same behavior.
 * <p>
 * This class was written by <a href="http://www.modeln.com">Model N, Inc.</a>
 * You may use it and modify it any way you wish--but please leave this 
 * message intact.
 *
 * @author Kevin Bourrillion 
 * &lt;<a href="mailto:kevinb@modeln.com">kevinb@modeln.com</a>&gt;
 */
public class RandomTestSuite extends TestSuite {

    /**
     * We must keep a redundant list of the tests since the superclass'
     * list is private.
     */
    protected List/*Test*/ _tests = new ArrayList();

    /**
     * Constructs an empty suite.
     */
    public RandomTestSuite() {
    }

    /**
     * Constructs an empty suite with the given name.
     */
    public RandomTestSuite(String name) {
        super(name);
    }

    /**
     * Constructs a suite from the given class. Adds all the methods 
     * starting with "test" as test cases to the suite.
     */
    public RandomTestSuite(Class theClass) {
        this();
        addTestSuite(theClass);
    }
    
    /**
     * Shortcut for new RandomTestSuite().addTest(test).
     */
    public RandomTestSuite(Test test) {
        this();
        addTest(test);
    }

    // override junit method
    public void addTest(Test test) {
        super.addTest(test);
        extractTests(test);
    }
    
    /**
     * Recursively descends a TestSuite hierarchy in this test object and
     * pulls out all "leaf-level" tests, adding them to our local list of
     * tests to run.  Any test that is not a direct instance of TestSuite, 
     * will be left intact and will not be descended into.
     */
    private void extractTests(Test test) {
        // we don't want to mess up ActiveTestSuites and so forth
        if (test.getClass().equals(TestSuite.class) ||
            test.getClass().equals(RandomTestSuite.class))
        {
            Enumeration e = ((TestSuite)test).tests();
            while (e.hasMoreElements()) {
                extractTests((Test)e.nextElement());
            }
        } else {
            _tests.add(test);
        }
    }

    // override junit method
    public void run(TestResult result) {
        Collections.shuffle(_tests); // the whole point of this entire class!
        Iterator it = _tests.iterator();
        while (it.hasNext()) {
            Test test= (Test)it.next();
            if (result.shouldStop()) {
                break;
            }
            runTest(test, result);
        }
    }
}


package com.modeln.junitext.test;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import com.modeln.junitext.RetriedAssert;

public class RetriedAssertTest extends TestCase {

    /** Standard constructor. */
    public RetriedAssertTest(String name) {
        super(name);
    }

    /** Returns all tests. */
    public static Test suite() {
        return new TestSuite(RetriedAssertTest.class);
    }

    /** Tests RetriedAssert succeeding on the first try. */
    public void testSucceedOneTry() throws Exception {
        final Counter c = new Counter();
        new RetriedAssert(5000, 250) {
            public void run() throws Exception {
                c.value++;
                assert(true);
            }
        }.start();
        assertEquals("Number of tries", 1, c.value);
    }

    /** Tests RetriedAssert succeeding after several tries. */
    public void testSucceedSeveralTries() throws Exception {
        final long start = System.currentTimeMillis();
        new RetriedAssert(5000, 250) {
            public void run() throws Exception {
                assert(System.currentTimeMillis() - start > 2000);
            }
        }.start();
    }

    /** Tests RetriedAssert failing after all tries. */
    public void testFailure() throws Exception {
        final Counter c = new Counter();
        try {
            new RetriedAssert(5000, 250) {
                public void run() throws Exception {
                    c.value++;
                    fail();
                }
            }.start();
        } catch (AssertionFailedError e) {
            // good, it failed
            assert("Should have tried at least 10 times", c.value > 10);
            return;
        }
        fail("Inner assertion failure not propagated");
    }

    /** Tests RetriedAssert failing immediately on an error. */
    public void testError() throws Exception {
        final Counter c = new Counter();
        try {
            new RetriedAssert(5000, 250) {
                public void run() throws Exception {
                    c.value++;
                    throw new IllegalArgumentException(); // pick one
                }
            }.start();
        } catch (IllegalArgumentException e) {
            // good, it failed
            assertEquals("Number of tries", 1, c.value);
            return;
        }
        fail("Inner exception not propagated");
    }

    /** Dumb trick to get around the "final" restriction. */
    private static class Counter {
        int value = 0;
    }
}

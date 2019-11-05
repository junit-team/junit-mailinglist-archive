package junit.samples;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.extensions.ExceptionTestCase;
import java.util.NoSuchElementException;
import java.util.Collection;
import java.util.Vector;

/** Demonstration of ExceptionTestCase use. This sample tests
 * Vector class behaviour in exceptional situations, like
 * removing from empty vector.
 */
public class VectorExceptionTest extends ExceptionTestCase {
    
    VectorExceptionTest(String name, Class exception) {
        super(name, exception);
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new VectorExceptionTest("testConstructorNegativeCapacity",
        IllegalArgumentException.class));
        suite.addTest(new VectorExceptionTest("testConstructorNullCollection",
        NullPointerException.class));
        suite.addTest(new VectorExceptionTest("testSetSizeNegative", 
        ArrayIndexOutOfBoundsException.class));
        suite.addTest(new VectorExceptionTest("testIndexOfNegativeStart",
        IndexOutOfBoundsException.class));
        suite.addTest(new VectorExceptionTest("testFirstElementOfEmpty",
        NoSuchElementException.class));
        suite.addTest(new VectorExceptionTest("testToArrayNonHomogeneous1",
        ArrayStoreException.class));
        suite.addTest(new VectorExceptionTest("testToArrayNonHomogeneous2",
        ArrayStoreException.class));
        suite.addTest(new VectorExceptionTest("testToArrayNull",
        NullPointerException.class));
        suite.addTest(new VectorExceptionTest("testRemoveFromEmpty",
        ArrayIndexOutOfBoundsException.class));
        
        return suite;
    }
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
    
    public void testConstructorNegativeCapacity() {
        Vector negative = new Vector(-1);
    }
    
    public void testConstructorNullCollection() {
        Collection nullCollection = null;
        Vector fromCollection = new Vector(nullCollection);
    }
    
    public void testSetSizeNegative() {
        Vector one = new Vector(1);
        one.setSize(-1);
    }
    
    public void testIndexOfNegativeStart() {
        Vector empty = new Vector();
        empty.indexOf(new Object(),-1);
    }
    
    public void testFirstElementOfEmpty() {
        Vector empty = new Vector();
        empty.firstElement();
    }
    
    public void testToArrayNonHomogeneous1() {
        Vector boolAndInt = new Vector();
        boolAndInt.add(new Boolean(true));
        boolAndInt.add(new Integer(1));
        boolAndInt.toArray(new Boolean[0]);
    }
    
    public void testToArrayNonHomogeneous2() {
        Vector boolAndInt = new Vector();
        boolAndInt.add(new Boolean(true));
        boolAndInt.add(new Integer(1));
        boolAndInt.toArray(new Integer[0]);
    }
    
    public void testToArrayNull() {
        Vector couple = new Vector();
        couple.add(new Boolean(true));
        couple.add(new Boolean(false));
        Boolean array[] = null;
        couple.toArray(array);
    }
    
    public void testRemoveFromEmpty() {
        Vector empty = new Vector();
        empty.remove(1);
    }
}

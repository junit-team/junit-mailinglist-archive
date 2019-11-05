import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestTest extends TestCase
{
    public TestTest(String s)
    {
        super(s);
    }

    public static void main(String[] args)
    {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(TestTest.class);
        suite.addTestSuite(TestTest.TestTestTest.class);
        junit.textui.TestRunner.run(suite);
    }

    public void testFoo()
    {
        assertEquals(2, 1 + 1);
    }

    public class TestTestTest extends TestCase
    {
        public TestTestTest(String s)
        {
            super(s);
        }

        public void testFoo()
        {
            assertEquals(4, 2 + 2);
        }
    }
}
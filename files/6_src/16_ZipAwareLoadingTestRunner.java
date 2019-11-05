
package junit.contrib;

import junit.util.*;
import junit.swingui.*;

public class ZipAwareLoadingTestRunner 
{
	public static void main(String[] args) {
		new junit.swingui.TestRunner().start(args, new ZipAwareReloadingTestSuiteLoader());
	}
	
static class ZipAwareReloadingTestSuiteLoader implements TestSuiteLoader
{
	public Class load(String suiteClassName) throws ClassNotFoundException {
		ZipAwareTestCaseClassLoader loader= new ZipAwareTestCaseClassLoader();
		return loader.loadClass(suiteClassName, true);
	}
	public Class reload(Class aClass) throws ClassNotFoundException {
		ZipAwareTestCaseClassLoader loader= new ZipAwareTestCaseClassLoader();
		return loader.loadClass(aClass.getName(), true);
	}
}	

static class ZipAwareTestCaseClassLoader extends TestCaseClassLoader
{
	public synchronized Class loadClass(String name, boolean resolve)
	throws ClassNotFoundException {
		
		try {
			return super.loadClass( name, resolve );
		} 
		catch ( Exception maybeInZipFile )
		{
			return findSystemClass(name);
		}
	}	
}

}
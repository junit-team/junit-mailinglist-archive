import junit.framework.Assert;
import java.io.*;

public class FileAsserts {

  private static String processOneLine( int lineNumber,
					BufferedReader expectedData,
					BufferedReader actualData )
    throws java.io.IOException {

    String problem = null;
    String expectedLine = expectedData.readLine();
    if( !actualData.ready() ){
      problem = "at line " + lineNumber + ", expected:\n" +
	expectedLine + "\n" +
	"but actual file was not ready for reading at this line.";
    }
    else{
      String actualLine = actualData.readLine();
      if( !expectedLine.equals( actualLine ) ){
	// Uh oh, they did not match.
	problem = "at line " + lineNumber + " there was a mismatch.  Expected:\n";
	int maxLen = expectedLine.length();
	if( expectedLine.length() > actualLine.length() ){
	  maxLen = actualLine.length();
	}
	int startOffset = 0;
	for( int i = 0; i < maxLen; i++ ){
	  if( expectedLine.charAt( i ) != actualLine.charAt( i ) ){
	    startOffset = i;
	    break;
	  }
	}
	problem += expectedLine.substring( startOffset ) + "\n" +
	  "actual was:\n" +
	  actualLine.substring( startOffset ) + "\n";
      }
    }
    return problem;
  }

  public static void assertEquals( BufferedReader expected,
				   BufferedReader actual ){
    Assert.assertNotNull( expected );
    Assert.assertNotNull( actual );

    String problem = null;
    try{
      int lineCounter = 0;
      while( expected.ready() && problem == null ){
	problem = processOneLine( lineCounter, expected, actual );
	lineCounter++;
      }
    }
    catch( java.io.IOException e ){
      problem = e.getMessage();
    }
    if( problem != null ){
      Assert.fail( problem );
    }
  }

  public static void assertEquals( InputStream expected, File actual ){
    Assert.assertNotNull( expected );
    Assert.assertNotNull( actual );

    Assert.assertTrue( actual.canRead() );

    int lineCounter = 0;
    String problem = null;
    BufferedReader expectedData = new BufferedReader( new InputStreamReader( expected ));
    try{
      BufferedReader actualData =
	new BufferedReader( new InputStreamReader( new FileInputStream( actual ) ) );
      assertEquals( expectedData, actualData );
    }
    catch( java.io.IOException e ){
      Assert.fail( e.getMessage() );
    }
  }

  public static void assertEquals( File expected, File actual ){
    Assert.assertNotNull( expected );
    Assert.assertNotNull( actual );

    Assert.assertTrue( expected.canRead() );
    Assert.assertTrue( actual.canRead() );

    try{
      BufferedReader expectedData =
	new BufferedReader( new InputStreamReader( new FileInputStream( expected ) ) );
      BufferedReader actualData =
	new BufferedReader( new InputStreamReader( new FileInputStream( actual ) ) );
      assertEquals( expectedData, actualData );
    }
    catch( java.io.IOException e ){
      Assert.fail( e.getMessage() );
    }
  }
}

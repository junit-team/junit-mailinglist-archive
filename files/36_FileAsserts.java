import junit.framework.Assert;
import java.io.*;

public class FileAsserts {
  
  private static String processOneLine( int lineNumber,
					BufferedReader expectedData,
					BufferedReader actualData )
    throws java.io.IOException {

    StringBuffer problem = new StringBuffer();
    String expectedLine = expectedData.readLine();
    if( !actualData.ready() ){
      problem.append( "at line " );
      problem.append( lineNumber );
      problem.append( ", expected:\n" );
      problem.append( expectedLine + "\n" );
      problem.append( "but actual file was not ready for reading at this line." );
    }
    else{
      String actualLine = actualData.readLine();
      if( !expectedLine.equals( actualLine ) ){
	// Uh oh, they did not match.
	problem.append( "at line " );
	problem.append( lineNumber );
	problem.append( " there was a mismatch, starting at the \"^\" indicator. Expected:\n" );
	problem.append( insertDiff( expectedLine, actualLine ));
      }
    }
    if( !problem.toString().equals( "" ) ){
      return problem.toString();
    }
    else{
      return null;
    }
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

  private static String carets( int startOffset, int length ){
    StringBuffer retval = new StringBuffer();
    for( int i = 0; i < startOffset; i++ ){
      retval.append( " " );
    }
    for( int i = startOffset; i < length; i++ ){
      retval.append( "^" );
    }
    retval.append( "\n" );
    
    return retval.toString();
  }

  private static String insertDiff( String expected, String actual ){
    StringBuffer problem = new StringBuffer();

    int maxLen = ( expected.length() > actual.length() ) ? 
      actual.length() : expected.length();
    int startOffset = 0;
    for( int i = 0; i < maxLen; i++ ){
      if( expected.charAt( i ) != actual.charAt( i ) ){
	startOffset = i;
	break;
      }
    }
    problem.append( "\"" + expected.substring( 0, startOffset ) + "\"" );
    problem.append( "^" );
    problem.append( "\"" + expected.substring( startOffset, expected.length() ) + "\"\n" );
    problem.append( "actual was:\n\"" );
    problem.append( actual.substring( 0, startOffset ) );
    problem.append( "\"^\"" );
    problem.append( actual.substring( startOffset, actual.length() ) + "\"\n" );
    return problem.toString();
  }
}

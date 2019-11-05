package a30app;

import java.sql.*;
import java.math.*;
import java.io.*;
import java.util.Calendar;
import java.util.Map;

/**
 * provides defaults for all methods to make implementing 
 * a MockResultSet easier.
 */
public abstract class AbstractMockResultSet implements ResultSet {
  private static final SQLException NI = new SQLException("Not Implemented");
  
  public boolean next() throws SQLException { throw NI; }
  public void close() throws SQLException { throw NI; }
  public boolean wasNull() throws SQLException { throw NI; }
  
  public String getString(int columnIndex) throws SQLException { throw NI; }
  public boolean getBoolean(int columnIndex) throws SQLException { throw NI; }
  public byte getByte(int columnIndex) throws SQLException { throw NI; }
  public short getShort(int columnIndex) throws SQLException { throw NI; }
  public int getInt(int columnIndex) throws SQLException { throw NI; }
  public long getLong(int columnIndex) throws SQLException { throw NI; }
  public float getFloat(int columnIndex) throws SQLException { throw NI; }
  public double getDouble(int columnIndex) throws SQLException { throw NI; }
  public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException { throw NI; }
  public byte[] getBytes(int columnIndex) throws SQLException { throw NI; }
  public Date getDate(int columnIndex) throws SQLException { throw NI; }
  public Time getTime(int columnIndex) throws SQLException { throw NI; }
  public Timestamp getTimestamp(int columnIndex) throws SQLException { throw NI; }
  public InputStream getAsciiStream(int columnIndex) throws SQLException { throw NI; }
  public InputStream getUnicodeStream(int columnIndex) throws SQLException { throw NI; }
  public InputStream getBinaryStream(int columnIndex) throws SQLException { throw NI; }
  
  public String getString(String columnName) throws SQLException { throw NI; }
  public boolean getBoolean(String columnName) throws SQLException { throw NI; }
  public byte getByte(String columnName) throws SQLException { throw NI; }
  public short getShort(String columnName) throws SQLException { throw NI; }
  public int getInt(String columnName) throws SQLException { throw NI; }
  public long getLong(String columnName) throws SQLException { throw NI; }
  public float getFloat(String columnName) throws SQLException { throw NI; }
  public double getDouble(String columnName) throws SQLException { throw NI; }
  public BigDecimal getBigDecimal(String columnName, int scale) throws SQLException { throw NI; }
  public byte[] getBytes(String columnName) throws SQLException { throw NI; }
  public Date getDate(String columnName) throws SQLException { throw NI; }
  public Time getTime(String columnName) throws SQLException { throw NI; }
  public Timestamp getTimestamp(String columnName) throws SQLException { throw NI; }
  public InputStream getAsciiStream(String columnName) throws SQLException { throw NI; }
  public InputStream getUnicodeStream(String columnName) throws SQLException { throw NI; }
  public InputStream getBinaryStream(String columnName) throws SQLException { throw NI; }
  
  public SQLWarning getWarnings() throws SQLException { throw NI; }
  public void clearWarnings() throws SQLException { throw NI; }
  public String getCursorName() throws SQLException { throw NI; }
  public ResultSetMetaData getMetaData() throws SQLException { throw NI; }
  public Object getObject(int columnIndex) throws SQLException { throw NI; }
  public Object getObject(String columnName) throws SQLException { throw NI; }
  public int findColumn(String columnName) throws SQLException { throw NI; }
  public Reader getCharacterStream(int columnIndex) throws SQLException { throw NI; }
  public Reader getCharacterStream(String columnName) throws SQLException { throw NI; }
  public BigDecimal getBigDecimal(int columnIndex) throws SQLException { throw NI; }
  public BigDecimal getBigDecimal(String columnName) throws SQLException { throw NI; }
  public boolean isBeforeFirst() throws SQLException { throw NI; }
  public boolean isAfterLast() throws SQLException { throw NI; }
  public boolean isFirst() throws SQLException { throw NI; }
  public boolean isLast() throws SQLException { throw NI; }
  public void beforeFirst() throws SQLException { throw NI; }
  public void afterLast() throws SQLException { throw NI; }
  public boolean first() throws SQLException { throw NI; }
  public boolean last() throws SQLException { throw NI; }
  public int getRow() throws SQLException { throw NI; }
  public boolean absolute(int row) throws SQLException { throw NI; }
  public boolean relative(int rows) throws SQLException { throw NI; }
  public boolean previous() throws SQLException { throw NI; }
  public void setFetchDirection(int direction) throws SQLException { throw NI; }
  public int getFetchDirection() throws SQLException { throw NI; }
  public void setFetchSize(int rows) throws SQLException { throw NI; }
  public int getFetchSize() throws SQLException { throw NI; }
  public int getType() throws SQLException { throw NI; }
  public int getConcurrency() throws SQLException { throw NI; }
  public boolean rowUpdated() throws SQLException { throw NI; }
  public boolean rowInserted() throws SQLException { throw NI; }
  public boolean rowDeleted() throws SQLException { throw NI; }
    
  public void updateNull(int columnIndex) throws SQLException { throw NI; }
  public void updateBoolean(int columnIndex, boolean x) throws SQLException { throw NI; }
  public void updateByte(int columnIndex, byte x) throws SQLException { throw NI; }
  public void updateShort(int columnIndex, short x) throws SQLException { throw NI; }
  public void updateInt(int columnIndex, int x) throws SQLException { throw NI; }
  public void updateLong(int columnIndex, long x) throws SQLException { throw NI; }
  public void updateFloat(int columnIndex, float x) throws SQLException { throw NI; }
  public void updateDouble(int columnIndex, double x) throws SQLException { throw NI; }
  public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException { throw NI; }
  public void updateString(int columnIndex, String x) throws SQLException { throw NI; }
  public void updateBytes(int columnIndex, byte[] x) throws SQLException { throw NI; }
  public void updateDate(int columnIndex, Date x) throws SQLException { throw NI; }
  public void updateTime(int columnIndex, Time x) throws SQLException { throw NI; }
  public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException { throw NI; }
  public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException { throw NI; }
  public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException { throw NI; }
  public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException { throw NI; }
  public void updateObject(int columnIndex, Object x, int scale) throws SQLException { throw NI; }
  public void updateObject(int columnIndex, Object x) throws SQLException { throw NI; }

  public void updateNull(String columnName) throws SQLException { throw NI; }
  public void updateBoolean(String columnName, boolean x) throws SQLException { throw NI; }
  public void updateByte(String columnName, byte x) throws SQLException { throw NI; }
  public void updateShort(String columnName, short x) throws SQLException { throw NI; }
  public void updateInt(String columnName, int x) throws SQLException { throw NI; }
  public void updateLong(String columnName, long x) throws SQLException { throw NI; }
  public void updateFloat(String columnName, float x) throws SQLException { throw NI; }
  public void updateDouble(String columnName, double x) throws SQLException { throw NI; }
  public void updateBigDecimal(String columnName, BigDecimal x) throws SQLException { throw NI; }
  public void updateString(String columnName, String x) throws SQLException { throw NI; }
  public void updateBytes(String columnName, byte[] x) throws SQLException { throw NI; }
  public void updateDate(String columnName, Date x) throws SQLException { throw NI; }
  public void updateTime(String columnName, Time x) throws SQLException { throw NI; }
  public void updateTimestamp(String columnName, Timestamp x) throws SQLException { throw NI; }
  public void updateAsciiStream(String columnName, InputStream x, int length) throws SQLException { throw NI; }
  public void updateBinaryStream(String columnName, InputStream x, int length) throws SQLException { throw NI; }
  public void updateCharacterStream(String columnName, Reader x, int length) throws SQLException { throw NI; }
  public void updateObject(String columnName, Object x, int scale) throws SQLException { throw NI; }
  public void updateObject(String columnName, Object x) throws SQLException { throw NI; }
    
  public void insertRow() throws SQLException { throw NI; }
  public void updateRow() throws SQLException { throw NI; }
  public void deleteRow() throws SQLException { throw NI; }
  public void refreshRow() throws SQLException { throw NI; }
  public void cancelRowUpdates() throws SQLException { throw NI; }
  public void moveToInsertRow() throws SQLException { throw NI; }
  public void moveToCurrentRow() throws SQLException { throw NI; }
  public Statement getStatement() throws SQLException { throw NI; }
  public Object getObject(int i, Map map) throws SQLException { throw NI; }
  public Ref getRef(int i) throws SQLException { throw NI; }
  public Blob getBlob(int i) throws SQLException { throw NI; }
  public Clob getClob(int i) throws SQLException { throw NI; }
  public Array getArray(int i) throws SQLException { throw NI; }
  public Object getObject(String colName, Map map) throws SQLException { throw NI; }
  public Ref getRef(String colName) throws SQLException { throw NI; }
  public Blob getBlob(String colName) throws SQLException { throw NI; }
  public Clob getClob(String colName) throws SQLException { throw NI; }
  public Array getArray(String colName) throws SQLException { throw NI; }
  public Date getDate(int columnIndex, Calendar cal) throws SQLException { throw NI; }
  public Date getDate(String columnName, Calendar cal) throws SQLException { throw NI; }
  public Time getTime(int columnIndex, Calendar cal) throws SQLException { throw NI; }
  public Time getTime(String columnName, Calendar cal) throws SQLException { throw NI; }
  public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException { throw NI; }
  public Timestamp getTimestamp(String columnName, Calendar cal) throws SQLException { throw NI; }
}
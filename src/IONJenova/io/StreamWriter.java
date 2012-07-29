
package IONJenova.io;

import IONJenova.os.OSSystem;
import java.io.IOException;
import java.io.OutputStream;
import sun.awt.OSInfo;
import sun.management.OperatingSystemImpl;
import sun.misc.OSEnvironment;

/**
 *
 * @author Aditya Avaga Purwa
 */
public class StreamWriter {

  //Fields
  private OutputStream _stream;
  /**
   * Get the base stream
   * @return the underlying stream
   */
  public OutputStream getStream(){
    return _stream;
  }
  //~Fields

  //Constructor
  /**
   * Create a new StreamReader
   * @param stream , the stream to write
   */
  public StreamWriter(OutputStream stream){
    _stream = stream;
  }
  //~Constructor

  //Methods
  /**
   * Write a single byte into this stream
   * @param b 
   */
  public void write(int b) throws IOException{
    _stream.write(b);
  }
  /**
   * Write a buffered byte into this stream
   * @param b , the buffer of the byte to write into
   * @param offset , the offset from the buffer
   * @param length , the length of data to write
   */
  public void write(byte[] b,int offset,int length) throws IOException{
    _stream.write(b, offset, length);
  }
  /**
   * Write a string and append a line at the end of it
   * @param str , the string to write
   * @throws IOException
   */
  public void writeLine(String str) throws IOException{
    //Accumulate the string with OS new line
    str += OSSystem.getOSNewLine();
    //Create a buffer from the string
    byte[] buffer = str.getBytes();
    //Write data into the stream
    write(buffer,0,buffer.length);
  }
  /**
   * Write an empty line to the stream
   * @throws IOException 
   */
  public void writeLine() throws IOException{
    //Accumulate the string with OS new line
    String str = OSSystem.getOSNewLine();
    //Create a buffer from the string
    byte[] buffer = str.getBytes();
    //Write data into the stream
    write(buffer,0,buffer.length);
  }
  /**
   * Write a string into the stream
   * @param str  the string to write
   * @throws IOException 
   */
  public void write(String str) throws IOException{    
    //Create a buffer from the string
    byte[] buffer = str.getBytes();
    //Write data into the stream
    write(buffer,0,buffer.length);
  }
  
  /**
   * Flush pending data at this stream
   */
  public void flush() throws IOException{
    _stream.flush();    
  }
    
  /**
   * Close the stream used by this writer
   */
  public void close() throws IOException{
    _stream.close();
  }
  //~Methods
}

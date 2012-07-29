
package IONJenova.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Implements functionality to read an input stream
 * @author Aditya Avaga Purwa
 */
public class StreamReader {

  //Fields
  private InputStream _stream;
  /**
   * Get the base stream
   * @return the underlying stream
   */
  public InputStream getStream(){
    return _stream;
  }
  //~Fields

  //Constructor
  /**
   * Create a new StreamReader
   * @param stream , the stream to read
   */
  public StreamReader(InputStream stream){
    _stream = stream;
  }
  //~Constructor

  //Methods
  /**
   * Read a single byte from this stream
   * @return Single byte from this stream
   * @throws IOException 
   */
  public int read() throws IOException{
    return _stream.read();
  }
  /**
   * Read from this stream
   * @param buffer , the buffer for receiving the data
   * @param offset , offset at the buffer to insert the received data into
   * @param length , the length of data to read
   * @return Length of data read
   * @throws IOException 
   */
  public int read(byte[] buffer,int offset,int length) throws IOException{
    return _stream.read(buffer, offset, length);
  }
  public String readLine() throws IOException{
    int ch; //Container for current character
    String line = ""; //Container for all line read
    //Continously read from the stream
    do{
      //Read single char
      ch = _stream.read();
      //Accumulate into the line container
      line += (char)ch;
    }while(ch != 13 && ch != 10);
    //^While not new line or carriage return
    return line;
  }
  
  /**
   * Close the reader used by this reader
   */
  public void close() throws IOException{    
    _stream.close();
  }
  //~Methods
}

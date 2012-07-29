
package IONJenova.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.CharBuffer;
import java.nio.file.FileSystems;

/**
 *
 * @author Aditya Avaga Purwa
 */
public class IONFile {

  //Fields
    //Class fields here
  //~Fields

  //Constructor
    //Class constructor here
  //~Constructor

  //Methods
  /**
   * Read all text in a file
   * @param path , path to the file
   * @return A string representation of the file content
   */
  public static String readAll(String path) throws FileNotFoundException, IOException{    
    //Create container for the file content
    String fileContent="";
    //Create the file handle
    File f = new File(path);
    //Create the reader for the file
    FileReader fr = new FileReader(f);
    //The container of currently readed character
    int c;
    //While not eof, read the stream    
    do{
      c = fr.read();
      //If not eof, dont include the eof
      if(c != -1){
        fileContent += (char)c;
      }
    }while(c != -1);
    //Return the file content
    return fileContent;
  }
  //~Methods
}

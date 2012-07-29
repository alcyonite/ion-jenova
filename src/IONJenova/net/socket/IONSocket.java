
package IONJenova.net.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import sun.nio.ch.ServerSocketAdaptor;

/**
 * Implements Server or client socket, using SSL or non SSL
 * @author Aditya Avaga Purwa
 */
public class IONSocket {

  //Fields
  private ServerSocket _jvServerSocket;  
  /**
   * Get the native java server socket representation of this socket
   * @return ServerSocket, native representation of the socket
   */
  public ServerSocket getJavaServerSocket(){
    return _jvServerSocket;
  }
  /**
   * Set the native java server socket this IONSocket resemble
   * @param sock , the native java server socket
   */
  public void setJavaServerSocket(ServerSocket sock){
    _jvServerSocket = sock;
  }
  
  private Socket _jvSocket;
  /**
   * Get the native java socket representation of this socket
   * @return 
   */
  public Socket getJavaSocket(){
    return _jvSocket;
  }
  /**
   * Set the native java socket this IONSocket resemble
   * @param sock , the native java socket
   */
  public void setJavaSocket(Socket sock){
    _jvSocket = sock;
  }
  
  private Thread _thdListener;
  
  private boolean _listeningState=false;
  /**
   * Get the listening state of this IONSocket when act as a server
   * @return 
   */
  public boolean GetListeningState(){
    return _listeningState;
  }
  //~Fields

  //Constructor
  /**
   * Create IONSocket as a basic socket
   */
  public IONSocket(){
    _jvSocket = new Socket();
  }
  /**
   * Create IONSocket that act as a server
   * @param inetAddr , the address this socket will bind into
   */
  public IONSocket(int port, int backlog,InetAddress inetAddr) throws IOException{
    _jvServerSocket = new ServerSocket(port, backlog, inetAddr);
  }
  /**
   * Create IONSocket as socket based on native java socket
   * @param sock 
   */
  public IONSocket(Socket sock){
    _jvSocket = sock;
  }
  //~Constructor

  //Methods
  /**
   * Listen for incoming client socket request
   * @param listener 
   */
  public void Listen(final ISocketListener listener){
    //First, we create a thread so no blocking will occurrs during listening    
    _thdListener = new Thread(new Runnable() {      
      @Override
      public void run() {
        try{
          //We listeng for incoming connection until stopListen is called
          while(_listeningState){
            //Invoke the onClientRequest when client is requesting            
            Socket jvClientSock = _jvServerSocket.accept();
            //We double check the listening state, @see stopListen for the reason
            if(_listeningState){
              //Then invoke the clientRequest event
              listener.onClientRequest(new IONSocket(jvClientSock));
            }
          }
        }catch(Exception ex){
          //Invoke the onError if an error occurred
          listener.onError(ex);
        }
      }
      
    });
    //Set the listening state to true    
    _listeningState=true;
    //Start the thread
    _thdListener.start();    
  }
  /**
   * Stop listening for incoming connection   
   * @throws InterruptedException 
   */
  public void stopListen() throws InterruptedException, IOException{
    //Set the listening state to false
    _listeningState=false;
    /*
     * The only way to safely stop the event without waiting for final last socket
     * request is, CREATE A FINAL SOCKET REQUEST itself.
     * Thread.stop() is dangerous, while ServerSocket.accept() is blocking,
     * so we try to stop the blocking by sending our own final socket request
     */
    //Make the final request socket
    IONSocket finalRequest = new IONSocket();
    //Connect to end the accept() blocking
    finalRequest.connect(new InetSocketAddress(_jvServerSocket.getInetAddress(), _jvServerSocket.getLocalPort()));
    //Close the final request
    finalRequest.close();
  }
  /**
   * Connect to other socket
   * @param addr , the address of the listening socket
   */
  public void connect(SocketAddress addr) throws IOException{
    _jvSocket.connect(addr);
  }
  /**
   * Close this socket
   */
  public void close() throws IOException{
    _jvSocket.close();
  }
  //~Methods
}

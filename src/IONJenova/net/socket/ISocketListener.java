package IONJenova.net.socket;

/**
 * Implements a callback collection when implementing IONSocket as socket on
 * listening state
 * @author Aditya Avaga Purwa
 */
public interface ISocketListener {
  /**
   * Called when there is incoming client request
   * @param client , the client requesting for connection
   */
  void onClientRequest(IONSocket client);
  /**
   * Called when an error is occurred
   * @param error , the error information
   */
  void onError(Exception error);
}

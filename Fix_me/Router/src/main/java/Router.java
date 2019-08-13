import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.AsynchronousServerSocketChannel;

public class Router {
	private static final String ANSI_CYAN = "\u001B[36m";
	private static final int brokerPort = 5000;
	private static final int marketPort = 5001;

	public static void main(String[] args) {
//		try {
//			final AsynchronousServerSocketChannel listener = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(5000));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		while (true) {
			try (ServerSocket serverSocket = new ServerSocket(brokerPort)) {
				new SocketManager(serverSocket.accept()).start();
				System.out.println(ANSI_CYAN + "Broker Connected");
			} catch (IOException e) {
				System.out.println(ANSI_CYAN + " Server exception " + e.getMessage());
			}
			try (ServerSocket serverSocket = new ServerSocket(marketPort)) {
				new SocketManager(serverSocket.accept()).start();
				System.out.println(ANSI_CYAN + "Market Connected");
			} catch (IOException e) {
				System.out.println(ANSI_CYAN + " Server exception " + e.getMessage());
			}
		}
	}
}

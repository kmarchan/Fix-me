import java.io.IOException;
import java.net.ServerSocket;

public class Router {
	private static final String ANSI_CYAN = "\u001B[36m";
	private static final int brokerPort = 5000;
	private static final int marketPort = 5001;

	public static void main(String[] args) {
		while (true) {
			try (ServerSocket serverSocket = new ServerSocket(brokerPort)) {
				System.out.println(ANSI_CYAN + "Broker Connected");
				new SocketManager(serverSocket.accept()).start();
			} catch (IOException e) {
				System.out.println(ANSI_CYAN + " Server exception " + e.getMessage());
			}
			try (ServerSocket serverSocket = new ServerSocket(marketPort)) {
				System.out.println(ANSI_CYAN + "Market Connected");
				new SocketManager(serverSocket.accept()).start();
			} catch (IOException e) {
				System.out.println(ANSI_CYAN + " Server exception " + e.getMessage());
			}
		}
	}
}

import java.io.IOException;
import java.net.ServerSocket;

public class Router {
	public static final String ANSI_CYAN = "\u001B[36m";
	static final int brokerPort = 5000;
	static final int marketPort = 5001;
	public static void main(String[] args) {
		try(ServerSocket serverSocket = new ServerSocket(brokerPort)) {
			while(true) {
				System.out.println(ANSI_CYAN + "Client Connected");
				new MarketListener(serverSocket.accept()).start();
			}
		} catch(IOException e) {
			System.out.println(ANSI_CYAN + "Server exception " + e.getMessage());
		}
	}
}

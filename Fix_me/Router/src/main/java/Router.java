import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Router {
	public static final String ANSI_CYAN = "\u001B[36m";
	static final int brokerPort = 5000;
	static final int marketPort = 5001;
	public static void main(String[] args) {
		try(ServerSocket serverSocket = new ServerSocket(brokerPort)) {
			Socket socket = serverSocket.accept();
			System.out.println(ANSI_CYAN + "Client Connected");
			BufferedReader input = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

			while(true) {
				String echoString = input.readLine();
				if(echoString.equals(ANSI_CYAN + "exit")) {
					break;
				}
				output.println(ANSI_CYAN + "Echo from server: " + echoString);
			}


		} catch(IOException e) {
			System.out.println(ANSI_CYAN + "Server exception " + e.getMessage());
		}
	}
}

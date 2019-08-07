import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Market {
	public static final String ANSI_BLUE = "\u001B[34m";
	public static void main(String[] args) {
		try(ServerSocket serverSocket = new ServerSocket(5000)) {
			Socket socket = serverSocket.accept();
			System.out.println(ANSI_BLUE + "Client Connected");
			BufferedReader input = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

			while(true) {
				String echoString = input.readLine();
				if(echoString.equals(ANSI_BLUE + "exit")) {
					break;
				}
				output.println(ANSI_BLUE + "Echo from server: " + echoString);
			}


		} catch(IOException e) {
			System.out.println(ANSI_BLUE + "Server exception " + e.getMessage());
		}
	}
}

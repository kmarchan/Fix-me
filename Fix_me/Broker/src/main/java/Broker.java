import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Broker {
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", 5000)) {
			BufferedReader echoes = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);

			Scanner scanner = new Scanner(System.in);
			String echoString;
			String response;

			do {
				System.out.println(ANSI_PURPLE + "Enter string to be echoed: ");
				echoString = scanner.nextLine();

				stringToEcho.println(echoString);
				if(!echoString.equals(ANSI_PURPLE + "exit")) {
					response = echoes.readLine();
					System.out.println(response);
				}
			} while(!echoString.equals(ANSI_PURPLE + "exit"));

		} catch (IOException e) {
			System.out.println(ANSI_PURPLE + "Client Error: " + e.getMessage());

		}
	}
}

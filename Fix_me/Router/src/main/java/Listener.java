import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Listener extends Thread{
	public static final String ANSI_CYAN = "\u001B[36m";
	private Socket socket;

	public Listener(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		portThread(socket);

	}

	static void portThread(Socket socket) {
		String ANSI_CYAN = "\u001B[36m";
		try {
			BufferedReader input = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			while(true) {
				String echoString = input.readLine();
				if(echoString.equals("exit")) {
					break;
				}
				output.println(ANSI_CYAN + echoString);
			}
		} catch(IOException e) {
			System.out.println("Oops: " + e.getMessage());
		} finally {
			try {
				socket.close();
			} catch(IOException e) {
				// Oh, well!
			}
		}
	}
}

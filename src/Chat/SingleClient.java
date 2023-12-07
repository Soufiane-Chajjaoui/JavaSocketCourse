package Chat;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost" , 1234);
		OutputStream os = socket.getOutputStream();
		Scanner scanner = new Scanner(System.in);
		int datasendToServer = scanner.nextInt();
		os.write(datasendToServer);
		os.close();
	}
}

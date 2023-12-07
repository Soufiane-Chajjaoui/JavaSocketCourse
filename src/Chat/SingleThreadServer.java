package Chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class SingleThreadServer {
	public static void main(String[] args) {
		try {
			ServerSocket serversocket = new ServerSocket(1234);
			Socket s = serversocket.accept();
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			int data = is.read();
			System.out.println(data);
			os.close();
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

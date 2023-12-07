package Chat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class RealServer extends Thread {
	int nbClient = 1;

	private ArrayList<Client> clients = new ArrayList<Client>();

	public static void main(String[] args) {
		new RealServer().start();
	}

	@Override
	public void run() {

		try {
			ServerSocket serversocket = new ServerSocket(4040);
			
			while (true) {
				Socket socket = serversocket.accept();
				Client client = new Client(nbClient, socket);
				client.start();
				clients.add(client);
				nbClient++;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private class Client extends Thread {
		private int nbClient;
		private Socket socket;

		public Client(int nbClient, Socket socket) {
			super();
			this.nbClient = nbClient;
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				// Input : Data from Clients via le console
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				// OutPut : Envoyer les donnees vers Clients
				OutputStream os = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(os, true);
				pw.println();
				System.out.println("Le Client de numero " + nbClient + " Et son Addresse est "
						+ socket.getRemoteSocketAddress() + " et numero de port de client " + socket.getPort());
				String data;
				while (true) {
					data = br.readLine().toString(); // ce line est blockant tant que lire les donnees de client
					System.out.println(data);
					if (data.equalsIgnoreCase("fin")) {
						this.socket.close();
						break;
					} else {
						for (Client clientsocket : clients) {
							if (clientsocket.nbClient != this.nbClient) {
								PrintWriter pwSendAnother = new PrintWriter(clientsocket.socket.getOutputStream(),true);
								pwSendAnother.println(data);
							}
						}
					}

				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}

}

package Chat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class RealClient extends Thread {
	private Socket socket ;
	private int nbClient ;
	private String nom ;
	private int note ;
	public RealClient(Socket s , int nbC) {
		this.socket = s ;
 		this.nbClient = nbC ;
	}
	@Override
	public void run() {
		
		try {
			//Input
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is); // convert quatre Octets vers caractere
			BufferedReader br = new BufferedReader(isr);
			//output
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os,true);
			
            // ANSI escape codes for red text
            String ANSI_RED = "\u001B[31m";
            String ANSI_RESET = "\u001B[0m";


			pw.println("******************************** Welcome to Quiz LPISIR ********************************");
			
			pw.println("port de votre application est " + socket.getPort());
			
			pw.println(ANSI_RED + "Veuillez saisi votre nom" + ANSI_RESET);
			
			while ((this.nom = br.readLine()).length() <= 2) {
				pw.println("Please entree votre nom correcte");
			}
 			System.out.println("Client " + this.nbClient +" et son nom est "+this.nom +" de addresse " + socket.getRemoteSocketAddress().toString() + " ete Connecte ");

 			pw.println("Choise le bonne reponse chaque reponse est note par un "
					+ ANSI_RED + " . BONNE CHANCE" + ANSI_RESET);
 			
			while (true) {

				QuizServer.Quizz.forEach((q)->{
					pw.println("\n Question :" +  q.getQuestion() + " : ");
					int index = 1 ;
					for (String option : q.getReponses()) {
						pw.println("\t[" + index++ + "] : "+option);
					}
					try {
						int choice = Integer.parseInt( br.readLine());
						if(choice == q.getCorrectReponse()) {
							this.note++ ;
						}
					} catch (Exception e) {
						// TODO: handle exception
					}

				});
				System.out.println(this.nom + " Obtient note : " + this.note);
		        Thread.sleep(1000);
				pw.println("\nVotre note est " + this.note);
		        socket.close();
		        break; 
			}
				
		} catch (Exception e) {
			// TODO: handle exception
		}

		
		
	}
	
}

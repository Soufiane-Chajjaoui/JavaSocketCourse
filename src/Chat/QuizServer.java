package Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
 
public class QuizServer implements Runnable {
	int number = 1;
	public static ArrayList<RealClient> Clients = new ArrayList<RealClient>();
	public static ArrayList<Quiz> Quizz = new ArrayList<Quiz>();
	
	public static void main(String[] args) {
		AjouteQuiz();
		new QuizServer().run();
	}

	@Override
	public void run() {
		try {
			ServerSocket ss = new ServerSocket(2020);
 
			while (true) {
				Socket s = ss.accept();
				RealClient realClient = new RealClient(s, number);
				realClient.start();
				Clients.add(realClient);
				number++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void AjouteQuiz() {
	    try (BufferedReader read = new BufferedReader(new InputStreamReader(System.in))) {

	        while (true) {
		        System.out.println("Ajoute une Quiz (saisi 'fin' pour terminer la saisi)");

	            String question = read.readLine();
	            if (question.equalsIgnoreCase("fin")) {
	                break;
	            }

	            System.out.println("Nombre des Options");
	            int nbOptions = Integer.parseInt(read.readLine());
	            String[] reponses = new String[nbOptions];
	            for (int i = 0; i < nbOptions; i++) {
	                System.out.print("ajoute une reponse : ");
	                reponses[i] = read.readLine();
	            }

	            System.out.println("Choise la bonne Reponse");
	            int reponse = Integer.parseInt(read.readLine());

	            Quiz quiz = new Quiz(question, reponses, reponse--);
	            if (Quizz.add(quiz)) {
				System.out.println("Quiz Has been added");	
				}
	            
	        }
	        System.out.println("Vos Quiz ete bien enregistre les etudiants peuvent Connecte sans au qu'un probleme");
 	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}

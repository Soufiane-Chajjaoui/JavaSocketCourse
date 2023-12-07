package Chat;

 
public class Quiz {

	private String question ;
	private String[] reponses ;
	private int CorrectReponse ;
	
	
	
	public Quiz() {
		 
	}

	public Quiz(String question, String[] reponses, int correctReponse) {
 		this.question = question;
		this.reponses = reponses;
		this.CorrectReponse = correctReponse;
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String[] getReponses() {
		return reponses;
	}
	public void setReponses(String[] reponses) {
		this.reponses = reponses;
	}
	public int getCorrectReponse() {
		return CorrectReponse;
	}
	public void setCorrectReponse(int correctReponse) {
		CorrectReponse = correctReponse;
	}
	
	
}

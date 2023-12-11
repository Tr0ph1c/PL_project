package cemsystemjava;

/**
 *
 * @ x bn z
 */

public class Question {
    public String question;
    public String[] answers;
    public int correctAnswer;
    public boolean isCorrect;
    public Question()
    {
        this.answers = new String[4];
    }
    public Question(String question, String[] answers, int correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.isCorrect = false;
    }
    
    public Question(String line) {
        this.answers = new String[4];
        String[] attributes = line.split(";") ;
        
        this.question = attributes[0];
        
        for(int i = 0 ; i < 4 ; i++)
            this.answers[i] = attributes[ i + 1 ];
        
        this.correctAnswer = Integer.parseInt(attributes[5]);
    }
    
    //CorrectAnswer is (int)
    //ans is (char) [a,b,c,d]
    public boolean answer(char ans) {
        return (ans - 96 == correctAnswer);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(); //String data type but with append()
        result.append(question).append(";");
        for (String answer : answers) {
            result.append(answer).append(";");
        }
        result.append(correctAnswer);
        return result.toString();
    }
}


package cemsystemjava;

/**
 *
 * @ x bn z
 */
import DirectoryBasedDB.Database;

public class Question {
    private final String question;
    private final String[] answers;
    private final int correctAnswer;
    private boolean isCorrect;

    public Question(String question, String[] answers, int correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.isCorrect = false;
    }

    public void answer(int ans) {
        if (ans >= 1 && ans <= 4) {
            if (ans - 1 == correctAnswer) {
                isCorrect = true;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(question).append(";");
        for (String answer : answers) {
            result.append(answer).append(";");
        }
        result.append(correctAnswer).append(";").append(isCorrect);
        return result.toString();
    }
}

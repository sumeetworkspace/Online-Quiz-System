public class Question {
    int id;
    String question;
    String optionA, optionB, optionC, optionD;
    String correctOption;

    public Question(int id, String qText, String a, String b, String c, String d, String correct) {
        this.id = id;
        this.question = qText;
        this.optionA = a;
        this.optionB = b;
        this.optionC = c;
        this.optionD = d;
        this.correctOption = correct;
    }
}

package IS_IT_LOST;

import java.util.Vector;
interface Iquiries{
    void AddQuestions() ;

    void AddCorrectAnswers();

    void AddVictimAnswers();
}
public class Inquiries implements Iquiries{
    private int PostID;
    private int UserID;
    private Vector <String> Questions;
    private Vector <String> CorrectAnswers;
    private Vector <String> VictimAnswers;

    @Override
    public void AddQuestions() {

    }

    @Override
    public void AddCorrectAnswers() {
    }

    @Override
    public void AddVictimAnswers(){

    }


    public int getPostID() {
        return PostID;
    }

    public void setPostID(int postID) {
        PostID = postID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public Vector <String> getQuestions() {
        return Questions;
    }

    public void setQuestions(Vector <String> questions) {
        Questions = questions;
    }

    public Vector <String> getCorrectAnswers() {
        return CorrectAnswers;
    }

    public void setCorrectAnswers(Vector <String> correctAnswers) {
        CorrectAnswers = correctAnswers;
    }

    public Vector <String> getVictimAnswers() {
        return VictimAnswers;
    }

    public void setVictimAnswers(Vector <String> victimAnswers) {
        VictimAnswers = victimAnswers;
    }
}

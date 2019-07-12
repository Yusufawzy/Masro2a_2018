package IS_IT_LOST;

import java.util.Scanner;
import java.util.Vector;

interface IUser {
    public void managePost();

    public void search();


    public void Answer(int commID);

    public void CommunicateVictim(int UserID);

    public Inquiries Ask(User u, int commID);

    public boolean CheckAnswers(User v, int commID);

    public void Refresh();

    public void displayAll();

    public void CommunicatePasserBy(int UserID);

    void ShowOptions(int n);
}

public class User {
    UserService userService;
    private boolean type = false; //  0 for passerby , 1 for victim
    private String userName;
    private long pass;
    private String email;
    private String phone;
    private int UserID;
    private int ReportCount = 0;
    private Address address = new Address();
    Vector <Inquiries> Communications;


    Vector <Post> UserPosts;

    public User(String userName, long pass, String email, String phone) {
        this.userName = userName;
        this.pass = pass;
        this.email = email;
        this.phone = phone;
        // we need to add the address here also
    }

    public User() {
    }

    public boolean isType() {
        return type;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getUserID() {
        return UserID;
    }

    public int getReportCount() {
        return ReportCount;
    }

    public Address getAddress() {
        return address;
    }

    public void setReportCount(int reportCount) {
        ReportCount = reportCount;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}

class UserService implements IUser {
    User u;

    public void managePost() {
        //Enter postID , get the actual post from the array and pass it to the following function
        PostService m = new PostService();
        m.managePost(u, u.UserPosts.elementAt(0));
    }

    public void search() {
        PostService.search();
    }

    public void Answer(int commID) {

    }

    public void CommunicateVictim(int UserID) {
    }

    public Inquiries Ask(User u, int commID) {
        Inquiries e = new Inquiries();
        e.AddQuestions();
        e.AddCorrectAnswers();
        //adding this inquiry to the victim communications vector
        u.Communications.add(e);
        return e;
    }

    public boolean CheckAnswers(User v, int commID) {
        //if content of CorrectAnswers same as VictimAnswers then return true
        return false;
    }

    public void Refresh() {//Check Periodically
        displayAll();
        //we will ask again in the users main side if to search or not
        PostService.search();
    }

    public void displayAll() {
        PostService.Display();
    }

    public void CommunicatePasserBy(int UserID) {
        System.out.println("Enter your chosen Passerby ID");
        Scanner input = new Scanner(System.in);
        var PasserbyID = input.nextInt();
        System.out.println("Enter your chosen Post ID");
        var postID = input.nextInt();
    }

    public void ShowOptions(int n) {
        //based on the passed value from the user you have to maintain and to the function wanted
    }

}

class UserWindow implements IWindow {
    UserService userService;

    @Override
    //we need a method to maintain the work with this specified selected user to apply all changes to him
    public void ShowMenu() {
        System.out.println("1.managePost");
        System.out.println("2.Search");
        System.out.println("3.CommunicateWithUser"); //entirely choose if this is a passerby or victim
        System.out.println("4.DisplayAll");
        System.out.println("6.Display Posts");
        System.out.println("7.Verify"); //based on its type if victim then he ask if 0 then he answer w kda
        System.out.println("8.check Answers");
        System.out.println("9.ReportDecitful");
        System.out.println("10.AddEnhancement");
        System.out.println("11.Exit");
        Scanner input = new Scanner(System.in);
        //we here will check the number and pass it to the application and there it
        var n = input.nextInt();
        userService.ShowOptions(n);
    }
}

class UserDB {
    Vector <User> AllUsers = new Vector <>();
    void selectUser(){

    }
}

interface IUserDBService {
    void Add();

    User SelectUser(int UserID);

    void Remove(User u);
}

class UserDBService implements IUserDBService {
    UserDB userDB;

    public void Add() {
        //here you will enter the info of ur user
        User u = null;
        userDB.AllUsers.add(u);
    }

    public User SelectUser(int UserID) {
        return new User();
    }

    public void Remove(User u) {
    }
}

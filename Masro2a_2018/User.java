package IS_IT_lOST;

import com.sun.deploy.util.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
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

class User {
    UserService userService;
    private boolean type = false; //  0 for passerby , 1 for victim
    private String userName;
    private String pass;
    private String email;
    private String phone;
    private int UserID = 0;
    private int ReportCount = 0;
    private Address address = new Address();
    Vector <Inquiries> Communications;
    public static final String User_DB_File_Path = "userdb.txt"; // File that holds info for each user.
    private StringBuilder userInfo = new StringBuilder(); // Will be used to set the user info with delimiters which will be saved in userdb file


    Vector <Post> UserPosts;

    public User(String userName, String pass, String email, String phone) {
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

    public StringBuilder getUserInfo() {
        return userInfo;
    }

    public User userRegister()
    {
        User u = new User();
        Scanner sc = new Scanner(System.in);
        String delimiter = "|";

        System.out.println("Personnel information :- ");
        System.out.print("User name: ");
        u.userName = sc.next(); // No spaces in username
        // While is not valid phone number Ask user to re-enter it!
        do {
            System.out.print("Phone number : ");
            u.phone = sc.next(); // No spaces in phone number
        }
        while (! u.checkPhoneNumber(u.phone));

        System.out.print("Email address : ");
        u.email = sc.next(); // No spaces in email
        System.out.print("Password : ");
        u.pass = sc.nextLine();
        System.out.println("Address information :- ");
        System.out.print("Country name : ");
        u.address.setCountry(sc.nextLine());
        System.out.print("Governorate name : ");
        u.address.setGovernate(sc.nextLine());
        System.out.print("Street name : ");
        u.address.setStreet(sc.nextLine());
        System.out.print("Building number : ");
        u.address.setBuilding(sc.nextInt());

        u.UserID++; // For every new user give him an id and increment UserID. // When delete user the UserID will be decremented.

        userInfo.append(u.userName + delimiter + u.phone + delimiter + u.email + delimiter + u.pass + delimiter +
                u.address.getCountry() + delimiter + u.address.getGovernate() + delimiter + u.address.getStreet() + delimiter +
                u.address.getBuilding());
        return u;
    }

    boolean checkPhoneNumber(String phoneNum)
    {
        for (char c: phoneNum.toCharArray()) {
            if((int) c > (int)'9' || (int) c < (int)'0')
            {
                return false;
            }
        }
        return true;
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
//        var PasserbyID = input.nextInt();
        System.out.println("Enter your chosen Post ID");
//        var postID = input.nextInt();
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
//        var n = input.nextInt();
//        userService.ShowOptions(n);
    }
}

class UserDB {
    Vector <User> AllUsers = new Vector <>();
    User user = new User();
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
        User u = new User();
        try {
            u = u.userRegister();
            userDB.AllUsers.add(u);
            try(FileWriter fileWriter = new FileWriter(u.User_DB_File_Path)) {
                StringBuilder userInfo = u.getUserInfo();
                fileWriter.write(String.valueOf(userInfo));
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        catch (Exception e)
        {
            System.err.println(e);
        }

    }

    public User SelectUser(int UserID) {
        return new User();
    }

    public void Remove(User u) {
    }
}

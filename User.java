package IS_IT_lOST;

import com.sun.deploy.util.StringUtils;
import sun.applet.Main;

import java.io.*;
import java.util.ArrayList;
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
    private static int UserID = 0;
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

    public String getPass() {
        return pass;
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static void setUserID(int userID) {
        UserID = userID;
    }

    public void setUserInfo(StringBuilder userInfo) {
        this.userInfo = userInfo;
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

    public void load_data_from_userdbFile_into_AllUsers_Vector(String user_DB_File_Path, Vector<User> allUsers)
    {
        String userData;
        String[] userInfo; // User info fields
        User user;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(user_DB_File_Path))) {
            userData = bufferedReader.readLine();

            while (userData != null)
            {
                user = new User();
                userInfo = userData.split("[|]");
                user.setUserName(userInfo[0]);
                user.setPhone(userInfo[1]);
                user.setEmail(userInfo[2]);
                user.setPass(userInfo[3]);
                user.address.setCountry(userInfo[4]);
                user.address.setGovernate(userInfo[5]);
                user.address.setStreet(userInfo[6]);
                user.address.setBuilding(Integer.parseInt(userInfo[7]));
                allUsers.add(user);
                userData = bufferedReader.readLine();
            }

        } catch (Exception e) {
            // exception handling
            System.err.println(e);
        }
    }

    public Vector<String> getAllEmailsOfCurrentUsers(Vector<User> users)
    {
        Vector<String> mails = new Vector<>();
        for (int i = 0, size = users.size(); i < size; i++) {
            mails.add(users.get(i).getEmail());
        }
        return mails;
    }

    public boolean userLogin(String email, String password)
    {
        Vector<User> allUsers = new Vector();
        load_data_from_userdbFile_into_AllUsers_Vector(User_DB_File_Path, allUsers);
        Vector<String> allMails = getAllEmailsOfCurrentUsers(allUsers);

        int indexOfUSer = allMails.indexOf(email);

        //Not Found
        if(indexOfUSer < 0)
        {
            System.out.println("Not valid email");
            return false;
        }
        else
        {
            if(password.equals(allUsers.get(indexOfUSer).getPass()))
            {
                System.out.println("Login Successfully");
                return true;
            }
            else
            {
                System.out.println("email address and password doesn't match! ");
            }
        }
        return false;
    }

    public User userRegister()
    {
        User u = new User();
        Scanner sc = new Scanner(System.in);
        String delimiter = "|";

        System.out.println("Personnel information :- ");
        System.out.print("User name: ");
        do {
            u.userName = sc.next(); // No spaces in username
        }
        while (u.userName.length() == 0);

        System.out.print("Phone number : ");
        // While is not valid phone number Ask user to re-enter it!
        do {
            u.phone = sc.next(); // No spaces in phone number
        }
        while (! u.checkPhoneNumber(u.phone));
        System.out.print("Email address : ");
        do {
            u.email = sc.next(); // No spaces in email
        }
        while (u.email.length() == 0);
        System.out.print("Password : ");
        do {
            u.pass = sc.nextLine();
        }
        while (u.pass.length() == 0);
        System.out.println("Address information :- ");
        System.out.print("Country name : ");
        do {
            u.address.setCountry(sc.nextLine());
        }
        while (u.address.getCountry().length() == 0);
        System.out.print("Governorate name : ");
        do {
            u.address.setGovernate(sc.nextLine());
        }
        while (u.address.getGovernate().length() == 0);
        System.out.print("Street name : ");
        do {
            u.address.setStreet(sc.nextLine());
        }
        while (u.address.getStreet().length() == 0);
        System.out.print("Building number : ");
        do {
            u.address.setBuilding(sc.nextInt());
        }
        while (u.address.getBuilding() < 0);

//        u.UserID++; // For every new user give him an id and increment UserID. // When delete user the UserID will be decremented.

        userInfo.append(/*u.getUserID() + delimiter + */u.userName + delimiter + u.phone + delimiter + u.email + delimiter + u.pass + delimiter +
                u.address.getCountry() + delimiter + u.address.getGovernate() + delimiter + u.address.getStreet() + delimiter +
                u.address.getBuilding() + "\n");
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
    UserDB userDB = new UserDB();

    public void Add() {
        //here you will enter the info of ur user
        User u = new User();
        try {
            u.userRegister();
            userDB.AllUsers.add(u);
            try(FileWriter fileWriter = new FileWriter(u.User_DB_File_Path, true)) {
                StringBuilder userInfo = u.getUserInfo();
                System.out.println(userInfo);
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


    public static void main(String[] args) {
//        UserDBService userDBService = new UserDBService();
//        userDBService.Add();
        Vector<User> users = new Vector<>();
        User u = new User();
//        u.load_data_from_userdbFile_into_AllUsers_Vector( u.User_DB_File_Path, users);
        u.userLogin("usamae@yahoo.com", "0123456789");
        /*for (int i = 0; i <users.size(); i++)
        {
            u = users.get(i);
            System.out.println(u.getUserName());
            System.out.println(u.getPhone());
            System.out.println(u.getEmail());
            System.out.println(u.getPass());
            System.out.println(u.getAddress().getCountry());
            System.out.println(u.getAddress().getGovernate());
            System.out.println(u.getAddress().getStreet());
            System.out.println(u.getAddress().getBuilding());
        }*/
    }
}

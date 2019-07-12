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
    Address address = new Address();
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
        this.setUserID(UserDBService.counter++);

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

    public void setUserID(int userID) {
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
    }

    public void ShowOptions(int n) {
        //based on the passed value from the user you have to maintain and to the function wanted
    }

}

class UserWindow implements IWindow {
    UserService userService;

    @Override
    //we need a method to maintain the work with this specified selected user to apply all changes to him
    public void ShowMenu(User u) {
        System.out.println("=====UserWindow====");
        System.out.println("1.ManagePost");
        System.out.println("2.Search");
        System.out.println("3.CommunicateWithUser"); //entirely choose if this is a passerby or victim
        System.out.println("4.DisplayAll");
        System.out.println("6.Display Posts");
        System.out.println("7.Verify"); //based on its type if victim then he ask if 0 then he answer w kda
        System.out.println("8.Check Answers");
        System.out.println("9.ReportDecitful");
        System.out.println("10.AddEnhancement");
        System.out.println("11.LogOut");
        System.out.println("12.Exit");
        Scanner input = new Scanner(System.in);
        int x = input.nextInt();
        if (x == 1) {
            new PostService().managePost(u, null);
            new UserWindow().ShowMenu(u);
        }
        if (x == 2) {
            //here search will be implemented using the userservice methods
            new UserService().search();
            new UserWindow().ShowMenu(u);
        }
        if (x >= 3 && x <= 10) {
            System.out.println("Not Implemented Functions");
            new UserWindow().ShowMenu(u);
        }
        if (x == 11) {
            new AppWindow().ShowMenu(null);
        }
        if (x == 12) {
            return;
        }
    }
}

class UserDB {
    static Vector <User> AllUsers = new Vector <>();
}


class UserDBService {
    static int counter = 0; //to set an id for all of the users

    static public void Add(User u) {
        //here you will enter the info of ur user

        UserDB.AllUsers.add(u);
       /* try {
            u.userRegister();
            userDB.AllUsers.add(u);
            try (FileWriter fileWriter = new FileWriter(u.User_DB_File_Path, true)) {
                StringBuilder userInfo = u.getUserInfo();
                System.out.println(userInfo);
                fileWriter.write(String.valueOf(userInfo));
            } catch (Exception e) {
                System.err.println(e);
            }
        } catch (Exception e) {
            System.err.println(e);
        }*/

    }

    static public User SelectUser(int UserID) {
        return new User();
    }

    static public void Remove(User u) {
    }



}

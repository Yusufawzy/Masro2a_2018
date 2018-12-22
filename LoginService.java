import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

public class LoginService {
    public void Register() {
        User u = new User();
        Scanner sc = new Scanner(System.in);

        System.out.println("Personnel information :- ");
        System.out.print("User name: ");
        do {
            u.setUserName(sc.next());
            ; // No spaces in username
        }
        while (u.getUserName().length() == 0);

        System.out.print("Phone number : ");
        // While is not valid phone number Ask user to re-enter it!
        do {
            u.setPhone(sc.next());  // No spaces in phone number
        }
        while (!checkPhoneNumber(u.getPhone()));
        System.out.print("Email address : ");
        do {
            u.setEmail(sc.next());  // No spaces in email
        }
        while (u.getEmail().length() == 0);
        System.out.print("Password : ");
        do {
            u.setPass(sc.nextLine());
        }
        while (u.getPass().length() == 0);
      /*  System.out.println("Address information :- ");
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
        u.setUserID(UserDBService.counter++);
       */


       /* userInfo.append(u.getUserName() + delimiter + u.getPhone() + delimiter + u.getEmail() + delimiter + u.getPass() + delimiter +
                u.address.getCountry() + delimiter + u.address.getGovernate() + delimiter + u.address.getStreet() + delimiter +
                u.address.getBuilding() + "\n");*/
        UserDBService.Add(u);
        //here we will go to the USERWINDOW
        new UserWindow().ShowMenu(u);
    }

    public void LogOut() {

    }

    public void showOptions() {
        System.out.println("1-Log in --YOU have registered before");
        System.out.println("2-Register --A new User to the system");
        System.out.println("3-Log out");
        System.out.println("Enter your choice number");
        Scanner input = new Scanner(System.in);
        int x = input.nextInt();
        if (x == 1) {
            LogIn();
        } else if (x == 2) {
            Register();
        } else if (x == 3) {
            LogOut();
        }
    }

    public void forgetPassword() {

    }

    public void Load(String user_DB_File_Path, Vector <User> allUsers) {
        String userData;
        String[] userInfo; // User info fields
        User user;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(user_DB_File_Path))) {
            userData = bufferedReader.readLine();

            while (userData != null) {
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

    public Vector <String> getAllEmailsOfCurrentUsers(Vector <User> users) {
        Vector <String> mails = new Vector <>();
        for (int i = 0, size = users.size(); i < size; i++) {
            mails.add(users.get(i).getEmail());
        }
        return mails;
    }


    boolean checkPhoneNumber(String phoneNum) {
        for (char c : phoneNum.toCharArray()) {
            if ((int) c > (int) '9' || (int) c < (int) '0') {
                return false;
            }
        }
        return true;
    }

    public void LogIn() {
        //enter name
        //enter password ;
        //enter the type he want to enter as
        //return as victim or passerby 0 or 1
        //also return valid ir not -1
        //get the user from usersDB


        String email = "", password = "";
        System.out.println("Enter your email");
        Scanner input = new Scanner(System.in);
        email = input.nextLine();
        System.out.println("Enter your password");
        password = input.nextLine();
        User u = null;
        boolean flag = false;
        for (User user : UserDB.AllUsers) {
            if (user.getEmail().equals(email)  && password.equals(user.getPass())) {
                u = user;
                flag = true;
            }
        }
        if (flag == false) {
            System.out.println("This user is not found in the system or email is wrong");
            new AppWindow().ShowMenu(null);
        } else {
            System.out.println("You have LOGGED SUCCESSFULLY");
            new UserWindow().ShowMenu(u);
        }
    }

}

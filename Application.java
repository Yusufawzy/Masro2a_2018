package IS_IT_LOST;

import java.util.Scanner;

interface IWindow {
    void ShowMenu();
}

class AppWindow implements IWindow {

    final static Application app = new Application();

    public static void main(String[] args) {
        new AppWindow().ShowMenu();
    }

    @Override
    public void ShowMenu() {
        System.out.println("1.LogIn");
        System.out.println("2.Display Posts");
        System.out.println("3.AddEnhancement");
        System.out.println("4.ReportDecitful");
        System.out.println("5.Exit");
        Scanner input = new Scanner(System.in);
        //we here will check the number and pass it to the application and there it
        var n = input.nextInt();
        app.ShowOptions(n);
    }
}
//we will move the elements of the user entity to the UserWindow


class LoginService {
    User u ;
    public void register() {
        //enter all of info of the user
        //add this user to the UsersDB
    }

    public void logOut() {

    }

    public void showOptions() {
        System.out.println("Log in --YOU have registered before");
        System.out.println("Register --A new User to the system");
        System.out.println("Log out");
        Scanner input = new Scanner(System.in);
        //then select method base on  the enetered number
    }

    public void forgetPassword() {

    }

    public int LogIn() {
        //enter name
        //enter password ;
        //enter the type he want to enter as
        //return as victim or passerby 0 or 1
        //also return valid ir not -1
        //get the user from usersDB
        UserDBService userDBService=new UserDBService();

        return 0; // hatrg3 element mn al array 3la 7sb a5trna a7na eh
    }

}

public class Application {
    EnahncementService enahncementService;
    ReportService reportService;
    PostService postService;
    /* we will have a list of names with passwords each has the chance to enter as passer or victim */
    LoginService loginService;

    void ShowOptions(int n) {
        if (n == 1) {
            loginService.showOptions(); //after login we should be into the userWindo

        } else if (n == 2) {
            PostService.Display();
        } else if (n == 3) {
            enahncementService.showOptions();
        } else if (n == 4) {
            reportService.showOptions();
        } else if (n == 5) {
            {
                return;
            }
        }
    }

}

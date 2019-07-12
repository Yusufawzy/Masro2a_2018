import java.util.Scanner;

interface IWindow {
    void ShowMenu(User u);
}

//we will move the elements of the user entity to the UserWindow
class AppWindow implements IWindow {

    final static Application app = new Application();

    public static void main(String[] args) {
        new AppWindow().ShowMenu(null);
    }

    @Override
    public void ShowMenu(User u) {
        System.out.println("\"=====AppWindow====\"");
        System.out.println("1.LogIn/Register");
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

public class Application {
    /* we will have a list of names with passwords each has the chance to enter as passer or victim */
    LoginService loginService = new LoginService();

    void ShowOptions(int n) {
        // 1 to add users
        if (n == 1) {
            loginService.showOptions(); //after login we should be into the userWindo
        } else if (n == 2) {
            PostService.Display();
        } else if (n == 5) {
            return;
        } else {
            System.out.println("NOT Implemented Functions");
            new AppWindow().ShowMenu(null);
        }
    }

}

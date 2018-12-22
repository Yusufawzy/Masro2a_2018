import java.util.Scanner;
import java.util.Vector;

enum Categories {Cloths, Electronics, Jewelries, Kids, PersonalThings, Wallet}

public class Post {
    static int count = 0;
    private int PostDBIdx;
    private String Title;
    private String Description;
    private String City;
    private int PostID;
    private int UserID;   //to be used by victim to communicate with this person
    private int category; //will be chosen from the enum

    public void setPostDBIdx(int postDBIdx) {
        PostDBIdx = postDBIdx;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setPostID(int postID) {
        PostID = postID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public Post(String title, String description, String city, int userID) {
        Title = title;
        Description = description;
        City = city;
        UserID = userID;
        this.PostID = count++;
        //A variable to tell me where my post are in the DB
    }

    public Post() {
    }

    void Display() {
        System.out.println("=====PostDetails=====");
        System.out.println("The Post Title is "+getTitle());
        System.out.println("The Post Decription is "+getDescription());
        System.out.println("THe Post City is "+getCity());
    }

    public int getPostDBIdx() {
        return PostDBIdx;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getCity() {
        return City;
    }

    public int getPostID() {
        return PostID;
    }

    public int getUserID() {
        return UserID;
    }


}

interface IPostService {
    void managePost(User u, Post p);

    Post addPost(User u); //what if add how to add

    Post removePost(User u, Post p);

    Post viewPost(User u, Post p);

    Post UpdatePost(User u, Post p);
}

class PostService implements IPostService {
    static PostsDB postsDB;

    @Override
    public void managePost(User u, Post p) {
        System.out.println("1.Add a new Post");
        System.out.println("2.Remove Post");
        System.out.println("3.Modify Post");
        int x = input.nextInt();
        if (x == 1)
            addPost(u);
        new UserWindow().ShowMenu(u);
    }

    @Override
    public Post addPost(User u) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Post's Title");
        var title = (input.nextLine());
        System.out.println("Enter Post's description");
        var describe = (input.nextLine());
        System.out.println("Enter City");
        var city = input.nextLine();
        var p = new Post(title, describe, city, u.getUserID());

        p.setPostDBIdx(PostsDB.getAllPosts().size()); //the idx is like a pointer to this place in the future need to use
        /*add to the PostDB*/
        PostsDB.getAllPosts().add(p);
        return p;
    }

    @Override
    public Post removePost(User u, Post p) {
        return p;
    }

    @Override
    public Post viewPost(User u, Post p) {
        return p;
    }

    @Override
    public Post UpdatePost(User u, Post p) {
        return p;
    }

    static void Display() {
        for (Post Post : postsDB.getAllPosts()) {
            System.out.println(Post);
        }
    }

    private static Scanner input = new Scanner(System.in);

    static void search() {
        Vector <Post> SearchRes = new Vector <>();
        System.out.println("1.Search By Description");
        System.out.println("2.Search By Title");
        System.out.println("3.Search By City");
        int choice = input.nextInt();
        if (choice == 1) {
            SearchRes = searchByDescription();
        } else if (choice == 2) {
            SearchRes = searchByTitle();
        } else if (choice == 3) {
            SearchRes = searchByCity();
        }
    }

    private static Vector <Post> searchByTitle() {
        Vector <Post> SearchRes = new Vector <>();
        System.out.println("Enter The Title");
        input = new Scanner(System.in);

        String a = input.nextLine();
        for (Post post : PostsDB.getAllPosts()) {
            if (post.getTitle().equals(a)) SearchRes.add(post);
        }
        //printing the Search Result posts
        if (SearchRes.size() == 0) System.out.println("No matches found");
        else for (int i = 0; i < SearchRes.size(); i++) {
            SearchRes.elementAt(i).Display();
        }
        return SearchRes;
    }

    private static Vector <Post> searchByDescription() {
        Vector <Post> SearchRes = new Vector <>();
        System.out.println("Enter your keyword");
        input = new Scanner(System.in);

        String a = input.nextLine();
        for (Post post : PostsDB.getAllPosts()) {
            if (post.getDescription().contains(a)) SearchRes.add(post);
        }
        //printing the Search Result posts
        if (SearchRes.size() == 0) System.out.println("No matches found");
        else for (int i = 0; i < SearchRes.size(); i++) {
            SearchRes.elementAt(i).Display();
        }
        return SearchRes;
    }

    private static Vector <Post> searchByCity() {
        Vector <Post> SearchRes = new Vector <>();
        System.out.println("Enter your city name");
         input = new Scanner(System.in);
        String a = input.nextLine();
        for (Post post : PostsDB.getAllPosts()) {
            if (post.getCity().equals(a)) SearchRes.add(post);
        }
        //printing the Search Result posts
        if (SearchRes.size() == 0) System.out.println("No matches found");
        else for (int i = 0; i < SearchRes.size(); i++) {
            SearchRes.elementAt(i).Display();
        }
        return SearchRes;

    }
}


class PostsDB {

    static final Vector <Post> AllPosts = new Vector <>(); //It's declared as final so we can't set it anytime

    public static Vector <Post> getAllPosts() {
        return AllPosts;
    }

    //We need to handle if any edits happend there at user level need to be modified here.
    //anyTime we add a post among all passersby, It's added to this DataBase to enable the fast search
}
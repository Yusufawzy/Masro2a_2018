package IS_IT_LOST;

import java.util.Scanner;
import java.util.Vector;

enum Categories{Cloths, Electronics, Jewelries, Kids, PersonalThings, Wallet}

public class Post {
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

    public Post(String title, String description, String city, int postID, int userID, int category) {
        Title = title;
        Description = description;
        City = city;
        PostID = postID;
        UserID = userID;
        this.category = category;
        //A variable to tell me where my post are in the DB
    }

    public Post() {
    }

    void Display(){
        System.out.println(this.toString()); //prints info of the post
    }
    @Override
    public String toString() {
        return super.toString();
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

    public int getCategory() {
        return category;
    }
}

interface IPostService {
    Post managePost(User u , Post p);
    Post addPost(User u, Post p); //what if add how to add
    Post removePost(User u, Post p);
    Post viewPost(User u, Post p);
    Post UpdatePost(User u, Post p);
}

class  PostService implements IPostService {
    static PostsDB postsDB;
    @Override
    public Post managePost(User u, Post p) {
        //if want to add then add null
        return p;
    }

    @Override
    public Post addPost(User u, Post p) {
        Scanner input = new Scanner(System.in);
        p.setTitle(input.nextLine());
        p.setDescription(input.nextLine());
        //We need to handle here how to increase the ids of the post among the whole application
        //We also wants to find how to use the enum by inputting its ID or num to return a string aw al3ks
        //Add Photo
        p.setPostDBIdx( PostsDB.getAllPosts().size() ); //the idx is like a pointer to this place in the future need to use
        PostsDB.getAllPosts().add(p);//we add all to the PostDB, we don't have any methods in the database.
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
    static void Display(){
        for (Post Post : postsDB.getAllPosts()) {
            System.out.println(Post);
        }
    }
    private static Scanner input = new Scanner(System.in);
    static void search() {
        Vector <Post> SearchRes = new Vector <>();
        int choice =input.nextInt();
        System.out.println("Save your Post and User IDs if you want to communicate with this person");
        if (choice == 1) {
            SearchRes = searchByCategory();
        } else if (choice == 2) {
            SearchRes = searchByTitle();
        } else if (choice == 3) {
            SearchRes = searchByCity();
        }
        //what if we want to communicate
    }

    private static Vector <Post> searchByTitle() {
        Vector <Post> SearchRes = new Vector <>();
        String a = input.nextLine();
        //we will search in POSTSDB rather than TrackLists
        /*for (int i = 0; i < TrackList.size(); i++) {
            if (TrackList.elementAt(i).Title.contains(a)){ // if any of the posts has this title then add it
                SearchRes.add(TrackList.elementAt(i));
            }
        }*/
        //printing the Search Result posts
        for (int i = 0; i < SearchRes.size(); i++) {
            SearchRes.elementAt(i).Display();
        }
        return SearchRes;
    }

    private static Vector <Post> searchByCategory() {
        Vector <Post> SearchRes = new Vector <>();
        return SearchRes;
    }

    private static Vector <Post> searchByCity() {
        Vector <Post> SearchRes = new Vector <>();
        return SearchRes;

    }
}


class PostsDB {

    static final Vector<Post> AllPosts= new Vector <>(); //It's declared as final so we can't set it anytime

    public static Vector <Post> getAllPosts() {
        return AllPosts;
    }

    //We need to handle if any edits happend there at user level need to be modified here.
    //anyTime we add a post among all passersby, It's added to this DataBase to enable the fast search
}
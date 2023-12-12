package user;

import observer.Observer;
import observer.Subject;
import tree.UserElementTreeModel;
import visitor.Visitor;
import java.util.ArrayList;
import java.util.Date;

/**
 * Represents a user in the Mini Twitter application.
 */
public class User extends Subject implements Observer, UserElement {

    private UserElementTreeModel userElementTreeModel;
    private String userID;
    private ArrayList<String> userTweets;
    private ArrayList<String> userNewsFeed;
    private UserView userView;
    private final String iconURL = "/resources/person.png";
    private ArrayList<User> followers;
    private long creationTime;
	private long lastUpdateTime;
    
    /**
     * Constructs a new User with the specified tree model and unique identifier.
     */
    public User(UserElementTreeModel userElementTreeModel, String userID) {
        this.userElementTreeModel = userElementTreeModel;
        this.userID = userID;
        this.creationTime = new Date().getTime(); // Initialize creationTime
        this.lastUpdateTime = this.creationTime;

        
        
        userView = new UserView(this);
        userTweets = new ArrayList<>();
        userNewsFeed = new ArrayList<>();
        followers = new ArrayList<>();
        
        userView.init();
        attach(this);
        followers.add(this);
        
        System.out.println("User " + userID + " created at: " + new Date(creationTime));

    }

    /**
     * Gets the latest tweet posted by the user.
     */
    public String retrieveLatestTweet() {
        return userTweets.get(userTweets.size() - 1);
    }

    /**
     * Gets the list of tweets posted by the user.
     */
    public ArrayList<String> retrieveTweets() {
        return userTweets;
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof User) {
            // Update news feed when observed user posts a new tweet
            shareTweetOnNewsFeed(((User) subject).getUniqueID() + ": " + ((User) subject).retrieveLatestTweet());
        }
    }

    /**
     * Shares a tweet on the user's news feed.
     */
    public void shareTweetOnNewsFeed(String tweet) {
        System.out.println("tweet: " + tweet);
        userNewsFeed.add(tweet);
        userView.addTweetToNewsFeed(tweet);
    }

    /**
     * Posts a new tweet and notifies followers.
     */
    public void postTweet(String tweet) {
        userTweets.add(tweet);
        notifyObservers();
        long lastUpdateTime = System.currentTimeMillis();

    }
    
    /**
     * Follows the specified user by adding them to the followers list.
     */
    public boolean followUser(String followedUserID) {
        User followedUser = (User) userElementTreeModel.findUserByID((UserElement) userElementTreeModel.getRoot(), followedUserID);        
        if (followedUser != null && !followers.contains(followedUser)){
            followedUser.attach(this);
            followers.add(followedUser);
            return true;
        } else {
            return false;
        }       
    }
    
    @Override
    public String toString(){
        return userID;
    }   
    
    @Override
    public String getUniqueID(){
        return userID;
    }

    @Override
    public void add(UserElement userElement) {
        // Display an error message as users cannot have children
        userView.errorMessage("Children Error", "Error: Users cannot have children.");
    }

    @Override
    public UserElement getChild(int index) {	
        // Users cannot have children, so return null
        return null;
    }

    @Override
    public int getIndexOfChild(UserElement userElement) {    
        // Users cannot have children, so return -1
        return -1;
    }

    @Override
    public int getChildCount() {     
        // Users cannot have children, so return 0
        return 0;
    }

    @Override
    public void accept(Visitor visitor) {
        // Accept the visitor for user-specific operations
        visitor.atUser(this);
    }   

    /**
     * Gets the URL for the user's icon.
     */
    @Override
    public String getIconURL() {
        return iconURL;
    }
 
    /**
     * Opens the user view.
     */
    @Override
    public void openUserView(){
        userView.setVisible(true);
    }
    
    // Add a getter for creationTime
    public long getCreationTime() {
        return creationTime;
    }
    
    public long getLastUpdateTime() {
        return lastUpdateTime;
    }


}

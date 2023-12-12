package user;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.*;

import app.MiniTwitterForm;

public class UserView extends MiniTwitterForm {
    
    private User user;                              
    private JLabel formTitle;                       
    private JLabel userIDLabel;                 
    
    private JPanel followingPanel;                 
    private JScrollPane followingScrollPane;        
    private JList followingList;                    
    private DefaultListModel followingListModel;    
    private JButton followUser;                     
    private JTextField followUserIDTextField;       
    private JLabel followUserIDLabel;               
    private JLabel followingTitle;                  
    
    private JPanel newsFeedPanel;                   
    private JScrollPane newsFeedScrollPane;         
    private DefaultListModel newsFeedListModel;    
    private JList newsFeedList;                    
    private JButton postTweet;                      
    private JTextField tweetTextField;              
    private JLabel newsFeedTitle;                   
    private JLabel tweetLabel;                     
    private JLabel creationTimeLabel; // New label to display creationTime

    
    
    public UserView(User user){
        this.user = user;
    }
    
    public void init(){
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Mini-Twitter @" + user.getUniqueID());
        setSize(500, 600);
        setLocationRelativeTo(null);
        
     // Initialize and style creationTime label
        creationTimeLabel = new JLabel("Creation Time: " + new Date(user.getCreationTime()) + " milliseconds");
        creationTimeLabel.setBounds(200, 10, 250, 15);
        creationTimeLabel.setForeground(Color.black);
        add(creationTimeLabel);
        
        userIDLabel = new JLabel("@" + user.getUniqueID());
        userIDLabel.setBounds(10,5,200,50);
        userIDLabel.setFont(new Font("SANS_SERIF", Font.BOLD + Font.ITALIC, 36));
        add(userIDLabel);
        
        // following panel
        followingPanel = new JPanel();
        stylePanel(followingPanel, 10, 60, 375, 210);
        
        followUser = new JButton("Follow User");
        styleButton(followUser, 205, 50, 160, 35);
        followingPanel.add(followUser);
        
        followUserIDLabel = new JLabel("User ID:");
        followUserIDLabel.setBounds(10, 36, 160, 15);
        followingPanel.add(followUserIDLabel);
        
        followingTitle = new JLabel("Following:");
        styleTitleLabel(followingTitle, 0, 5, 365, 30);
        followingPanel.add(followingTitle);
        
        followUserIDTextField = new JTextField();
        followUserIDTextField.setBounds(10, 50, 185, 35);
        followingPanel.add(followUserIDTextField);
        
        followingListModel = new DefaultListModel();
        followingList = new JList(followingListModel);
        
        followingScrollPane = new JScrollPane(followingList);
        followingScrollPane.setBounds(10, 100, 355, 100);
        followingPanel.add(followingScrollPane);
        
        // newsfeed panel
        newsFeedPanel = new JPanel();
        stylePanel(newsFeedPanel, 10, 280, 375, 260);
        
        postTweet = new JButton("Post Tweet");
        styleButton(postTweet, 205, 50, 160, 35);
        newsFeedPanel.add(postTweet);
        
        tweetLabel = new JLabel("New Tweet:");
        tweetLabel.setBounds(10, 36, 160, 15);
        newsFeedPanel.add(tweetLabel);
        
        newsFeedTitle = new JLabel("News Feed:");
        styleTitleLabel(newsFeedTitle, 0, 5, 365, 30);
        newsFeedPanel.add(newsFeedTitle);
        
        tweetTextField = new JTextField();
        tweetTextField.setBounds(10, 50, 185, 35);
        newsFeedPanel.add(tweetTextField);
        
        newsFeedListModel = new DefaultListModel();
        newsFeedList = new JList(newsFeedListModel);
        
        newsFeedScrollPane = new JScrollPane(newsFeedList);
        newsFeedScrollPane.setBounds(10, 100, 355, 150);
        newsFeedPanel.add(newsFeedScrollPane);
        
        System.out.println("User View Initialized");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == postTweet) {
           postTweet();
        } 
        else if (ae.getSource() == followUser){
            followUser();
        }
    }
 
    public void addTweetToNewsFeed(String tweet){
        newsFeedListModel.add(0, tweet);
    }
 
    public void postTweet(){
        if (!tweetTextField.getText().equals("")){
            user.postTweet(tweetTextField.getText());
            tweetTextField.setText("");
        }
    }

    public void followUser(){
        String followUserID = followUserIDTextField.getText();
        if (!followUserID.equals("")){
            if(user.followUser(followUserID)){
                System.out.println("User succesfully followed");
                followingListModel.add(0, followUserID);
            }
            else {
                errorMessage("Follow Failed",  "Error: Could not follow User " + followUserID + ".");
            }
            followUserIDTextField.setText("");
        }
    }  
    
    public void updateCreationTimeLabel() {
        creationTimeLabel.setText("Creation Time: " + user.getCreationTime());
    }
    
    
}

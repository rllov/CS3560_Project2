package app;

import tree.*;
import user.User;
import user.UserElement;
import visitor.*;
import java.awt.*;
import javax.swing.*;

import group.Group;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends MiniTwitterForm implements ActionListener{

    // Singleton instance
    private static AdminPanel instance;

    // Root element for the user tree
    private UserElement rootUserElement;

    // UserElementTreeModel for managing the tree model
    private UserElementTreeModel treeModel;

    // Swing components
    private JTree userTree;
    private JScrollPane treeScrollPane;
    private JTextField userIDField, groupIDField;
    private JPanel analysisPanel, treeViewPanel, userGroupManagementPanel;
    private JLabel userGroupManagementTitle, groupIDLabel, userIDLabel, treeViewTitle, analysisTitle, formTitle;
    private JButton addUserButton, addGroupButton, openUserViewButton, showUserTotalButton, showGroupTotalButton, showMessagesTotalButton, showPositivePercentageButton;

    // Private constructor for Singleton pattern
    private AdminPanel(){
        System.out.println("Admin Panel Rendered");
    }

    // Singleton instance retrieval method
    public static AdminPanel getInstance(){
        if (instance == null){
            synchronized(AdminPanel.class){
                if (instance == null){
                    instance = new AdminPanel();
                }
            }
        }
        return instance;
    }

    // Initialization method
    public void init(){
        // JFrame setup
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("CS3560 Project 2 - Mini-Twitter");
        setSize(800, 550);
        setLocationRelativeTo(null);

        // Title label
        formTitle = new JLabel("Mini-Twitter");
        formTitle.setHorizontalAlignment(SwingConstants.LEFT);
        formTitle.setBounds(10,15,250,50);
        formTitle.setForeground(new Color(0, 0, 0));
        formTitle.setFont(new Font("SANS_SERIF", Font.BOLD + Font.ITALIC, 36));
        add(formTitle);

        // Analysis panel setup
        analysisPanel = new JPanel();
        stylePanel(analysisPanel, 230, 330, 545, 130);

        // Analysis buttons
        showUserTotalButton = new JButton("Show User Total");
        styleButton(showUserTotalButton, 10, 25,  250, 35);
        analysisPanel.add(showUserTotalButton);

        showGroupTotalButton = new JButton("Show Group Total");
        styleButton(showGroupTotalButton, 275, 25,  250, 35);
        analysisPanel.add(showGroupTotalButton);

        showMessagesTotalButton = new JButton("Show Messages Total");
        styleButton(showMessagesTotalButton, 10, 75,  250, 35);
        analysisPanel.add(showMessagesTotalButton);

        showPositivePercentageButton = new JButton("Show Positive Percentage");
        styleButton(showPositivePercentageButton, 275, 75,  250, 35);
        analysisPanel.add(showPositivePercentageButton);

        // Tree view panel setup
        treeViewPanel = new JPanel();
        stylePanel(treeViewPanel, 10, 100, 210, 360);

        // Root user element and tree model
        rootUserElement = new Group(treeModel, "Root");
        treeModel = new UserElementTreeModel(rootUserElement);
        userTree = new JTree(treeModel);
        userTree.setCellRenderer(new UserElementTreeCellRenderer());

        // Styling tree
        styleTree(userTree, 0, 0, 190, 255);

        // Tree scroll pane
        treeScrollPane = new JScrollPane(userTree);
        treeScrollPane.setBounds(10, 40, 190, 300);
        treeViewPanel.add(treeScrollPane);

        // Tree view title
        treeViewTitle = new JLabel("Users");
        styleTitleLabel(treeViewTitle, 0, 5, 210, 30);
        treeViewTitle.setForeground(Color.white);
        treeViewPanel.add(treeViewTitle);

        // Adding analysis panel
        add(analysisPanel);

        // User/group navigation panel setup
        userGroupManagementPanel = new JPanel();
        stylePanel(userGroupManagementPanel, 230, 100, 545, 225);

        // User ID text field
        userIDField = new JTextField();
        userIDField.setBounds(10, 20, 250, 35);
        userGroupManagementPanel.add(userIDField);

        // Add user button
        addUserButton = new JButton("Add User");
        styleButton(addUserButton, 275, 20, 250, 35);
        userGroupManagementPanel.add(addUserButton);

        // Group ID text field
        groupIDField = new JTextField();
        groupIDField.setBounds(10, 70, 250, 35);
        userGroupManagementPanel.add(groupIDField);

        // Add group button
        addGroupButton = new JButton("Add Group");
        styleButton(addGroupButton, 275, 70, 250, 35);
        userGroupManagementPanel.add(addGroupButton);

        // Open user view button
        openUserViewButton = new JButton("Open User View");
        styleButton(openUserViewButton, 10, 125, 515, 70);
        userGroupManagementPanel.add(openUserViewButton);

        // User ID label
        userIDLabel = new JLabel("User ID:");
        userIDLabel.setBounds(10, 5, 160, 15);
        userIDLabel.setForeground(Color.white);
        userGroupManagementPanel.add(userIDLabel);

        // Group ID label
        groupIDLabel = new JLabel("Group ID:");
        groupIDLabel.setBounds(10, 55, 160, 15);
        groupIDLabel.setForeground(Color.white);
        userGroupManagementPanel.add(groupIDLabel);

        // Making JFrame visible
        setVisible(true);
        System.out.println("Admin Panel Generated");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // Action handling based on the source of the event
        if (ae.getSource() == addUserButton) {
           addUser();
        } 
        else if (ae.getSource() == addGroupButton){
            addGroup();
        }
        else if (ae.getSource() == openUserViewButton){
            openUserView();
        }
        else if (ae.getSource() == showUserTotalButton){
            showUserTotal();
        }
        else if (ae.getSource() == showGroupTotalButton){
            showGroupTotal();
        }
        else if (ae.getSource() == showMessagesTotalButton){
            showMessagesTotal();
        }
        else if (ae.getSource() == showPositivePercentageButton){
            showPositivePercentage();
        }
    }

    // Method to add a user
    private void addUser(){
        String id = userIDField.getText();
        if (!id.equals("")){
            UserElement parent = getSelectedUserElement();
            if (treeModel.findUserByID(rootUserElement, id) == null){
                treeModel.addUserElement(parent, new User(treeModel, id));
                userIDField.setText("");
            }
            else {
                errorMessage("User Already Exists",  "Error: That username is taken.");
            }
        }
    }

    // Method to add a group
    private void addGroup(){
        String id = groupIDField.getText();
        if (!id.equals("")){
            UserElement parent = getSelectedUserElement();
            if (treeModel.findGroupByID(rootUserElement, id) == null){
                treeModel.addUserElement(parent, new Group(treeModel, id));
                groupIDField.setText("");
            }
            else {
                errorMessage("Group Already Exists",  "Error: That Group name is taken.");
            }            
        }
    }

    // Method to open user view
    private void openUserView() {
        UserElement element = getSelectedUserElement();
        element.openUserView();
    }

    // Method to show user total
    private void showUserTotal(){
        int result;
        UserElement start = getSelectedUserElement();
        UserTotalVisitor visitor = new UserTotalVisitor();
        start.accept(visitor);
        result = visitor.total;
        JOptionPane.showMessageDialog(this, "Total count of Users: " + result, "Total Messages", JOptionPane.PLAIN_MESSAGE);
        System.out.println("Total users: " + result);
    }

    // Method to show group total
    private void showGroupTotal(){
        int result;
        UserElement start = getSelectedUserElement();
        GroupTotalVisitor visitor = new GroupTotalVisitor();
        start.accept(visitor);
        result = visitor.total;
        JOptionPane.showMessageDialog(this, "Total count of Groups: " + result, "Total Groups", JOptionPane.PLAIN_MESSAGE);
        System.out.println("Total groups: " + result);
    }

    // Method to show messages total
    private void showMessagesTotal(){
        int result;
        UserElement start = getSelectedUserElement();
        MessagesTotalVisitor visitor = new MessagesTotalVisitor();
        start.accept(visitor);
        result = visitor.total;
        JOptionPane.showMessageDialog(this, "Total count of Tweets: " + result, "Total Messages", JOptionPane.PLAIN_MESSAGE);
        System.out.println("Total messages: " + result);
    }

    // Method to show positive percentage
    private void showPositivePercentage(){
        double result;
        UserElement start = getSelectedUserElement();
        PositiveMessagesTotalVisitor posTotalVisitor = new PositiveMessagesTotalVisitor();
        MessagesTotalVisitor messagesTotalVisitor = new MessagesTotalVisitor();
        start.accept(posTotalVisitor);
        start.accept(messagesTotalVisitor);
        
        result = (double)posTotalVisitor.total / (double)messagesTotalVisitor.total * 100.0;
        
        JOptionPane.showMessageDialog(this, "Percentage of Tweets containing positive messages: " + result + "%", "Positive Percentage", JOptionPane.PLAIN_MESSAGE);
        System.out.println("Positive percentage: " + result + "%");
    }

    // Method to get the selected user element from the tree
    private UserElement getSelectedUserElement(){
        UserElement result = ((UserElement)userTree.getLastSelectedPathComponent());
        if (result == null){
            result = rootUserElement;
        }
        return result;
    }
}

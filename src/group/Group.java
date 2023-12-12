package group;

import tree.UserElementTreeModel;
import user.UserElement;
import visitor.Visitor;
import java.util.ArrayList;
import java.util.Date;

public class Group implements UserElement{
    private UserElementTreeModel treeModel;                         
    private String uniqueID;                                
    private ArrayList<UserElement> children;                       
    private final String iconURL = "/resources/cloud.png"; 
    private long creationTime; // New attribute

    
    public Group(UserElementTreeModel treeModel, String uniqueID){
        this.treeModel = treeModel;
        this.uniqueID = uniqueID;
        this.creationTime = new Date().getTime(); // Initialize creationTime
        children = new ArrayList<>();
        System.out.println("Group " + uniqueID + " created at: " + new Date(creationTime));

    }
    
    @Override
    public String getUniqueID() {
        return uniqueID;
    }

    @Override
    public void add(UserElement elem) {
        children.add(elem);
    }

    @Override
    public UserElement getChild(int i) {
        return children.get(i);
    }

    @Override
    public int getIndexOfChild(UserElement elem) {
        return children.indexOf(elem);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }
    
    @Override
    public void accept(Visitor vis) {
        vis.atGroup(this);
        for (UserElement elem : children){
            elem.accept(vis);
        }
    }

    @Override
    public String getIconURL() {
        return iconURL;
    }
    
    @Override
    public void openUserView(){
        for (UserElement elem : children){
            elem.openUserView();
        }
    }
    
    @Override
    public String toString(){
        return uniqueID;
    }
    
    public long getCreationTime() {
        return creationTime;
    }

}

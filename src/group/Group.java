package group;

import tree.UserElementTreeModel;
import user.UserElement;
import visitor.Visitor;
import java.util.ArrayList;

public class Group implements UserElement{
    private UserElementTreeModel treeModel;                         
    private String uniqueID;                                
    private ArrayList<UserElement> children;                       
    private final String iconURL = "/resources/cloud.png"; 
    
    public Group(UserElementTreeModel treeModel, String uniqueID){
        this.treeModel = treeModel;
        this.uniqueID = uniqueID;
        children = new ArrayList<>();
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

}

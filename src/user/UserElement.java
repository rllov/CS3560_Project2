package user;

import visitor.Visitor;

public interface UserElement {
    
    public String getUniqueID();
    
    public void add(UserElement elem);
    
    public UserElement getChild(int i);

    public int getIndexOfChild(UserElement elem);
    
    public int getChildCount();
 
    public void accept(Visitor vis);
    
    public String getIconURL();
    
    public void openUserView();
}

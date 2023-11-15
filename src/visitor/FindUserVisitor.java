package visitor;

import group.Group;
import user.User;

public class FindUserVisitor implements Visitor {
    
    public User result;  
    private String id;     
    
    public FindUserVisitor(String searchID){
        id = searchID.toLowerCase();
    }

    @Override
    public void atUser(User e) {
        System.out.println("Searching User: " + e.getUniqueID());
        if (e.getUniqueID().toLowerCase().equals(id)){
            System.out.println("Found User: " + e.getUniqueID());
            result = e;
        }
    }

    @Override
    public void atGroup(Group e) {
        System.out.println("Searching Group: " + e.getUniqueID());
    }
    
}

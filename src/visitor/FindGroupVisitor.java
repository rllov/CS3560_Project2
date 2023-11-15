package visitor;

import group.Group;
import user.User;

public class FindGroupVisitor implements Visitor {
    
    public Group result;
    private String id;      
 
    public FindGroupVisitor(String searchID){
        id = searchID.toLowerCase();
    }

    @Override
    public void atUser(User e) {
        System.out.println("Searching User: " + e.getUniqueID());
    }

    @Override
    public void atGroup(Group e) {
        System.out.println("Searching Group: " + e.getUniqueID());
        if (e.getUniqueID().toLowerCase().equals(id)){
            System.out.println("Found Group: " + e.getUniqueID());
            result = e;
        }
    }   
}

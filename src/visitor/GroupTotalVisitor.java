package visitor;

import group.Group;
import user.User;

public class GroupTotalVisitor implements Visitor {
    
    public int total;   

    @Override
    public void atUser(User e) {
        System.out.println("Found User: " + e.getUniqueID());
    }

    @Override
    public void atGroup(Group e) {
        System.out.println("Counting Group: " + e.getUniqueID());
        total++;
    }    
}

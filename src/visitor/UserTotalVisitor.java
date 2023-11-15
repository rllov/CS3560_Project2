package visitor;

import group.Group;
import user.User;

public class UserTotalVisitor implements Visitor {
    
    public int total;   //the total number of Users counted

    @Override
    public void atUser(User e) {
        System.out.println("Counted User: " + e.getUniqueID());
        total++;
    }

    @Override
    public void atGroup(Group e) {
        System.out.println("Counting children of Group: " + e.getUniqueID());
    }    
}

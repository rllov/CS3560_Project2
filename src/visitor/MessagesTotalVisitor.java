package visitor;

import group.Group;
import user.User;

public class MessagesTotalVisitor implements Visitor {
    
    public int total;   

    @Override
    public void atUser(User e) {
        System.out.println("Counted messages from User: " + e.getUniqueID());
        total+= e.retrieveTweets().size();
    }

    @Override
    public void atGroup(Group e) {
        System.out.println("Counting children of Group: " + e.getUniqueID());
    }   
}

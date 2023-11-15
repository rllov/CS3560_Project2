package visitor;

import group.Group;
import user.User;

public interface Visitor {
    public void atUser(User e);
    public void atGroup(Group e);
}

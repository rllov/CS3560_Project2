package tree;

import visitor.FindUserVisitor;
import visitor.FindGroupVisitor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.*;

import user.UserElement;

public class UserElementTreeModel implements TreeModel{
    
    private UserElement root;                   
    private List<TreeModelListener> listeners;
    
    public UserElementTreeModel(UserElement root){
        this.root = root;
        listeners = new ArrayList<>();
    }
    
    public UserElement findUserByID(UserElement start, String id){
        FindUserVisitor vis = new FindUserVisitor(id);
        start.accept(vis);
        return vis.result;
    }
    
    public UserElement findGroupByID(UserElement start, String id){
        FindGroupVisitor vis = new FindGroupVisitor(id);
        start.accept(vis);
        return vis.result;
    }
    
    private void fireTreeStructureChanged()
    {
        Object[] o = {root};
        TreeModelEvent e = new TreeModelEvent(this, o);
        for(TreeModelListener l : listeners){
            l.treeStructureChanged(e);
        }
    }
    
    public void addUserElement(UserElement parent, UserElement elem){
        parent.add(elem);
        fireTreeStructureChanged();
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        return ((UserElement)(((UserElement) parent).getChild(index)));
    }

    @Override
    public int getChildCount(Object parent) {
        return (((UserElement) parent).getChildCount());
    }

    @Override
    public boolean isLeaf(Object node) {
        return (((UserElement) node).getChildCount() == 0);
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return (((UserElement) parent).getIndexOfChild(((UserElement) child)));
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        listeners.remove(l);
    }   
}

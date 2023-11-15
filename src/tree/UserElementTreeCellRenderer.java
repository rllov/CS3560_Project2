package tree;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

import group.Group;
import user.UserElement;

public class UserElementTreeCellRenderer implements TreeCellRenderer {
    private JLabel label;

    public UserElementTreeCellRenderer() {
        label = new JLabel();
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
                                                  boolean leaf, int row, boolean hasFocus) {
        UserElement userElem = ((UserElement) value);
        URL imageUrl = getClass().getResource(userElem.getIconURL());
            if (imageUrl != null) {
                label.setIcon(new ImageIcon(new ImageIcon(imageUrl).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
            }
            label.setText(userElem.getUniqueID() + " ");
            if (selected) {
                label.setOpaque(true);
            }
            else {
                label.setOpaque(false);
            }
            if (userElem instanceof Group){
                label.setFont(new Font("SANS_SERIF", Font.BOLD, 12));
            }
            else {
                label.setFont(new Font("SANS_SERIF", Font.PLAIN, 12));
            }

        return label;
    }
}


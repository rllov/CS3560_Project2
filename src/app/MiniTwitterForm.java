package app;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Abstract class representing the base form for Mini Twitter application.
 */
public abstract class MiniTwitterForm extends JFrame implements ActionListener {

    /**
     * Styles the given button with specified position, size, and appearance.
     */
    protected void styleButton(JButton button, int posX, int posY, int width, int height) {
        button.setBounds(posX, posY, width, height);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setBackground(new Color(0, 190, 253));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(true);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setFont(new Font("SANS_SERIF", Font.BOLD, 12));
        addButtonMouseListener(button);
        button.addActionListener(this);
    }

    /**
     * Adds a mouse listener to the button for handling mouse events.
     */
    private void addButtonMouseListener(JButton button){
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent event) {
                setBackground(button, new Color(0, 184, 255));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent event) {
                setBackground(button, new Color(0, 190, 253));
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent event) {
                setBackground(button, new Color(0, 150, 82));
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent event) {
                setBackground(button, new Color(0, 150, 82));
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent event) {
                setBackground(button, new Color(0, 190, 253));
            }

            /**
             * Sets the background color of the button.
             */
            void setBackground(JButton b, Color c) {
                if (b.isEnabled()) {
                    b.setBackground(c);
                }
            }
        });
    }

    /**
     * Styles the given panel with specified position, size, and appearance.
     */
    protected void stylePanel(JPanel panel, int posX, int posY, int width, int height) {
        panel.setBounds(posX, posY, width, height);
        panel.setLayout(null);
        panel.setBackground(Color.DARK_GRAY);
        panel.setOpaque(true);
        panel.setBorder(BorderFactory.createBevelBorder(1));
        add(panel);
    }

    /**
     * Styles the given tree with specified position, size, and appearance.
     */
    protected void styleTree(JTree tree, int posX, int posY, int width, int height) {
        tree.setBounds(posX, posY, width, height);
        tree.setLayout(null);
        tree.setBackground(Color.WHITE);
        tree.setOpaque(true);
    }

    /**
     * Styles the given label with specified position, size, and appearance.
     */
    protected void styleTitleLabel(JLabel label, int posX, int posY, int width, int height) {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("SANS_SERIF", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        label.setBounds(posX,posY,width,height);
    }

    /**
     * Displays an error message dialog with the given title and message.
     */
    public void errorMessage(String messageTitle, String messageText){
        JOptionPane.showMessageDialog(this, messageText, messageTitle, JOptionPane.ERROR_MESSAGE);
    }
}

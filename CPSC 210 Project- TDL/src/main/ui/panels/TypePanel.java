package ui.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TypePanel extends JPanel {
    private JTextField textField;

    //EFFECTS: make the type panel
    public TypePanel() {
        setLayout(new FlowLayout());
        setBackground(new Color(102, 102, 102, 255));
        setPreferredSize(new Dimension(5, 50));
        this.textField = new JTextField();
        this.textField.setPreferredSize(new Dimension(300, 30));
        add(this.textField);
    }

    //EFFECTS: return the text field
    public JTextField getTextField() {
        return textField;
    }
}

package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextFieldLabel extends JPanel {
    private Label lbTitle;
    private JTextField txtInput;
    private boolean cero;

    public TextFieldLabel(String str, boolean cero) {
        super();
        createControl(str);
        this.cero = cero;
    }

    public TextFieldLabel(String str) {
        super();
        createControl(str);
        this.cero = false;
    }

    private void createControl(String str) {
        this.setLayout(new GridLayout(1, 2));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // add this line

        lbTitle = new Label(str);
        lbTitle.setFont(new Font("Montserrat", Font.BOLD, 15));
        lbTitle.setAlignment(Label.CENTER);
        this.add(lbTitle);
        txtInput = new JTextField();
        txtInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                char c = e.getKeyChar();
                if (cero) {
                    if (!Character.isDigit(c)) {
                        e.consume(); // ignora el evento de teclado
                        return;
                    }
                } else {
                    if (!Character.isDigit(c) || c == '0') {
                        e.consume(); // ignora el evento de teclado
                        return;
                    }
                }
                try {
                    int n = Integer.parseInt(txtInput.getText() + e.getKeyChar());
                    if (n > 9 || n <= 1) {
                        txtInput.setText("");

                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }

            }

        });
        this.add(txtInput);
    }

    public String getInput() {
        return txtInput.getText();
    }

    public void setInput(String str) {
        txtInput.setText(str);
    }
}

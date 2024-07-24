package Views;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import Models.Celda;

public class CeldaPanel extends JPanel {
    private Celda celda;

    private JLabel label;
    // private boolean isAwait;

    public CeldaPanel(Celda celda) {
        super(new BorderLayout());
        // isAwait = false;
        label = new JLabel("");
        label.setHorizontalAlignment(JLabel.CENTER);

        label.setFont(new Font("Noto Sans JP", Font.BOLD, 10));
        label.setForeground(Color.BLUE);
        setCelda(celda);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        this.add(label);
        this.setBackground(Color.white);
        addActionListener();
        ;

    }

    public void addActionListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                celda.setPassable(!celda.isPassable());
                revalidate();
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (celda == null) {
            // label.setText("null");
            return;
        }
        label.setText(celda.toString());
        if (celda.isNotPath()) {
            this.setBackground(Color.red);
            label.setForeground(Color.white);
            return;
        }
        if (celda.isFinish()) {
            this.setBackground(Color.blue);
            label.setForeground(Color.white);
            return;
        }
        if (celda.isPassed()) {
            this.setBackground(Color.yellow);
            label.setForeground(Color.BLACK);

            return;
        }

        if (celda.isPassable()) {
            this.setBackground(Color.WHITE);
            label.setForeground(Color.BLUE);
            return;
        } else {
            this.setBackground(Color.DARK_GRAY);
            label.setForeground(Color.YELLOW);
            return;
        }

    }

    public Celda getCelda() {
        return celda;
    }

    public void setCelda(Celda celda) {
        this.celda = celda;

    }

    @Override
    public String toString() {
        return celda.getCelda();
    }

}

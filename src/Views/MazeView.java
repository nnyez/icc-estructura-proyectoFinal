package Views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.*;

public class MazeView extends JFrame {
    private TextFieldLabel txtRows;
    private TextFieldLabel txtCols;

    private TextFieldLabel txtStartRow;
    private TextFieldLabel txtStartCol;
    private TextFieldLabel txtFinishRow;
    private TextFieldLabel txtFinishCol;
    private JButton btnGenerate;
    private JButton btnClear;
    private JButton btnDFS;
    private JButton btnBFS;
    private JButton btnCachingRecursive;
    private JButton btnRecursive;
    private Panel centerPanel;

    private CeldaPanel[][] grid;
    Queue<CeldaPanel> shortPath = new LinkedList<>();

    public void setShortPath(Queue<CeldaPanel> shortPath) {
        this.shortPath = shortPath;
    }

    public MazeView() {
        this.setSize(1200, 800);
        this.setTitle("Laberinto");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });
        JPanel topPanel = new JPanel(new GridLayout(1, 4));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // add
        JPanel gridPanel = new JPanel(new GridLayout(2, 1));

        txtCols = new TextFieldLabel("Columns:");
        txtRows = new TextFieldLabel("Rows:");
        gridPanel.add(txtRows);
        gridPanel.add(txtCols);

        topPanel.add(gridPanel);

        JPanel startPanel = new JPanel(new GridLayout(2, 2));
        JPanel FinishPanel = new JPanel(new GridLayout(2, 2));
        txtStartRow = new TextFieldLabel("StartRow:", true);
        startPanel.add(txtStartRow);
        txtStartCol = new TextFieldLabel("StartColumn:", true);
        startPanel.add(txtStartCol);
        txtFinishRow = new TextFieldLabel("FinishRow:", true);
        FinishPanel.add(txtFinishRow);
        txtFinishCol = new TextFieldLabel("FinishColumn:", true);
        FinishPanel.add(txtFinishCol);

        topPanel.add(startPanel);
        topPanel.add(FinishPanel);

        JPanel btnsPanel = new JPanel(new GridLayout(2, 1));

        btnGenerate = new JButton("Generate");
        btnsPanel.add(btnGenerate);
        btnClear = new JButton("Clear");
        btnsPanel.add(btnClear);
        topPanel.add(btnsPanel);

        this.add(topPanel, BorderLayout.NORTH);

        centerPanel = new Panel();
        centerPanel.setBackground(Color.BLACK);
        this.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(2, 2));
        btnDFS = new JButton("DFS");
        bottomPanel.add(btnDFS);
        btnBFS = new JButton("BFS");
        bottomPanel.add(btnBFS);
        btnCachingRecursive = new JButton("Caching Recursive");
        bottomPanel.add(btnCachingRecursive);
        btnRecursive = new JButton("Recursive");
        bottomPanel.add(btnRecursive);
        this.add(bottomPanel, BorderLayout.SOUTH);

    }

    public CeldaPanel[][] getGrid() {
        return grid;
    }

    public void setGrid(CeldaPanel[][] grid) {
        this.grid = grid;
    }

    public Panel getCenterPanel() {
        return centerPanel;
    }

    public void setCenterPanel(Panel centerPanel) {
        this.centerPanel = centerPanel;
    }

    public String getRows() {
        return txtRows.getInput();
    }

    public String getCols() {
        return txtCols.getInput();
    }

    public int[] getStart() {
        try {
            int row = Integer.parseInt(txtStartRow.getInput());
            int column = Integer.parseInt(txtStartCol.getInput());
            return new int[] { row, column };
        } catch (Exception e) {
            // TODO: handle exception
        }

        return new int[] { 0, 0 };
    }

    public int[] getFinish() {
        try {
            int row = Integer.parseInt(txtFinishRow.getInput());
            int column = Integer.parseInt(txtFinishCol.getInput());
            return new int[] { row, column };
        } catch (Exception e) {
            // TODO: handle exception
        }

        return new int[] { grid.length - 1, grid[0].length - 1 };
    }

    public void setRows(String txtRows) {
        this.txtRows.setInput(txtRows);
    }

    public void setCols(String txtCols) {
        this.txtCols.setInput(txtCols);
    }

    public void addGenerateAction(ActionListener listener) {
        btnGenerate.addActionListener(listener);
    }

    public void addClearAction(ActionListener listener) {
        btnClear.addActionListener(listener);
    }

    public void addDFSAction(ActionListener listener) {
        btnDFS.addActionListener(listener);
    }

    public void addBFSAction(ActionListener listener) {
        btnBFS.addActionListener(listener);
    }

    public void addRecursiveAction(ActionListener listener) {
        btnRecursive.addActionListener(listener);
    }

    public void addCachingRecursiveAction(ActionListener listener) {
        btnCachingRecursive.addActionListener(listener);
    }

    boolean isPath;

    public void showPath(Queue<CeldaPanel> path, String time) {
        isPath = false;
        Queue<CeldaPanel> copy = new LinkedList<>(path);
        Timer timer = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!path.isEmpty()) {
                    CeldaPanel celda = path.remove();
                    celda.repaint();
                    if (celda.getCelda().getRow() == getFinish()[0] && celda.getCelda().getColumn() == getFinish()[1]) {
                        isPath = true;
                    }
                } else {
                    // El camino se ha mostrado completamente
                    ((Timer) e.getSource()).stop(); // Detener el timer
                    if (!isPath) {
                        JOptionPane.showMessageDialog(null, "No path found \n " + copy + "\n" + "Time: " + time);
                    } else {
                        JOptionPane.showMessageDialog(null, "Path found \n " + copy + "\n" + "Time: " + time);

                    }
                    if (!shortPath.isEmpty()) {
                        showPath(shortPath);

                    }
                }
            }
        });

        // Iniciar el timer
        timer.start();
    }

    public void showPath(Queue<CeldaPanel> shortPath) {
        isPath = false;
        Queue<CeldaPanel> copy = new LinkedList<>(shortPath);
        Timer timer = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!shortPath.isEmpty()) {
                    CeldaPanel celda = shortPath.remove();
                    celda.getCelda().setFinish(true);
                    celda.repaint();

                    if (celda.getCelda().getRow() == getFinish()[0] && celda.getCelda().getColumn() == getFinish()[1]) {
                        isPath = true;
                    }
                } else {
                    // El camino se ha mostrado completamente
                    ((Timer) e.getSource()).stop(); // Detener el timer
                    if (!isPath) {
                        JOptionPane.showMessageDialog(null, "No path found \n " + copy);
                    } else {
                        JOptionPane.showMessageDialog(null, "ShortPath found \n " + copy);

                    }
                }
            }
        });

        // Iniciar el timer
        timer.start();
    }

}

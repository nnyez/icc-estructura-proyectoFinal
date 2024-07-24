package Controllers;

import java.awt.event.*;

import javax.swing.JOptionPane;

import Models.Celda;

import java.awt.*;

import java.util.Queue;

import Services.MazeService;
import Views.CeldaPanel;
import Views.MazeView;

public class MazeController {
    private MazeService maze;
    private MazeView view;

    public MazeController(MazeService maze, MazeView view) {
        this.maze = maze;
        this.view = view;

        this.view.addBFSAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BFSAction();
            }

        });
        this.view.addDFSAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DFSAction();
            }

        });
        this.view.addCachingRecursiveAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cachingRecursiveAction();
            }

        });
        this.view.addGenerateAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateAction();
            }

        });
        this.view.addRecursiveAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recursiveAction();
            }

        });
        this.view.addClearAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAction();
            }

        });
        this.view.setVisible(true);
        this.view.setRows("4");
        this.view.setCols("4");
        generateAction();
    }

    private void BFSAction() {
        if (view.getGrid() == null) {
            JOptionPane.showMessageDialog(view, "Se debe generar una cuadricula", "Advertencia", 0);
            return;
        }

        Queue<CeldaPanel> path = maze.BFS(view.getGrid(), view.getStart(), view.getFinish());
        Queue<CeldaPanel> shortPath = maze.getShortPath(view.getGrid());

        view.showPath(path, maze.getTime());
        view.setShortPath(shortPath);
    }

    private void DFSAction() {
        if (view.getGrid() == null) {
            JOptionPane.showMessageDialog(view, "Se debe generar una cuadricula", "Advertencia", 0);
            return;
        }

        Queue<CeldaPanel> path = maze.DFS(view.getGrid(), view.getStart(), view.getFinish());

        view.showPath(path, maze.getTime());
    }

    private void cachingRecursiveAction() {
        if (view.getGrid() == null) {
            JOptionPane.showMessageDialog(view, "Se debe generar una cuadricula", "Advertencia", 0);
            return;
        }

        Queue<CeldaPanel> path = maze.recursiveCaching(view.getGrid(), view.getStart(), view.getFinish());

        view.showPath(path, maze.getTime());
    }

    private void generateAction() {
        int rows = Integer.parseInt(view.getRows());
        int col = Integer.parseInt(view.getCols());

        CeldaPanel[][] grid = new CeldaPanel[rows][col];
        GridLayout layout = new GridLayout(rows, col);

        Panel centerPanel = view.getCenterPanel();
        centerPanel.setLayout(layout);
        view.setGrid(null);
        centerPanel.removeAll();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                CeldaPanel p = new CeldaPanel(new Celda(j, i, true));
                centerPanel.add(p);
                grid[i][j] = p;
            }
        }

        // Repintar y revalidar el panel
        view.setGrid(grid);

        centerPanel.revalidate();
        centerPanel.repaint();
    }

    private void recursiveAction() {
        if (view.getGrid() == null) {
            JOptionPane.showMessageDialog(view, "Se debe generar una cuadricula", "Advertencia", 0);
            return;
        }

        Queue<CeldaPanel> path = maze.recursive(view.getGrid(), view.getStart(), view.getFinish());

        view.showPath(path, maze.getTime());

    }

    private void clearAction() {
        CeldaPanel[][] grid = view.getGrid();
        int rows = grid.length;
        int columns = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                CeldaPanel panel = grid[i][j];
                if (panel.getCelda().isNotPath() || panel.getCelda().isPassed() || panel.getCelda().isFinish()) {
                    panel.getCelda().setNotPath(false);
                    panel.getCelda().setPassed(false);
                    panel.getCelda().setFinish(false);
                }
                panel.repaint();
            }
        }
    }
}

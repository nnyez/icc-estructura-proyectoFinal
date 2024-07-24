package Services;

import java.util.*;

import Views.CeldaPanel;

public class MazeService {

    Queue<CeldaPanel> shortPath = new LinkedList<>();
    private long start;
    private long end;

    public String getTime() {
        long nanoseconds = end - start;
        double seconds = nanoseconds / 1_000_000_000.0; // Convert nanoseconds to seconds

        // Formatear los segundos con decimales
        String formattedTime = String.format("%.9f", seconds); // Ajusta el número de decimales según lo necesites

        return formattedTime;
    }

    public Queue<CeldaPanel> getShortPath(CeldaPanel[][] maze) {
        return shortPath;
    }

    public Queue<CeldaPanel> recursive(CeldaPanel[][] maze, int[] start, int[] finish) {
        Queue<CeldaPanel> list = new LinkedList<>();
        this.start = System.nanoTime();
        recursive(maze.clone(), list, finish, start[0], start[1]);
        this.end = System.nanoTime();

        return list;
    }

    private boolean recursive(CeldaPanel[][] grid, Queue<CeldaPanel> list, int[] finish, int row,
            int col) {

        if (!grid[row][col].getCelda().isPassable() || grid[row][col].getCelda().isPassed()) {
            return false;
        }

        boolean isEnd = (row == finish[0] && col == finish[1]);
        boolean isTruePath = false;
        CeldaPanel cel = grid[row][col];

        cel.getCelda().setPassed(true);
        list.add(cel);

        if (isEnd) {
            isTruePath = true;
            cel.getCelda().setFinish(true);
            return isTruePath;
        }
        if (row + 1 < grid.length) {
            if (recursive(grid, list, finish, row + 1, col)) {
                isTruePath = true;
                return isTruePath;

            }
        }
        if (col + 1 < grid[0].length) {
            if (recursive(grid, list, finish, row, col + 1)) {
                isTruePath = true;
                return isTruePath;

            }
        }
        if (row - 1 >= 0) {
            if (recursive(grid, list, finish, row - 1, col)) {
                isTruePath = true;
                return isTruePath;

            }
        }
        if (col - 1 >= 0) {
            if (recursive(grid, list, finish, row, col - 1)) {
                isTruePath = true;
                return isTruePath;

            }
        }

        cel.getCelda().setNotPath(true);

        return false;
    }

    public Queue<CeldaPanel> recursiveCaching(CeldaPanel[][] grid, int[] start, int[] finish) {
        Queue<CeldaPanel> list = new LinkedList<>();
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return null;
        }
        this.start = System.nanoTime();
        if (recursiveCaching(grid, list, new HashMap<>(), finish, start[0], start[1])) {
            this.end = System.nanoTime();

            return list;
        }
        return null;

    }

    private boolean recursiveCaching(CeldaPanel[][] grid, Queue<CeldaPanel> list, HashMap<CeldaPanel, Boolean> cache,
            int[] finish,
            int row,
            int col) {
        if (row < 0 || col < 0 || row > grid.length - 1 || col > grid[0].length - 1) {
            return false;
        }
        if (cache.containsKey(grid[row][col])) {
            return false;
        }
        if (!grid[row][col].getCelda().isPassable()) {
            return false;
        }

        grid[row][col].getCelda().setPassed(true);
        boolean isEnd = (row == finish[0] && col == finish[1]);
        if (isEnd) {
            grid[row][col].getCelda().setFinish(true);
        }
        boolean isTruePath = false;
        list.add(grid[row][col]);
        cache.put(grid[row][col], false);

        if (isEnd || recursiveCaching(grid, list, cache, finish, row + 1, col)
                || recursiveCaching(grid, list, cache, finish, row, col + 1)
                || recursiveCaching(grid, list, cache, finish, row, col - 1)
                || recursiveCaching(grid, list, cache, finish, row - 1, col)) {

            isTruePath = true;
        }

        if (!isTruePath) {
            grid[row][col].getCelda().setNotPath(true);
        }

        return isTruePath;

    }

    public Queue<CeldaPanel> BFS(CeldaPanel[][] maze, int[] start, int[] finish) {
        Queue<CeldaPanel> queue = new LinkedList<>();
        Queue<CeldaPanel> list = new LinkedList<>();
        this.start = System.nanoTime();
        queue.add(maze[start[0]][start[1]]);

        Map<CeldaPanel, CeldaPanel> parent = new HashMap<>();
        CeldaPanel end = null;

        while (!queue.isEmpty()) {
            CeldaPanel currentVertex = queue.poll();
            int r = currentVertex.getCelda().getRow();
            int c = currentVertex.getCelda().getColumn();
            list.add(currentVertex);

            if (r >= maze.length || c >= maze[0].length || r < 0 || c < 0 || !maze[r][c].getCelda().isPassable()
                    || maze[r][c].getCelda().isPassed()) {
                continue;
            }

            boolean isEnd = (r == finish[0] && c == finish[1]);
            if (isEnd) {
                end = currentVertex;
                currentVertex.getCelda().setFinish(true);
                break;
            }

            if (r + 1 < maze.length) {
                if (maze[r + 1][c].getCelda().isPassable() && !maze[r + 1][c].getCelda().isPassed()) {
                    queue.add(maze[r + 1][c]);
                    parent.put(maze[r + 1][c], currentVertex);
                }
            }
            if (c + 1 < maze[0].length) {
                if (maze[r][c + 1].getCelda().isPassable() && !maze[r][c + 1].getCelda().isPassed()) {
                    queue.add(maze[r][c + 1]);
                    parent.put(maze[r][c + 1], currentVertex);
                }
            }
            if (r - 1 >= 0) {
                if (maze[r - 1][c].getCelda().isPassable() && !maze[r - 1][c].getCelda().isPassed()) {
                    queue.add(maze[r - 1][c]);
                    parent.put(maze[r - 1][c], currentVertex);
                }
            }
            if (c - 1 >= 0) {
                if (maze[r][c - 1].getCelda().isPassable() && !maze[r][c - 1].getCelda().isPassed()) {
                    queue.add(maze[r][c - 1]);
                    parent.put(maze[r][c - 1], currentVertex);
                }
            }

            currentVertex.getCelda().setPassed(true);
        }

        // Reconstruir el camino más corto
        shortPath = new LinkedList<>();
        while (end != null) {
            shortPath.add(end);
            end = parent.get(end);
        }

        this.end = System.nanoTime();
        return list;
    }

    public Queue<CeldaPanel> DFS(CeldaPanel[][] grid, int[] start, int[] finish) {
        Queue<CeldaPanel> list = new LinkedList<>();
        this.start = System.nanoTime();
        DFS(grid.clone(), list, finish, start[0], start[1]);
        this.end = System.nanoTime();

        return list;
    }

    private void DFS(CeldaPanel[][] grid, Queue<CeldaPanel> list, int[] finish, int row, int col) {
        if (grid[row][col].getCelda().isPassed() && !grid[row][col].getCelda().isPassable()) {
            return;
        }
        grid[row][col].getCelda().setPassed(true);

        list.add(grid[row][col]);

        LinkedList<CeldaPanel> i = new LinkedList<>();
        boolean isEnd = (row == finish[0] && col == finish[1]);
        if (isEnd) {
            grid[row][col].getCelda().setFinish(true);
            return;
        }

        if (row + 1 < grid.length) {
            i.add(grid[row + 1][col]);
        }
        if (col + 1 < grid[0].length) {
            i.add(grid[row][col + 1]);
        }
        if (row - 1 >= 0) {
            i.add(grid[row - 1][col]);
        }
        if (col - 1 >= 0) {
            i.add(grid[row][col - 1]);
        }
        boolean hasNeight = false;
        while (!i.isEmpty()) {
            CeldaPanel neighbor = i.pop();
            if (!neighbor.getCelda().isPassed() && neighbor.getCelda().isPassable()) {
                DFS(grid, list, finish, neighbor.getCelda().getRow(), neighbor.getCelda().getColumn());
                hasNeight = true;
            }
        }
        if (!hasNeight) {
            grid[row][col].getCelda().setNotPath(true);
        }

    }
}

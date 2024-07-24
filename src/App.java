import Controllers.MazeController;
import Services.MazeService;
import Views.MazeView;

public class App {
    public static void main(String[] args) throws Exception {
        // MazeService n = new MazeService();
        // Celda[][] m = {
        // { new Celda(0, 0, true), new Celda(1, 0, true), new Celda(2, 0, true) },
        // { new Celda(0, 1, false), new Celda(1, 1, true), new Celda(2, 1, false) },
        // { new Celda(0, 2, false), new Celda(1, 2, true), new Celda(2, 2, true) }
        // };
        // // System.out.println(n.recursive(m.clone()));
        // // n.BFS(m.clone());

        new MazeController(new MazeService(), new MazeView());

    }
}

package lab11.graphs;

import java.util.Random;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int[] cameFrom = new int[maze.V()];
    private boolean foundCircle;

    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        Random rand = new Random();
        int startX = rand.nextInt(maze.N());
        int startY = rand.nextInt(maze.N());
        int s = maze.xyTo1D(startX, startY);
        cameFrom[s] = s;
        dfs(s);
        announce();
    }

    private void dfs(int v) {
        marked[v] = true;
        announce();

        for (int w : maze.adj(v)) {
            if (foundCircle) {
                return;
            }
            if (!marked[w]) {
                cameFrom[w] = v;
                dfs(w);
            } else if (w != cameFrom[v]) {
                cameFrom[w] = v;
                int cur = v;
                edgeTo[cur] = cameFrom[cur];
                while (cur != w) {
                    cur = cameFrom[cur];
                    edgeTo[cur] = cameFrom[cur];
                }
                foundCircle = true;
                return;
            }
        }
    }

}


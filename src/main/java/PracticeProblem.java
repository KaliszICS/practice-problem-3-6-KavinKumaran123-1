import java.util.*;

public class PracticeProblem {

	public static void main(String args[]) {

	}

	// ---------------- MIN MOVES (BFS) ----------------
	public static int searchMazeMoves(String[][] maze) {
		int rows = maze.length;
		int cols = maze[0].length;

		int startRow = rows - 1;
		int startCol = 0;

		boolean[][] visited = new boolean[rows][cols];
		Queue<int[]> queue = new LinkedList<>();

		queue.add(new int[]{startRow, startCol, 0});
		visited[startRow][startCol] = true;

		// ONLY UP and RIGHT
		int[][] directions = {
			{-1, 0}, // up
			{0, 1}   // right
		};

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			int moves = curr[2];

			if (maze[r][c].equals("F")) {
				return moves;
			}

			for (int[] d : directions) {
				int nr = r + d[0];
				int nc = c + d[1];

				if (nr >= 0 && nr < rows && nc >= 0 && nc < cols &&
					!visited[nr][nc] && !maze[nr][nc].equals("*")) {

					visited[nr][nc] = true;
					queue.add(new int[]{nr, nc, moves + 1});
				}
			}
		}

		return -1;
	}

	// ---------------- NUMBER OF PATHS (DFS) ----------------
	public static int noOfPaths(String[][] maze) {
		int rows = maze.length;
		int cols = maze[0].length;

		int startRow = rows - 1;
		int startCol = 0;

		boolean[][] visited = new boolean[rows][cols];

		return dfs(maze, startRow, startCol, visited);
	}

	private static int dfs(String[][] maze, int r, int c, boolean[][] visited) {
		int rows = maze.length;
		int cols = maze[0].length;

		if (r < 0 || r >= rows || c < 0 || c >= cols ||
			maze[r][c].equals("*") || visited[r][c]) {
			return 0;
		}

		if (maze[r][c].equals("F")) {
			return 1;
		}

		visited[r][c] = true;

		int paths = 0;

		// ONLY UP and RIGHT
		paths += dfs(maze, r - 1, c, visited);
		paths += dfs(maze, r, c + 1, visited);

		visited[r][c] = false;

		return paths;
	}
}
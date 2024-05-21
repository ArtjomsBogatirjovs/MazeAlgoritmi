
import java.util.*;

public class MainAlgorithm {
    //Artjoms Bogatirjovs FINAL ASSIGNMENT
    private static final String MAZE_FILE = "maze.txt";
    private static final String MAZE_FILE_11_X_11 = "maze_11x11.txt";
    private static final String MAZE_FILE_31_X_31 = "maze_31x31.txt";
    private static final String MAZE_FILE_101_X_101 = "maze_101x101.txt";

    private static final int ALL_POSSIBLE_DIRECTIONS = 8;//nubmer of directions
    private static final int NO_SOLUTION = -1; // if algorithm can't find, then return this value

    // Class to represent each cell in the maze
    private static class MazeCell {
        int x, y;
        int coins;
        MazeCell parent;

        MazeCell(int x, int y, int coins, MazeCell parent) {
            this.x = x;
            this.y = y;
            this.coins = coins;
            this.parent = parent;
        }
    }

    // Directions for 8 possible moves including diagonals
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) {
        Tests.runTests();
        String[] mazesNames = new String[]{MAZE_FILE, MAZE_FILE_11_X_11, MAZE_FILE_31_X_31, MAZE_FILE_101_X_101};

        for (String mazeName : mazesNames) {
            // Read the maze from the input file
            char[][] maze = FileUtils.fileToCharArray(mazeName);

            MazeCell start = null;
            MazeCell goal = null;

            for (int i = 0; i < maze.length; i++) { //find the start and goal point
                for (int j = 0; j < maze[0].length; j++) {
                    if (maze[i][j] == 'S') {
                        start = new MazeCell(i, j, 0, null);//in start 0 coins
                    } else if (maze[i][j] == 'G') {
                        goal = new MazeCell(i, j, 0, null);// and in end 0 coins, so algorithm always want to go here ))
                    }
                    if (start != null && goal != null) {//stop iterate when find
                        break;
                    }
                }
            }

            if (start == null || goal == null) { //if not S or G then stop algorithm
                System.out.println("Maze must have a start (S) and a goal (G) point. " + mazeName);
                continue;
            }

            int minCoins = findPathWithLeastCoins(maze, start, goal);// Find the path with least coins collected

            if (minCoins == NO_SOLUTION) {// if no solution
                System.out.println("No path found from S to G.");
            } else {
                System.out.println("In " + mazeName + ". Minimum coins collected: " + minCoins);
            }
        }
    }

    // Function to find the path with the least coins collected
    //I will not print in console path, but here is my results
//    In maze.txt. Minimum coins collected: 33
//    In maze_11x11.txt. Minimum coins collected: 99
//    In maze_101x101.txt. Minimum coins collected: 1763
//    In maze_101x101.txt. Minimum coins collected: 1763
    //Time Complexity: O(n * m). n,m - columns and rows
    private static int findPathWithLeastCoins(char[][] maze, MazeCell start, MazeCell goal) {
        int rows = maze.length;
        int cols = maze[0].length;
        boolean[][] visited = new boolean[rows][cols];
        //priority queue in the best for this algorithm, collection that make order by coins
        PriorityQueue<MazeCell> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.coins)); // https://www.geeksforgeeks.org/priority-queue-class-in-java/
        queue.add(start);

        while (!queue.isEmpty()) {
            MazeCell current = queue.poll();

            if (visited[current.x][current.y]) {
                continue;
            }
            visited[current.x][current.y] = true;

            // Check if we reached the goal
            if (current.x == goal.x && current.y == goal.y) {
                return current.coins;
            }

            // Explore all possible moves
            for (int i = 0; i < ALL_POSSIBLE_DIRECTIONS; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];

                if (isValidMove(newX, newY, rows, cols)
                        && maze[newX][newY] != 'X' //not the 'wall'
                        && !visited[newX][newY]) {//not visited
                    int newCoins = current.coins + (Character.isDigit(maze[newX][newY]) ? Character.getNumericValue(maze[newX][newY]) : 0); //calculate coins for new move
                    queue.add(new MazeCell(newX, newY, newCoins, current));//add in queue possible move
                }
            }
        }
        return NO_SOLUTION; //No path found
    }

    // check if a move is valid, is in range of maze
    private static boolean isValidMove(int x, int y, int rows, int cols) {
        return x >= 0 && y >= 0 && x < rows && y < cols;
    }
}

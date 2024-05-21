import java.io.FileWriter;
import java.io.IOException;

public class Tests {
    //Artjoms Bogatirjovs FINAL ASSIGNMENT
    //Here i didn't write much comments, because this is optional thing - just for me to make sure that my methods at least works correctly.
    private static final String MAZE_FILE = "testMaze.txt";

    //run all tests
    public static void runTests() {
        System.out.println("TEST STARTING");
        test1();
        // test2();
        //test3();
        // test4();
        System.out.println("TEST END\n");
    }

    private static void test1() {
        String fileName = MAZE_FILE;

        // Create a test maze file
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("G503746\n");
            writer.write("0XXXXX4\n");
            writer.write("3XS6138\n");
            writer.write("3XXXXXX\n");
            writer.write("1X19736\n");
            writer.write("5XXXXX8\n");
            writer.write("3375042\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Test the fileToCharArray method
        try {
            char[][] actualMaze = FileUtils.fileToCharArray(fileName);

            // Expected actualMaze representation
            char[][] expectedMaze = {
                    {'G', '5', '0', '3', '7', '4', '6'},
                    {'0', 'X', 'X', 'X', 'X', 'X', '4'},
                    {'3', 'X', 'S', '6', '1', '3', '8'},
                    {'3', 'X', 'X', 'X', 'X', 'X', 'X'},
                    {'1', 'X', '1', '9', '7', '3', '6'},
                    {'5', 'X', 'X', 'X', 'X', 'X', '8'},
                    {'3', '3', '7', '5', '0', '4', '2'}
            };

            // Check if the actualMaze is correctly converted
            boolean isCorrect = true;
            if (actualMaze.length != expectedMaze.length) {
                isCorrect = false;
            } else {
                for (int i = 0; i < actualMaze.length; i++) {
                    if (actualMaze[i].length != expectedMaze[i].length) {
                        isCorrect = false;
                        break;
                    }
                    for (int j = 0; j < actualMaze[i].length; j++) {
                        if (actualMaze[i][j] != expectedMaze[i][j]) {
                            isCorrect = false;
                            break;
                        }
                    }
                }
            }

            if (isCorrect) {
                System.out.println("Test passed!");
            } else {
                throw new RuntimeException("Test failed!");
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}

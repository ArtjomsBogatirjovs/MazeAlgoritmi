/*
 * @author ArtjomsBogatirjovs
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {
    //Artjoms Bogatirjovs FINAL ASSIGNMENT

    public static char[][] fileToCharArray(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            List<char[]> lines = new ArrayList<>();

            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine().toCharArray());
            }
            scanner.close();
            return lines.toArray(new char[0][0]);
        } catch (IOException e) {
         throw new RuntimeException("Error reading file!");
        }
    }
}

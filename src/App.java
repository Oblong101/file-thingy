import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File file = null;
        while (true) {
            try {
                System.out.print("File path: ");
                String filePath = scanner.next();
                if (new File(filePath).exists()) {
                    file = new File(filePath);
                    break;
                } else {
                    System.out.print("That file does not exist. Would you like to make it? (y/n) ");
                    String answer = scanner.next();
                    while (true) {
                        if (Objects.equals(answer.toLowerCase(), "y")) {
                            File f = new File(filePath);
                            f.createNewFile();
                            file = f;
                            break;
                        } else {
                            break;
                        }
                    }
                    continue;
                }
            } catch (Exception e) {
                System.err.println("An error has occurred.");
            }
            break;
        }

        while (true) {
            System.out.print("Say r to read, w to write, or d to delete: ");
            String mode = scanner.next();
            if (Objects.equals(mode.toLowerCase(), "r")) {
                ArrayList<String> content = readFile(file);
                for (String line : content) {
                    System.out.println(line);
                }
                break;
            }
            if (Objects.equals(mode.toLowerCase(), "w")) {
                ArrayList<String> lines = new ArrayList<String>();
                System.out.println("Say :q to stop writing.");
                while (true) {
                    String nl = scanner.nextLine();
                    if (Objects.equals(nl.toLowerCase(), ":q")) {
                        break;
                    }
                    if (Objects.equals(nl, "\n")) {
                        continue;
                    } else {
                        lines.add(nl);
                    }
                }
                writeToFile(file, lines);
                System.out.println("Successfully wrote to file.");
                break;
            }
            if (Objects.equals(mode.toLowerCase(), "d")) {
                try {
                    file.delete();
                    System.out.println("File deleted.");
                    break;
                } catch (Exception e) {
                    System.err.println("Failed to delete file.");
                    break;
                }
            } else {
                continue;
            }
        }
        System.out.println();
        scanner.close();
    }

    public static ArrayList<String> readFile(File file) {
        try {
            Scanner fileReader = new Scanner(file);
            ArrayList<String> lines = new ArrayList<String>();
            while (fileReader.hasNextLine()) {
                lines.add(fileReader.nextLine());
            }
            fileReader.close();
            return lines;
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            return null;
        }
    }

    public static void writeToFile(File file, ArrayList<String> content) {
        try {
            PrintWriter fw = new PrintWriter(new FileWriter(file, true));
            for (String line : content) {
                fw.append(line + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("File not found.");
            return;
        }
    }
}
import java.io.*;
import java.util.Scanner;

public class NotesApp {
    private static final String FILE_NAME = "notes.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Notes App ===");
            System.out.println("1. Write Note");
            System.out.println("2. View Notes");
            System.out.println("3. Clear Notes");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> writeNote();
                case 2 -> readNotes();
                case 3 -> clearNotes();
                case 4 -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }

        System.out.println("Goodbye!");
    }

    private static void writeNote() {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(note + System.lineSeparator());
            System.out.println("Note saved.");
        } catch (IOException e) {
            System.out.println("Error writing note: " + e.getMessage());
        }
    }

    private static void readNotes() {
        System.out.println("\n--- Your Notes ---");

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean hasContent = false;
            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
                hasContent = true;
            }
            if (!hasContent) {
                System.out.println("(No notes yet)");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes file found.");
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
    }

    private static void clearNotes() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(""); // overwrite file with empty content
            System.out.println("All notes cleared.");
        } catch (IOException e) {
            System.out.println("Error clearing notes: " + e.getMessage());
        }
    }
}

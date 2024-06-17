import java.io.*;

public class FileSystemSimulator {
    private static final String JOURNAL_FILE = "journal.txt";

    public static void main(String[] args) {
        try {
            createFile("arquivonovo.txt");
            log("CREATE_FILE", "arquivonovo.txt");

            listFiles(".");
            log("LIST_FILES", ".");

            renameFile("arquivonovo.txt", "arquivoalterado.txt");
            log("RENAME_FILE", "arquivonovo.txt to arquivoalterado.txt");

            createDirectory("diretorionovo");
            log("CREATE_DIRECTORY", "diretorionovo");

            listFiles(".");
            log("LIST_FILES", ".");

            deleteFile("arquivoalterado.txt");
            log("DELETE_FILE", "arquivoalterado.txt");

            deleteDirectory("diretorionovo");
            log("DELETE_DIRECTORY", "diretorionovo");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void createFile(String path) throws IOException {
        File file = new File(path);
        if (file.createNewFile()) {
            System.out.println("File created: " + file.getName());
        } else {
            System.out.println("File already exists.");
        }
    }

    public static void deleteFile(String path) {
        File file = new File(path);
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    public static void renameFile(String oldPath, String newPath) {
        File oldFile = new File(oldPath);
        File newFile = new File(newPath);
        if (oldFile.renameTo(newFile)) {
            System.out.println("File renamed to: " + newFile.getName());
        } else {
            System.out.println("Failed to rename file.");
        }
    }

    public static void listFiles(String directoryPath) {
        File directory = new File(directoryPath);
        String[] files = directory.list();
        if (files != null) {
            for (String file : files) {
                System.out.println(file);
            }
        } else {
            System.out.println("The directory is empty or does not exist.");
        }
    }

    public static void createDirectory(String path) {
        File directory = new File(path);
        if (directory.mkdir()) {
            System.out.println("Directory created: " + directory.getName());
        } else {
            System.out.println("Failed to create directory.");
        }
    }

    public static void deleteDirectory(String path) {
        File directory = new File(path);
        if (directory.delete()) {
            System.out.println("Deleted the directory: " + directory.getName());
        } else {
            System.out.println("Failed to delete the directory.");
        }
    }

    public static void renameDirectory(String oldPath, String newPath) {
        File oldDirectory = new File(oldPath);
        File newDirectory = new File(newPath);
        if (oldDirectory.renameTo(newDirectory)) {
            System.out.println("Directory renamed to: " + newDirectory.getName());
        } else {
            System.out.println("Failed to rename directory.");
        }
    }

    public static void log(String operation, String details) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(JOURNAL_FILE, true))) {
            writer.write(operation + ": " + details);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the journal.");
            e.printStackTrace();
        }
    }
}

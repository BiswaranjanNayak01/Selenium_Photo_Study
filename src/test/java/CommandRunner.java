import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CommandRunner {

    public static int runCommand(String command) {
        try {
            // Split the command into parts
            List<String> commandParts = List.of(command.split("\\s+"));

            // Create a ProcessBuilder with the command parts
            ProcessBuilder processBuilder = new ProcessBuilder(commandParts);

            // Redirect the error stream to the output stream
            processBuilder.redirectErrorStream(true);

            // Start the process
            Process process = processBuilder.start();

            // Read the output of the command
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            // Wait for the process to finish
            int exitCode = process.waitFor();

            // Return the exit code
            return exitCode;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return -1; // Return a non-zero value to indicate an error
        }
    }

    public static void main(String[] args) {
        // Specify the command you want to run
        String command = "ls -l"; // Example command: list files in the current directory

        // Run the command
        int exitCode = runCommand("java -version");

        // Print the exit code
        System.out.println("Exit code: " + exitCode);
    }
    @Test
    public static void main() {
        int x=5; int y=0;
        for (int i=0;i<=x;i++){
            System.out.println("Y :: "+y++);
            x=x+5;
        }
    }
}

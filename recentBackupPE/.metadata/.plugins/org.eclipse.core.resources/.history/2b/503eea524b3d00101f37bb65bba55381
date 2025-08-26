package testingpython;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
public class PythonScriptEditor {
    public static void replaceInPythonScript(String scriptPath, String text, String outputPath) {
        try {
                // Read the script file
                String content = Files.readString(Paths.get(scriptPath));
                // Escape quotes and backslashes
                String escapedText = text.replace("\\", "\\\\").replace("\"", "\\\"");
                String escapedPath = outputPath.replace("\\", "\\\\").replace("\"", "\\\"");
                // Replace 'text = "...' using correct regex
                content = content.replaceAll(
                    "(?m)^\\stext\\s=\\s*\"[^\"]*\"",
                    "text = \"" + escapedText + "\""
                );
                // Replace 'output_file = "...' using correct regex
                content = content.replaceAll(
                    "(?m)^\\soutput_file\\s=\\s*\"[^\"]*\"",
                    "output_file = \"" + escapedPath + "\""
                );
                // Write updated content back to the file
                Files.writeString(Paths.get(scriptPath), content, StandardOpenOption.TRUNCATE_EXISTING);
                System.out.println(":white_tick: Script updated successfully.");
            } catch (IOException e) {
                System.err.println(":x: Error updating script: " + e.getMessage());
            }
        }
    // Example usage
    public static void main(String[] args) {
        String pythonScript = "C:\\Users\\User\\Downloads\\pythonspeechtovoice.py";
        String inputText = "naane execute madthirodu";
        String audioOutputPath = "C:\\Users\\User\\Downloads\\ExtractedSpeech.wav";
        replaceInPythonScript(pythonScript, inputText, audioOutputPath);
    }
}
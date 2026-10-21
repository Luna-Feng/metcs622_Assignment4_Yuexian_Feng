
import java.io.File;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class RunScriptWithProcess {

  public static void main(String[] args) {

    // Step 1: Find image files using a Lambda expression
    File[] images = new File("pics").listFiles(file ->
        file.isFile() &&
            (file.getName().toLowerCase().endsWith(".jpg") ||
                file.getName().toLowerCase().endsWith(".jpeg") ||
                file.getName().toLowerCase().endsWith(".png"))
    );

    if (images == null) {
      System.out.println("Cannot find the pics folder.");
      return;
    }

    // Step 2: Calculate and compare image hashes
    Map<String, String> seenHashes = new HashMap<>();

    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-1");

      for (File image : images) {
        byte[] data = Files.readAllBytes(image.toPath());
        byte[] hashBytes = digest.digest(data);
        String hash = java.util.HexFormat.of().formatHex(hashBytes);

        System.out.println("Hashed: " + image.getName());

        if (seenHashes.containsKey(hash)) {
          System.out.println("Java duplicate found: " +
              seenHashes.get(hash) + " and " + image.getName());
        } else {
          seenHashes.put(hash, image.getName());
        }
      }

    } catch (Exception e) {
      System.out.println("Error checking image hashes.");
      e.printStackTrace();
      return;
    }

    // Step 3: Execute the original shell script
    ProcessBuilder processBuilder =
        new ProcessBuilder("/bin/bash", "FindDuplicates.sh");

    processBuilder.inheritIO();

    try {
      Process process = processBuilder.start();
      int exitCode = process.waitFor();

      System.out.println("Script exit code: " + exitCode);

    } catch (Exception e) {
      System.out.println("Error running the shell script.");
      e.printStackTrace();
    }
  }
}


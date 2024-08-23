package z_universal.util;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
  public static BufferedReader getReader(String filePath) {
    try {
      return new BufferedReader(new FileReader(filePath));
    }
    catch (FileNotFoundException e) {
      throw new RuntimeException("FILE_NOT_FOUND: " + filePath, e);
    }
  }

  public static String readLine(BufferedReader reader) {
    try {
      return reader.readLine();
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void closeReader(BufferedReader reader) {
    try {
      reader.close();
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static List<String> listFileNames(String directory) {
    List<String> fileNames = new ArrayList<>();
    File[] files = listFiles(directory);
    for (File f : files) if(!f.isDirectory()) fileNames.add(f.getName());
    return fileNames;
  }

  public static File[] listFiles(String directory) {
    File[] fileArr = null;
    File dir = new File(directory);
    if (dir.exists() && dir.isDirectory()) {
      fileArr = dir.listFiles();
      if (fileArr == null) System.out.println("DIR_EMPTY");
    }
    else System.out.println("DIR_NOT_FOUND");
    return fileArr;
  }

  public static void createDirectory(String directoryPath) {
    File directory = new File(directoryPath);
    directory.mkdir();
    ensureDirectoryExistence(directoryPath);
  }

  private static boolean ensureDirectoryExistence(String directoryPath) {
    try {
      return directoryExists(directoryPath);
    }
    catch (RuntimeException e) {
      throw new RuntimeException("DIRECTORY_WAS_NOT_CREATED: " + directoryPath);
    }
  }

  public static boolean directoryExists(String directoryPath) {
    return Files.exists(FileSystems.getDefault().getPath(directoryPath));
  }

  public static void createFileWithText(String filePath, String text) {
    File file = new File(filePath);
    try {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
      bufferedWriter.write(text);
      bufferedWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String readFileAsString(String filePath) {
    try {
      return String.join("\n", Files.readAllLines(Paths.get(filePath)));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void createDirectoryIfNotExits(String dirPath) {
    if (!directoryExists(dirPath)) createDirectory(dirPath);
    else System.out.println(String.format("DIR_ALREADY_EXISTS"));
  }

  public static boolean fileExists(String filepath) {
    return new File(filepath).exists();
  }

  public static List<String> readFileAsListOfLines(String filepath) {
    List<String> listOfLines = new ArrayList<>();
    BufferedReader reader = getReader(filepath);
    String line = null;
    while ((line = FileUtil.readLine(reader)) != null) listOfLines.add(line);
    return listOfLines;
  }

  public static void appendLineToFile(String filePath, String line) {
    Path path = Path.of(filePath);
    try {
      Files.writeString(path, line + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    } catch (IOException e) {
      System.err.println("Error appending to file: " + e.getMessage());
    }
  }
}

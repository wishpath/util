package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CmdUtil {
  public static BufferedReader executeAndGetAnswerReader(String lineToExecute) {
    Process process = null;
    try {
      process = Runtime.getRuntime().exec(lineToExecute);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return new BufferedReader(new InputStreamReader(process.getInputStream()));
  }

  public static String readAnswerLine(BufferedReader reader) {
    try {
      return reader.readLine();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void executeAndReadAnswer(String lineToExecute) {
    BufferedReader answerReader = executeAndGetAnswerReader(lineToExecute);
    String line;
    while ((line = readAnswerLine(answerReader)) != null) System.out.println(line);
  }
}

package org.sa.util;

public class RegExUtil {
  public static final String LETTER = "[a-zA-Z]";
  public static final String NON_LETTER = "[^a-zA-Z]";
  public static final String ALPHANUMERIC = "\\w";  // [a-zA-Z0-9_]
  public static final String NON_ALPHANUMERIC = "\\W";  // [^a-zA-Z0-9_]
  public static final String ANSI_ESCAPE_CODE = "\\u001B\\[[;\\d]*m";  // ANSI escape codes (like colors)


  public static final String OR = "|";
  public static final String LETTER_WORD_EDGE = getLetterWordEdge();
  public static final String ALPHANUMERIC_UNDERSCORE_WORD_EDGE = "\\b";


  public static String[] splitOnLetterWordEdges(String s) {
    return s.split(LETTER_WORD_EDGE);
  }

  private static String lookBehind(String element) {
    String lookbehindStart = "(?<=";
    String lookbehindEnd = ")";
    return lookbehindStart + element + lookbehindEnd;
  }

  private static String lookAhead(String element) {
    String lookaheadStart = "(?=";
    String lookaheadEnd = ")";
    return lookaheadStart + element + lookaheadEnd;
  }

  private static String getLetterWordEdge() {
    String edgeWordToNonword = lookBehind(LETTER) + lookAhead(NON_LETTER);
    String edgeNonwordToWord = lookBehind(NON_LETTER) + lookAhead(LETTER);
    return edgeNonwordToWord + OR + edgeWordToNonword;
  }
}

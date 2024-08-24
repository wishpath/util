package org.sa.util;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {

  public static int sum(List<Integer> list) {
    int sum = 0;
    for (Integer num : list) {
      sum += num;
    }
    return sum;
  }

  public static List<String> joinMapKeyStringsInList(List<Map<String, Integer>> listOfMaps) {
    return listOfMaps.stream()
        .map(map -> map.keySet().stream().collect(Collectors.joining()))
        .collect(Collectors.toList());
  }

  public static long countSpacesWithinList(List<String> list) {
    return list.stream()
        .flatMapToInt(CharSequence::chars)
        .filter(c -> c == ' ')
        .count();
  }

  public static boolean isPositive(BigInteger decimalWordToCompareTo) {
    return decimalWordToCompareTo.compareTo(BigInteger.valueOf(-1)) > 0;
  }

  public static List<List<String>> getTopNListsWithMostSpaces(List<List<String>> listOfLists, int n) {
    return listOfLists.stream()
        .sorted(Comparator.comparingLong(list -> -Util.countSpacesWithinList(list))) // Sort by descending number of spaces
        .limit(n)
        .collect(Collectors.toList());
  }

  public static List<List<String>> getAllWithMaxSpaces(List<List<String>> possibleUnderstoodMessages) {
    int maxSpaces = 0;
    for (List<String> msg : possibleUnderstoodMessages) maxSpaces = java.lang.Math.max(maxSpaces, getSpacesCount(msg));
    System.out.println("max spaces:            " + maxSpaces);
    List<List<String>> result = new ArrayList<>();
    for (List<String> msg : possibleUnderstoodMessages) if (getSpacesCount(msg) == maxSpaces) result.add(msg);
    System.out.println("max spaced ones:       " + result.size());
    return result;
  }

  public static int getSpacesCount(List<String> msg) {
    int count = 0;
    for (String s : msg) if (" ".equals(s)) count++;
    return count;
  }

  public static String str(List list) {
    return str(list, "");
  }

  public static <T> String str(List<T> list, String delimiter) {
    return list.stream()
        .map(Object::toString)
        .collect(Collectors.joining(delimiter));
  }

  public static List<String> letterList(String string) {
    return Arrays.asList(string.split(""));
  }

  public static BigInteger findClosestSmallerInteger(List<BigInteger> integers, BigInteger target) {
    if (target.compareTo(integers.get(0)) <= 0) return null; //no smaller integer
    int lowIndex = 0;
    int highIndex = integers.size() - 1;
    while (lowIndex <= highIndex) {
      int midIndex = (lowIndex + highIndex) / 2;
      BigInteger midValue = integers.get(midIndex);
      if (midValue.compareTo(target) < 0) lowIndex = midIndex + 1;
      else highIndex = midIndex - 1;
    }
    return integers.get(highIndex);
  }

  public static boolean allStringLettersBelongToList(String word, List<String> fullAlphanumeric) {
    String[] letters = word.split("");
    for (String letter : letters) if (!fullAlphanumeric.contains(letter)) return false;
    return true;
  }

  public static List<String> joinWithSpacesAndCutToLetters(String[] unspacedWords) {
    return Arrays.stream(unspacedWords)
        .collect(Collectors.joining(" "))
        .chars()
        .mapToObj(c -> String.valueOf((char) c))
        .collect(Collectors.toList());
  }

  public static <T> List<T> subtractLists(List<T> originalList, List<T> listToSubtract) {
    return originalList.stream()
        .filter(element -> !listToSubtract.contains(element))
        .collect(Collectors.toList());
  }

  public static List<String> filterOutListItemsContainingSubstring(List<String> list, String shouldNotContainSubstring) {
    return list.stream()
        .filter(item -> !item.contains(shouldNotContainSubstring))
        .collect(Collectors.toList());
  }

  public static List<String> filterOutListItemsEqualingExactString(List<String> list, String shouldNotEqualString) {
    return list.stream()
        .filter(item -> !item.equals(shouldNotEqualString))
        .collect(Collectors.toList());
  }

  public static String removeWhitespace(String str) {
    return str.chars()
        .filter(c -> !Character.isWhitespace(c))
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }

  public static <T> List<T> concatListsAndUnique(List<T>... lists) {
    return Stream.of(lists)
        .flatMap(Collection::stream)
        .distinct()
        .collect(Collectors.toList());
  }
}

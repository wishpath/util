package org.sa.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MapToFile {
  public static void saveMapToFile(Map<String, String> map, String filePath){
    Properties properties = new Properties();
    properties.putAll(map);
    try (OutputStream outputStream = new FileOutputStream(filePath)) {
      properties.store(outputStream, "Map data");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static Map<String, String> loadMapFromFile(String filePath){
    Properties properties = new Properties();
    try (InputStream inputStream = new FileInputStream(filePath)) {
      properties.load(inputStream);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    Map<String, String> map = new HashMap<>();
    for (String key : properties.stringPropertyNames()) {
      map.put(key, properties.getProperty(key));
    }
    return map;
  }
  public static void appendToPropertiesFile(String key, String value, String filePath) {
    Properties properties = new Properties();

    try (InputStream inputStream = new FileInputStream(filePath)) {
      properties.load(inputStream);
    } catch (FileNotFoundException e) {
      // If file does not exist, proceed with an empty properties object
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    properties.setProperty(key, value);

    try (OutputStream outputStream = new FileOutputStream(filePath)) {
      properties.store(outputStream, "Map data");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

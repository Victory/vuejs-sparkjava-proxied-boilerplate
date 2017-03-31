package org.dfhu.thpwa;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TreasureHuntPwa {

  public static void main(String[] args) {
    for (String s : args) {
      System.out.println("arg: " + s);
    }

    System.out.println(System.getProperty("user.dir"));


    String userDir = System.getProperty("user.dir");
    if (!userDir.endsWith("/backspark")) {
      userDir += "/backspark";
    }
    String configFileName = System.getProperty("config");
    configFileName = (configFileName != null) ? configFileName : "config.properties";
    configFileName = userDir + "/" + configFileName;

    Properties properties = getProperties(configFileName);

    Application application = new Application(properties);
    application.init();
  }

  private static Properties getProperties(String configFileName) {
    Properties properties = new Properties();

    FileInputStream input = null;
    try {
      input = new FileInputStream(configFileName);
    } catch (FileNotFoundException e) {
      System.out.println("ERROR: Could not open config file: " + configFileName);
      System.out.println("       Run with the config filename -Dconfig=/path/to/properties.confg");
      System.exit(1);
    }

    try {
      properties.load(input);
    } catch (IOException e) {
      System.out.println("Could not parse config file");
      e.printStackTrace();
      System.exit(1);
    }

    return properties;
  }


}

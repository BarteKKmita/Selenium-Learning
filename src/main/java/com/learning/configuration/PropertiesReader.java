package com.learning.configuration;

import com.google.inject.Singleton;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class PropertiesReader {

  private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesReader.class);
  private final String pathToFile;

  public PropertiesReader() {
    pathToFile = "src/main/resources/application-properties.yaml";
  }

  public String getProperty(String property) {
    String readProperty = "";
    try (InputStream input = new FileInputStream(pathToFile)) {
      Properties propertiesReader = new Properties();
      propertiesReader.load(input);
      readProperty = propertiesReader.getProperty(property);
    } catch (IOException ex) {
      LOGGER.error("File cannot be opened or not exists");
      ex.printStackTrace();
      LOGGER.error("Shouting down system.");
      System.exit(1);
    }
    return readProperty;
  }
}

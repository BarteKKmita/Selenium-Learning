package com.learning.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesReader {

  private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesReader.class);
  private static final String PATH_TO_FILE = "testProperties.yaml";

  public String getProperty(String property) {
    String readProperty = "";
    try (InputStream input = getPropertiesFileAsStream()) {
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

  private InputStream getPropertiesFileAsStream() {
    return this.getClass()
        .getClassLoader()
        .getResourceAsStream(PATH_TO_FILE);
  }
}

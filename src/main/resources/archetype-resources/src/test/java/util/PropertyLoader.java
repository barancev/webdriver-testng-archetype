#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.util;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Class that extracts properties from the prop file.
 */
public class PropertyLoader {

  private static final String DEBUG_PROPERTIES = "/debug.properties";

  public static Capabilities loadCapabilities() throws IOException {
    return loadCapabilities(System.getProperty("application.properties", DEBUG_PROPERTIES));
  }

  public static Capabilities loadCapabilities(String fromResource) throws IOException {
    Properties props = new Properties();
    props.load(PropertyLoader.class.getResourceAsStream(fromResource));
    String capabilitiesFile = props.getProperty("capabilities");

    Properties capsProps = new Properties();
    capsProps.load(PropertyLoader.class.getResourceAsStream(capabilitiesFile));

    DesiredCapabilities capabilities = new DesiredCapabilities();
    for (String name : capsProps.stringPropertyNames()) {
      String value = capsProps.getProperty(name);
      if (value.toLowerCase().equals("true") || value.toLowerCase().equals("false")) {
        capabilities.setCapability(name, Boolean.valueOf(value));
      } else if (value.startsWith("file:")) {
        capabilities.setCapability(name, new File(".", value.substring(5)).getCanonicalFile().getAbsolutePath());
      } else {
        capabilities.setCapability(name, value);
      }
    }

    return capabilities;
  }

  public static String loadProperty(String name) throws IOException {
    return loadProperty(name, System.getProperty("application.properties", DEBUG_PROPERTIES));
  }

  public static String loadProperty(String name, String fromResource) throws IOException {
    Properties props = new Properties();
    props.load(PropertyLoader.class.getResourceAsStream(fromResource));

    return props.getProperty(name);
  }

}
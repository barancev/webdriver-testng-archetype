#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.util;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

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

    DesiredCapabilities capabilities = new DesiredCapabilities();
    if (props.getProperty("browser.name") != null) {
      capabilities.setBrowserName(props.getProperty("browser.name"));
    }
    if (props.getProperty("browser.version") != null) {
      capabilities.setVersion(props.getProperty("browser.version"));
    }
    if (props.getProperty("browser.platform") != null && !props.getProperty("browser.platform").equals("")) {
      capabilities.setPlatform(Platform.valueOf(props.getProperty("browser.platform")));
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
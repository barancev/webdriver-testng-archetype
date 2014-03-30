#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${groupId};

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ${groupId}.pages.HomePage;

public class SampleTestNgTest extends TestNgTestBase {

  private HomePage homepage;

  @BeforeClass
  public void initPageObjects() {
    homepage = PageFactory.initElements(driver, HomePage.class);
  }

  @Test
  public void testHomePageHasAHeader() {
    driver.get(baseUrl);
    Assert.assertFalse("".equals(homepage.header.getText()));
  }
}

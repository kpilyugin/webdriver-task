import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static org.junit.Assert.*;

public class CatalogTest {
  private WebDriver driver;

  @Before
  public void setup() {
//    System.setProperty("webdriver.gecko.driver", "/Users/kirill/Downloads/geckodriver");
    driver = new FirefoxDriver();
    driver.get("https://market.yandex.ru/catalog/54761/list?local-offers-first=0&deliveryincluded=0&onstock=1");
  }

  @Test
  public void testFilter() {
    Catalog catalog = new Catalog(driver);
    int priceFrom = 1000;
    int priceTo = 5000;
    String producer = "SJCAM";
    Catalog filtered = catalog.applyFilter(producer, priceFrom, priceTo);
    List<Camera> cameras = filtered.getCameras();
    assertFalse(cameras.isEmpty());
    cameras.forEach(camera -> {
      assertTrue(camera.getPrice() >= priceFrom);
      assertTrue(camera.getPrice() <= priceTo);
      assertTrue(camera.getName().startsWith(producer));
    });
  }

  @After
  public void shutdown() {
    driver.quit();
  }
}
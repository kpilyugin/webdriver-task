import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class Catalog {
  private final WebDriver driver;
  private final Filter filter;
  private final List<Camera> cameras;

  public Catalog(WebDriver driver) {
    this.driver = driver;
    filter = new Filter(driver.findElement(By.className("n-filter-panel-aside")));
    cameras = driver.findElements(By.className("snippet-card"))
        .stream().map(Camera::new)
        .collect(Collectors.toList());
  }

  public List<Camera> getCameras() {
    return cameras;
  }

  public Catalog applyFilter(String producer, int priceFrom, int priceTo) {
    WebElement beforeFilter = driver.findElement(By.className("snippet-list"));
    filter.applyFilter(producer, priceFrom, priceTo);

    WebDriverWait wait = new WebDriverWait(driver, 10, 1000);
    wait.until(ExpectedConditions.stalenessOf(beforeFilter));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("filter-applied-results")));
    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("snippet-list_js_inited")));
    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("snippet-card")));
    return new Catalog(driver);
  }
}
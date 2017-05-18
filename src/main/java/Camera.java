import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Camera {
  private final WebElement name;
  private final WebElement price;

  public Camera(WebElement element) {
    name = element.findElement(By.className("snippet-card__header"));
    price = element.findElement(By.className("snippet-card__price"));
  }

  public String getName() {
    return name.getText();
  }

  public int getPrice() {
    return Integer.parseInt(price.getText().replaceAll("[^\\d]", ""));
  }
}

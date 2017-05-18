import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Producer {
  private final WebElement checkBox;
  private final WebElement label;

  public Producer(WebElement element) {
    checkBox = element;
    label = element.findElement(By.className("checkbox__label"));
  }

  public String getName() {
    return label.getText();
  }

  public void setChecked() {
    checkBox.click();
  }
}

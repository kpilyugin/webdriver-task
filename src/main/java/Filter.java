import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class Filter {
  private final WebElement priceFromInput;
  private final WebElement priceToInput;
  private final List<Producer> producers;
  private final WebElement applyButton;

  public Filter(WebElement element) {
    priceFromInput = element.findElement(By.id("glf-pricefrom-var"));
    priceToInput = element.findElement(By.id("glf-priceto-var"));
    producers = element.findElements(By.xpath("//*[starts-with(@id,'glf-7893318')]/../.."))
        .stream().map(Producer::new)
        .collect(Collectors.toList());
    applyButton = element.findElement(By.className("button_action_n-filter-apply"));
  }

  public void applyFilter(String name, int priceFrom, int priceTo) {
    priceFromInput.clear();
    priceFromInput.sendKeys(Integer.toString(priceFrom));
    priceToInput.clear();
    priceToInput.sendKeys(Integer.toString(priceTo));
    producers.stream()
        .filter(producer -> producer.getName().equals(name))
        .forEach(Producer::setChecked);
    applyButton.click();
  }
}
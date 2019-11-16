package tests;

import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;

import javax.inject.Inject;

import static com.google.common.truth.Truth.assertThat;

@Listeners({TestListener.class})
public class HomepageTest extends BaseTest {

  @Inject
  HomePage homePage;

  @Test
  public void verifyPurchaseText() {
    browserKeywords.goTo("https://another-nodejs-shopping-cart.herokuapp.com/");
    assertThat(elementKeywords.getText(homePage.addToCartButton)).isEqualTo("Add to cart");
    elementKeywords.click(homePage.addToCartButton);
  }
}
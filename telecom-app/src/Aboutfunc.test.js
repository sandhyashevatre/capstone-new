const { Builder, By, Key, until } = require('selenium-webdriver');

describe('Contact Component Functional Test', () => {
  let driver;

  beforeAll(async () => {
    driver = new Builder().forBrowser('chrome').build();
  });

  afterAll(async () => {
    await driver.quit();
  });

  it('navigates to the Contact page and checks for specific content', async () => {
 
    await driver.get('http://localhost:3000/contact'); 

   
    const pageTitle1 = await driver.findElement(By.xpath('//*[contains(text(), "About Wireless Inventory Number Management")]'));
    expect(await pageTitle1.isDisplayed()).toBe(true);

    const pageTitle2 = await driver.findElement(By.xpath('//*[contains(text(), "Our Mission")]'));
    expect(await pageTitle2.isDisplayed()).toBe(true);

    const pageTitle3 = await driver.findElement(By.xpath('//*[contains(text(), "What We Offer Number Reservation:")]'));
    expect(await pageTitle3.isDisplayed()).toBe(true);

    const pageTitle4 = await driver.findElement(By.xpath('//*[contains(text(), "Join the 5G Revolution with Us")]'));
    expect(await pageTitle4.isDisplayed()).toBe(true);


  });
});

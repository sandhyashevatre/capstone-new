const { Builder, By, Key, until } = require('selenium-webdriver');

describe('ImageSlider Component Functional Test', () => {
  let driver;

  beforeAll(async () => {
    driver = new Builder().forBrowser('chrome').build();
  });

  afterAll(async () => {
    await driver.quit();
  });

  it('navigates to a page containing the ImageSlider and checks for specific content', async () => {
    // Assuming your web application is running and accessible at a specific URL
    await driver.get('http://localhost:3000/some-page'); // Adjust the URL as needed

    // Interact with the page and make assertions
    const perksElement = await driver.findElement(By.css('.perks'));
    const carouselElement = await driver.findElement(By.css('.carousel'));
    const imageElements = await driver.findElements(By.css('.carousel-item img'));

    expect(await perksElement.isDisplayed()).toBe(true);
    expect(await carouselElement.isDisplayed()).toBe(true);
    expect(imageElements.length).toBeGreaterThan(0); // Ensure images are present
  });
});

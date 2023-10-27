const { Builder, By, Key, until } = require('selenium-webdriver');

describe('React App Functional Test', () => {
  let driver;

  beforeAll(async () => {
    driver = new Builder().forBrowser('chrome').build();
  });

  afterAll(async () => {
    await driver.quit();
  });

  it('navigates to the Home page and checks for specific content', async () => {
    // Assuming your React application is running and accessible at a specific URL
    await driver.get('http://localhost:3000/home'); // Adjust the URL as needed

    // Interact with the Home page and make assertions
    const homePageTitle = await driver.findElement(By.tagName('h1'));
    expect(await homePageTitle.getText()).toBe('Home Page');

    const imageSlider = await driver.findElement(By.css('[data-testid="image-slider"]'));
    expect(await imageSlider.isDisplayed()).toBe(true);

    const images = await driver.findElements(By.css('[data-testid="image-slider"] img'));
    expect(images.length).toBeGreaterThan(0); // Ensure images are present
  });
});

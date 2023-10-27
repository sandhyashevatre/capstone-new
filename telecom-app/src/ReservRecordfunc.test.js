const { Builder, By, until } = require('selenium-webdriver');

describe('ReserRecords Functional Tests', () => {
  let driver;

  beforeAll(async () => {
    driver = new Builder().forBrowser('chrome').build();
  });

  afterAll(async () => {
    await driver.quit();
  });

  it('should display the ReserRecords component', async () => {
    await driver.get('http://localhost:3000'); // Change the URL to your app's URL

    const headerElement = await driver.findElement(By.tagName('h3'));
    expect(await headerElement.getText()).toBe('Reserved Record');
  });

  it('should display SIM records fetched from the API', async () => {
    await driver.get('http://localhost:3000'); // Change the URL to your app's URL

    // Wait for the SIM records to load
    await driver.wait(until.elementLocated(By.css('tr > td')));

    // Replace these with actual expected values or data you expect to appear in your application
    const msisdnElement = await driver.findElement(By.xpath("//td[text()='1234567890']"));
    const customerNameElement = await driver.findElement(By.xpath("//td[text()='John Doe']"));

    expect(msisdnElement).toBeTruthy();
    expect(customerNameElement).toBeTruthy();
  });
});

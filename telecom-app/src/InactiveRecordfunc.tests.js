const { Builder, By, Key, until } = require('selenium-webdriver');

describe('InactiveRecord Functional Tests', () => {
  let driver;

  beforeAll(async () => {
    driver = new Builder().forBrowser('chrome').build();
  });

  afterAll(async () => {
    await driver.quit();
  });

  it('should display Inactive Sims title', async () => {
    await driver.get('http://localhost:3000'); // Change the URL to your app's URL

    const titleElement = await driver.findElement(By.tagName('h3'));
    const titleText = await titleElement.getText();

    expect(titleText).toBe('Inactive Sims');
  });

  it('should fetch and display inactive SIM records', async () => {
    await driver.get('http://localhost:3000'); // Change the URL to your app's URL

    // Assuming you have a table in your UI
    const tableRows = await driver.findElements(By.css('.record-table table tr'));

    // Ensure that the data is displayed correctly
    expect(tableRows.length).toBeGreaterThan(1); // At least 1 data row

    // You can interact with specific elements to validate their content
    const firstRow = tableRows[1]; // Assuming the first row is the header
    const cells = await firstRow.findElements(By.css('td'));

    // Assuming the data is displayed in the expected format
    expect(cells.length).toBe(3); // Expecting 3 cells per data row
  });

  it('should handle API fetch error', async () => {
    await driver.get('http://localhost:3000'); // Change the URL to your app's URL

    // Mock the API to return an error or disconnect the network
    // Ensure that an error message is displayed to the user
    const errorMessageElement = await driver.findElement(By.className('error-message'));
    const errorMessageText = await errorMessageElement.getText();

    expect(errorMessageText).toBe('Failed to fetch data');
  });
});

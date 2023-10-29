const { Builder, By, Key, until } = require("selenium-webdriver");

describe("InactiveRecord Functional Tests", () => {
  let driver;

  beforeAll(async () => {
    driver = new Builder().forBrowser("chrome").build();
  });

  afterAll(async () => {
    await driver.quit();
  });

  it("should display Inactive Sims title", async () => {
    await driver.get("http://localhost:3000");

    const titleElement = await driver.findElement(By.tagName("h3"));
    const titleText = await titleElement.getText();

    expect(titleText).toBe("Inactive Sims");
  });

  it("should fetch and display inactive SIM records", async () => {
    await driver.get("http://localhost:3000");

    const tableRows = await driver.findElements(
      By.css(".record-table table tr")
    );

    expect(tableRows.length).toBeGreaterThan(1);

    const firstRow = tableRows[1];
    const cells = await firstRow.findElements(By.css("td"));

    expect(cells.length).toBe(3);
  });

  it("should handle API fetch error", async () => {
    await driver.get("http://localhost:3000");

    const errorMessageElement = await driver.findElement(
      By.className("error-message")
    );
    const errorMessageText = await errorMessageElement.getText();

    expect(errorMessageText).toBe("Failed to fetch data");
  });
});

const { Builder, By, Key, until } = require("selenium-webdriver");

describe("ImeiManager Component Functional Test", () => {
  let driver;

  beforeAll(async () => {
    driver = new Builder().forBrowser("chrome").build();
  });

  afterAll(async () => {
    await driver.quit();
  });

  it("navigates to a page containing the ImeiManager form, fills it, and submits it", async () => {
    await driver.get("http://localhost:3000/some-page");

    const imeiInput = await driver.findElement(By.css('[for="IMEINumber"]'));
    await imeiInput.sendKeys("1234567890");

    const phoneInput = await driver.findElement(By.css('[for="PhoneNumber"]'));
    await phoneInput.sendKeys("9876543210");

    const submitButton = await driver.findElement(
      By.css('button[type="submit"]')
    );
    await submitButton.click();
  });
});

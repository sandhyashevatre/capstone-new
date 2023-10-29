const { Builder, By, Key, until } = require("selenium-webdriver");

describe("Postpaid Functional Tests", () => {
  let driver;

  beforeAll(async () => {
    driver = new Builder().forBrowser("chrome").build();
  });

  afterAll(async () => {
    await driver.quit();
  });

  it("should display the Postpaid title", async () => {
    await driver.get("http://localhost:3000");

    const titleElement = await driver.findElement(By.tagName("h2"));
    const titleText = await titleElement.getText();

    expect(titleText).toBe("Postpaid");
  });

  it("should update form fields when input values change", async () => {
    await driver.get("http://localhost:3000");

    const customerInput = await driver.findElement(By.name("customer"));
    const phoneNumberInput = await driver.findElement(By.name("phone-number"));
    const airtelRadio = await driver.findElement(By.value("AIRTEL"));

    await customerInput.sendKeys("John Doe");
    await phoneNumberInput.sendKeys("1234567890");
    await airtelRadio.click();

    const customerValue = await customerInput.getAttribute("value");
    const phoneNumberValue = await phoneNumberInput.getAttribute("value");
    const airtelChecked = await airtelRadio.isSelected();

    expect(customerValue).toBe("John Doe");
    expect(phoneNumberValue).toBe("1234567890");
    expect(airtelChecked).toBeTruthy();
  });

  it("should submit the form and display success message", async () => {
    await driver.get("http://localhost:3000");

    const customerInput = await driver.findElement(By.name("customer"));
    const phoneNumberInput = await driver.findElement(By.name("phone-number"));
    const airtelRadio = await driver.findElement(By.value("AIRTEL"));
    const submitButton = await driver.findElement(By.className("button"));

    await customerInput.sendKeys("John Doe");
    await phoneNumberInput.sendKeys("1234567890");
    await airtelRadio.click();
    await submitButton.click();

    const successMessage = await driver.wait(
      until.elementLocated(By.className("success-message"))
    );

    const successMessageText = await successMessage.getText();
    expect(successMessageText).toBe("Sim has been registered successfully");
  });
});

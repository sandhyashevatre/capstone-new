const { Builder, By, Key, until } = require("selenium-webdriver");

describe("ReplaceSim Functional Tests", () => {
  let driver;

  beforeAll(async () => {
    driver = new Builder().forBrowser("chrome").build();
  });

  afterAll(async () => {
    await driver.quit();
  });

  it("should display the ReplaceSim form", async () => {
    await driver.get("http://localhost:3000"); // Change the URL to your app's URL

    const customerInput = await driver.findElement(By.name("customer"));
    const phoneNumberInput = await driver.findElement(By.name("phone-number"));
    const airtelRadio = await driver.findElement(By.value("AIRTEL"));

    expect(customerInput).toBeTruthy();
    expect(phoneNumberInput).toBeTruthy();
    expect(airtelRadio).toBeTruthy();
  });

  it("should update form fields when input values change", async () => {
    await driver.get("http://localhost:3000"); // Change the URL to your app's URL

    const customerInput = await driver.findElement(By.name("customer"));
    const phoneNumberInput = await driver.findElement(By.name("phone-number"));
    const airtelRadio = await driver.findElement(By.value("AIRTEL"));

    await customerInput.sendKeys("John Doe");
    await phoneNumberInput.sendKeys("1234567890");
    await airtelRadio.click();

    expect(await customerInput.getAttribute("value")).toBe("John Doe");
    expect(await phoneNumberInput.getAttribute("value")).toBe("1234567890");
    expect(await airtelRadio.isSelected()).toBeTruthy();
  });

  it("should submit the form and display success message", async () => {
    await driver.get("http://localhost:3000"); // Change the URL to your app's URL

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

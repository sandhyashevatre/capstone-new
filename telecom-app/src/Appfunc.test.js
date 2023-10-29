const { Builder, By, Key, until } = require('selenium-webdriver');

describe('React App', () => {
  let driver;

  beforeAll(async () => {
    driver = new Builder().forBrowser('chrome').build();
  });

  afterAll(async () => {
    await driver.quit();
  });

  it('should navigate to the Home page', async () => {
    await driver.get('http://localhost:3000');

    const homeLink = await driver.findElement(By.linkText('Home'));
    await homeLink.click();

    const pageTitle = await driver.wait(until.elementLocated(By.tagName('h1'))).getText();
    expect(pageTitle).toBe('Home Page');
  });

  it('should log in and log out', async () => {
    await driver.get('http://localhost:3000'); 

    const loginLink = await driver.findElement(By.linkText('LOGIN'));
    await loginLink.click();

    const usernameInput = await driver.findElement(By.id('username'));
    const passwordInput = await driver.findElement(By.id('password'));
    const loginButton = await driver.findElement(By.id('loginButton')); 

    await usernameInput.sendKeys('your-username');
    await passwordInput.sendKeys('your-password');
    await loginButton.click();

   
    const userState = await driver.findElement(By.linkText('LOGOUT')).getText();
    expect(userState).toBe('LOGOUT');
  });
});

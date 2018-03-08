package com.web.ui.automation.browser;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.web.ui.automation.configurations.WebdriverConfiguration;


public class BrowserHandle{
	
	private final WebDriver driver;

	public BrowserHandle(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebDriver getDriver(){
		return driver;
	}
	
	public BrowserHandle Wait(int timeInSec) throws Exception {
		Thread.sleep(timeInSec*1000);
		return this;					
	}
	
	public BrowserHandle Wait(long arg,TimeUnit unit) throws Exception{
		Thread.sleep(unit.toMillis(arg));
		return this;
	}
	
	public BrowserHandle waitForFrameToBeAvailableAndSwitchToIt(String frameLocator){
		new WebDriverWait(driver, WebdriverConfiguration.getWaitTimeOut()).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		return this;
	}
	
	public BrowserHandle waitForFrameToBeAvailableAndSwitchToIt(String frameLocator,long timeOutInSeconds){
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		return this;
	}
	
	public BrowserHandle waitForElementPresent(By by) throws Exception{
		new WebDriverWait(driver, WebdriverConfiguration.getWaitTimeOut()).until(ExpectedConditions.presenceOfElementLocated(by));
		return this;
	}
	
	public BrowserHandle waitForElementPresent(By by,long timeOutInSeconds) throws Exception{
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.presenceOfElementLocated(by));
		return this;
	}
	

	public BrowserHandle waitForElementNotPresent(By by){
		new WebDriverWait(driver, WebdriverConfiguration.getWaitTimeOut()).until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(by)));
		return this;
	}
	
	public BrowserHandle waitForElementNotPresent(By by,long timeOutInSeconds){
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(by)));
		return this;
	}
	
	public BrowserHandle waitForVisible(WebElement element){
		new WebDriverWait(driver, WebdriverConfiguration.getWaitTimeOut()).until(ExpectedConditions.visibilityOf(element));
		return this;
	}
	
	public BrowserHandle waitForNotVisible(WebElement element) throws Exception{
		new WebDriverWait(driver, WebdriverConfiguration.getWaitTimeOut()).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
		return this;
	}
	
	public BrowserHandle waitForVisible(WebElement element,long timeOutInSeconds){
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.visibilityOf(element));
		return this;
	}
	
	public BrowserHandle waitForNotVisible(final WebElement element,long timeOutInSeconds) throws Exception{
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
		return this;
	}
	
	public BrowserHandle waitForValue(final WebElement element, final String value) throws Exception{
		new WebDriverWait(driver, WebdriverConfiguration.getWaitTimeOut()).until(ExpectedConditions.textToBePresentInElementValue(element, value));
		return this;
	}
	
	public BrowserHandle waitForNotValue(final WebElement element, final String value) throws Exception{
		new WebDriverWait(driver, WebdriverConfiguration.getWaitTimeOut()).until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementValue(element, value)));
		return this;
	}
	
	public BrowserHandle waitForValue(final WebElement element, final String value,long timeOutInSeconds) throws Exception{
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.textToBePresentInElementValue(element, value));
		return this;
	}
	
	public BrowserHandle waitForNotValue(final WebElement element, final String value,long timeOutInSeconds) throws Exception{
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementValue(element, value)));
		return this;
	}
	
	public BrowserHandle waitForEditable(final WebElement element) throws Exception{
		new WebDriverWait(driver, WebdriverConfiguration.getWaitTimeOut()).until(ExpectedConditions.elementToBeClickable(element));
		return this;
	}
	
	public BrowserHandle waitForNotEditable(final WebElement element) throws Exception{
		new WebDriverWait(driver, WebdriverConfiguration.getWaitTimeOut()).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(element)));
		return this;
	}
	
	public BrowserHandle waitForEditable(final WebElement element,long timeOutInSeconds) throws Exception{
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(element));
		return this;
	}
	
	public BrowserHandle waitForNotEditable(final WebElement element,long timeOutInSeconds) throws Exception{
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(element)));
		return this;
	}
	
	public BrowserHandle waitForText(final WebElement element, final String value) throws Exception{
		new WebDriverWait(driver, WebdriverConfiguration.getWaitTimeOut()).until(ExpectedConditions.textToBePresentInElement(element, value));
		return this;
	}
	
	public BrowserHandle waitForNotText(final WebElement element, final String value) throws Exception{
		new WebDriverWait(driver, WebdriverConfiguration.getWaitTimeOut()).until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, value)));
		return this;
	}
	
	public BrowserHandle waitForText(final WebElement element, final String value,long timeOutInSeconds) throws Exception{
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.textToBePresentInElement(element, value));
		return this;
	}
	
	public BrowserHandle waitForNotText(final WebElement element, final String value,long timeOutInSeconds) throws Exception{
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, value)));
		return this;
	}
	
	public BrowserHandle waitForAlertPresent(){
		new WebDriverWait(driver, WebdriverConfiguration.getWaitTimeOut()).until(ExpectedConditions.alertIsPresent());
		return this;
	}
	
	public BrowserHandle waitForAlertPresent(long timeOutInSeconds){
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.alertIsPresent());
		return this;
	}
	
	public BrowserHandle waitForTitle(String pageTitle){
		new WebDriverWait(driver, WebdriverConfiguration.getWaitTimeOut()).until(ExpectedConditions.titleIs(pageTitle));
		return this;
	}
	
	public BrowserHandle waitForTitle(String pageTitle,long timeOutInSeconds){
		new WebDriverWait(driver,timeOutInSeconds).until(ExpectedConditions.titleIs(pageTitle));
		return this;
	}
	
	public BrowserHandle assertTitle(String expectedTitle) throws Exception{
		Assert.assertEquals(getTitle(),expectedTitle);
		return this;
	}
	
	
	public Select select(WebElement element){
		return new Select(element);
	}
	
	public Keyboard getKeyBoard(){
		return ((HasInputDevices) driver).getKeyboard();
	}
	
	public Mouse getMouse(){
		return ((HasInputDevices) driver).getMouse();
	}
	
	public Actions actions(){
		return new Actions(driver);
	}
	
	public String getBrowserName(){
		return ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
	}
	
	public String getBrowserVersion(){
		return ((RemoteWebDriver) driver).getCapabilities().getVersion();
	}
	
	public void get(String url){
		driver.get(url);
	}
	
	public String getTitle(){
		return driver.getTitle();
	}
	
	public String getCurrentUrl(){
		return driver.getCurrentUrl();
	}
	
	public boolean isElementPresent(WebElement element){
		try {
			element.getTagName();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
	}
	
	public boolean isVisible(WebElement element){
		return element.isDisplayed();
	}
	
	public boolean isEnabled(WebElement element){
		return element.isEnabled();
	}
	
	public boolean isSelected(WebElement element){
		return element.isSelected();
	}
	
	public String getText(WebElement element){
		return element.getText();
	}
	
	public String getTextFromHiddenElement(WebElement element){
		return executeJavascript(element, "return arguments[0].innerHTML");
	}
	
	public String getValue(WebElement element){
		return element.getAttribute("value");
	}
	
	public String getValueFromHiddenElement(WebElement element){
		return executeJavascript(element, "return arguments[0].value");
	}
	
	public String getAttribute(WebElement element,String attributeName){
		return element.getAttribute(attributeName);
	}
	
	public String getTagName(WebElement element){
		return element.getTagName();
	}
	
	public WebElement findElement(String locator){
		return driver.findElement(byLocator(locator));
	}
	
	public WebElement findElement(By by){
		return driver.findElement(by);
	}
	
	public List<WebElement> findElements(String locator){
		return driver.findElements(byLocator(locator));
	}
	
	public List<WebElement> findElements(By by){
		return driver.findElements(by);
	}
	
	public BrowserHandle clear(WebElement element){
		element.clear();
		return this;
	}
	
	public BrowserHandle type(WebElement element,String value){
		element.sendKeys(value);
		return this;
	}
	
	public BrowserHandle clearAndType(WebElement element,String value){
		element.clear();
		element.sendKeys(value);
		return this;
	}
	
	public BrowserHandle click(WebElement element) throws Exception{
		element.click();
		return this;
	}
	
	public BrowserHandle clickAt(WebElement element,String value){
		String[] v = value.split(",");
		new Actions(driver).moveByOffset(Integer.parseInt(v[0]), Integer.parseInt(v[1])).click(element).build().perform();
		return this;
	}
	
	public BrowserHandle doubleClick(WebElement element){
		new Actions(driver).doubleClick(element).build().perform();
		return this;
	}
	
	public BrowserHandle selectByText(WebElement element, String visibleText){
		select(element).selectByVisibleText(visibleText);
		return this;
	}
	
	public BrowserHandle dragAndDrop(WebElement element,String value) throws Exception {
		String[] v = value.split(",");
		new Actions(driver).dragAndDropBy(element, Integer.parseInt(v[0]), Integer.parseInt(v[1])).build().perform();
		return this;
	}
	
	public BrowserHandle contextMenu(WebElement element){
		new Actions(driver).contextClick(element).build().perform();
		return this;
	}
	
	public BrowserHandle contextMenuAt(WebElement element,String value){
		String[] v = value.split(",");
		new Actions(driver).moveByOffset(Integer.parseInt(v[0]), Integer.parseInt(v[1])).contextClick(element).build().perform();
		return this;
	}
	
	public BrowserHandle mouseDown(WebElement element){
		getMouse().mouseDown((Coordinates) element.getLocation());
		return this;
	}
	
	public BrowserHandle mouseDownAt(WebElement element,String coordString) throws Exception {
		String[] v = coordString.split(",");
		getMouse().mouseDown((Coordinates) element.getLocation().moveBy(Integer.parseInt(v[0]), Integer.parseInt(v[1])));
		return this;
	}
	
	public BrowserHandle mouseUp(WebElement element){
		getMouse().mouseUp((Coordinates) element.getLocation());
		return this;
	}
	
	public BrowserHandle mouseUpAt(WebElement element,String coordString){
		String[] v = coordString.split(",");
		getMouse().mouseUp((Coordinates) element.getLocation().moveBy(Integer.parseInt(v[0]), Integer.parseInt(v[1])));
		return this;
	}
	
	public BrowserHandle mouseOver(WebElement element) throws Exception{
		new Actions(driver).moveToElement(element).build().perform();
		return this;
	}
	
	public BrowserHandle focus(WebElement element){
		element.sendKeys(Keys.TAB);
		return this;
	}
	
	public BrowserHandle keyDown(WebElement element, String value){
		new Actions(driver).keyDown(element, Keys.valueOf(value)).build().perform();
		return this;
	}
	
	public BrowserHandle keyDown(WebElement element, Keys key){
		new Actions(driver).keyDown(element, key).build().perform();
		return this;
	}
	
	public BrowserHandle keyUp(WebElement element, String value){
		new Actions(driver).keyUp(element, Keys.valueOf(value)).build().perform();
		return this;
	}
	
	public BrowserHandle keyUp(WebElement element, Keys key){
		new Actions(driver).keyUp(element, key).build().perform();
		return this;
	}
	
	public BrowserHandle controlKeyUp(){
		new Actions(driver).keyUp(Keys.CONTROL).build().perform();
		return this;
	}
	
	public BrowserHandle controlKeyDown(){
		new Actions(driver).keyDown(Keys.CONTROL).build().perform();
		return this;
	}
	
	public BrowserHandle chooseOk(){
		driver.switchTo().alert().accept();
		return this;
	}
	
	public BrowserHandle chooseCancel(){
		driver.switchTo().alert().dismiss();
		return this;
	}
	
	public BrowserHandle check(WebElement checkBoxElement){
		if(!isSelected(checkBoxElement)){
			checkBoxElement.click();
		}
		return this;
	}
	
	public BrowserHandle uncheck(WebElement checkBoxElement){
		if(isSelected(checkBoxElement)){
			checkBoxElement.click();
		}
		return this;
	}
	
	public BrowserHandle deleteAllVisibleCookies(){
		driver.manage().deleteAllCookies();
		return this;
	}
	
	public Object executeJavascript(String executeJavascript){
		return ((JavascriptExecutor)driver).executeScript(executeJavascript);
	}
	
	public String executeJavascript(WebElement webElement,String executeJavascript){
		return (String) ((JavascriptExecutor)driver).executeScript(executeJavascript, webElement);
	}
	
	public BrowserHandle switchToFrame(int index){
		driver.switchTo().frame(index);
		return this;
	}
	
	public BrowserHandle switchToFrame(String nameOrId){
		driver.switchTo().frame(nameOrId);
		return this;
	}
	
	public BrowserHandle switchToFrame(WebElement frameElement){
		driver.switchTo().frame(frameElement);
		return this;
	}
	
	public BrowserHandle switchToDefault(){
		driver.switchTo().defaultContent();
		return this;
	}
	
	public BrowserHandle windowMaximize(){
		driver.manage().window().maximize();
		return this;
	}
	
	public BrowserHandle windowFocus(){
		driver.switchTo().window(driver.getWindowHandle());
		return this;
	}
	
	public String getWindowHandle(){
		return driver.getWindowHandle();
	}
	
	public Set<String> getWindowHandles(){
		return driver.getWindowHandles();
	}
	
	public BrowserHandle switchToWindow(String nameOrHandle){
		driver.switchTo().window(nameOrHandle);
		return this;
	}
	
	public By byLocator(String locator) {
		if(locator.startsWith("css=")){
			locator=locator.replaceFirst("css=", "");
			return By.cssSelector(locator);
		}else if(locator.startsWith("xpath=")){
			locator=locator.replaceFirst("xpath=", "");
			return By.xpath(locator);
		}else if(locator.startsWith("class=")){
			locator=locator.replaceFirst("class=", "");
			return By.className(locator);
		}else if(locator.startsWith("id=")){
			locator=locator.replaceFirst("id=", "");
			return By.id(locator);
		}else if(locator.startsWith("linkText=")){
			locator=locator.replaceFirst("linkText=", "");
			return By.linkText(locator);
		}else if(locator.startsWith("partialLinkText=")){
			locator=locator.replaceFirst("partialLinkText=", "");
			return By.partialLinkText(locator);
		}else if(locator.startsWith("name=")){
			locator=locator.replaceFirst("name=", "");
			return By.name(locator);
		}else{
			return By.id(locator);
		}
	}
	
}

package androidTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import resources.flowerData;


public class Demo extends flowerData{

    private static final String APP = "C:\\Users\\dmarc\\StudioProjects\\views-widgets-samples\\RecyclerViewKotlin\\app\\build\\outputs\\apk\\debug\\app-debug.apk";
    private static final String APPIUM = "http://localhost:4723/wd/hub";

    private AndroidDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "11");
        caps.setCapability("deviceName", "Pixel XL API 30");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("app", APP);
        driver = new AndroidDriver(new URL(APPIUM), caps);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void NavigationTest() {

        List <WebElement> initialFlowerAppState = driver.findElements(MobileBy.xpath("//*[@resource-id='com.example.recyclersample:id/recycler_view']//android.view.ViewGroup"));
        int initialFlowerCount = initialFlowerAppState.size();

        String ar[] = {"Rose", "Freesia", "Lily", "Sunflower", "Peony", "Daisy", "Lilac",
                "Marigold", "Poppy", "Daffodil", "Dahlia"};
        int i;
        String x;

        // iterating over an array
        // Could wrap the loop with a scroll/swipe into view
        for (i = 0; i < ar.length; i++) {

            // accessing each element of array
            x = ar[i];

            WebElement flower = (WebElement) new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.view.ViewGroup//*[@text='"+ x +"']")));

            driver.findElement(MobileBy.xpath("//android.view.ViewGroup//*[@text='"+ x +"']"));
            flower.click();

            driver.navigate().back();
        }

    }

    @Test
    public void initialCountCounterTest() {

        List <WebElement> initialFlowerAppState = driver.findElements(MobileBy.xpath("//*[@resource-id='com.example.recyclersample:id/recycler_view']//android.view.ViewGroup"));
        int initialFlowerCount = initialFlowerAppState.size();

        WebElement flowerHeadertext = driver.findElement(MobileBy.id("com.example.recyclersample:id/flower_number_text"));
        String flowerHeaderCount = flowerHeadertext.getText();

        assertEquals(String.valueOf(initialFlowerCount),flowerHeaderCount);

    }


    @Test
    public void checkingFlowerNamesAndDescriptionsTestCase() {

        List <WebElement> initialFlowerAppState = driver.findElements(MobileBy.xpath("//*[@resource-id='com.example.recyclersample:id/recycler_view']//android.view.ViewGroup"));
        int initialFlowerCount = initialFlowerAppState.size();

        String ar[] = {"Rose", "Freesia", "Lily", "Sunflower", "Peony", "Daisy", "Lilac",
                "Marigold", "Poppy", "Daffodil", "Dahlia"};

        String ar2[] = {roseDesc, freesiasDesc, liliesDesc, sunflowerDesc, peonyDesc, daisiesDesc, lilacsDesc,
                marigoldDesc, poppiesDesc, daffodilsDesc, dahliaDesc};

        int i;
        String varFlower;
        String varDesc;

        // iterating over an array
        // Could wrap the loop with a scroll/swipe into view
        for (i = 0; i < ar.length; i++) {

            // accessing each element of array
            varFlower = ar[i];
            varDesc = ar2[i];

            WebElement flower = (WebElement) new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.view.ViewGroup//*[@text='"+ varFlower +"']")));

            flower.click();

            WebElement flowerNameTitle = (WebElement) new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//*[@resource-id='com.example.recyclersample:id/flower_detail_name']")));

            WebElement flowerNameDesc = (WebElement) new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//*[@resource-id='com.example.recyclersample:id/flower_detail_description']")));

            String flowerNameTitleContent = flowerNameTitle.getText();
            String flowerNameDescContent = flowerNameDesc.getText();

            assertEquals(flowerNameTitleContent,varFlower);
            assertEquals(flowerNameDescContent,varDesc);

            driver.navigate().back();
        }

    }

    @Test
    public void AddFlowerAttemptMissingDesc() {

        List <WebElement> initialFlowerAppState = driver.findElements(MobileBy.xpath("//*[@resource-id='com.example.recyclersample:id/recycler_view']//android.view.ViewGroup"));
        int initialFlowerCount = initialFlowerAppState.size();

        WebElement flowerHeadertext = driver.findElement(MobileBy.id("com.example.recyclersample:id/flower_number_text"));
        String flowerHeaderCount = flowerHeadertext.getText();

        driver.findElement(MobileBy.AccessibilityId("fab")).click();

        WebElement flowerNameInput = (WebElement) new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.example.recyclersample:id/add_flower_name")));

        flowerNameInput.sendKeys("newFlower");

        driver.findElement(MobileBy.id("com.example.recyclersample:id/done_button")).click();

        WebElement flowerHeadertextUpdate = (WebElement) new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.example.recyclersample:id/flower_number_text")));

        String flowerHeaderCountUpdate = flowerHeadertextUpdate.getText();

        assertEquals(String.valueOf(initialFlowerCount),flowerHeaderCountUpdate);
        assertEquals(flowerHeaderCount,flowerHeaderCountUpdate);


    }

    @Test
    public void AddFlowerAttemptMissingName() {

        List <WebElement> initialFlowerAppState = driver.findElements(MobileBy.xpath("//*[@resource-id='com.example.recyclersample:id/recycler_view']//android.view.ViewGroup"));
        int initialFlowerCount = initialFlowerAppState.size();

        WebElement flowerHeadertext = driver.findElement(MobileBy.id("com.example.recyclersample:id/flower_number_text"));
        String flowerHeaderCount = flowerHeadertext.getText();

        driver.findElement(MobileBy.AccessibilityId("fab")).click();

        WebElement flowerNameInput = (WebElement) new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.example.recyclersample:id/add_flower_description")));

        flowerNameInput.sendKeys("newDesc");

        driver.findElement(MobileBy.id("com.example.recyclersample:id/done_button")).click();

        WebElement flowerHeadertextUpdate = (WebElement) new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.example.recyclersample:id/flower_number_text")));

        String flowerHeaderCountUpdate = flowerHeadertextUpdate.getText();

        assertEquals(String.valueOf(initialFlowerCount),flowerHeaderCountUpdate);
        assertEquals(flowerHeaderCount,flowerHeaderCountUpdate);

    }

    @Test
    public void CreateNewFlower() {

        WebElement addBtn = (WebElement) new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("fab")));

        addBtn.click();

        WebElement flowerNameInput = (WebElement) new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.example.recyclersample:id/add_flower_name")));

        flowerNameInput.sendKeys("newFlower");
        driver.findElement(MobileBy.id("com.example.recyclersample:id/add_flower_description"))
                .sendKeys("newDesc");


        driver.findElement(MobileBy.id("com.example.recyclersample:id/done_button"))
                .click();

        WebElement flowerHeadertextUpdate = (WebElement) new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.example.recyclersample:id/flower_number_text")));

        WebElement flowerHeadertext = driver.findElement(MobileBy.id("com.example.recyclersample:id/flower_number_text"));
        String flowerHeaderCount = flowerHeadertext.getText();

        String flowerHeaderCountUpdate = flowerHeadertextUpdate.getText();

        assertEquals(flowerHeaderCount,flowerHeaderCountUpdate);

    }

    @Test
    public void deleteFirstSortedFlower() {

        List <WebElement> initialFlowerAppState = driver.findElements(MobileBy.xpath("//*[@resource-id='com.example.recyclersample:id/recycler_view']//android.view.ViewGroup"));
        int initialFlowerCount = initialFlowerAppState.size();

        WebElement firstFlower = (WebElement) new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//*[@resource-id='com.example.recyclersample:id/recycler_view']//android.view.ViewGroup[1]")));

        firstFlower.click();

        WebElement removeBtn = (WebElement) new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.example.recyclersample:id/remove_button")));

        String flowerName2bDeleted = driver.findElement(MobileBy.id("com.example.recyclersample:id/remove_button")).getText();

        removeBtn.click();

        WebElement wait = (WebElement) new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//*[@resource-id='com.example.recyclersample:id/recycler_view']//android.view.ViewGroup[1]")));

        String firstFlowerNameAfterDeletion = driver.findElement(MobileBy.xpath("//*[@resource-id='com.example.recyclersample:id/recycler_view']//android.view.ViewGroup[1]")).getText();

        List <WebElement> initialFlowerAppStateAfterDel = driver.findElements(MobileBy.xpath("//*[@resource-id='com.example.recyclersample:id/recycler_view']//android.view.ViewGroup"));
        int initialFlowerCountAfterDel = initialFlowerAppStateAfterDel.size();

        assertEquals(initialFlowerCount-1,initialFlowerCountAfterDel);
        assertNotEquals(flowerName2bDeleted,firstFlowerNameAfterDeletion);

    }
}

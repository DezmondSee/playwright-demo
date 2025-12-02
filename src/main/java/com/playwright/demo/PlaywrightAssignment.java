package com.playwright.demo;
import com.microsoft.playwright.*;
import java.nio.file.Paths;

public class PlaywrightAssignment {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {

            // 1. Open Browser
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            // 2. Go to Website
            System.out.println("Navigating to Practice Software Testing...");
            page.navigate("https://practicesoftwaretesting.com/");

            // 3. Search for "Pliers"
            Locator searchBox = page.getByPlaceholder("Search");
            searchBox.fill("Pliers");

            // *** FIXED LINE BELOW ***
            // We use the unique ID (data-test) to find the specific search button
            page.locator("[data-test='search-submit']").click();

            // Wait for results
            page.waitForTimeout(1000);

            // 4. Take Screenshot
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("evidence_screenshot.png"))
                    .setFullPage(true));

            System.out.println("Test Complete! Screenshot saved as 'evidence_screenshot.png'");

            browser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
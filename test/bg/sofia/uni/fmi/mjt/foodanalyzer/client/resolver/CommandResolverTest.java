package bg.sofia.uni.fmi.mjt.foodanalyzer.client.resolver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CommandResolverTest {
    CommandResolver resolver;

    @Before
    public void initResolver() {
        resolver = new CommandResolver();
    }

    @Test
    public void testIsBarcodePromptRecognizesBarcodePrompts() {
        final String barcodePrompt1 = "get-food-by-barcode --code=009800146130";
        final String barcodePrompt2 = "get-food-by-barcode --img=C:\\Users\\krasi"
                + "\\eclipse-workspace\\FoodAnalyzer\\resources\\barcode.gif";
        final String barcodePrompt3 = "get-food-by-barcode --img=C:\\Users\\krasi"
                + "\\eclipse-workspace\\FoodAnalyzer\\resources\\barcode.gif --code=009800146130";

        assertTrue("Barcode command with code is recognized", resolver.isBarcodePrompt(barcodePrompt1));
        assertTrue("Barcode command with image path is recognized", resolver.isBarcodePrompt(barcodePrompt2));
        assertTrue("Barcode command with both code and image pathis recognized",
                resolver.isBarcodePrompt(barcodePrompt3));
    }

    @Test
    public void testIsQuitPromptRecognizesQuitPrompts() {
        final String quit1 = "quit";
        final String quit2 = "   quit  ";

        assertTrue("\"quit\" only is recognized", resolver.isQuitPrompt(quit1));
        assertTrue("\"quit\" with trailing and leading whitespaces is recognized", resolver.isQuitPrompt(quit2));
    }

    @Test
    public void testResolveResolvesCommandsCorrectly() {
        final String illegalCommand = "whatever ---";
        final String fdcIdPrompt = "get-food-report 415269";
        final String foodDescriptionPrompt = "get-food beef noodle soup";
        final String barcodePrompt1 = "get-food-by-barcode --code=009800146130";
        final String barcodePrompt2 = "get-food-by-barcode --img=C:\\Users\\krasi"
                + "\\eclipse-workspace\\FoodAnalyzer\\resources\\barcode.gif";
        final String barcodePrompt2Expected = "get-food-by-barcode --code=725272730706";
        final String barcodePrompt3 = "get-food-by-barcode --img=C:\\Users\\krasi"
                + "\\eclipse-workspace\\FoodAnalyzer\\resources\\barcode.gif --code=009800146130";
        final String barcodePrompt3Expected = "get-food-by-barcode --code=009800146130";

        assertEquals("illegal command is resolved correctly", illegalCommand, resolver.resolve(illegalCommand));
        assertEquals("fdcId command is resolved correctly", fdcIdPrompt, resolver.resolve(fdcIdPrompt));
        assertEquals("Food description command is resolved correctly", foodDescriptionPrompt,
                resolver.resolve(foodDescriptionPrompt));
        assertEquals("Barcode command with code is resolved correctly", barcodePrompt1,
                resolver.resolve(barcodePrompt1));
        assertEquals("Barcode command with img is resolved correctly", barcodePrompt2Expected,
                resolver.resolve(barcodePrompt2));
        assertEquals("Barcode command with both image and code is resolved correctly", barcodePrompt3Expected,
                resolver.resolve(barcodePrompt3));

    }

}

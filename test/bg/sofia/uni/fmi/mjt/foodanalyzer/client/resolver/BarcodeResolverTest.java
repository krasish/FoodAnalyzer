package bg.sofia.uni.fmi.mjt.foodanalyzer.client.resolver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import com.google.zxing.NotFoundException;

public class BarcodeResolverTest {

    @Test
    public void testResolveImageReturnsCorrectBarcode() {
        String pathToImage = "C:\\Users\\krasi\\eclipse-workspace\\FoodAnalyzer\\resources\\barcode.gif";
        String expectedOutput = "009427402305";

        try {
            assertEquals("Image with barcode 72527273070 is resolved", expectedOutput,
                    BarcodeResolver.resolveImage(pathToImage));
        } catch (NotFoundException e) {
            fail();
        } catch (IOException e) {
            fail();
        }
    }

}

package bg.sofia.uni.fmi.mjt.foodanalyzer.client.resolver;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

public class BarcodeResolver {
    public static String resolveImage(String path) throws NotFoundException, IOException {

        Path pathToBarcode = Path.of(path);
        BufferedImage image = null;
        image = ImageIO.read(pathToBarcode.toFile());

        int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
        RGBLuminanceSource source = new RGBLuminanceSource(image.getWidth(), image.getHeight(), pixels);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        MultiFormatReader multiFormatReader = new MultiFormatReader();

        Result result = null;
        result = multiFormatReader.decode(bitmap);
        return result.getText();

    }
}

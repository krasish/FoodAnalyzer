package bg.sofia.uni.fmi.mjt.foodanalyzer.server.command;

import java.nio.channels.SocketChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {

    private static final Pattern GET_BY_DESCRIPTION_PATTERN = Pattern.compile("^\\s*get-food\\s+(.+)$");
    private static final Pattern GET_FOOD_REPORT_PATTERN = Pattern.compile("^\\s*get-food-report\\s+(\\d+)\\s*$");
    private static final Pattern GET_BY_BARCODE_PATTERN = Pattern
            .compile("^^\\s*get-food-by-barcode\\s+--code=(\\d+)\\s*$");

    public static Command parse(String commandLine, SocketChannel socketChannel) {

        Matcher descriptionMatcher = GET_BY_DESCRIPTION_PATTERN.matcher(commandLine);
        Matcher barcodeMatcher = GET_BY_BARCODE_PATTERN.matcher(commandLine);
        Matcher foodReportMathcer = GET_FOOD_REPORT_PATTERN.matcher(commandLine);

        if (descriptionMatcher.find()) {
            String description = descriptionMatcher.group(1);
            return new GetFoodByDescriptionCommand(socketChannel, description);
        } else if (barcodeMatcher.find()) {
            String barcode = barcodeMatcher.group(1);
            return new GetFoodByBarcodeCommand(socketChannel, barcode);
        } else if (foodReportMathcer.find()) {
            int fdcId = Integer.parseInt(foodReportMathcer.group(1));
            return new GetFoodReportCommand(socketChannel, fdcId);
        } else {
            return new InvalidCommand(socketChannel);
        }
    }
}

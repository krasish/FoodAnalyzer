package bg.sofia.uni.fmi.mjt.foodanalyzer.client.resolver;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.zxing.NotFoundException;

public class CommandResolver {

    private static final String NULL_RESOLVE = "Cannot resolve null";
    private static final String UNRESOLVABLE_COMMAND = "Unresolvable command";

    private static final String QUIT_REGEX = "^\\s*quit\\s*$";
    private static final String BARCODE_REGEX = "^\\s*get-food-by-barcode.*$";
    private static final String BARCODE_COMMAND_TEMPLATE = "get-food-by-barcode --code=%s";
    private static final String WINDOWS_PATH_REGEX = "[a-zA-Z]:\\\\[\\\\\\S|*\\S]?.*";
    private static final String BARCODE_WITH_CODE_REGEX = "^.*--code=(\\d+).*$";
    private static Pattern codePattern = Pattern.compile(BARCODE_WITH_CODE_REGEX);
    private static Pattern pathPattern = Pattern.compile(WINDOWS_PATH_REGEX);

    public boolean isBarcodePrompt(String prompt) {
        if (prompt == null) {
            throw new IllegalArgumentException(NULL_RESOLVE);
        }

        return prompt.matches(BARCODE_REGEX);
    }

    public boolean isQuitPrompt(String prompt) {
        if (prompt == null) {
            throw new IllegalArgumentException(NULL_RESOLVE);
        }
        return prompt.matches(QUIT_REGEX);
    }

    public String resolveFromPath(Matcher pathMatcher) throws NotFoundException, IOException {
        String path = (pathMatcher.group(0));
        String code = BarcodeResolver.resolveImage(path);
        return String.format(BARCODE_COMMAND_TEMPLATE, code);

    }

    public String resolve(String prompt) {
        if (prompt == null) {
            throw new IllegalArgumentException(NULL_RESOLVE);
        }

        if (!isBarcodePrompt(prompt)) {
            return prompt;
        }

        Matcher codeMatcher = codePattern.matcher(prompt);
        Matcher pathMatcher = pathPattern.matcher(prompt);

        if (codeMatcher.find()) {
            return String.format(BARCODE_COMMAND_TEMPLATE, codeMatcher.group(1));
        } else if (pathMatcher.find()) {
            try {
                return resolveFromPath(pathMatcher);
            } catch (NotFoundException | IOException e) {
                throw new IllegalArgumentException(UNRESOLVABLE_COMMAND);
            }
        }
        throw new IllegalArgumentException(UNRESOLVABLE_COMMAND);
    }
}

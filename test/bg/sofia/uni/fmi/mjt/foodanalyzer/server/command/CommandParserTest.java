package bg.sofia.uni.fmi.mjt.foodanalyzer.server.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CommandParserTest {

    @Test
    public void testGetFormattedDescriptionREturnsCorrectDescriptions() {
        final String descriptionAndExpected0 = "vafla";
        final String description1 = "chocolate cake";
        final String description2 = " vkusna mazna  banichkaaa    ";
        final String expected1 = "chocolate%20cake";
        final String expected2 = "vkusna%20mazna%20banichkaaa";
        assertEquals("getFormattedDescription handles descriptions without", descriptionAndExpected0,
                CommandParser.getFormattedDescription(descriptionAndExpected0));
        assertEquals("getFormattedDescription handles descriptions with spaces", expected1,
                CommandParser.getFormattedDescription(description1));
        assertEquals("getFormattedDescription handles descriptions with trailing and elading spaces", expected2,
                CommandParser.getFormattedDescription(description2));

    }

}

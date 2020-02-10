package bg.sofia.uni.fmi.mjt.foodanalyzer.server.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GetFoodReportCommandTest extends CommandTestBase {

    @Test
    public void testExecuteWritesToChannelExpectedOutputForFullNutritionDataObject() {

        GetFoodReportCommand reportCommand = new GetFoodReportCommand(channelStub, FDC_ID_1);
        reportCommand.execute(database, buffer);

        assertEquals(TEST_1_MESSAGE, testNutritionData1.toString(), outputStream.toString());
    }

    @Test
    public void testExecuteWritesToChannelExpectedOutputForPARTIALLYFullNutritionDataObject() {

        GetFoodReportCommand reportCommand = new GetFoodReportCommand(channelStub, FDC_ID_2);
        reportCommand.execute(database, buffer);

        assertEquals(TEST_1_MESSAGE, testNutritionData2.toString(), outputStream.toString());
    }

}

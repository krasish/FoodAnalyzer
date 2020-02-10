package bg.sofia.uni.fmi.mjt.foodanalyzer.server;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.command.CommandParserTest;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.command.GetFoodByBarcodeCommandTest;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.command.GetFoodByDescriptionCommandTest;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.command.GetFoodReportCommandTest;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.NutritionDataTest;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.io.ChannelReaderTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ CommandParserTest.class, GetFoodByDescriptionCommandTest.class, GetFoodByBarcodeCommandTest.class,
        NutritionDataTest.class, ChannelReaderTest.class, GetFoodReportCommandTest.class })
public class ServerAllTestsSuite {
    // nothing
}

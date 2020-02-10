package bg.sofia.uni.fmi.mjt.foodanalyzer;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import bg.sofia.uni.fmi.mjt.foodanalyzer.client.io.ServerReaderTest;
import bg.sofia.uni.fmi.mjt.foodanalyzer.client.io.ServerWriterTest;
import bg.sofia.uni.fmi.mjt.foodanalyzer.client.resolver.BarcodeResolverTest;
import bg.sofia.uni.fmi.mjt.foodanalyzer.client.resolver.CommandResolverTest;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.command.CommandParserTest;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.command.GetFoodByBarcodeCommandTest;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.command.GetFoodByDescriptionCommandTest;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.command.GetFoodReportCommandTest;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.FoodTest;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.NutritionDataTest;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.http.json.JSONParserTest;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.io.ChannelReaderTest;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.io.ChannelWriterTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ CommandParserTest.class, GetFoodByDescriptionCommandTest.class, GetFoodByBarcodeCommandTest.class,
        NutritionDataTest.class, ChannelReaderTest.class, GetFoodReportCommandTest.class, ServerReaderTest.class,
        ServerWriterTest.class, BarcodeResolverTest.class, CommandResolverTest.class, FoodTest.class,
        JSONParserTest.class, ChannelWriterTest.class })
public class AllTestsSuite {
    // nothing
}

package bg.sofia.uni.fmi.mjt.foodanalyzer.server.http.json;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.entities.Food;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.entities.NutritionData;

public class JSONParser {
    public static List<Food> parseFromFoodSearchEndpoint(String inputJson) {
        Type type = new TypeToken<List<Food>>() {
        }.getType();

        Gson gson = new GsonBuilder().registerTypeAdapter(type, new FoodDeserializer()).create();

        List<Food> list = gson.fromJson(inputJson, type);
        return list;
    }

    public static NutritionData parseFromFoodDetailsEndpoint(String inputJson) {
        Gson gson = new Gson();

        NutritionData nutrientData = gson.fromJson(inputJson, NutritionData.class);

        return nutrientData;
    }

}

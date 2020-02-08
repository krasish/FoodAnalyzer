package bg.sofia.uni.fmi.mjt.foodanalyzer.server.http.json;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Food;

public class FoodDeserializer implements JsonDeserializer<List<Food>> {

    @Override
    public List<Food> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonElement content = json.getAsJsonObject().get("foods");
        Type type = new TypeToken<List<Food>>() {
        }.getType();

        return new Gson().fromJson(content, type);
    }

}

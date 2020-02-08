package bg.sofia.uni.fmi.mjt.foodanalyzer.server.database;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Database {
    private Map<String, List<Food>> descriptionMap;
    private Map<Integer, NutritionData> fdcIdMap;
    private Map<String, List<Food>> gtinUpcMap;

    public Database() {
        fdcIdMap = new ConcurrentHashMap<>();
        descriptionMap = new ConcurrentHashMap<>();
        gtinUpcMap = new ConcurrentHashMap<>();
    }

    public void addFood(List<Food> foods) {
        descriptionMap.putIfAbsent(foods.get(0).getDescription(), foods);
        if (foods.get(0).getGtinUpc() != null) {
            gtinUpcMap.putIfAbsent(foods.get(0).getGtinUpc(), null);
        }
    }

    public void addNutritionData(NutritionData nutritionData) {
        if (nutritionData != null) {
            fdcIdMap.put(nutritionData.getFdcId(), nutritionData);
        }
    }

    public List<Food> getByDescription(String description) {
        return descriptionMap.get(description);
    }

    public NutritionData getByfdcId(int fdcID) {
        return fdcIdMap.get(fdcID);
    }

    public List<Food> getByBarcode(String barcode) {
        return gtinUpcMap.get(barcode);
    }
}

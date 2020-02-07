package bg.sofia.uni.fmi.mjt.foodanalyzer.server.database;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Database {
    private Map<String, Food> descriptionMap;
    private Map<Integer, Food> fdcIdMap;
    private Map<String, Food> gtinUpcMap;

    public Database() {
        fdcIdMap = new ConcurrentHashMap<>();
        descriptionMap = new ConcurrentHashMap<>();
        gtinUpcMap = new ConcurrentHashMap<>();
    }

    public void addFood(Food food) {
        descriptionMap.putIfAbsent(food.getDescription(), food);
        fdcIdMap.putIfAbsent(food.getFdcId(), food);
        if (food.getGtinUpc() != null) {
            gtinUpcMap.putIfAbsent(food.getGtinUpc(), null);
        }
    }

    public Food getByDescription(String description) {
        return descriptionMap.get(description);
    }

    public Food getByfdcId(int fdcID) {
        return fdcIdMap.get(fdcID);
    }

    public Food getBygtinUpc(String gtinUpc) {
        return gtinUpcMap.get(gtinUpc);
    }
}

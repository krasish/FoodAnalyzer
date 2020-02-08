package bg.sofia.uni.fmi.mjt.foodanalyzer.server.database;

public class Nutrient {
    private double value;

    public Nutrient(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

}

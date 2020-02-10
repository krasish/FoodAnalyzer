package bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.entities;

public class Food {
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + fdcId;
        result = prime * result + ((gtinUpc == null) ? 0 : gtinUpc.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Food other = (Food) obj;
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (fdcId != other.fdcId) {
            return false;
        }
        if (gtinUpc == null) {
            if (other.gtinUpc != null) {
                return false;
            }
        } else if (!gtinUpc.equals(other.gtinUpc)) {
            return false;
        }
        return true;
    }

    private int fdcId;
    private String description;
    private String gtinUpc;

    public Food(int fdcId, String description, String gtinUpc, String ingredients) {
        this.fdcId = fdcId;
        this.description = description;
        this.gtinUpc = gtinUpc;
    }

    public int getFdcId() {
        return fdcId;
    }

    public String getDescription() {
        return description;
    }

    public String getGtinUpc() {
        return gtinUpc;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(String.format("Description: %s%n", description));

        result.append(String.format("fdcId: %d%n", fdcId));

        if (gtinUpc != null) {
            result.append(String.format("GTIN/UPC: %s%n", gtinUpc));
        }

        return result.toString();
    }

}

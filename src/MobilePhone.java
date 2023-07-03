import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MobilePhone {
    private final String brand;
    private final String model;
    private final String serialNumber;
    private double storage;
    private final OperatingSystem operatingSystem;
    private Map<Contact, String> contactStringMap;
    private Map<Application, String> appStringMap;


    public MobilePhone(String brand, String model, String serialNumber, double storage, OperatingSystem operatingSystem) {
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.storage = storage;
        this.operatingSystem = operatingSystem;
        this.contactStringMap = new HashMap<>();
        this.appStringMap = new HashMap<>();

    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public double getStorage() {
        return storage;
    }

    public void setStorage(double storage) {
        this.storage = storage;
    }

    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("----- Mobile Phone Spesifications -----").append('\n');
        sb.append("Brand: ").append(brand).append('\n');
        sb.append("Model: ").append(model).append('\n');
        sb.append("Storage: ").append(storage).append('\n');
        sb.append("Operating System: ").append(operatingSystem);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MobilePhone that)) return false;
        return storage == that.storage
                && Objects.equals(brand, that.brand)
                && Objects.equals(model, that.model)
                && Objects.equals(serialNumber, that.serialNumber)
                && operatingSystem == that.operatingSystem;
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, serialNumber, storage, operatingSystem);
    }
}

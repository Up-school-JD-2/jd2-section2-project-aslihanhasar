import java.util.EnumSet;

public enum OperatingSystem {
    iOS,
    ANDROID,
    WINDOWS,
    BLACKBERRY,
    SYMBIAN;

    public static OperatingSystem getOperatingSystem(String operatingSystem) {
        EnumSet<OperatingSystem> operatingSystems = EnumSet.allOf(OperatingSystem.class);
        for (OperatingSystem os : operatingSystems) {
            if (os.name().equalsIgnoreCase(operatingSystem)) {
                return os;
            }
        }
        return null;
    }

    public static void listOperatingSystems() {
        EnumSet<OperatingSystem> operatingSystems = EnumSet.allOf(OperatingSystem.class);
        System.out.println("----- Operating Systems -----");
        for (OperatingSystem os : operatingSystems) {
            System.out.println(os.name());
        }
    }

}

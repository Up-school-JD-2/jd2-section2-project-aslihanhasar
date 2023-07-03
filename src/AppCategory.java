import java.util.EnumSet;

public enum AppCategory {
    SHOPPING,
    ENTERTAINMENT,
    MUSIC,
    LIFESTYLE,
    EDUCATION,
    MEDICAL,
    SPORTS,
    TRAVEL;

    public static AppCategory getAppCategory(String appCategory) {
        EnumSet<AppCategory> appCategories = EnumSet.allOf(AppCategory.class);
        for (AppCategory c : appCategories) {
            if (c.name().equalsIgnoreCase(appCategory)) {
                return c;
            }
        }
        return null;
    }

    public static void listCategories() {
        System.out.println("----- Application Categories -----");
        EnumSet<AppCategory> appCategories = EnumSet.allOf(AppCategory.class);
        for (AppCategory c : appCategories) {
            System.out.println(c.name());
        }
    }
}



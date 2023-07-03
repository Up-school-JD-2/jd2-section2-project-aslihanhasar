import java.io.Serializable;

public class Application implements Serializable {
    private String appTitle;
    private String version;
    private AppCategory category;
    private double size;

    public Application(String appTitle, String version, AppCategory category, double size) {
        this.appTitle = appTitle;
        this.version = version;
        this.category = category;
        this.size = size;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public AppCategory getCategory() {
        return category;
    }

    public void setCategory(AppCategory category) {
        this.category = category;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("----- Application Information -----").append('\n');
        sb.append("Name: ").append(appTitle).append('\n');
        sb.append("Version: ").append(version).append('\n');
        sb.append("Category: ").append(category).append('\n');
        sb.append("Size: ").append(size).append('\n');
        return sb.toString();
    }
}

package model;

public class ApplicationCategoryInstallation {
    private String name;
    private String installs;

    private String category;

    public ApplicationCategoryInstallation(String name, String installs, String category) {
        this.name = name;
        this.installs = installs;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstalls() {
        return installs;
    }

    public void setInstalls(String installs) {
        this.installs = installs;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}

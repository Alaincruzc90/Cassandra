package model;

public class ApplicationDateRating {
    private String name;
    private String lastUpdate;
    private Float rating;
    private String installs;
    private Integer reviewCount;

    public ApplicationDateRating(String name, String lastUpdate, Float rating, String installs, Integer reviewCount) {
        this.name = name;
        this.lastUpdate = lastUpdate;
        this.rating = rating;
        this.installs = installs;
        this.reviewCount = reviewCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getInstalls() {
        return installs;
    }

    public void setInstalls(String installs) {
        this.installs = installs;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }


}

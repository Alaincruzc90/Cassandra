package model;

public class ApplicationVersionFeeling {
    private String androidVersion;
    private Float feeling;
    private Float rating;
    private Integer reviewCount;
    private String installs;
    private String name;

    public ApplicationVersionFeeling(String androidVersion, Float feeling, Float rating, Integer reviewCount, String installs, String name) {
        this.androidVersion = androidVersion;
        this.feeling = feeling;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.installs = installs;
        this.name = name;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public Float getFeeling() {
        return feeling;
    }

    public void setFeeling(Float feeling) {
        this.feeling = feeling;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getInstalls() {
        return installs;
    }

    public void setInstalls(String installs) {
        this.installs = installs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

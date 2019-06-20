package model;

public class Detail {

    private String id;
    private Integer categoryOneId;
    private Integer categoryTwoId;
    private Integer value;

    public Detail(String id, Integer categoryOneId, Integer categoryTwoId, Integer value) {
        this.id = id;
        this.categoryOneId = categoryOneId;
        this.categoryTwoId = categoryTwoId;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCategoryOneId() {
        return categoryOneId;
    }

    public void setCategoryOneId(Integer categoryOneId) {
        this.categoryOneId = categoryOneId;
    }

    public Integer getCategoryTwoId() {
        return categoryTwoId;
    }

    public void setCategoryTwoId(Integer categoryTwoId) {
        this.categoryTwoId = categoryTwoId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

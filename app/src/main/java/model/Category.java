package model;

public class Category {

    private String categoryId;
    private String categoryName;
    private String categoryImage;
    private String categoryDescription;

    public Category(String categoryDescription, String categoryImage, String categoryName, String categoryId) {
        this.categoryDescription = categoryDescription;
        this.categoryImage = categoryImage;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}

package fptu.summer.foodmanage.entity;

import javax.persistence.*;

@Entity
@Table(name = "Category", schema = "dbo", catalog = "FoodSystem")
public class CategoryEntity {
    private String categoryId;
    private String categoryName;

    public CategoryEntity() {
    }

    @Id
    @Column(name = "CategoryId")
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "CategoryName")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

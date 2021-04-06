package sel.exicon.mvc_thymeleaf_project.entity;

import sel.exicon.mvc_thymeleaf_project.dto.ProductDetailsDto;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int price;
    @OneToOne(cascade = CascadeType.ALL)
    private ProductDetails productDetails;

    public Product() {
    }

    public Product(String name, int price, ProductDetails productDetails) {
        this.name = name;
        this.price = price;
        this.productDetails = productDetails;
    }

    public Product(int id, String name, int price, ProductDetails productDetails) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productDetails = productDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && price == product.price && Objects.equals(name, product.name) && Objects.equals(productDetails, product.productDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, productDetails);
    }
}




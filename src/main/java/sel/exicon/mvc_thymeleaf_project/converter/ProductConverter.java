package sel.exicon.mvc_thymeleaf_project.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sel.exicon.mvc_thymeleaf_project.dto.ProductDto;
import sel.exicon.mvc_thymeleaf_project.entity.Product;
import sel.exicon.mvc_thymeleaf_project.entity.ProductDetails;
import sel.exicon.mvc_thymeleaf_project.service.ProductService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class ProductConverter implements  Converter <Product, ProductDto>{

    ProductDetailsConverter productDetailsConverter;

    @Autowired
    public void setProductDetailsConverter(ProductDetailsConverter productDetailsConverter) {
        this.productDetailsConverter = productDetailsConverter;
    }

    @Override
    public Product toModel(ProductDto dto) {
        Product model = new Product();
        if (dto != null) {
            model.setId(dto.getId());
            model.setName(dto.getName());
            model.setPrice(dto.getPrice());
            model.setProductDetails(productDetailsConverter.toModel(dto.getProductDetailsDto()));
        }
        return model;
    }
    @Override
    public ProductDto toDto(Product model) {
        ProductDto dto = new ProductDto();
        if (model != null) {
            dto.setId(model.getId());
            dto.setName(model.getName());
            dto.setPrice(model.getPrice());
            dto.setProductDetailsDto(productDetailsConverter.toDto(model.getProductDetails()));
        }
        return dto;
    }

    @Override
    public Collection<Product> toModels(Collection<ProductDto> dtos) {
        Collection<Product> models = new ArrayList<>();
        if (dtos != null) {
            for (ProductDto dto : dtos) {
                models.add(toModel(dto));
            }
        }
        return models;
    }

    @Override
    public Collection<ProductDto> toDtos(Collection<Product> models) {
        Collection<ProductDto> dtoList = new ArrayList<>();

        if (models != null) {
            for (Product model : models) {
                ProductDto dto = toDto(model);
                dtoList.add(dto);
            }
        }
        return dtoList;
    }
}

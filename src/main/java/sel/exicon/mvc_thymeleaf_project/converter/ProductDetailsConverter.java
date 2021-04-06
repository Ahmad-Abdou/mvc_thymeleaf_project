package sel.exicon.mvc_thymeleaf_project.converter;

import org.springframework.stereotype.Component;
import sel.exicon.mvc_thymeleaf_project.dto.ProductDetailsDto;
import sel.exicon.mvc_thymeleaf_project.entity.ProductDetails;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class ProductDetailsConverter implements Converter<ProductDetails, ProductDetailsDto>{
    @Override
    public ProductDetails toModel(ProductDetailsDto dto) {
        ProductDetails productDetails = new ProductDetails();
        if (dto != null) {
            productDetails.setId(dto.getId());
            productDetails.setName(dto.getName());
            productDetails.setDescription(dto.getDescription());
            productDetails.setImage(dto.getImage());
        }
            return productDetails;
        }

    @Override
    public ProductDetailsDto toDto(ProductDetails model) {
        ProductDetailsDto dto = new ProductDetailsDto();
        if (model != null) {
            dto.setId(model.getId());
            dto.setName(model.getName());
            dto.setDescription(model.getDescription());
            if (model.getImage() != null)
                dto.setImage(model.getImage());
        }
        return dto;
    }
    @Override
    public Collection<ProductDetails> toModels(Collection<ProductDetailsDto> dtos) {
        Collection<ProductDetails> models= new ArrayList<>();
        if (dtos !=null){
            for (ProductDetailsDto dto: dtos){
                models.add(toModel(dto));
            }
        }
        return models;
    }
    @Override
    public Collection<ProductDetailsDto> toDtos(Collection<ProductDetails> models) {
        Collection<ProductDetailsDto> dtoList = new ArrayList<>();
        if (models !=null){
            for (ProductDetails model: models){
                dtoList.add(toDto(model));
            }
        }
        return dtoList;
    }
}

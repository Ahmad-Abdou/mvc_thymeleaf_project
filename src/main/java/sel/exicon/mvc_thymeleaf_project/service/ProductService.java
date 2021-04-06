package sel.exicon.mvc_thymeleaf_project.service;

import org.springframework.stereotype.Service;
import sel.exicon.mvc_thymeleaf_project.dto.ProductDto;

import java.util.List;


public interface ProductService {

    ProductDto saveOrUpdate(ProductDto productDto);

    List<ProductDto> getAll();

    ProductDto findById(int id);

    void deleteById(int id);

    List<ProductDto> findByName(String name);
}

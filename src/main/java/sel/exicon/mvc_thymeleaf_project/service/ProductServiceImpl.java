package sel.exicon.mvc_thymeleaf_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sel.exicon.mvc_thymeleaf_project.converter.ProductConverter;
import sel.exicon.mvc_thymeleaf_project.dto.ProductDto;
import sel.exicon.mvc_thymeleaf_project.entity.Product;
import sel.exicon.mvc_thymeleaf_project.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService{

    ProductRepository productRepository;
    ProductConverter productConverter;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setProductConverter(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    @Override
    public ProductDto saveOrUpdate(ProductDto productDto) {
      Product product= productRepository.save(productConverter.toModel(productDto));
        productDto = productConverter.toDto(product);
        return productDto;
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> productList = new ArrayList<>();
       Iterable<Product> products = productRepository.findAll();
       products.iterator().forEachRemaining(productList :: add);
        return new ArrayList<>(productConverter.toDtos(productList));
    }

    @Override
    public ProductDto findById(int id) {

      Product product = productRepository.findById(id).orElse(null);
        return productConverter.toDto(product);
    }

    @Override
    public void deleteById(int id) {
      productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> findByName(String name) {
        List<Product> productList = productRepository.findByNameIgnoreCase(name);

        return new ArrayList<>(productConverter.toDtos(productList));
    }
}

package sel.exicon.mvc_thymeleaf_project.repository;

import org.springframework.data.repository.CrudRepository;
import sel.exicon.mvc_thymeleaf_project.entity.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Integer> {

   List<Product> findByNameIgnoreCase(String name);

   List<Product> findByProductDetails_DescriptionContains(String description);
}
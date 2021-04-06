package sel.exicon.mvc_thymeleaf_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sel.exicon.mvc_thymeleaf_project.dto.ProductDetailsDto;
import sel.exicon.mvc_thymeleaf_project.dto.ProductDto;
import sel.exicon.mvc_thymeleaf_project.entity.Product;
import sel.exicon.mvc_thymeleaf_project.service.ProductService;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/product")
public class ProductManagementController {

    ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    // http request type = GET
    // URL = http://localhost:8080/admin/product/
    @GetMapping("/")
    public String getAll(Model model) {
        model.addAttribute("productDtoList", productService.getAll());
        return "productManagement";
    }


    @GetMapping("/find/{id}")
    public String getProductById(@PathVariable("id") Integer id, Model model) {
        System.out.println("ID: " + id);
        ProductDto optionalProductDto = productService.findById(id);
        model.addAttribute("productDto", optionalProductDto);
        return "adminProductDetails";
    }

    @GetMapping("/addForm")
    public String registerForm(Model model) {
        ProductDto dto = new ProductDto();
        model.addAttribute("dto", dto);
        return "productAddForm";
    }


    @PostMapping("/add")
    public String add(@ModelAttribute("dto") @Valid ProductDto productDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (productDto.getName().startsWith("J")){
            FieldError error= new FieldError("dto","name","Name should not be started with J character :) ");
            bindingResult.addError(error);
        }

        if (bindingResult.hasErrors()){
            return "productAddForm";
        }


        productService.saveOrUpdate(productDto);
        redirectAttributes.addFlashAttribute("message", "Add Product Name: " + productDto.getName() + " is Done");
        redirectAttributes.addFlashAttribute("alertClass","alert-success");

        return "redirect:/admin/product/";
    }


    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        productService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Delete Product ID: " +id + " is Done");
        redirectAttributes.addFlashAttribute("alertClass","alert-info");
        return "redirect:/admin/product/";
    }
}
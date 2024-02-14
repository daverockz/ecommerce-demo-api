package com.minex.ecommerce.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/products")
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }
  
  @GetMapping
  public List<Product> getProducts() {
    return productService.getProducts();
  }

  @GetMapping(path = "{productId}")
  public Product getProduct(@PathVariable("productId") Long productId) {
    return productService.getProduct(productId);
  }
  
  @PostMapping
  public void addProduct(@RequestBody Product product) {
    productService.addNewProduct(product);
  }
  
  @DeleteMapping(path = "{productId}")
  public void deleteProduct(@PathVariable("productId") Long productId) {
    System.out.println(productId);
  }
  
  @PutMapping(path = "{productId}")
  public void updateProduct(
    @PathVariable("productId") Long productId,
    @RequestParam(required = false) String name,
    @RequestParam(required = false) String description
  ) {
    System.out.println(name + " " + description);
  }
  
  @PatchMapping(path = "{productId}")
  public void updateProduct(@PathVariable("productId") Long productId, @RequestBody Product product) {
    System.out.println(productId + " " + product);
  }
  
}

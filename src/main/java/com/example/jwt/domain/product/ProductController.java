package com.example.jwt.domain.product;


import com.example.jwt.domain.product.dto.ProductDTO;
import com.example.jwt.domain.product.dto.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

  private final ProductService productService;
  private final ProductMapper productMapper;

  @Autowired
  public ProductController(ProductService productService, ProductMapper productMapper) {
    this.productService = productService;
    this.productMapper = productMapper;
  }

  @PostMapping({"", "/"})
  public ResponseEntity<Product> create(@Valid @RequestBody ProductDTO productDTO) {
    Product product = productMapper.productDTOToProduct(productDTO);
    return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> retrieveById(@PathVariable UUID id) {
    return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
  }

  @GetMapping("/aufgabe1")
  public ResponseEntity<List<Object[]>> aufgabe1() {
    return new ResponseEntity<>(productService.findAnzahlHergestellterProdukteProLand(), HttpStatus.OK);
  }

  @GetMapping("/aufgabe2")
  public ResponseEntity<Product> aufgabe2() {
    return new ResponseEntity<>(productService.findTeuerstesSchweizerProduktMitLagerbedingung(), HttpStatus.OK);
  }

  @GetMapping({"", "/{page}/{size}"})
  public ResponseEntity<List<Product>> retrieveAll(
          @PathVariable int page,
          @PathVariable int size

  ) {
    return new ResponseEntity<>(productService.findAll(PageRequest.of(page-1, size, Sort.by("price").descending())), HttpStatus.OK);
  }

  @PutMapping({"", "/"})
  public ResponseEntity<Product> updateById(@RequestBody Product product) {
    return new ResponseEntity<>(productService.updateById(product.getId(), product), HttpStatus.OK);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Product> partialUpdateById(@RequestBody Product product) {
    return new ResponseEntity<>(productService.updateById(product.getId(), product), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/price/highest")
  public ResponseEntity<Product> retrieveMostExpensive() {
    return new ResponseEntity<>(productService.findMostExpensive(), HttpStatus.OK);
  }

}

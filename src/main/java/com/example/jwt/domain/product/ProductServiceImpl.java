package com.example.jwt.domain.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository repository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.repository = productRepository;
  }

  @Override
  public Product save(Product entity) {
    return repository.save(entity);
  }

  @Override
  public Product findById(UUID id) {
    return findOrThrow(repository.findById(id));
  }

  @Override
  public List<Product> findAll(Pageable pageable) {
    Page<Product> pagedProducts = repository.findAll(pageable);
    return pagedProducts.hasContent() ? pagedProducts.getContent() : List.of();
  }

  @Override
  public Product updateById(UUID id, Product entity) throws NoSuchElementException {
    if (repository.existsById(id)) {
      entity.setId(id);
      return repository.save(entity);
    } else {
      throw new NoSuchElementException(String.format("Product with ID '%s' could not be found", id));
    }
  }

  @Override
  public Void deleteById(UUID id) throws NoSuchElementException {
    if (repository.existsById(id)) {
      repository.deleteById(id);
    } else {
      throw new NoSuchElementException(String.format("Product with ID '%s' could not be found", id));
    }
    return null;
  }

  @Override
  public boolean existsById(UUID id) {
    return repository.existsById(id);
  }

  @Override
  public Product findOrThrow(Optional<Product> optional) throws NoSuchElementException {
    if (optional.isPresent()) {
      return optional.get();
    } else {
      throw new NoSuchElementException("No value present");
    }
  }

  // As soon as we got a database connected to our application, we can fetch the most expensive product from the database
  // and return it. For now, we just return the most expensive product from a list of products!
  @Override
  public Product findMostExpensive() {
//    return repository.findFirstByOrderByPriceDesc();
    return repository.getHigestPrice();

  }

  @Override
  public Product findTeuerstesSchweizerProduktMitLagerbedingung() {
    return repository.findTeuerstesSchweizerProduktMitLagerbedingung();
  }

  @Override
  public List<Object[]> findAnzahlHergestellterProdukteProLand() {
    return repository.findAnzahlHergestellterProdukteProLand();
  }


}

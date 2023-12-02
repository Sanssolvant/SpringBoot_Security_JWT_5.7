package com.example.jwt.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    //Derived Queries
    Product findFirstByOrderByPriceDesc();

    //Native Queries
    @Query(value = "SELECT * FROM Product WHERE price = 99", nativeQuery = true)
    List<Product> findAllProductsNativeQuery();

    @Query(value = "SELECT * FROM Product ORDER BY price DESC LIMIT 1", nativeQuery = true)
    Product getHigestPrice();

    // Aufgabe 1

    @Query("SELECT p.herstellerland, COUNT(p) FROM Product p GROUP BY p.herstellerland")
    List<Object[]> findAnzahlHergestellterProdukteProLand();

    // Aufgabe 2

//    Product getProductByNameStartingWithAndLageranzahlGreaterThanAndOrderByPriceDesc(String name, int lageranzahl);
    @Query(value = "SELECT * FROM Product WHERE UPPER(name) LIKE 'A%' AND herstellerland like 'Switzerland'  AND lageranzahl >= 3 ORDER BY price DESC LIMIT 1", nativeQuery = true)
    Product findTeuerstesSchweizerProduktMitLagerbedingung();
}

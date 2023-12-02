package com.example.jwt.domain.product;

import com.example.jwt.domain.user.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "product")
public class Product {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "price")
  private int price;
  @Column(name = "herstellerland")
  @Enumerated(EnumType.STRING)
  private Herstellerland herstellerland;
  @Column(name = "lageranzahl")
  private int lageranzahl;

  private User users;

  public Product(UUID id, String name, int price, Herstellerland herstellerland, int lageranzahl) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.herstellerland = herstellerland;
    this.lageranzahl = lageranzahl;
  }

  public Product() {
  }

  public int getLageranzahl() {
    return lageranzahl;
  }

  public Product setLageranzahl(int lageranzahl) {
    this.lageranzahl = lageranzahl;
    return this;
  }

  public Herstellerland getHerstellerland() {
    return herstellerland;
  }

  public Product setHerstellerland(Herstellerland herstellerland) {
    this.herstellerland = herstellerland;
    return this;
  }

  public UUID getId() {
    return id;
  }

  public Product setId(UUID id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Product setName(String name) {
    this.name = name;
    return this;
  }

  public double getPrice() {
    return price;
  }

  public Product setPrice(int price) {
    this.price = price;
    return this;
  }

}

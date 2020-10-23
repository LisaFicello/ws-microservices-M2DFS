package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.web.exceptions.ProduitIntrouvableException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    private static List<Product> products = new ArrayList<Product>(){{
        add(new Product(1, "Ordinateur Portable", 350, 230));
        add(new Product(2, "Aspirateur Robot", 500, 300));
        add(new Product(3, "Table de Ping Pong", 750, 350));
    }};

    //PARTIE 0
    //Récupérer la liste des produits
    @RequestMapping(value = "/Produits", method = RequestMethod.GET)
    public List<Product> listeProduits(){
        return products;
    }
    /*public MappingJacksonValue listeProduits() {
        Iterable<Product> produits = productDao.findAll();
        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat");
        FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);
        MappingJacksonValue produitsFiltres = new MappingJacksonValue(produits);
        produitsFiltres.setFilters(listDeNosFiltres);
        return produitsFiltres;
    }*/


    //Récupérer un produit par son Id
    @RequestMapping(value = "/Produits/{id}", method = RequestMethod.GET)
    public Product afficherUnProduit(@PathVariable int id) {
        Product produit = new Product();
        for (Product product : products){
            if (product.getId() == id){
                produit = product;
            }
        }
        return produit;
    }

    //ajouter un produit

    //TO DO

    /*@PostMapping(value = "/Produits")
    public ResponseEntity<Void> ajouterProduit(@Valid @RequestBody Product product) {

        Product productAdded =  productDao.save(product);

        if (productAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }*/

    // supprimer un produits
    @DeleteMapping(value = "/deleteProduits/{id}")
    public void supprimerProduit(@PathVariable int id) {
        products.removeIf(product -> product.getId() == id);
    }

    // Mettre à jour un produit
    @PutMapping(value = "/updateProduit/{id}")
    public void updateProduit(@PathVariable int id, @RequestBody Product product) {
        for (Product produit : products){
            if (produit.getId() == id){
                produit.setId(product.getId());
                produit.setNom(product.getNom());
                produit.setPrix(product.getPrix());
                produit.setPrixAchat(product.getPrixAchat());
            }
        }
    }

    @GetMapping(value="/AdminProduits")
    public Map<String,Integer> calculerMargeProduit(){
        int marge = 0;
        Map<String,Integer> responses = new HashMap<>();
        for (Product product : products){
            marge = product.getPrix() - product.getPrixAchat();
            responses.put("id="+product.getId()+", nom="+product.getNom()+", prix="+product.getPrix(), marge);
        }
        return responses;
    }

    //Pour les tests
    @GetMapping(value = "test/produits/{prix}")
    public List<Product>  testeDeRequetes(@PathVariable int prix) {
        return productDao.chercherUnProduitCher(400);
    }



}

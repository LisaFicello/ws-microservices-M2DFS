package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.web.exceptions.ProduitGratuitException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.mockito.Mockito.*;

class ProductControllerTest {
    @Mock
    ProductDao productDao;
    @Mock
    List<Product> products;
    @InjectMocks
    ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testListeProduits() {
        List<Product> result = productController.listeProduits();
        List<Product> products = new ArrayList<Product>(){{
            add(new Product(1, "Ordinateur Portable", 350, 230));
            add(new Product(2, "Aspirateur Robot", 500, 300));
            add(new Product(3, "Table de Ping Pong", 750, 350));
        }};
        Assertions.assertEquals(products, result);
    }

    @Test
    void testAfficherUnProduit() {
        Product p = new Product(0, "nom", 0, 0);
        Product result = productController.afficherUnProduit(0);
        Assertions.assertTrue(p.getId() == result.getId());
        Assertions.assertTrue(p.getNom().equals(p.getNom()));
        Assertions.assertTrue(p.getPrix() == result.getPrix());
        Assertions.assertTrue(p.getPrixAchat() == result.getPrixAchat());
    }

    @Test
    void testAjouterProduit() throws ProduitGratuitException {
        ResponseEntity<Void> result = productController.ajouterProduit(new Product(8, "nom", 10, 4));
        Assertions.assertTrue(result.getStatusCodeValue() >= 200);
        Assertions.assertTrue( result.getStatusCodeValue() < 300);
    }

    @Test
    void testSupprimerProduit() {
        productController.supprimerProduit(0);
    }

    @Test
    void testUpdateProduit() throws ProduitGratuitException {
        productController.updateProduit(0, new Product(0, "nom", 2, 3));
    }

    @Test
    void testMauvaisUpdateProduit() throws ProduitGratuitException {
        Product p = new Product(1,"Ordinateur portable gratuit", 0,120);
        Assertions.assertThrows(ProduitGratuitException.class, () -> {
            productController.updateProduit(1, p);
        });
    }


    @Test
    void testCalculerMargeProduit() {
        Map<String, Integer> result = productController.calculerMargeProduit();
        List<Product> products = new ArrayList<Product>(){{
                add(new Product(1, "Ordinateur Portable", 350, 230));
                add(new Product(2, "Aspirateur Robot", 500, 300));
                add(new Product(3, "Table de Ping Pong", 750, 350));
            }};
        Map<String, Integer> listTest = new HashMap<String, Integer>();
        for (Product product : products){
            int marge = product.getPrix() - product.getPrixAchat();
            listTest.put("id="+product.getId()+", nom="+product.getNom()+", prix="+product.getPrix(), marge);
        }
        Assertions.assertEquals(listTest, result);
    }

    @Test
    void testTrierProduitsParOrdreAlphabetique() {
        when(productDao.findAllByOrderByNomAsc()).
                thenReturn(Arrays.<Product>asList(
                        new Product(1, "Ordi", 10, 0),
                        new Product(2, "Table de ping pong", 10, 0),
                        new Product(3, "Jus d'orange", 10, 0)));

        List<Product> result = productController.trierProduitsParOrdreAlphabetique();
        Assertions.assertEquals(1, result.get(0).getId());
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Assertions.assertEquals(Arrays.<Product>asList(new Product(0, "nom", 0, 0)), result);
    }

    @Test
    void testAfficherUnProduit() {
        Product result = productController.afficherUnProduit(0);
        Assertions.assertEquals(new Product(0, null, 0, 0), result);
    }

    @Test
    void testAjouterProduit() throws ProduitGratuitException {
        ResponseEntity<Void> result = productController.ajouterProduit(new Product(0, null, 0, 0));
        Assertions.assertEquals(null, result);
    }

    @Test
    void testSupprimerProduit() {
        productController.supprimerProduit(0);
    }

    @Test
    void testUpdateProduit() {
        productController.updateProduit(0, new Product(0, "nom", 0, 0));
    }

    @Test
    void testCalculerMargeProduit() {
        Map<String, Integer> result = productController.calculerMargeProduit();
        Assertions.assertEquals(new HashMap<String, Integer>() {{
            put("String", Integer.valueOf(0));
        }}, result);
    }

    @Test
    void testTrierProduitsParOrdreAlphabetique() {
        when(productDao.findAllByOrderByNomAsc()).thenReturn(Arrays.<Product>asList(new Product(0, "nom", 0, 0)));

        List<Product> result = productController.trierProduitsParOrdreAlphabetique();
        Assertions.assertEquals(Arrays.<Product>asList(new Product(0, "nom", 0, 0)), result);
    }

    @Test
    void testTesteDeRequetes() {
        when(productDao.chercherUnProduitCher(anyInt())).thenReturn(Arrays.<Product>asList(new Product(0, "nom", 0, 0)));

        List<Product> result = productController.testeDeRequetes(0);
        Assertions.assertEquals(Arrays.<Product>asList(new Product(0, "nom", 0, 0)), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
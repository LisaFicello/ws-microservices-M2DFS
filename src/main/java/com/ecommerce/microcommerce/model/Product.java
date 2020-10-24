package com.ecommerce.microcommerce.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;


@Entity
//@JsonFilter("monFiltreDynamique")
public class Product {

    @Id
    @GeneratedValue
    @ApiModelProperty(
            value = "id produit",
            name = "id",
            required = true,
            dataType = "int")
    private int id;

    @ApiModelProperty(
            value = "nom produit",
            name = "nom",
            required = true,
            dataType = "String")
    @Length(min=3, max=20, message = "Le nom du produit fais moins de 3 caractères ou plus de 20")
    private String nom;

    @ApiModelProperty(
            value = "prix produit",
            name = "prix",
            required = true,
            dataType = "int")
    @Min(value = 1)
    private int prix;

    @ApiModelProperty(
            value = "prix achat produit",
            name = "prixAchat",
            required = true,
            dataType = "int"
    )

    //Non affiché
    private int prixAchat;

    //constructeur par défaut
    public Product() {
    }

    //constructeur pour nos tests
    public Product(int id, String nom, int prix, int prixAchat) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.prixAchat = prixAchat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(int prixAchat) {
        this.prixAchat = prixAchat;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                '}';
    }
}
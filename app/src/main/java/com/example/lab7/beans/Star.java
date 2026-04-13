package com.example.lab7.beans;

/**
 * Classe modèle représentant une star (acteur/actrice).
 */
public class Star {

    private int id;           
    private String name;      
    private int img;       // Changé de String à int pour les ressources locales
    private float star;       

    private static int counter = 0;

    public Star(String name, int img, float star) {
        this.id = ++counter;
        this.name = name;
        this.img = img;
        this.star = star;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getImg() { return img; }
    public float getStar() { return star; }

    public void setName(String name) { this.name = name; }
    public void setImg(int img) { this.img = img; }
    public void setStar(float star) { this.star = star; }
}
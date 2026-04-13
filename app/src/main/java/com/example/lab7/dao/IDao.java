package com.example.lab7.dao;

import java.util.List;

/**
 * Interface générique définissant les opérations CRUD.
 */
public interface IDao<T> {
    boolean create(T o);         // Ajouter un élément
    boolean update(T o);         // Modifier un élément existant
    boolean delete(T o);         // Supprimer un élément
    T findById(int id);          // Trouver un élément par son ID
    List<T> findAll();           // Récupérer tous les éléments
}
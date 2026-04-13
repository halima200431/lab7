package com.example.lab7.service;

import com.example.lab7.R;
import com.example.lab7.beans.Star;
import com.example.lab7.dao.IDao;
import java.util.ArrayList;
import java.util.List;

/**
 * Couche service pour la gestion des stars (Images Locales).
 */
public class StarService implements IDao<Star> {

    private List<Star> stars;
    private static StarService instance;

    private StarService() {
        stars = new ArrayList<>();
        seed();
    }

    public static StarService getInstance() {
        if (instance == null) {
            instance = new StarService();
        }
        return instance;
    }

    private void seed() {
        // Utilisation des ressources locales ajoutées par l'utilisateur
        stars.add(new Star(
            "George Clooney",
            R.drawable.george,
            3.0f
        ));
        stars.add(new Star(
            "Michelle Rodriguez",
            R.drawable.michelle,
            5.0f
        ));
        stars.add(new Star(
            "Scarlette Johansson",
            R.drawable.scarlette,
            4.5f
        ));
        stars.add(new Star(
            "Leonardo DiCaprio",
            R.drawable.leonardo,
            4.8f
        ));
    }

    @Override
    public boolean create(Star o) {
        return stars.add(o);
    }

    @Override
    public boolean update(Star o) {
        for (Star s : stars) {
            if (s.getId() == o.getId()) {
                s.setName(o.getName());
                s.setImg(o.getImg());
                s.setStar(o.getStar());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Star o) {
        return stars.remove(o);
    }

    @Override
    public Star findById(int id) {
        for (Star s : stars) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    @Override
    public List<Star> findAll() {
        return stars;
    }
}
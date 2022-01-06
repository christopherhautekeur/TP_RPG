package Metier;


import models.Personnage;

public interface IPersonnage {

    void addPersonnage(String name);
    void  attack(Personnage personnage);
    boolean  levelLives();
    void  addLives(int lvl);
}

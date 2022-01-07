package models;

import enums.Race;

public class Joueur extends Personnage {

    private Race race;

    public Joueur(String nom){
        super(nom,5,5,1,1,1);
    }

    public Joueur(String nom, Race race){
        this(nom);
        this.race = race;
        gagnerStats();

    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void gagnerStats(){
        switch(race){
            case HUMAIN:
                gagnerIntelligence(2);
                gagnerForce(2);
                break;
            case MORT_VIVANT:
                gagnerIntelligence(1);
                gagnerForce(3);
                break;
            case ELF:
                gagnerIntelligence(4);
                gagnerForce(0);
                break;
            case DEMI_DIEU:
                gagnerIntelligence(3);
                gagnerForce(3);
                break;
            case DEMI_ORC:
                gagnerIntelligence(0);
                gagnerForce(4);
                break;
        }
    }

    @Override
    public void gagnerNiveau() {
        super.gagnerNiveau();
        gagnerStats();
    }
}

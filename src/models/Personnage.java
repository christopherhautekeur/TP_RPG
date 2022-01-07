package models;


import java.util.ArrayList;
import java.util.List;

public class Personnage {
    private String nom;
    private int niveau, vieMax, vieAct, manaMax, manaAct, force, intelligence, defense, defenseMagique;
    private int experience;
    private List<Sort> sorts;

    public Personnage(){
        niveau = 1;
        experience = 0;
        sorts = new ArrayList<>();
    }

    public Personnage(String nom, int force, int intelligence, int defense, int defenseMagique){
        this();
        this.nom = nom;
        this.force = force;
        this.intelligence = intelligence;
        this.defense = defense;
        this.defenseMagique = defenseMagique;

        vieMax = vieAct = force * 5;
        manaMax = manaAct = intelligence * 5;
    }

    public Personnage(String nom, int force, int intelligence, int defense, int defenseMagique, int niveau){
        this(nom, force, intelligence, defense, defenseMagique);
        this.niveau = niveau;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getVieMax() {
        return vieMax;
    }

    public void setVieMax(int vieMax) {
        this.vieMax = vieMax;
    }

    public int getVieAct() {
        return vieAct;
    }

    public void setVieAct(int vieAct) {
        this.vieAct = vieAct;
    }

    public int getManaMax() {
        return manaMax;
    }

    public void setManaMax(int manaMax) {
        this.manaMax = manaMax;
    }

    public int getManaAct() {
        return manaAct;
    }

    public void setManaAct(int manaAct) {
        this.manaAct = manaAct;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDefenseMagique() {
        return defenseMagique;
    }

    public void setDefenseMagique(int defenseMagique) {
        this.defenseMagique = defenseMagique;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void prendreDegat(int montant){
        vieAct -= montant;
    }

    public int attaquer(Personnage p){
        int dommage = this.force - p.getDefense();
        if(dommage > 0){
            p.prendreDegat(dommage);
            return dommage;
        }
        return 0;
    }

    public int lancerSort(Personnage p){
        int dommage = this.intelligence - p.getDefenseMagique();
        if(dommage > 0){
            p.prendreDegat(dommage);
            return dommage;
        }
        return 0;
    }

    public boolean fuir(){
        int rand = (int)Math.floor(Math.random() * 100) + 1; // nombre entre 1 et 100
        return rand <= 30;
    }

    public void gagnerForce(int force){
        this.force += force;
        vieMax = this.force * 5;
    }

    public void gagnerIntelligence(int intelligence){
        this.intelligence += intelligence;
        manaMax = this.intelligence * 5;
    }

    public void gagnerNiveau(){
        niveau++;
    }

    public boolean gagnerExperience(int experience){
        this.experience += experience;
        if(this.experience >= 4+(niveau*3)){
            this.experience = this.experience - (4+(niveau*3));
            gagnerNiveau();
            return true;
        }
        return false;
    }

}

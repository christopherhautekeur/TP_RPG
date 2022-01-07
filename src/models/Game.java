package models;

import enums.Race;

import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static Joueur joueur;
    private static Scanner sc;

    public static void run(){
        sc = new Scanner(System.in);

        int choix = 0;

        creationPerso();
        do{
            afficherChoix();
            choix = sc.nextInt();


            switch(choix){
                case 1:
                    // combat
                    lancerCombat();
                    break;
                case 2:
                    // Repos
                    System.out.println("repos");
                    System.out.println("vous regagnez votre vie");
                    joueur.setVieAct(joueur.getVieMax());
                    joueur.setManaAct(joueur.getManaMax());
                    break;
                case 3:
                    infoDetail();
                    sc.nextLine();
                    attendreInput();
                    break;

            }

        } while (choix != 4);

        sc.close();
    }

    public static void afficherInfosPersonnage(Joueur j){
        System.out.println("---------------------");
        System.out.println("Personnage : " + j.getNom());
        System.out.println("      Race : " + j.getRace().name());
        System.out.println("---------------------");
        System.out.println("Niveau : " + j.getNiveau());
        System.out.println("    XP : " + j.getExperience() + "/" + (4+(j.getNiveau()*3)));
        System.out.println("    HP : " + j.getVieAct() + "/" + j.getVieMax());
        System.out.println("    MP : " + j.getManaAct() + "/" + j.getManaMax());
        System.out.println("---------------------");
    }

    public static void afficherInfosPersonnage(Personnage p){
        System.out.println("---------------------");
        System.out.println("Personnage : " + p.getNom());
        System.out.println("---------------------");
        System.out.println("Niveau : " + p.getNiveau());
        System.out.println("    HP : " + p.getVieAct() + "/" + p.getVieMax());
        System.out.println("---------------------");
    }

    public static void infoDetail(){
        System.out.println("---------------------");
        System.out.println("Personnage : " + joueur.getNom());
        System.out.println("      Race : " + joueur.getRace().name());
        System.out.println("---------------------");
        System.out.println("Niveau : " + joueur.getNiveau());
        System.out.println("    HP : " + joueur.getVieAct() + "/" + joueur.getVieMax());
        System.out.println("    MP : " + joueur.getManaAct() + "/" + joueur.getManaMax());
        System.out.println("---------------------");
        System.out.println("   Caracteristique");
        System.out.println("Force :           " + joueur.getForce());
        System.out.println("Intelligence :    " + joueur.getIntelligence());
        System.out.println("Defense :         " + joueur.getDefense());
        System.out.println("Defense Magique:  " + joueur.getDefenseMagique());
        System.out.println("---------------------");
    }

    public static void attendreInput(){
        System.out.println();
        System.out.println("Appuyez sur entrée pour continuer");
        sc.nextLine();
    }

    public static void afficherChoix(){
        afficherInfosPersonnage(joueur);
        System.out.println("1- Continuer d'explorer");
        System.out.println("2- Se reposer");
        System.out.println("3- Infos perso");
        System.out.println("4- Partir");
    }

    public static void creationPerso(){
        //Scanner sc = new Scanner(System.in);
        String nom;
        Race race;

        System.out.println("Bonjour aventurier, quel est ton nom ?");
        nom = sc.nextLine();

        for(int i = 0; i < Race.values().length; i++){
            System.out.println((i+1) + "- " + Race.values()[i]);
        }
        int choix = sc.nextInt();
        race = Race.values()[choix-1];

        joueur = new Joueur(nom, race);
        joueur.setVieAct(joueur.getVieMax());
        joueur.setManaAct(joueur.getManaMax());
    }

    public static void lancerCombat(){
        // Creation du monstre
        Random r = new Random();
        Personnage monstre = new Personnage("Slime", r.nextInt(3)+1,1,r.nextInt(4),r.nextInt(2),5);

        System.out.println("Vous croisez un " + monstre.getNom());



        int choix = 0;
        int dmg;

        while(monstre.getVieAct() > 0 && joueur.getVieAct() > 0){
            afficherInfosPersonnage(joueur);
            afficherInfosPersonnage(monstre);
            System.out.println("1- Attaquer");
            System.out.println("2- Lancer un sort");
            System.out.println("3- Fuir");
            choix = sc.nextInt();
            System.out.println("-------------------------------------------");
            System.out.println("\u001B[36m");
            switch(choix){ // Action joueur
                case 1:
                    System.out.println("Vous attaquez le monstre");
                    dmg = joueur.attaquer(monstre);
                    if(dmg > 0)
                        System.out.println("L'attaque réussi, vous infligez " + dmg + " au " + monstre.getNom());
                    else
                        System.out.println("Vous n'arrivez pas a infliger des dégats au " + monstre.getNom());
                    break;
                case 2:
                    System.out.println("Vous lancez une boule de feu sur le monstre");
                    dmg = joueur.lancerSort(monstre);
                    if(dmg > 0)
                        System.out.println("L'attaque réussi, vous infligez " + dmg + " au " + monstre.getNom());
                    else
                        System.out.println("Vous n'arrivez pas a infliger des dégats au " + monstre.getNom());
                    break;
                case 3:
                    System.out.println("Vous essayez de fuir le combat");
                    if(joueur.fuir()){
                        System.out.println("Vous avez réussi à échapper au monstre");
                        System.out.println("\u001B[0m");
                        System.out.println("-------------------------------------------");
                        return;
                    }
                    System.out.println("\u001B[31m");
                    System.out.println("Vous n'avez pas réussi à échapper au monstre");
                    break;
            }
            System.out.println("\u001B[0m");
            System.out.println("-------------------------------------------");
            sc.nextLine();
            attendreInput();

            if(monstre.getVieAct() > 0){
                System.out.println("-------------------------------------------");
                System.out.println("\u001B[31m");
                System.out.println("Le " + monstre.getNom() + " vous attaque");
                dmg = monstre.attaquer(joueur);
                System.out.println("Vous recevez " + dmg + " points de dégats");
                System.out.println("\u001B[0m");
                System.out.println("-------------------------------------------");
                attendreInput();
            }

        }
        System.out.println("-------------------------------------------");
        if(joueur.getVieAct() > 0){
            System.out.println("Vous avez vaincu le " + monstre.getNom());
            System.out.println("Vous recevez " + monstre.getNiveau() + " points d'expérience");
            if(joueur.gagnerExperience(monstre.getNiveau() > joueur.getNiveau() ? 1 + monstre.getNiveau() - joueur.getNiveau() : 1)){
                System.out.println("Vous gagnez un niveau");
            }

        }
        else{
            System.out.println("Le " + monstre.getNom() + " vous a vaincu");
        }
        System.out.println("-------------------------------------------");
        attendreInput();
    }

}

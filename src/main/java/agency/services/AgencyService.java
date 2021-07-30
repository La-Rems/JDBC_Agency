package agency.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import agency.dao.ActorDAO;
import agency.dao.CastDAO;
import agency.models.Actor;
import agency.models.Cast;
import utils.DataConnect;
import utils.ConsoleManager;


public class AgencyService {
    Connection connection = null;
    Scanner sc;
    ActorDAO actorDAO;
    CastDAO castDAO;

    public AgencyService() throws SQLException {
        connection = DataConnect.getConnection();
        this.sc = new Scanner(System.in);
        this.actorDAO = new ActorDAO(connection);
        this.castDAO = new CastDAO(connection);
    }

    public void run() throws SQLException {
        newData();
        boolean answer = true;
        do {
            ConsoleManager.getInstance().printToConsole("------------------------------------------------", true);
            ConsoleManager.getInstance().printToConsole("|                   * MENU *                   |", true);
            ConsoleManager.getInstance().printToConsole("------------------------------------------------", true);
            ConsoleManager.getInstance().printToConsole("|  1 - Lister tous les Acteurs                 |", true);
            ConsoleManager.getInstance().printToConsole("|  2 - Créer un nouvel Acteur                  |", true);
            ConsoleManager.getInstance().printToConsole("|  3 - Modifier un Acteur                      |", true);
            ConsoleManager.getInstance().printToConsole("|  4 - Supprimer un Acteur                     |", true);
            ConsoleManager.getInstance().printToConsole("|  5 - Voir les détails d'un Acteur            |", true);
            ConsoleManager.getInstance().printToConsole("|  6 - Ajouter un casting                      |", true);
            ConsoleManager.getInstance().printToConsole("|  7 - Modifier un casting                     |", true);
            ConsoleManager.getInstance().printToConsole("|  8 - Supprimer un casting                    |", true);
            ConsoleManager.getInstance().printToConsole("|  0 - Quitter                                 |", true);
            ConsoleManager.getInstance().printToConsole("------------------------------------------------", true);
            ConsoleManager.getInstance().printToConsole("Votre choix -> ", false);
            String choice = ConsoleManager.getInstance().readUserInput();

            if(choice.equals("1")){

                ConsoleManager.getInstance().printToConsole("", true);
                ConsoleManager.getInstance().printToConsole("##         Liste de tous les acteurs          ##", true);
                ConsoleManager.getInstance().printToConsole("", true);
                List<Actor> actors = actorDAO.getAll();
                for(Actor actor : actors){
                    System.out.println(actor);
                }
                ConsoleManager.getInstance().printToConsole("", true);

            } else if(choice.equals("2")){

                boolean again;
                do {
                    ConsoleManager.getInstance().printToConsole("", true);
                    ConsoleManager.getInstance().printToConsole("##              Créer un acteur               ##", true);
                    ConsoleManager.getInstance().printToConsole("", true);
                    ConsoleManager.getInstance().printToConsole("Nom de l'acteur : ", false);
                    String name = ConsoleManager.getInstance().readUserInput();
                    ConsoleManager.getInstance().printToConsole("Age de l'acteur : ", false);
                    int age = Integer.parseInt(sc.nextLine());
                    if(name != null){
                        Actor actor = new Actor(name, age);
                        actorDAO.create(actor);
                        System.out.println("L'acteur a bien été crée");
                    } else {
                        ConsoleManager.getInstance().printToConsole("Le formulaire comporte une erreur", true);
                    }
                    ConsoleManager.getInstance().printToConsole("", true);
                    ConsoleManager.getInstance().printToConsole("Souhaitez-vous ajouter un nouveau produit ? Oui / Non", true);
                    String yon = ConsoleManager.getInstance().readUserInput();
                    if(yon.equalsIgnoreCase("Oui")){
                        again = true;
                    } else {
                        again = false;
                    }
                }while(again);

            } else if(choice.equals("3")){

                boolean again;
                do {
                    ConsoleManager.getInstance().printToConsole("", true);
                    ConsoleManager.getInstance().printToConsole("##            Modifier un acteur              ##", true);
                    ConsoleManager.getInstance().printToConsole("", true);
                    ConsoleManager.getInstance().printToConsole("Identifiant de l'acteur: ", false);
                    int id = ConsoleManager.getInstance().readUserInputInteger();
                    ConsoleManager.getInstance().printToConsole("Nouveau nom : ", false);
                    String name = ConsoleManager.getInstance().readUserInput();
                    ConsoleManager.getInstance().printToConsole("Nouveau âge : ", false);
                    int age = ConsoleManager.getInstance().readUserInputInteger();

                    Actor actor = actorDAO.getById(id);
                    if (actor != null) {
                        actor.setName(name);
                        actor.setAge(age);
                        actorDAO.update(actor);
                        Actor updatedActor = actorDAO.getById(id);
                        System.out.println("L'acteur a bien été modifié : " + updatedActor);
                    }
                    ConsoleManager.getInstance().printToConsole("", true);
                    ConsoleManager.getInstance().printToConsole("Souhaitez-vous ajouter un nouveau produit ? Oui / Non", true);
                    String yon = ConsoleManager.getInstance().readUserInput();
                    if(yon.equalsIgnoreCase("Oui")){
                        again = true;
                    } else {
                        again = false;
                    }
                }while(again);

            } else if(choice.equals("4")){

                boolean again;
                do {
                    ConsoleManager.getInstance().printToConsole("", true);
                    ConsoleManager.getInstance().printToConsole("##           Supprimer un acteur              ##", true);
                    ConsoleManager.getInstance().printToConsole("", true);
                    ConsoleManager.getInstance().printToConsole("Identifiant de l'acteur: ", false);
                    int id = ConsoleManager.getInstance().readUserInputInteger();
                    Actor actor = actorDAO.getById(id);
                    if (actor != null) {
                        actorDAO.delete(actor);
                        ConsoleManager.getInstance().printToConsole("L'acteur a bien été supprimé", false);
                    } else {
                        ConsoleManager.getInstance().printToConsole("Aucun produit pour l'ID : " + id, false);
                    }
                    ConsoleManager.getInstance().printToConsole("", true);
                    ConsoleManager.getInstance().printToConsole("Souhaitez-vous ajouter un nouveau produit ? Oui / Non", true);
                    String yon = ConsoleManager.getInstance().readUserInput();
                    if(yon.equalsIgnoreCase("Oui")){
                        again = true;
                    } else {
                        again = false;
                    }
                }while(again);

            } else if(choice.equals("5")){

                int count = 0;
                boolean again;
                do {
                    ConsoleManager.getInstance().printToConsole("", true);
                    ConsoleManager.getInstance().printToConsole("##           Détails d'un acteur              ##", true);
                    ConsoleManager.getInstance().printToConsole("", true);
                    ConsoleManager.getInstance().printToConsole("Identifiant de l'acteur: ", false);
                    int id = ConsoleManager.getInstance().readUserInputInteger();
                    Actor actor = actorDAO.getById(id);

                    List<Cast> casts = castDAO.getAll();
                    System.out.println(actor);
                    for(Cast cast : casts){
                        if(cast.getIdActor() == actor.getId()){
                            System.out.println(cast);
                            count++;
                        }
                    }
                    if(count == 0){
                        ConsoleManager.getInstance().printToConsole("Cet acteur n'a pas encore de casting.", true);
                    }
                    ConsoleManager.getInstance().printToConsole("", true);
                    ConsoleManager.getInstance().printToConsole("Souhaitez-vous ajouter un nouveau produit ? Oui / Non", true);
                    String yon = ConsoleManager.getInstance().readUserInput();
                    if(yon.equalsIgnoreCase("Oui")){
                        again = true;
                    } else {
                        again = false;
                    }
                }while(again);

            }else if(choice.equals("6")){

                boolean again;
                do {
                    ConsoleManager.getInstance().printToConsole("", true);
                    ConsoleManager.getInstance().printToConsole("##             Créer un casting               ##", true);
                    ConsoleManager.getInstance().printToConsole("", true);
                    ConsoleManager.getInstance().printToConsole("Id de l'acteur: ", false);
                    int id = ConsoleManager.getInstance().readUserInputInteger();
                    ConsoleManager.getInstance().printToConsole("Appréciation du casting : ", false);
                    String appreciation = ConsoleManager.getInstance().readUserInput();
                    ConsoleManager.getInstance().printToConsole("Score du casting : ", false);
                    int score = ConsoleManager.getInstance().readUserInputInteger();
                    if(appreciation != null){
                        Cast cast = new Cast(appreciation, score, id);
                        castDAO.create(cast);
                        System.out.println("Le casting a bien été crée");
                    } else {
                        ConsoleManager.getInstance().printToConsole("Le formulaire comporte une erreur", true);
                    }
                    ConsoleManager.getInstance().printToConsole("", true);
                    ConsoleManager.getInstance().printToConsole("Souhaitez-vous ajouter un nouveau produit ? Oui / Non", true);
                    String yon = ConsoleManager.getInstance().readUserInput();
                    if(yon.equalsIgnoreCase("Oui")){
                        again = true;
                    } else {
                        again = false;
                    }
                }while(again);

            }else if(choice.equals("7")){

                boolean again;
                do {
                    ConsoleManager.getInstance().printToConsole("", true);
                    ConsoleManager.getInstance().printToConsole("##            Modifier un casting             ##", true);
                    ConsoleManager.getInstance().printToConsole("", true);
                    ConsoleManager.getInstance().printToConsole("Identifiant du casting : ", false);
                    int id = ConsoleManager.getInstance().readUserInputInteger();
                    Cast cast = castDAO.getById(id);

                    ConsoleManager.getInstance().printToConsole("Nouvelle appréciation : ", false);
                    String appreciation = ConsoleManager.getInstance().readUserInput();
                    ConsoleManager.getInstance().printToConsole("Nouveau score : ", false);
                    int score = ConsoleManager.getInstance().readUserInputInteger();

                    if (cast != null) {
                        cast.setAppreciation(appreciation);
                        cast.setScore(score);
                        castDAO.update(cast);
                        Cast updatedCast = castDAO.getById(id);
                        System.out.println("Le casting a bien été modifié : " + updatedCast);
                    }
                    ConsoleManager.getInstance().printToConsole("", true);
                    ConsoleManager.getInstance().printToConsole("Souhaitez-vous ajouter un nouveau produit ? Oui / Non", true);
                    String yon = ConsoleManager.getInstance().readUserInput();
                    if(yon.equalsIgnoreCase("Oui")){
                        again = true;
                    } else {
                        again = false;
                    }
                }while(again);

            } else if(choice.equals("8")){

                boolean again;
                do {
                    ConsoleManager.getInstance().printToConsole("", true);
                    ConsoleManager.getInstance().printToConsole("##           Supprimer un casting             ##", true);
                    ConsoleManager.getInstance().printToConsole("", true);
                    ConsoleManager.getInstance().printToConsole("Identifiant du casting : ", false);
                    int id = ConsoleManager.getInstance().readUserInputInteger();
                    Cast cast = castDAO.getById(id);
                    if (cast != null) {
                        castDAO.delete(cast);
                        ConsoleManager.getInstance().printToConsole("Le casting a bien été supprimé", false);
                    } else {
                        ConsoleManager.getInstance().printToConsole("Aucun casting pour l'ID : " + id, true);
                    }
                    ConsoleManager.getInstance().printToConsole("", true);
                    ConsoleManager.getInstance().printToConsole("Souhaitez-vous ajouter un nouveau produit ? Oui / Non", true);
                    String yon = ConsoleManager.getInstance().readUserInput();
                    if(yon.equalsIgnoreCase("Oui")){
                        again = true;
                    } else {
                        again = false;
                    }
                }while(again);

            }else if(choice.equals("0")){

                answer = false;
                ConsoleManager.getInstance().printToConsole("------------------------------------------------", true);
                ConsoleManager.getInstance().printToConsole("--        Merci pour votre visite !           --", true);
                ConsoleManager.getInstance().printToConsole("------------------------------------------------", true);

            }
        }while(answer);
    }

    public void newData() throws SQLException {
        List<Actor> actors = actorDAO.getAll();
        List<Cast> casts = castDAO.getAll();
        if(actors.size() == 0 && casts.size() == 0){
            Actor actor1 = new Actor("Gérard Depardieu", 72);
            Actor actor2 = new Actor("Jean Dujardin", 48);
            Actor actor3 = new Actor("Alain Delon", 85);
            Actor actor4 = new Actor("Vincent Cassel", 54);
            Actor actor5 = new Actor("Daniel Auteuil", 71);
            Integer id1 = actorDAO.create(actor1);
            Integer id2 = actorDAO.create(actor2);
            Integer id3 = actorDAO.create(actor3);
            Integer id4 = actorDAO.create(actor4);
            Integer id5 = actorDAO.create(actor5);

            Cast cast1 = new Cast("C'était cool", 80, id1);
            Cast cast2 = new Cast("Bof bof", 40, id1);
            Cast cast3 = new Cast("Amazing!!", 100, id2);
            Cast cast4 = new Cast("Ouais ça va", 50, id3);
            Cast cast5 = new Cast("Plutôt pas mal", 60, id3);
            Cast cast6 = new Cast("Trop cool", 79, id4);
            Cast cast7 = new Cast("Zéro!!!", 0, id5);
            Cast cast8 = new Cast("Pas fou", 10, id5);
            castDAO.create(cast1);
            castDAO.create(cast2);
            castDAO.create(cast3);
            castDAO.create(cast4);
            castDAO.create(cast5);
            castDAO.create(cast6);
            castDAO.create(cast7);
            castDAO.create(cast8);
        }

    }
}

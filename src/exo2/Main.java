package exo2;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args)  {
        // Exercice 2.1
        Date date = null;
        Date today = new Date();

        try {
            System.out.println(date.getClass().getName());
        } catch (NullPointerException e) {
            System.out.println("date est null !");
        }

        System.out.println(today.getClass().getName());
        System.out.println(today);

        // Exercice 2.2
        List<Map<String, List<Integer>>> listOfMaps = new ArrayList<>();

        try {
            searchLetter("mystère", "e", listOfMaps); 
            searchLetter("mystère", "", listOfMaps); 
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public static void searchLetter(String mysteryWord, String letter, List<Map<String, List<Integer>>> listOfMaps)
            throws IllegalArgumentException {

        if (mysteryWord == null || letter == null) {
            throw new IllegalArgumentException("Le mot ou la lettre ne peut pas être null");
        }

        if (letter.isEmpty()) {
            throw new IllegalArgumentException("La lettre ne peut pas etre vide");
        }

        Map<String, List<Integer>> indexlist = new HashMap<>();
        List<Integer> positions = new ArrayList<>();

        for (int i = 0; i < mysteryWord.length(); i++) {
            if (mysteryWord.charAt(i) == letter.charAt(0)) {
                positions.add(i * 2);
            }
        }

        System.out.println();
        if (!positions.isEmpty()) {
            System.out.println("Bien joué, la lettre '" + letter + "' est dans le mot");
        } else {
            System.out.println("La lettre '" + letter + "' n'est pas dans le mot");
        }

        indexlist.put(letter, positions);
        listOfMaps.add(indexlist);
    }
}

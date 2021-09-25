package advisor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String> mountains = new ArrayList<>(List.of("Sia", "Diplo", "Labrinth"));
        List<String> runaway = new ArrayList<>(List.of("Lil peep"));
        List<String> theGreatestShow = new ArrayList<>(List.of("Panic! At The Disco"));
        List<String> allOutLife = new ArrayList<>(List.of("Slipknot"));
        List<String> featured = new ArrayList<>(List.of("Mellow Morning", "Wake Up and Smell the Coffee"
                , "Monday Motivation", "Songs to Sing in the Shower"));
        List<String> mood = new ArrayList<>(List.of("Walk Like a Badass", "Rage Beats", "Arab Mood Booster"
                , "Sunday Stroll"));
        Scanner get = new Scanner(System.in);
        boolean switch_control = true;
        while (switch_control) {
            String input = get.nextLine();
            switch (input) {
                case "new":
                    System.out.println("---NEW RELEASES---");
                    System.out.println("Mountains " + mountains);
                    System.out.println("Runaway " + runaway);
                    System.out.println("The Greatest Show " + theGreatestShow);
                    System.out.println("All Out Life " + allOutLife);
                    break;
                case "featured":
                    System.out.println("---FEATURED---");
                    for (String st : featured) {
                        System.out.println(st);
                    }
                    break;
                case "categories":
                    System.out.println("---CATEGORIES---");
                    System.out.println("Top Lists\nPop\nMood\nLatin");
                    break;
                case "playlists Mood":
                    System.out.println("---MOOD PLAYLISTS---");
                    for (String st : mood) {
                        System.out.println(st);
                    }
                    break;
                case "exit":
                    System.out.println("---GOODBYE!---");
                    switch_control = false;
                    break;


            }
        }

    }
}

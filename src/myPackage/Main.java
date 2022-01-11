package myPackage;
import groovy.json.JsonOutput;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
/**
 * Generates a route from one Station to the next on the Island of Sodor.
 * Sodor residents can pick a station to travel too, and will get a full route on how to get there
 * Future ideas:  implement timetable, Arlesdale Railway, Skarloey Railway, Culdee Fell, Peel Godred
 * its also slightly buggy
 * @author  Peter Le
 * @version 1.0
 * @since   1/11/2022
 */
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Sodor r = new Sodor();
        ArrayList<String> lowerStations = new ArrayList<>();
        for (Station i: Station.stations) {
            lowerStations.add(i.getName().toLowerCase());
        }
        Random random = new Random();
        ArrayList<String> choices = new ArrayList<>();
        choices.add("1");
        choices.add("2");
        choices.add("3");
        String[] engines = {"Bill and Ben", "Donald and Douglas", "Boco",
                "Diesel", "Henry", "James", "Percy", "Toby", "Duck", "Oliver"};
        String[] animals = {"dog", "cat", "cow"};
        String curr = "Knapford";
        System.out.println("Welcome to the Island of Sodor!  I am the Fat Controller and will assist you on your journeys.");
        String choice = "";
        while (!choice.equals("3")) {
            System.out.println("We are currently at " + curr + " Station.  What would you like to do?");
            System.out.println("Enter 1 to view a list of all stations on the island.");
            System.out.println("Enter 2 to travel to a new station.");
            System.out.println("Enter 3 to exit.");
            choice = s.nextLine();
            while (!choices.contains(choice)) {
                System.out.println("Erhm...  I don't seem to understand what you're saying.  Could you say it again?");
                choice = s.nextLine();
            }
            if (choice.equals("1")) {
                System.out.println("Alright.  Here are all the stations that run train services today!");
                for (Station i : Station.stations) {
                    System.out.println(i.getName());
                }
                System.out.println();
            } else if (choice.equals("2")) {
                System.out.println("Where would you like to go?");
                String dest = s.nextLine();
                while (!lowerStations.contains(dest.toLowerCase())
                        || curr.toLowerCase().equals(dest.toLowerCase())) {
                    if (!lowerStations.contains(dest.toLowerCase())) {
                        System.out.println("Erhm...  I don't seem to recognize that station.  Could you say it again?");
                    }
                    else {
                        System.out.println("*The Fat Controller laughed");
                        System.out.println("You're already in this station!  Why don't you go ahead and pick another one.");
                    }
                    dest = s.nextLine();
                }

                Station currS = null;
                Station destS = null;
                for (Station i: Station.stations) {
                    if (i.getName().toLowerCase().equals(curr.toLowerCase())) {
                        currS = i;
                    }
                    else if (i.getName().toLowerCase().equals(
                            dest.toLowerCase())) {
                        destS = i;
                    }
                }
                dest = dest.substring(0, 1).toUpperCase()
                        + dest.substring(1).toLowerCase();
                System.out.println("Ok, to get to " + dest + ", you need to...");
                r.findRoute(currS, destS);
                System.out.println("Would you like to go (1 for yes, 2 for no)?");
                String option = s.nextLine();
                while (!option.equals("1") && !option.equals("2")) {
                    System.out.println("Erhm...  I don't seem to understand what you're saying.  Could you say it again?");
                    option = s.nextLine();
                }
                if (option.equals("1")) {
                    System.out.println("Heading to " + dest + " Station...");
                    int n = random.nextInt(3);
                    if (n == 1) {
                        System.out.println("Oh, look out the window! It's "
                                + engines[random.nextInt(engines.length)]
                                + " pulling a local goods train!  What a marvelous sight!");
                    } else if (n == 2) {
                        System.out.println("Oh look, a "
                                + animals[random.nextInt(animals.length)]
                                + "!");
                    }
                    System.out.println("Alright, we made it to " + dest + " Station!");
                    curr = dest;
                }
            }
        }
        System.out.println("Thank you for travelling with us on the Island of Sodor!");
    }

}

package myPackage;

import java.util.ArrayList;
public class Sodor {
    //Mainline
    public Station tidmouth;
    public Station knapford;
    public Station elsbridge;
    public Station crosby;
    public Station wellsworth;
    public Station maron;
    public Station cronk;
    public Station killdane;
    public Station kellsthorpeRoad;
    public Station crovansGate;
    public Station vicarstown;
    //Ffarqhuar
    public Station dryaw;
    public Station toryreck;
    public Station hackenbeck;
    public Station ffarquhar;
    //Brendam Bay
    public Station suddery;
    public Station brendam;
    //Arlesburgh
    public Station haultraugh;
    public Station arlesburghWest;
    //Kirk Ronan
    public Station rolfsCastle;
    public Station kirkRonan;
    //Norramby
    public Station ballahoo;
    public Station norramby;
    public Sodor() {
        //initialize
        tidmouth = new Station("Tidmouth", true);
        knapford = new Station("Knapford", true);
        elsbridge = new Station("Elsbridge", true);
        crosby = new Station("Crosby");
        wellsworth = new Station("Wellsworth", true);
        maron = new Station("Maron", true);
        cronk = new Station("Cronk");
        killdane = new Station("Killdane");
        kellsthorpeRoad = new Station("Kellsthorpe Road", true);
        crovansGate = new Station("Crovan's Gate", true);
        vicarstown = new Station("Vicarstown", true);
        dryaw = new Station("Dryaw");
        toryreck = new Station("Toryreck");
        hackenbeck = new Station("Hackenbeck");
        ffarquhar = new Station("Ffarquhar");
        suddery = new Station("Suddery");
        brendam = new Station("Brendam");
        haultraugh = new Station("haultraugh");
        arlesburghWest = new Station("Arlesburgh West");
        rolfsCastle = new Station("Rolf's Castle");
        kirkRonan = new Station("Kirk Ronan");
        ballahoo = new Station("Ballahoo");
        norramby = new Station("Norramby");
        //assign lines
        tidmouth.addLine("Main", "Arlesburgh");
        knapford.addLine("Main", "Ffarquhar");
        elsbridge.addLine("Main", "Ffarquhar");
        crosby.addLine("Main");
        wellsworth.addLine("Main", "Brendam Bay");
        maron.addLine("Main");
        cronk.addLine("Main");
        killdane.addLine("Main");
        kellsthorpeRoad.addLine("Main", "Kirk Ronan");
        crovansGate.addLine("Main", "Norramby");
        vicarstown.addLine("Main", "Norramby");
        dryaw.addLine("Ffarquhar");
        toryreck.addLine("Ffarquhar");
        hackenbeck.addLine("Ffarquhar");
        ffarquhar.addLine("Ffarquhar");
        suddery.addLine("Brendam Bay");
        brendam.addLine("Brendam Bay");
        haultraugh.addLine("Arlesburgh");
        arlesburghWest.addLine("Arlesburgh");
        rolfsCastle.addLine("Kirk Ronan");
        kirkRonan.addLine("Kirk Ronan");
        ballahoo.addLine("Norramby");
        norramby.addLine("Norramby");
        //connect stations
        tidmouth.addAdjacent(knapford, haultraugh);
        knapford.addAdjacent(tidmouth, crosby, dryaw);
        elsbridge.addAdjacent(dryaw, toryreck);
        elsbridge.addAdjacent(knapford, crosby);
        crosby.addAdjacent(elsbridge, wellsworth);
        wellsworth.addAdjacent(crosby, suddery, maron);
        maron.addAdjacent(wellsworth, cronk);
        cronk.addAdjacent(maron, killdane);
        killdane.addAdjacent(cronk, kellsthorpeRoad);
        kellsthorpeRoad.addAdjacent(killdane, rolfsCastle, crovansGate);
        crovansGate.addAdjacent(kellsthorpeRoad, ballahoo, vicarstown);
        vicarstown.addAdjacent(ballahoo, crovansGate);
        dryaw.addAdjacent(knapford, toryreck);
        toryreck.addAdjacent(dryaw, elsbridge);
        elsbridge.addAdjacent(hackenbeck, toryreck);
        hackenbeck.addAdjacent(tidmouth, ffarquhar);
        ffarquhar.addAdjacent(hackenbeck);
        suddery.addAdjacent(wellsworth, brendam);
        brendam.addAdjacent(suddery);
        haultraugh.addAdjacent(tidmouth, arlesburghWest);
        arlesburghWest.addAdjacent(haultraugh);
        rolfsCastle.addAdjacent(kellsthorpeRoad, kirkRonan);
        kirkRonan.addAdjacent(rolfsCastle);
        ballahoo.addAdjacent(crovansGate, norramby, vicarstown);
        norramby.addAdjacent(ballahoo);
    }
    public void findRoute(Station a, Station b) {
        ArrayList<Station> stops = new ArrayList<>();
        ArrayList<Station> visited = new ArrayList<>();
        stops.add(a);
        helper(a, b, stops, visited);
    }
    public void helper(Station a, Station b, ArrayList<Station> stops,
                       ArrayList<Station> visited) {
        visited.add(a);
        if (a.equals(b)) {
            /*
            for (Station i: stops) {
                System.out.println(i.getName());
            }
            */
            ArrayList<Station> points = new ArrayList<>();
            points.add(stops.get(0));
            for (int i = 1; i < stops.size() - 1; i++) {
                if (!hasDuplicates(stops.get(i - 1).getLines(),
                        stops.get(i + 1).getLines())) {
                    points.add(stops.get(i));
                }
            }
            points.add(stops.get(stops.size() - 1));
            for (int i = 1; i < points.size(); i++) {
                if (points.get(i - 1).isExpress()
                        && points.get(i).isExpress()) {
                    System.out.println("Take the Express from "
                            + points.get(i-1).getName() + " Station to "
                            + points.get(i).getName() + " Station.");
                }
                else {
                    String line = findDuplicate(points.get(i - 1).getLines(),
                            points.get(i).getLines());
                    System.out.println("Take the " + line + " Line from "
                            + points.get(i-1).getName() + " Station to "
                            + points.get(i).getName() + " Station.");
                }

            }
        }

        for (Station i: a.getAdjacent()) {
            if (!visited.contains(i)) {
                stops.add(i);
                helper(i, b, stops, visited);
                stops.remove(i);
            }
        }
    }
    private boolean hasDuplicates(ArrayList<String> a, ArrayList<String> b) {
        for (String i: a) {
            for (String j: b) {
                if (i.equals(j)) {
                    return true;
                }
            }
        }
        return false;
    }
    private String findDuplicate(ArrayList<String> a, ArrayList<String> b) {
        for (String i: a) {
            for (String j: b) {
                if (i.equals(j)) {
                    return i;
                }
            }
        }
        return "";
    }
}
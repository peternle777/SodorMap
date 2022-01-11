package myPackage;

import java.util.ArrayList;
public class Station {
    private String name;
    private ArrayList<Station> adjacent;
    private ArrayList<String> lines;
    private boolean isExpress;
    public static ArrayList<Station> stations = new ArrayList<>();
    public Station(String n) {
        adjacent = new ArrayList<>();
        lines = new ArrayList<>();
        name = n;
        isExpress = false;
        stations.add(this);
    }
    public Station(String n, boolean i) {
        adjacent = new ArrayList<>();
        lines = new ArrayList<>();
        name = n;
        isExpress = i;
        stations.add(this);
    }
    public String getName() {
        return name;
    }
    public ArrayList<String> getLines() {
        return lines;
    }
    public ArrayList<Station> getAdjacent() {
        return adjacent;
    }
    public boolean isExpress() {
        return isExpress;
    }

    public void addAdjacent(Station s) {
        adjacent.add(s);
    }
    public void addAdjacent(Station s, Station s2) {
        addAdjacent(s);
        addAdjacent(s2);
    }
    public void addAdjacent(Station s, Station s2, Station s3) {
        addAdjacent(s);
        addAdjacent(s2);
        addAdjacent(s3);
    }
    public void addLine(String l) {
        lines.add(l);
    }
    public void addLine(String l, String l2) {
        addLine(l);
        addLine(l2);
    }
}
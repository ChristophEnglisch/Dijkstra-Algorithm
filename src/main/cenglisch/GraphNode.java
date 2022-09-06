package main.cenglisch;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>Materialien zu den zentralen
 * Abiturpruefungen im Fach Informatik ab 2012 in
 * Nordrhein-Westfalen.</p>
 * <p>Klasse GraphNode</p>
 * <p>Ein ungerichteter Graph besteht aus einer Menge
 * von Knoten und einer Menge von Kanten. Die Kanten
 * verbinden jeweils zwei Knoten und koennen ein Gewicht haben.
 * Objekte der Klasse GraphNode sind Knoten eines Graphen.
 * Ein Knoten hat einen Namen und kann markiert werden.</p>
 *
 * <p>NW-Arbeitsgruppe: Materialentwicklung zum Zentralabitur
 * im Fach Informatik</p>
 *
 * @version 2020-10-07
 */
public class GraphNode {
    private String name;
    private List<Edge> edges;
    private boolean marked;
    private int level;

    private double distance;

    private GraphNode predecessor;

    // Klasse Edge
    private class Edge {
        protected GraphNode neighbour;
        protected double weight;
        protected boolean marked;


        public Edge(GraphNode pNeighbour, double pWeight) {
            neighbour = pNeighbour;
            weight = pWeight;
            marked = false;
        }

        public GraphNode getNeighbour() {
            return neighbour;
        }

        public double getWeight() {
            return weight;
        }

        public boolean isMarked() {
            return marked;
        }

    }

    // Ende Klasse Edge

    /**
     * Ein Knoten mit dem Namen pName wird erzeugt.
     * Der Knoten ist nicht markiert.
     *
     * @param pName Bezeichnung des Knotens
     */
    public GraphNode(String pName) {
        name = pName;
        edges = new LinkedList<>();
        marked = false;
    }

    /**
     * Der Knoten wird markiert. Falls er
     * nicht markiert ist, sonst bleibt er unveraendert.
     */
    public void mark() {
        marked = true;
    }

    /**
     * Die Markierung des Knotens wird entfernt, falls er markiert ist,
     * sonst bleibt er unveraendert.
     */
    public void unmark() {
        marked = false;
    }

    /**
     * Die Anfrage liefert den Wert true, wenn der Knoten markiert ist,
     * sonst liefert sie den Wert false.
     *
     * @return true falls markiert, sonst false
     */
    public boolean isMarked() {
        return marked;

    }

    /**
     * Die Anfrage liefert den Namen des Knotens.
     *
     * @return Bezeichnung des Knotens
     */
    public String getName() {
        return name;
    }

    public void setLevel(int value) {
        level = value;
    }

    public int getLevel() {
        return level;
    }

    // Hilfsmethoden

    /**
     * Interne Methode
     */
    void addEdge_(GraphNode pNode, double pWeight) {
        Edge lEdge = new Edge(pNode, pWeight);
        edges.add(lEdge);
    }

    /**
     * Interne Methode
     */
    double getEdgeWeight_(GraphNode pNode) {
        boolean ok = false;
        Edge e = null;
        Iterator<Edge> it = edges.iterator();


        while (!ok && it.hasNext()) {
            e = it.next();
            if (e.getNeighbour() == pNode)
                ok = true;

        }
        if (!ok)
            return Double.NaN; // Not a Number
        else
            return e.getWeight();
    }

    /**
     * Interne Methode
     */
    void removeEdge_(GraphNode pNode) {
        if (pNode != null) {
            Edge e;
            Iterator<Edge> it = edges.iterator();

            while (it.hasNext()) {
                e = it.next();
                if (e.getNeighbour() == pNode)
                    edges.remove(pNode);

            }
        }
    }

    /**
     * Interne Methode
     */
    List getNeighbours_() {
        // liefert eine Liste mit den Nachbarknoten
        List lList = new LinkedList();
        Iterator<Edge> it = edges.iterator();


        while (it.hasNext()) {
            Edge lEdge = it.next();
            lList.add(lEdge.getNeighbour());

        }
        return lList;
    }

    public GraphNode getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(GraphNode predecessor) {
        this.predecessor = predecessor;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }



}
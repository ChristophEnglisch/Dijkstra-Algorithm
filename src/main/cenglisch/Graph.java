package main.cenglisch;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>Klasse Graph</p>
 * <p>Objekte der Klasse Graph sind ungerichtete, gewichtete Graphen.
 * Der Graph besteht aus Knoten, die Objekte der Klasse GraphNode sind,
 * und Kanten, die Knoten miteinander verbinden.
 * Die Knoten werden ueber ihren Namen eindeutig identifiziert.</p>
 *
 * @version 2016-11-28
 */
public class Graph {
    private List<GraphNode> nodeList;

    /**
     * Ein neuer Graph wird erzeugt.
     * Er enthaelt noch keine Knoten.
     */
    public Graph() {
        nodeList = new LinkedList<>();
    }

    /**
     * Die Anfrage liefert true, wenn der Graph keine Knoten
     * enthaelt, andernfalls liefert die Anfrage false.
     *
     * @return true, falls leer, sonst false
     */
    public boolean isEmpty() {
        return nodeList.isEmpty();
    }

    /**
     * Der Knoten pNode wird dem Graphen hinzugefuegt.
     * Falls bereits ein Knoten mit gleichem Namen im
     * Graphen existiert, wird dieser Knoten nicht eingefuegt.
     * Falls pNode null ist, veraendert sich der Graph nicht.
     *
     * @param pNode neuer Knoten
     */
    public void addNode(GraphNode pNode) {
        if (pNode != null && !this.hasNode(pNode.getName()))
            nodeList.add(pNode);
    }

    /**
     * Die Anfrage liefert true, wenn ein Knoten mit dem Namen
     * pName im Graphen existiert.
     * Sonst wird false zurueck gegeben.
     *
     * @param pNode Knotenbezeichnung
     * @return true, falls es Knoten gibt, sonst false
     */
    public boolean hasNode(String pNode) {
        boolean lGefunden = false;
        Iterator<GraphNode> it = nodeList.iterator();


        while (it.hasNext() && !lGefunden) {
            GraphNode lKnoten = it.next();
            lGefunden = lKnoten.getName().equals(pNode);

        }
        return lGefunden;
    }

    /**
     * Die Anfrage liefert den Knoten mit dem Namen pName zurueck.
     * Falls es keinen Knoten mit dem Namen im Graphen gibt,
     * wird null zurueck gegeben.
     *
     * @param pName Knotenbezeichnung
     * @return Objekt der Klasse GraphNode
     */
    public GraphNode getNode(String pName) {
        GraphNode lNode0 = null;
        boolean lStop = false;
        Iterator<GraphNode> it = nodeList.iterator();

        while (it.hasNext() && !lStop) {
            GraphNode lNode = it.next();
            if (lNode.getName().equals(pName)) {
                lNode0 = lNode;
                lStop = true;
            }

        }
        return lNode0;
    }

    /**
     * Falls pNode ein Knoten des Graphen ist, so werden er und alle
     * mit ihm verbundenen Kanten aus dem Graphen entfernt.
     * Sonst wird der Graph nicht veraendert.
     *
     * @param pNode Knoten
     */


    public void removeNode(GraphNode pNode) {
        if (pNode != null) {
            //Ist Element im Graphen vorhanden
            if (hasNode(pNode.getName())) {
                //Finde alle Nachbarn zum Graphen
                List<GraphNode> lListe = this.getNeighbours(pNode);
                Iterator<GraphNode> it = nodeList.iterator();
                //Lösche alle Kanten zu den Nachbarn
                while (it.hasNext()) {
                    GraphNode lNode1 = it.next();
                    this.removeEdge(pNode, lNode1);
                    this.removeEdge(lNode1, pNode);

                }
                //Lösche den Eelement aus Liste
                nodeList.remove(pNode);

            }
        }
    }

    /**
     * Falls eine Kante zwischen pNode1 und pNode2 noch nicht existiert,
     * werden die Knoten pNode1 und pNode2 durch eine Kante verbunden,
     * die das Gewicht pWeight hat. pNode1 ist also Nachbarknoten
     * von pNode2 und umgekehrt. Falls eine Kante zwischen pNode1 und pNode2
     * bereits existiert, erhaelt sie das Gewicht pWeight.
     * Falls einer der Knoten pNode1 oder pNode2 im Graphen nicht existiert oder null ist,
     * veraendert sich der Graph nicht.
     *
     * @param pNode1  Knoten
     * @param pNode2  Knoten
     * @param pWeight Kantengewicht
     */
    public void addEdge(GraphNode pNode1, GraphNode pNode2,
                        double pWeight) {
        if (pNode1 != null && pNode2 != null) {
            if (this.hasEdge(pNode1, pNode2))
                this.removeEdge(pNode1, pNode2);
            pNode1.addEdge_(pNode2, pWeight);
            // pNode2.addEdge_(pNode1,pWeight);
        }
    }

    /**
     * Die Anfrage liefert true, wenn ein Knoten mit
     * dem Namen pName im Graphen existiert.
     * Sonst wird false zurueck gegeben.
     *
     * @param pNode1 Knoten
     * @param pNode2 Knoten
     * @return true, falls Kante existiert, sonst false
     */
    public boolean hasEdge(GraphNode pNode1, GraphNode pNode2) {
        boolean result = false;
        List<GraphNode> lNeighbours;
        GraphNode lNeighbour;
        if ((pNode1 != null) && (pNode2 != null)) {
            lNeighbours = pNode1.getNeighbours_();
            if (!lNeighbours.isEmpty()) {
                Iterator<GraphNode> it = lNeighbours.iterator();

                while (it.hasNext()) {
                    lNeighbour = it.next();
                    if (lNeighbour.getName().equals(pNode2.getName()))
                        result = true;

                }
            }
        }
        return result;
    }


    /**
     * Falls pNode1 und pNode2 nicht null sind und eine Kante zwischen
     * pNode1 und pNode2 existiert, wird die Kante geloescht. Sonst
     * bleibt der Graph unveraendert.
     *
     * @param pNode1 Knoten
     * @param pNode2 Knoten
     */
    public void removeEdge(GraphNode pNode1,
                           GraphNode pNode2) {
        if (pNode1 != null &&
                pNode2 != null &&
                this.hasEdge(pNode1, pNode2)) {
            pNode1.removeEdge_(pNode2);
            // pNode2.removeEdge_(pNode1);
        }
    }

    /**
     * Die Anfrage liefert das Gewicht der Kante zwischen pNode1 und pNode2.
     * Falls die Kante nicht existiert, wird Double.NaN (not a number)
     * zurueck gegeben.
     *
     * @param pNode1 Knoten
     * @param pNode2 Knoten
     * @return Kantengewicht
     */
    public double getEdgeWeight(GraphNode pNode1, GraphNode pNode2) {
        return pNode1.getEdgeWeight_(pNode2);
    }

    /**
     * Alle Knoten des Graphen werden als unmarkiert gekennzeichnet.
     */
    public void resetMarks() {

        for (GraphNode gn : nodeList) {
            gn.unmark();
        }

    }

    /**
     * Die Anfrage liefert den Wert true, wenn alle Knoten des Graphen
     * markiert sind, sonst liefert sie den Wert false.
     *
     * @return true, lass alle Knoten markiert, sonst false
     */
    public boolean allNodesMarked() {
        if (!nodeList.isEmpty()) {

            boolean lAllMarked = true;

            for (GraphNode gn : nodeList) {
                if (!gn.isMarked()) {
                    lAllMarked = false;
                    break;
                }
            }
            return lAllMarked;
        } else return true;
    }

    /**
     * Die Anfrage liefert eine Liste, die alle Knoten des Graphen enthaelt.
     *
     * @return Knotenliste
     */
    public List<GraphNode> getNodes() { // liefert Knoten als Kopie der Knotenliste
        List<GraphNode> lList = new LinkedList<GraphNode>();
        for (GraphNode gn : nodeList) {
            lList.add(gn);
        }

        return lList;
    }

    /**
     * Die Anfrage liefert eine Liste, die alle Nachbarknoten des
     * Knotens pNode enthaelt.
     *
     * @param pNode Knoten
     * @return Liste mit allen Nachbarknoten
     */
    public List<GraphNode> getNeighbours(GraphNode pNode) {
        return pNode.getNeighbours_();
    }

}
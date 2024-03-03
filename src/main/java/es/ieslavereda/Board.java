package es.ieslavereda;

import java.util.*;

public class Board {

    private Map<Coordinate, Cell> cells;
    private String OS;

    public Board() {
        cells = new HashMap<>();
        OS = Input.askOS();

        for (int row = 1; row <= 8; row++)
            for (char col = 'A'; col <= 'H'; col++)
                cells.put(new Coordinate(col, row), new Cell(this, new Coordinate(col, row)));

    }

    public boolean contains(Coordinate c) {
        return cells.containsKey(c);
    }

    public Map<Coordinate, Cell> getCells() {
        return cells;
    }

    public Cell getCellAt(Coordinate c) {
        if (!contains(c)) return null;
        return cells.get(c);
    }

    public void highLight(Set<Coordinate> coordinates) {
        coordinates.stream().forEach(coordinate -> getCellAt(coordinate).highlight());
    }

    public void removeHighLight() {
        cells.values().stream().forEach(cell -> cell.removeHighLight());
    }


    @Override
    public String toString() {
        String aux = "    A  B  C  D  E  F  G  H\n";

        for (int row = 1; row <= 8; row++) {
            aux += " " + row + " ";
            for (char col = 'A'; col <= 'H'; col++)
                aux += cells.get(new Coordinate(col, row));

            aux += " " + row + "\n";
        }
        aux += "    A  B  C  D  E  F  G  H";
        return aux;
    }
}
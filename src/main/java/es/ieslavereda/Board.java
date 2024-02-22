package es.ieslavereda;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private Map<Coordinate, Cell> cells;

    public Board() {
        cells = new HashMap<>();

        for (int row = 1; row <= 8; row++)
            for (char col = 'A'; col <= 'H'; col++)
                cells.put(new Coordinate(col, row), new Cell(this, new Coordinate(col, row)));

    }

    public boolean contains(Coordinate c) {
        return cells.containsKey(c);
    }

    public Cell getCellAt(Coordinate c) {
        if (!contains(c)) return null;
        return cells.get(c);
    }

    public void highLight(Collection<Coordinate> coordinates) {
        for (Coordinate c : coordinates)
            getCellAt(c).highlight();
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
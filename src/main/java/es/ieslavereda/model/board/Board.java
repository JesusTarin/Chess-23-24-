package es.ieslavereda.model.board;

import com.diogonunes.jcolor.Attribute;
import es.ieslavereda.view.Game;

import java.io.Serializable;
import java.util.*;

import static com.diogonunes.jcolor.Ansi.colorize;


public class Board implements Serializable {

    private Map<Coordinate, Cell> cells;

    public Board() {
        cells = new HashMap<>();
        for (int row = 8; row >= 1; row--) {
            for (char col = 'A'; col <= 'H'; col++) {
                cells.put(new Coordinate(col, row), new Cell(this, new Coordinate(col, row)));
            }
        }
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

    public void addCell(Coordinate c, Cell cell) {
        this.cells.put(c, cell);
    }

    public void highLight(Set<Coordinate> coordinates) {
        coordinates.stream().forEach(coordinate -> getCellAt(coordinate).highlight());
    }

    public void removeHighLight() {
        cells.values().stream().forEach(cell -> cell.removeHighLight());
    }

    @Override
    public String toString() {
        String aux = "\t\t\t"+colorize(colorize("    A  B  C  D  E  F  G  H    ", Attribute.BACK_COLOR(0,0,0)), Attribute.TEXT_COLOR(255,255,255))+"\n";

        for (int row = 1; row <= 8; row++) {
            aux += "\t\t\t"+colorize(colorize(" " + row + " ", Attribute.BACK_COLOR(0,0,0)), Attribute.TEXT_COLOR(255,255,255));
            for (char col = 'A'; col <= 'H'; col++) {
                aux += cells.get(new Coordinate(col, row));
            }
            aux += colorize(colorize(" " + row + " ", Attribute.BACK_COLOR(0,0,0)), Attribute.TEXT_COLOR(255,255,255))+"\n";
        }
        aux += "\t\t\t"+colorize(colorize("    A  B  C  D  E  F  G  H    ", Attribute.BACK_COLOR(0,0,0)), Attribute.TEXT_COLOR(255,255,255));
        aux += Game.printPieces();
        return aux;
    }
}
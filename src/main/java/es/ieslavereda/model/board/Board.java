package es.ieslavereda.model.board;

import com.diogonunes.jcolor.Attribute;
import es.ieslavereda.model.DeletedPieceManagerListImp;
import es.ieslavereda.model.piece.Piece;

import java.io.Serializable;
import java.util.*;

import static com.diogonunes.jcolor.Ansi.colorize;


public class Board implements Serializable {

    private Map<Coordinate, Cell> cells;
    private DeletedPieceManagerListImp remaining;
    private DeletedPieceManagerListImp deleted;

    public Board() {
        cells = new HashMap<>();
        for (int row = 8; row >= 1; row--) {
            for (char col = 'A'; col <= 'H'; col++) {
                cells.put(new Coordinate(col, row), new Cell(this, new Coordinate(col, row)));
            }
        }
        deleted = new DeletedPieceManagerListImp();
    }

    public DeletedPieceManagerListImp getDeleted() {
        return deleted;
    }

    public DeletedPieceManagerListImp getRemaining() {
        return remaining;
    }

    public void setRemaining(DeletedPieceManagerListImp remaining) {
        this.remaining = remaining;
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

    public boolean kingsAlive() {
        if (remaining.count(Piece.Type.BLACK_KING) == 0 || remaining.count(Piece.Type.BLACK_KING) == 0) {
            return false;
        }
        return true;
    }

    private String printPieces() {
        String aux="";
        aux+="\n\t\t\t\t   REMAINING PIECES\n\t\t ";
        for (Piece.Type type : Piece.Type.values()) {
            aux+=colorize(colorize(" "+type.getShape()+" ",Cell.Color.BLACK.getAttribute(), Attribute.TEXT_COLOR(255,255,255)));
        }
        aux+="\n\t\t\t\t"+remaining.toString();
        aux+="\n\t\t\t\t    DELETED PIECES\n\t\t ";
        for (Piece.Type type : Piece.Type.values()) {
            aux+=colorize(colorize(" "+type.getShape()+" ",Cell.Color.BLACK.getAttribute(), Attribute.TEXT_COLOR(255,255,255)));
        }

        return aux;
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
        aux += printPieces();
        return aux;
    }
}
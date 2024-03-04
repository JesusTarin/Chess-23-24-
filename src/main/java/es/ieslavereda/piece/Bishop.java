package es.ieslavereda.piece;

import es.ieslavereda.board.Board;
import es.ieslavereda.board.Coordinate;

import java.util.LinkedHashSet;
import java.util.Set;

public class Bishop extends Piece {
    public Bishop(Board board, Coordinate coordinate, Type type) {
        super(type.getType(), board.getCellAt(coordinate));
    }

    @Override
    public Set<Coordinate> getNextMovements() {
        return getNextMovementsAsBishop(this);
    }

    public static Set<Coordinate> getNextMovementsAsBishop(Piece piece){
        Set<Coordinate> nextMovements = new LinkedHashSet<>();
        Coordinate initialPosition = piece.getCell().getCoordinate();
        Coordinate coordinate;
        Board board = piece.getCell().getBoard();

        if(initialPosition==null)
            return nextMovements;

        //diagonal izquierda
        coordinate=initialPosition;
        do{
            coordinate=coordinate.diagonalUpLeft();
            if(piece.canAddToNextMovements(coordinate))
                nextMovements.add(coordinate);
        } while(board.contains(coordinate) && board.getCellAt(coordinate).getPiece()==null);

        coordinate=initialPosition;
        do{
            coordinate=coordinate.diagonalDownLeft();
            if(piece.canAddToNextMovements(coordinate))
                nextMovements.add(coordinate);
        } while(board.contains(coordinate) && board.getCellAt(coordinate).getPiece()==null);

        //diagonal derecha
        coordinate=initialPosition;
        do{
            coordinate=coordinate.diagonalUpRight();
            if(piece.canAddToNextMovements(coordinate))
                nextMovements.add(coordinate);
        } while(board.contains(coordinate) && board.getCellAt(coordinate).getPiece()==null);

        coordinate=initialPosition;
        do{
            coordinate=coordinate.diagonalDownRight();
            if(piece.canAddToNextMovements(coordinate))
                nextMovements.add(coordinate);
        } while(board.contains(coordinate) && board.getCellAt(coordinate).getPiece()==null);

        return nextMovements;

    }

    public enum Type {
        BLACK(Piece.Type.BLACK_BISHOP),
        WHITE(Piece.Type.WHITE_BISHOP);

        private Piece.Type type;

        Type(Piece.Type type) {
            this.type = type;
        }

        public Piece.Type getType() {
            return type;
        }
    }
}

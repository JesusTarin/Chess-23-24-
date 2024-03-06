package es.ieslavereda.model.piece;

import es.ieslavereda.model.board.Board;
import es.ieslavereda.model.board.Coordinate;

import java.util.LinkedHashSet;
import java.util.Set;

public class Rook extends Piece {
    public Rook(Board board, Coordinate coordinate, Type type){
        super(type.getType(), board.getCellAt(coordinate));
    }

    @Override
    public Set<Coordinate> getNextMovements() {
        return getNextMovementsAsRook(this);
    }

    public static Set<Coordinate> getNextMovementsAsRook(Piece piece){
        Set<Coordinate> nextMovements = new LinkedHashSet<>();
        Coordinate initialPosition = piece.getCell().getCoordinate();
        Coordinate coordinate;
        Board board = piece.getCell().getBoard();

        if(initialPosition==null)
            return nextMovements;

        //izquierda
        coordinate=initialPosition;
        do{
            coordinate=coordinate.left();
            if(piece.canAddToNextMovements(coordinate))
                nextMovements.add(coordinate);
        } while(board.contains(coordinate) && board.getCellAt(coordinate).getPiece()==null);

        //abajo
        coordinate=initialPosition;
        do{
            coordinate=coordinate.down();
            if(piece.canAddToNextMovements(coordinate))
                nextMovements.add(coordinate);
        } while(board.contains(coordinate) && board.getCellAt(coordinate).getPiece()==null);

        //derecha
        coordinate=initialPosition;
        do{
            coordinate=coordinate.right();
            if(piece.canAddToNextMovements(coordinate))
                nextMovements.add(coordinate);
        } while(board.contains(coordinate) && board.getCellAt(coordinate).getPiece()==null);

        //arriba
        coordinate=initialPosition;
        do{
            coordinate=coordinate.up();
            if(piece.canAddToNextMovements(coordinate))
                nextMovements.add(coordinate);
        } while(board.contains(coordinate) && board.getCellAt(coordinate).getPiece()==null);

        return nextMovements;

    }

    public enum Type {
        BLACK(Piece.Type.BLACK_ROOK),
        WHITE(Piece.Type.WHITE_ROOK);

        private Piece.Type type;

        Type(Piece.Type type) {
            this.type = type;
        }

        public Piece.Type getType() {
            return type;
        }
    }
}

package es.ieslavereda.piece;

import es.ieslavereda.board.Board;
import es.ieslavereda.board.Coordinate;

import java.util.Set;

public class Queen extends Piece {
    public Queen(Board board, Coordinate coordinate, Type type){
        super(type.getType(), board.getCellAt(coordinate));
    }

    @Override
    public Set<Coordinate> getNextMovements() {
        Set<Coordinate> nextMovements = Bishop.getNextMovementsAsBishop(this);
        nextMovements.addAll(Rook.getNextMovementsAsRook(this));
        return nextMovements;
    }

    public enum Type{
        BLACK(Piece.Type.BLACK_QUEEN),
        WHITE(Piece.Type.WHITE_QUEEN);

        private Piece.Type type;
        Type(Piece.Type type){
            this.type=type;
        }

        public Piece.Type getType() {
            return type;
        }
    }

}

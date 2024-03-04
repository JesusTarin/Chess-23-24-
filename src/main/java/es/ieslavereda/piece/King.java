package es.ieslavereda.piece;

import es.ieslavereda.board.Board;
import es.ieslavereda.board.Coordinate;

import java.util.LinkedHashSet;
import java.util.Set;

public class King extends Piece {
    public King(Board board, Coordinate coordinate, Type type){
        super(type.getType(), board.getCellAt(coordinate));
    }

    @Override
    public Set<Coordinate> getNextMovements() {
        Set<Coordinate> nextMovements = new LinkedHashSet<>();
        Coordinate initialPosition = getCell().getCoordinate();
        Coordinate coordinate;

        coordinate = initialPosition.up();
        if(canAddToNextMovements(coordinate))
            nextMovements.add(coordinate);

        coordinate = initialPosition.down();
        if(canAddToNextMovements(coordinate))
            nextMovements.add(coordinate);

        coordinate = initialPosition.left();
        if(canAddToNextMovements(coordinate))
            nextMovements.add(coordinate);

        coordinate = initialPosition.right();
        if(canAddToNextMovements(coordinate))
            nextMovements.add(coordinate);

        coordinate = initialPosition.diagonalUpRight();
        if(canAddToNextMovements(coordinate))
            nextMovements.add(coordinate);

        coordinate = initialPosition.diagonalUpLeft();
        if(canAddToNextMovements(coordinate))
            nextMovements.add(coordinate);

        coordinate = initialPosition.diagonalDownRight();
        if(canAddToNextMovements(coordinate))
            nextMovements.add(coordinate);

        coordinate = initialPosition.diagonalDownLeft();
        if(canAddToNextMovements(coordinate))
            nextMovements.add(coordinate);

        return nextMovements;
    }

    public enum Type{
        BLACK(Piece.Type.BLACK_KING),
        WHITE(Piece.Type.WHITE_KING);

        private Piece.Type type;
        Type(Piece.Type type){
            this.type=type;
        }

        public Piece.Type getType() {
            return type;
        }
    }

}

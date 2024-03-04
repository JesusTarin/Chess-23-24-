package es.ieslavereda.piece;

import es.ieslavereda.board.Board;
import es.ieslavereda.board.Coordinate;

import java.util.LinkedHashSet;
import java.util.Set;

public class Knight extends Piece {

    public Knight(Board board, Coordinate position, Type type) {
        super(type.getType(), board.getCellAt(position));
    }

    @Override
    public Set<Coordinate> getNextMovements(){
        Set<Coordinate> nextMovements = new LinkedHashSet<>();
        Coordinate initialPosition = getCell().getCoordinate();
        Coordinate coordinate;

        //Up
        coordinate = initialPosition.up().up().left();
        if(canAddToNextMovements(coordinate))
            nextMovements.add(coordinate);

        coordinate = initialPosition.up().up().right();
        if(canAddToNextMovements(coordinate))
            nextMovements.add(coordinate);

        //down
        coordinate = initialPosition.down().down().left();
        if(canAddToNextMovements(coordinate))
            nextMovements.add(coordinate);
        coordinate = initialPosition.down().down().right();
        if(canAddToNextMovements(coordinate))
            nextMovements.add(coordinate);

        //left
        coordinate = initialPosition.left().left().down();
        if(canAddToNextMovements(coordinate))
            nextMovements.add(coordinate);
        coordinate = initialPosition.left().left().up();
        if(canAddToNextMovements(coordinate))
            nextMovements.add(coordinate);

        //right
        coordinate = initialPosition.right().right().down();
        if(canAddToNextMovements(coordinate))
            nextMovements.add(coordinate);
        coordinate = initialPosition.right().right().up();
        if(canAddToNextMovements(coordinate))
            nextMovements.add(coordinate);

        return nextMovements;

    }

    public enum Type {
        BLACK(Piece.Type.BLACK_KNIGHT),
        WHITE(Piece.Type.WHITE_KNIGHT);
        private Piece.Type type;

        Type(Piece.Type type) {
            this.type = type;
        }

        public Piece.Type getType() {
            return type;
        }
    }
}
package es.ieslavereda;

public class Knight extends Piece {

    public Knight(Board board, Coordinate position, Type type) {
        super(type.getType(), board.getCellAt(position));
    }

    @Override
    public Coordinate[] getNextMovements(){
        Coordinate[] nextMovements = new Coordinate[0];
        Coordinate initialPosition = getCell().getCoordinate();
        Coordinate coordinate;

        //Up
        coordinate = initialPosition.up().up().left();
        if(canAddToNextMovements(coordinate))
            nextMovements = Tool.add(coordinate,nextMovements);

        coordinate = initialPosition.up().up().right();
        if(canAddToNextMovements(coordinate))
            nextMovements = Tool.add(coordinate,nextMovements);

        //down
        coordinate = initialPosition.down().down().left();
        if(canAddToNextMovements(coordinate))
            nextMovements = Tool.add(coordinate,nextMovements);
        coordinate = initialPosition.down().down().right();
        if(canAddToNextMovements(coordinate))
            nextMovements = Tool.add(coordinate,nextMovements);

        //left
        coordinate = initialPosition.left().left().down();
        if(canAddToNextMovements(coordinate))
            nextMovements = Tool.add(coordinate,nextMovements);
        coordinate = initialPosition.left().left().up();
        if(canAddToNextMovements(coordinate))
            nextMovements = Tool.add(coordinate,nextMovements);

        //right
        coordinate = initialPosition.right().right().down();
        if(canAddToNextMovements(coordinate))
            nextMovements = Tool.add(coordinate,nextMovements);
        coordinate = initialPosition.right().right().up();
        if(canAddToNextMovements(coordinate))
            nextMovements = Tool.add(coordinate,nextMovements);

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
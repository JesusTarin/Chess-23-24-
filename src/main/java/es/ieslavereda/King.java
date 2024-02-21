package es.ieslavereda;

public class King extends Piece {
    public King(Board board, Coordinate coordinate, Type type){
        super(type.getType(), board.getCellAt(coordinate));
    }

    @Override
    public Coordinate[] getNextMovements() {
        Coordinate[] nextMovements = new Coordinate[0];
        Coordinate initialPosition = getCell().getCoordinate();
        Coordinate coordinate;

        coordinate = initialPosition.up();
        if(canAddToNextMovements(coordinate))
            nextMovements = Tool.add(coordinate,nextMovements);

        coordinate = initialPosition.down();
        if(canAddToNextMovements(coordinate))
            nextMovements = Tool.add(coordinate,nextMovements);

        coordinate = initialPosition.left();
        if(canAddToNextMovements(coordinate))
            nextMovements = Tool.add(coordinate,nextMovements);

        coordinate = initialPosition.right();
        if(canAddToNextMovements(coordinate))
            nextMovements = Tool.add(coordinate,nextMovements);

        coordinate = initialPosition.diagonalUpRight();
        if(canAddToNextMovements(coordinate))
            nextMovements = Tool.add(coordinate,nextMovements);

        coordinate = initialPosition.diagonalUpLeft();
        if(canAddToNextMovements(coordinate))
            nextMovements = Tool.add(coordinate,nextMovements);

        coordinate = initialPosition.diagonalDownRight();
        if(canAddToNextMovements(coordinate))
            nextMovements = Tool.add(coordinate,nextMovements);

        coordinate = initialPosition.diagonalDownLeft();
        if(canAddToNextMovements(coordinate))
            nextMovements = Tool.add(coordinate,nextMovements);

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

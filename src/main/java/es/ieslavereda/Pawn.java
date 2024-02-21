package es.ieslavereda;

public class Pawn extends Piece {
    public Pawn(Board board, Coordinate coordinate, Type type){
        super(type.getType(), board.getCellAt(coordinate));
    }

    @Override
    public Coordinate[] getNextMovements() {
        if(getColor()== Piece.Color.WHITE)
            return getNextMovementsWhite();
        return getNextMovementsBlack();
    }

    @Override
    public boolean moveTo(Coordinate coordinate){
        if(!super.moveTo(coordinate))
            return false;
        //tengo que comprobarr si se transforma en reina
        if(getCell().getCoordinate().getNumber()==8 ||
                getCell().getCoordinate().getNumber()==1){
            Cell auxCell = this.getCell();
            this.remove();
            if(getColor()== Piece.Color.BLACK)
                new Queen(auxCell.getBoard(),coordinate, Queen.Type.BLACK);
            else
                new Queen(auxCell.getBoard(),coordinate, Queen.Type.WHITE);
        }
        return true;
    }

    public Coordinate[] getNextMovementsWhite(){
        Coordinate[] nextMovements = new Coordinate[0];
        Coordinate initialPosition = getCell().getCoordinate();
        Coordinate coordinate;
        Board board = getCell().getBoard();

        //movimiento 1
        coordinate = initialPosition.up();
        if(board.contains(coordinate) && board.getCellAt(coordinate).getPiece()==null) {
            nextMovements = Tool.add(coordinate, nextMovements);
            //posición inicial
            if(initialPosition.getNumber()==7){
                coordinate=coordinate.up();
                if(board.contains(coordinate) && board.getCellAt(coordinate).getPiece()==null)
                    nextMovements = Tool.add(coordinate, nextMovements);
            }
        }

        //avanza matando
        coordinate = initialPosition.diagonalUpRight();
        if(board.contains(coordinate) && board.getCellAt(coordinate).getPiece()!=null &&
            board.getCellAt(coordinate).getPiece().getColor()!= getColor())
            nextMovements = Tool.add(coordinate,nextMovements);

        coordinate = initialPosition.diagonalUpLeft();
        if(board.contains(coordinate) && board.getCellAt(coordinate).getPiece()!=null &&
                board.getCellAt(coordinate).getPiece().getColor()!= getColor())
            nextMovements = Tool.add(coordinate,nextMovements);

        return nextMovements;
    }

    public Coordinate[] getNextMovementsBlack(){
        Coordinate[] nextMovements = new Coordinate[0];
        Coordinate initialPosition = getCell().getCoordinate();
        Coordinate coordinate;
        Board board = getCell().getBoard();

        //movimiento 1
        coordinate = initialPosition.down();
        if(board.contains(coordinate) && board.getCellAt(coordinate).getPiece()==null) {
            nextMovements = Tool.add(coordinate, nextMovements);
            //posición inicial
            if(initialPosition.getNumber()==2){
                coordinate=coordinate.down();
                if(board.contains(coordinate) && board.getCellAt(coordinate).getPiece()==null)
                    nextMovements = Tool.add(coordinate, nextMovements);
            }
        }

        //avanza matando
        coordinate = initialPosition.diagonalDownRight();
        if(board.contains(coordinate) && board.getCellAt(coordinate).getPiece()!=null &&
                board.getCellAt(coordinate).getPiece().getColor()!= getColor())
            nextMovements = Tool.add(coordinate,nextMovements);

        coordinate = initialPosition.diagonalDownLeft();
        if(board.contains(coordinate) && board.getCellAt(coordinate).getPiece()!=null &&
                board.getCellAt(coordinate).getPiece().getColor()!= getColor())
            nextMovements = Tool.add(coordinate,nextMovements);

        return nextMovements;

    }

    public enum Type {
        BLACK(Piece.Type.BLACK_PAWN),
        WHITE(Piece.Type.WHITE_PAWN);

        private Piece.Type type;

        Type(Piece.Type type) {
            this.type = type;
        }

        public Piece.Type getType() {
            return type;
        }
    }
}

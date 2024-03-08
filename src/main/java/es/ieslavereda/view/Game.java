package es.ieslavereda.view;

import es.ieslavereda.controller.SaveGame;
import es.ieslavereda.model.DeletedPieceManagerListImp;
import es.ieslavereda.model.board.Board;
import es.ieslavereda.model.board.Cell;
import es.ieslavereda.model.board.Coordinate;
import es.ieslavereda.model.piece.*;
import es.ieslavereda.controller.Input;
import static com.diogonunes.jcolor.Ansi.colorize;
import com.diogonunes.jcolor.Attribute;


import java.io.IOException;
import java.io.Serializable;

public class Game implements Serializable {

    private static DeletedPieceManagerListImp pieces;
    private static Board board;
    private  Piece.Color turn;

    public Game(){
        board = new Board();
        pieces = new DeletedPieceManagerListImp();
        startNewBoard();
        board.setRemaining(pieces);
        turn = Piece.Color.WHITE;
    }
    public  Piece.Color getTurn() {
        return turn;
    }
    public  Board getBoard() {
        return board;
    }
    public  DeletedPieceManagerListImp getPieces() {
        return pieces;
    }

    public void setBoard(Board board) {
        Game.board = board;
    }

    public void start(){
        Coordinate coord1;
        Coordinate coord2;
        boolean stop=false;
        startBoard();
        System.out.println(board);
        Game game = new Game();
        Piece.Color turn = getTurn();
        while (board.kingsAlive() && !stop) { //Loop while both kings are alive
            try { //Try playing or stop if the player says so

                do { //Loop in case you cancel the move
                    System.out.println("------------------------\n|     " + turn + "'S TURN     |\n------------------------"); //Print the turn
                    System.out.println(colorize(colorize("Write 'save' to stop and save the game", Attribute.BACK_COLOR(232, 188, 14)), Attribute.TEXT_COLOR(0,0,0)));

                    do { //This won't stop until the coord has a piece, and the color of that piece is the correct one
                        //Is the coord on the board?
                        coord1 = getCoordinate();
                        while (!board.contains(coord1)) { //It is not
                            System.err.println("This coordinate is not in the board, try again");
                            coord1 = getCoordinate();
                        }
                        //Now it is
                        //Is the cell empty?
                        if (!board.getCellAt(coord1).isEmpty()) { //It is not empty
                            if (board.getCellAt(coord1).getPiece().getColor() != turn) { //If the piece has the wrong color
                                System.err.println("This piece is not " + turn.toString().toLowerCase() + ", try with the other color");
                            }
                        } else { //The cell is empty
                            System.err.println("This cell does not have a piece");
                        }
                    } while (board.getCellAt(coord1).isEmpty() || board.getCellAt(coord1).getPiece().getColor() != turn);
                    //Everything is correct

                    highlight(coord1); //Highlight the possible moves

                    do { //Loop until the coord is in the possible moves or until the player cancels de move
                        coord2 = getCoordinate();
                        if (!(board.getCellAt(coord1).getPiece().canMoveTo(coord2))) { //If the piece can not move there
                            System.err.println("The piece can't go there, try another coordinate to move or cancel writing where the piece is");
                        }
                    } while (!(board.getCellAt(coord1).getPiece().canMoveTo(coord2)) || board.getCellAt(coord1).getPiece().getColor() != turn);

                    removeHighLight(); //Remove the movements highlight

                    if (coord1.equals(coord2)) { //Print in case the player canceled the move
                        System.out.println(board);
                        System.out.println(colorize(colorize("Move canceled", Attribute.BACK_COLOR(11, 252, 3)), Attribute.TEXT_COLOR(0,0,0)));
                    }

                } while (coord1.equals(coord2));

                board.getCellAt(coord1).getPiece().moveTo(coord2); //Move the piece
                System.out.println(board); // Print the board

                //Change turn
                if (turn.equals(Piece.Color.WHITE)) { turn=Piece.Color.BLACK; } else { turn=Piece.Color.WHITE; }


            }catch (NumberFormatException e) { //Catch the game stop and save it
                SaveGame.save(this);
                stop = true;
            }
        }

        if (!stop){
            showWinner(turn); //Show the winner unless the player saved the game
        }
    }

    private static Coordinate getCoordinate(){
        return Input.askCoordinate();
    }

    private static void startNewBoard() {
        pieces.addPiece(new Queen(board,new Coordinate('D',1), Queen.Type.BLACK));
        pieces.addPiece(new Queen(board,new Coordinate('D',8), Queen.Type.WHITE));
        pieces.addPiece(new King(board,new Coordinate('E',1), King.Type.BLACK));
        pieces.addPiece(new King(board,new Coordinate('E',8), King.Type.WHITE));
        pieces.addPiece(new Rook(board,new Coordinate('A',1), Rook.Type.BLACK));
        pieces.addPiece(new Rook(board,new Coordinate('H',1), Rook.Type.BLACK));
        pieces.addPiece(new Rook(board,new Coordinate('A',8), Rook.Type.WHITE));
        pieces.addPiece(new Rook(board,new Coordinate('H',8), Rook.Type.WHITE));
        pieces.addPiece(new Knight(board,new Coordinate('B',1), Knight.Type.BLACK));
        pieces.addPiece(new Knight(board,new Coordinate('G',1), Knight.Type.BLACK));
        pieces.addPiece(new Knight(board,new Coordinate('B',8), Knight.Type.WHITE));
        pieces.addPiece(new Knight(board,new Coordinate('G',8), Knight.Type.WHITE));
        pieces.addPiece(new Bishop(board,new Coordinate('C',1), Bishop.Type.BLACK));
        pieces.addPiece(new Bishop(board,new Coordinate('F',1), Bishop.Type.BLACK));
        pieces.addPiece(new Bishop(board,new Coordinate('C',8), Bishop.Type.WHITE));
        pieces.addPiece(new Bishop(board,new Coordinate('F',8), Bishop.Type.WHITE));
        pieces.addPiece(new Pawn(board,new Coordinate('A',2),Pawn.Type.BLACK));
        pieces.addPiece(new Pawn(board,new Coordinate('B',2),Pawn.Type.BLACK));
        pieces.addPiece(new Pawn(board,new Coordinate('C',2),Pawn.Type.BLACK));
        pieces.addPiece(new Pawn(board,new Coordinate('D',2),Pawn.Type.BLACK));
        pieces.addPiece(new Pawn(board,new Coordinate('E',2),Pawn.Type.BLACK));
        pieces.addPiece(new Pawn(board,new Coordinate('F',2),Pawn.Type.BLACK));
        pieces.addPiece(new Pawn(board,new Coordinate('G',2),Pawn.Type.BLACK));
        pieces.addPiece(new Pawn(board,new Coordinate('H',2),Pawn.Type.BLACK));
        pieces.addPiece(new Pawn(board,new Coordinate('A',7),Pawn.Type.WHITE));
        pieces.addPiece(new Pawn(board,new Coordinate('B',7),Pawn.Type.WHITE));
        pieces.addPiece(new Pawn(board,new Coordinate('C',7),Pawn.Type.WHITE));
        pieces.addPiece(new Pawn(board,new Coordinate('D',7),Pawn.Type.WHITE));
        pieces.addPiece(new Pawn(board,new Coordinate('E',7),Pawn.Type.WHITE));
        pieces.addPiece(new Pawn(board,new Coordinate('F',7),Pawn.Type.WHITE));
        pieces.addPiece(new Pawn(board,new Coordinate('G',7),Pawn.Type.WHITE));
        pieces.addPiece(new Pawn(board,new Coordinate('H',7),Pawn.Type.WHITE));
    }

    private static Game startBoard() {
        String input = Input.newOrLoad();
        if (input.equals("n")) { // Create new game
            return new Game();
        }
        if (input.equals("l")){ //Load the saved game
            try {
                return SaveGame.load();
            } catch (IOException e) {
                System.err.println("Can't load the game, creating a new one");
                return new Game();
            }
        }
        return new Game();
    }

    private static void highlight(Coordinate coord) {
        board.highLight(board.getCellAt(coord).getPiece().getNextMovements());
        System.out.println(board);
    }

    public static void removeHighLight() {
        board.getCells().values().forEach(Cell::removeHighLight);
    }

    public static void showWinner(Piece.Color turn) {
        if (turn==Piece.Color.BLACK) {
            System.out.println(colorize(colorize("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                    "\nTHE WHITE KING DIED, BLACKS WIN " +
                    "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",Attribute.BACK_COLOR(0,0,0)),Attribute.TEXT_COLOR(255,255,255)));
        } else {
            System.out.println(colorize(colorize("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                    "\nTHE BLACK KING DIED, WHITES WIN " +
                    "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",Attribute.BACK_COLOR(255,255,255)),Attribute.TEXT_COLOR(0,0,0)));
        }
    }
}
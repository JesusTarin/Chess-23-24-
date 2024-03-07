package es.ieslavereda.view;

import es.ieslavereda.controller.SaveGame;
import es.ieslavereda.model.board.Board;
import es.ieslavereda.model.board.Cell;
import es.ieslavereda.model.board.Coordinate;
import es.ieslavereda.model.piece.*;
import es.ieslavereda.controller.Input;
import static com.diogonunes.jcolor.Ansi.colorize;
import com.diogonunes.jcolor.Attribute;


import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Game {

    private static Map<Piece.Type, Integer> pieces = new TreeMap<>();
    private static Board board = new Board();


    public static void start(){
        Piece.Color turn = Piece.Color.WHITE;
        Coordinate coord1;
        Coordinate coord2;
        startBoard();
        savePieces();
        while ((pieces.get(Piece.Type.WHITE_KING)>0 && pieces.get(Piece.Type.BLACK_KING)>0)) {
            try {
                SaveGame.save(board);
            } catch (IOException e) {
                System.out.println("Can't save the game");
            }
            do { //Loop in case you cancel the move
                System.out.println("------------------------\n|     " + turn + "'S TURN     |\n------------------------"); //Print the turn

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
                if (coord1.equals(coord2)) {
                    System.out.println(board);
                    System.out.println("Movement canceled");
                }
            } while (coord1.equals(coord2));

            board.getCellAt(coord1).getPiece().moveTo(coord2); //Move the piece
            System.out.println(board); // Print the board

            if (turn.equals(Piece.Color.WHITE)){
                turn = Piece.Color.BLACK;
            } else {
                turn = Piece.Color.WHITE;
            }
        }
        showWinner();
    }

    private static Coordinate getCoordinate(){
        return Input.askCoordinate();
    }

    private static void startBoard() {
        Queen queen1 = new Queen(board,new Coordinate('D',1), Queen.Type.BLACK);
        Queen queen2 = new Queen(board,new Coordinate('D',8), Queen.Type.WHITE);
        King king1 = new King(board,new Coordinate('E',4), King.Type.BLACK);
        King king2 = new King(board,new Coordinate('E',5), King.Type.WHITE);
        Rook rook1 = new Rook(board,new Coordinate('A',1), Rook.Type.BLACK);
        Rook rook2 = new Rook(board,new Coordinate('H',1), Rook.Type.BLACK);
        Rook rook3 = new Rook(board,new Coordinate('A',8), Rook.Type.WHITE);
        Rook rook4 = new Rook(board,new Coordinate('H',8), Rook.Type.WHITE);
        Knight knight1 = new Knight(board,new Coordinate('B',1), Knight.Type.BLACK);
        Knight knight2 = new Knight(board,new Coordinate('G',1), Knight.Type.BLACK);
        Knight knight3 = new Knight(board,new Coordinate('B',8), Knight.Type.WHITE);
        Knight knight4 = new Knight(board,new Coordinate('G',8), Knight.Type.WHITE);
        Bishop bishop1 = new Bishop(board,new Coordinate('C',1), Bishop.Type.BLACK);
        Bishop bishop2 = new Bishop(board,new Coordinate('F',1), Bishop.Type.BLACK);
        Bishop bishop3 = new Bishop(board,new Coordinate('C',8), Bishop.Type.WHITE);
        Bishop bishop4 = new Bishop(board,new Coordinate('F',8), Bishop.Type.WHITE);
        Pawn peon1 = new Pawn(board,new Coordinate('A',2),Pawn.Type.BLACK);
        Pawn peon2 = new Pawn(board,new Coordinate('B',2),Pawn.Type.BLACK);
        Pawn peon3 = new Pawn(board,new Coordinate('C',2),Pawn.Type.BLACK);
        Pawn peon4 = new Pawn(board,new Coordinate('D',2),Pawn.Type.BLACK);
        Pawn peon5 = new Pawn(board,new Coordinate('E',2),Pawn.Type.BLACK);
        Pawn peon6 = new Pawn(board,new Coordinate('F',2),Pawn.Type.BLACK);
        Pawn peon7 = new Pawn(board,new Coordinate('G',2),Pawn.Type.BLACK);
        Pawn peon8 = new Pawn(board,new Coordinate('H',2),Pawn.Type.BLACK);
        Pawn peon9 = new Pawn(board,new Coordinate('A',7),Pawn.Type.WHITE);
        Pawn peon10 = new Pawn(board,new Coordinate('B',7),Pawn.Type.WHITE);
        Pawn peon11 = new Pawn(board,new Coordinate('C',7),Pawn.Type.WHITE);
        Pawn peon12 = new Pawn(board,new Coordinate('D',7),Pawn.Type.WHITE);
        Pawn peon13 = new Pawn(board,new Coordinate('E',7),Pawn.Type.WHITE);
        Pawn peon14 = new Pawn(board,new Coordinate('F',7),Pawn.Type.WHITE);
        Pawn peon15 = new Pawn(board,new Coordinate('G',7),Pawn.Type.WHITE);
        Pawn peon16 = new Pawn(board,new Coordinate('H',7),Pawn.Type.WHITE);
        System.out.println(board);
    }

    private static void savePieces() {
        pieces.put(Piece.Type.BLACK_QUEEN,1);
        pieces.put(Piece.Type.WHITE_QUEEN,1);
        pieces.put(Piece.Type.BLACK_KING,1);
        pieces.put(Piece.Type.WHITE_KING,1);
        pieces.put(Piece.Type.BLACK_ROOK,2);
        pieces.put(Piece.Type.WHITE_ROOK,2);
        pieces.put(Piece.Type.BLACK_KNIGHT,2);
        pieces.put(Piece.Type.WHITE_KNIGHT,2);
        pieces.put(Piece.Type.BLACK_BISHOP,2);
        pieces.put(Piece.Type.WHITE_BISHOP,2);
        pieces.put(Piece.Type.BLACK_PAWN,8);
        pieces.put(Piece.Type.WHITE_PAWN,8);
    }
    public static Map<Piece.Type, Integer> getPieces() {
        return pieces;
    }

    public static void killPiece(Piece piece) {
        pieces.put(piece.getType(), pieces.get(piece.getType())-1);
    }

    private static void highlight(Coordinate coord) {
        board.highLight(board.getCellAt(coord).getPiece().getNextMovements());
        System.out.println(board);
    }

    public static void removeHighLight() {
        board.getCells().values().forEach(cell -> cell.removeHighLight());
    }

    public static void showWinner() {
        if (pieces.get(Piece.Type.WHITE_KING)==0) {
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


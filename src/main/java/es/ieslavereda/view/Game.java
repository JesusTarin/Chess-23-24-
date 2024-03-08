package es.ieslavereda.view;

import es.ieslavereda.controller.SaveGame;
import es.ieslavereda.model.board.Board;
import es.ieslavereda.model.board.Coordinate;
import es.ieslavereda.model.piece.*;
import es.ieslavereda.controller.Input;
import static com.diogonunes.jcolor.Ansi.colorize;
import com.diogonunes.jcolor.Attribute;


import java.io.IOException;
import java.util.*;

public class Game {

    private static Map<Piece.Type, Integer> pieces = new TreeMap<>();
    private static Board board = new Board();


    public static void start(){
        Piece.Color turn = Piece.Color.WHITE;
        Coordinate coord1;
        Coordinate coord2;
        boolean stop=false;
        startBoard(board); //Start the board
        savePieces(); //Save the pieces in a map
        while ((pieces.get(Piece.Type.WHITE_KING)>0 && pieces.get(Piece.Type.BLACK_KING)>0) && !stop) {
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
                if (turn.equals(Piece.Color.WHITE)){ turn = Piece.Color.BLACK; } else { turn = Piece.Color.WHITE; }

            }catch (NumberFormatException e) { //Catch the game stop and save it
                SaveGame.save(board);
                stop = true;
            }
        }

        if (!stop){
            showWinner(); //Show the winner unless the player saved the game
        }
    }

    private static Coordinate getCoordinate(){
        return Input.askCoordinate();
    }

    private static void startNewBoard(Board board) {
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

    private static void startBoard(Board board) {
        String input = Input.newOrLoad();
        if (input.equals("n")) {
            startNewBoard(board);
        }
        if (input.equals("l")){
            try {
                board = SaveGame.load();

            } catch (IOException e) {
                System.err.println("Can't load the game, creating a new one");
                startNewBoard(board);
            }
        }
    }

    public static String printPieces() {
        String aux="";
        Set<Piece.Type> remaining = new HashSet<>();
        Set<Piece.Type> deleted = new HashSet<>();

        for (Piece.Type piece : pieces.keySet()) {
            if (pieces.get(piece)==0) {
                deleted.add(piece);
            } else {
                remaining.add(piece);
            }
        }
        Set<Piece.Type> types = getPiecesTypes();
        for (Piece.Type piece : types) {
            aux+="\n\t\t\t"+colorize(colorize(piece.getShape(),Attribute.BACK_COLOR(100,100,100), Attribute.TEXT_COLOR(0,0,0)));
            aux+="\n\t\t\t"+colorize(colorize(piece.getShape(),Attribute.BACK_COLOR(100,100,100), Attribute.TEXT_COLOR(0,0,0)));
        }
        return aux;
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

    private static Set<Piece.Type> getPiecesTypes(){
        Set<Piece.Type> types = new HashSet<>();
        for (int i = 0; i < 8; i++) {
            types.add(Piece.Type.BLACK_PAWN);
            types.add(Piece.Type.WHITE_PAWN);
            types.add(Piece.Type.BLACK_BISHOP);
            types.add(Piece.Type.WHITE_BISHOP);
            types.add(Piece.Type.BLACK_KING);
            types.add(Piece.Type.WHITE_KING);
            types.add(Piece.Type.BLACK_KNIGHT);
            types.add(Piece.Type.WHITE_KNIGHT);
            types.add(Piece.Type.BLACK_QUEEN);
            types.add(Piece.Type.WHITE_QUEEN);
            types.add(Piece.Type.BLACK_ROOK);
            types.add(Piece.Type.WHITE_ROOK);
        }
        return types;
    }
}


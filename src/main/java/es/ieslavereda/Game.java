package es.ieslavereda;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Game {

    private static Map<Piece.Type, Integer> pieces = new TreeMap<>();

    private static Board tablero = new Board();


    public static void start(){
        Coordinate coord1;
        Coordinate coord2;
        startBoard();
        savePieces();
        while (pieces.get(Piece.Type.WHITE_KING)>0 && pieces.get(Piece.Type.BLACK_KING)>0) {
            coord1 = getCoordinate();
            hasAPiece(coord1);
            highlight(coord1);
            do {
                coord2 = getCoordinate();
                if (!(tablero.getCellAt(coord1).getPiece().canMoveTo(coord2))) {
                    System.err.println("The piece can't go there, try another coordinate to move or cancel with a 'c'");
                }
            } while (!(tablero.getCellAt(coord1).getPiece().canMoveTo(coord2)));

        }

    }

    private static Coordinate getCoordinate(){
        return Input.askCoordinate();
    }

    private static void startBoard() {
        Queen queen1 = new Queen(tablero,new Coordinate('D',1), Queen.Type.BLACK);
        Queen queen2 = new Queen(tablero,new Coordinate('D',8), Queen.Type.WHITE);
        King king1 = new King(tablero,new Coordinate('E',1), King.Type.BLACK);
        King king2 = new King(tablero,new Coordinate('E',8), King.Type.WHITE);
        Rook rook1 = new Rook(tablero,new Coordinate('A',1), Rook.Type.BLACK);
        Rook rook2 = new Rook(tablero,new Coordinate('H',1), Rook.Type.BLACK);
        Rook rook3 = new Rook(tablero,new Coordinate('A',8), Rook.Type.WHITE);
        Rook rook4 = new Rook(tablero,new Coordinate('H',8), Rook.Type.WHITE);
        Knight knight1 = new Knight(tablero,new Coordinate('B',1), Knight.Type.BLACK);
        Knight knight2 = new Knight(tablero,new Coordinate('G',1), Knight.Type.BLACK);
        Knight knight3 = new Knight(tablero,new Coordinate('B',8), Knight.Type.WHITE);
        Knight knight4 = new Knight(tablero,new Coordinate('G',8), Knight.Type.WHITE);
        Bishop bishop1 = new Bishop(tablero,new Coordinate('C',1), Bishop.Type.BLACK);
        Bishop bishop2 = new Bishop(tablero,new Coordinate('F',1), Bishop.Type.BLACK);
        Bishop bishop3 = new Bishop(tablero,new Coordinate('C',8), Bishop.Type.WHITE);
        Bishop bishop4 = new Bishop(tablero,new Coordinate('F',8), Bishop.Type.WHITE);
        Pawn peon1 = new Pawn(tablero,new Coordinate('A',2),Pawn.Type.BLACK);
        Pawn peon2 = new Pawn(tablero,new Coordinate('B',2),Pawn.Type.BLACK);
        Pawn peon3 = new Pawn(tablero,new Coordinate('C',2),Pawn.Type.BLACK);
        Pawn peon4 = new Pawn(tablero,new Coordinate('D',2),Pawn.Type.BLACK);
        Pawn peon5 = new Pawn(tablero,new Coordinate('E',2),Pawn.Type.BLACK);
        Pawn peon6 = new Pawn(tablero,new Coordinate('F',2),Pawn.Type.BLACK);
        Pawn peon7 = new Pawn(tablero,new Coordinate('G',2),Pawn.Type.BLACK);
        Pawn peon8 = new Pawn(tablero,new Coordinate('H',2),Pawn.Type.BLACK);
        Pawn peon9 = new Pawn(tablero,new Coordinate('A',7),Pawn.Type.WHITE);
        Pawn peon10 = new Pawn(tablero,new Coordinate('B',7),Pawn.Type.WHITE);
        Pawn peon11 = new Pawn(tablero,new Coordinate('C',7),Pawn.Type.WHITE);
        Pawn peon12 = new Pawn(tablero,new Coordinate('D',7),Pawn.Type.WHITE);
        Pawn peon13 = new Pawn(tablero,new Coordinate('E',7),Pawn.Type.WHITE);
        Pawn peon14 = new Pawn(tablero,new Coordinate('F',7),Pawn.Type.WHITE);
        Pawn peon15 = new Pawn(tablero,new Coordinate('G',7),Pawn.Type.WHITE);
        Pawn peon16 = new Pawn(tablero,new Coordinate('H',7),Pawn.Type.WHITE);
        System.out.println(tablero);
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

    private static void hasAPiece(Coordinate coord) {
        do {
            if ((tablero.getCellAt(coord).isEmpty())) {
                System.err.println("This coordinate does not have a piece");
            }
        } while (tablero.getCellAt(coord).isEmpty());
    }

    private static void highlight(Coordinate coord) {
        tablero.highLight(tablero.getCellAt(coord).getPiece().getNextMovements());
        System.out.println(tablero);
    }

    private static void removeHighlight(Set<Coordinate> coords) {

    }

}


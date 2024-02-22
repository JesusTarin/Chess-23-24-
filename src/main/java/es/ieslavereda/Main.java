package es.ieslavereda;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Board tablero = new Board();


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
        //tablero.resetColor();
        //tablero.highligh(tablero.getCellAt(new Coordinate('A',1)).getPiece().getNextMovements());

    }
}

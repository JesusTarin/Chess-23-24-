package es.ieslavereda.controller;

import es.ieslavereda.model.board.Board;
import es.ieslavereda.model.board.Cell;
import es.ieslavereda.model.board.Coordinate;
import es.ieslavereda.model.piece.Piece;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class SaveGame {

    public static void save(Board board) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("save.txt"));
        Map<Coordinate, Cell> cells = board.getCells();
        for (Cell cell : cells.values()) {
            bw.write(cell.getPiece() + "," + cell.getCoordinate());
            bw.newLine();
        }
    }










}

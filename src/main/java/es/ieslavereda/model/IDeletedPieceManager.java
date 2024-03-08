package es.ieslavereda.model;

import es.ieslavereda.model.piece.Piece;

public interface IDeletedPieceManager {
    void addPiece(Piece piece);
    Piece removeLast();
    int count(Piece.Type pieceType);
}

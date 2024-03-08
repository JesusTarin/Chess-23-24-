package es.ieslavereda.controller;

import es.ieslavereda.model.board.Board;

import java.io.*;
import java.util.Scanner;

public class SaveGame {

    public static void save(Board board) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write the name of the save:");
        String name = sc.nextLine();
        while (name.isEmpty()) {
            System.err.println("Write a name");
            name = sc.nextLine();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream((name+".dat"))))){
            oos.writeObject(board);
        } catch (Exception e) {
            System.out.println("Can't save this game");
        }
    }

    public static Board load() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write the name of the save you want to use:");
        String name = sc.nextLine();
        while (name.isEmpty()) {
            System.err.println("Write a name");
            name = sc.nextLine();
        }
        Board newBoard = new Board();
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream((name+".dat"))))) {
            newBoard = (Board) ois.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("This game may not exist");;
        }
        System.out.println(newBoard);
        return newBoard;
    }

}

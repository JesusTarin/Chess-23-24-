package es.ieslavereda.controller;

import es.ieslavereda.view.Game;

import java.io.*;
import java.util.Scanner;

public class SaveGame {

    public static void save(Game game) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write the name of the save:");
        String name = sc.nextLine();
        while (name.isEmpty()) {
            System.err.println("Write a name");
            name = sc.nextLine();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("src/main/java/es/ieslavereda/saves/"+name+".dat")))){
            oos.writeObject(game);
        } catch (Exception e) {
            System.out.println("Can't save this game");
        }
    }

    public static Game load() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write the name of the save you want to use:");
        String name = sc.nextLine();
        while (name.isEmpty()) {
            System.err.println("Write a name");
            name = sc.nextLine();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("src/main/java/es/ieslavereda/saves/"+name+".dat")))) {
            return (Game) ois.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("This game may not exist");
        }
        return new Game();
    }

}
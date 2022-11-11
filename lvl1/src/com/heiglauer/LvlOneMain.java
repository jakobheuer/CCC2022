package com.heiglauer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LvlOneMain {
    public static void main(String[] args) {

        String file ="src/test/resources/fileTest.txt";
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String currentLine = reader.readLine();
            reader.close();
        }
        catch (IOException e) {
            System.out.println("File Error");
            e.printStackTrace();
        }
    }
}

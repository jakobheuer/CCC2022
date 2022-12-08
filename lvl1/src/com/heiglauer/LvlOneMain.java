// Looks like I coded some parts of lvl2 in here and thus deleted my original lvl1 code

package com.heiglauer;

import java.io.*;

public class LvlOneMain {
    public static void main(String[] args) {

        String file ="files/level1_1.in";
        int counter=0;
        int colums=10;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            int lines = Integer.parseInt(reader.readLine());
            String[][] singleChar = new String[lines][colums];
            for(int i=0; i < lines; ++i){
                String currentLine = reader.readLine();
                singleChar[i] = currentLine.split("(?!^)");
            }
            reader.close();
            for(int i=0; i < lines; ++i){
                for(int j=0; j < colums;++j){
                    System.out.print(singleChar[i][j]);
                }
                System.out.println();
            }
        }
        catch (IOException e) {
            System.out.println("File Error");
            e.printStackTrace();
        }
    }
}

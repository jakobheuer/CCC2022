import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Backup {
    public static void main(String[] args) {
        String file ="lvl3/lvl3files/level3_7";
        int coinCounter=0;
        int colums=10;
        try(BufferedReader reader = new BufferedReader(new FileReader(file+".in"))){
            int lines = Integer.parseInt(reader.readLine());

            String[][] boardCharMatrix = new String[lines][colums];
            for(int i=0; i < lines; ++i){
                String currentLine = reader.readLine();
                boardCharMatrix[i] = currentLine.split("(?!^)");
            }
            //---------- Pacman Coords ----------
            String currentLine = reader.readLine();
            String[] pacmanXY;
            pacmanXY = currentLine.split(" ");
            int pacmanRow = Integer.parseInt(pacmanXY[0])-1;
            int pacmanColumn = Integer.parseInt(pacmanXY[1])-1;
            int sequenceLength = Integer.parseInt(reader.readLine());
            String[] packmanMovementPattern = reader.readLine().split("(?!^)");
            //---------- Ghost Coords ----------
            int numberOfGhosts = Integer.parseInt(reader.readLine());
            List<Ghost> ghosts = new ArrayList<>();
            for(int i=0; i <numberOfGhosts; ++i){
                currentLine = reader.readLine();
                String[] ghostXY = currentLine.split(" ");
                int ghostRow = Integer.parseInt(ghostXY[0])-1;
                int ghostColumn = Integer.parseInt(ghostXY[1])-1;
                int ghostSequenceLength = Integer.parseInt(reader.readLine());
                String[] ghostMoveSequence = reader.readLine().split("(?!^)");
                ghosts.add(new Ghost(ghostColumn, ghostRow, ghostMoveSequence));
            }
            reader.close();

            Boolean packmanIsAlive = true;
            int after=0;
            outer:
            for(int i = 0; i < packmanMovementPattern.length; ++i){ //Steps
                switch (packmanMovementPattern[i]){ //Move Packman
                    case "L" -> pacmanColumn -= 1;
                    case "R" -> pacmanColumn += 1;
                    case "U" -> pacmanRow -= 1;
                    case "D" -> pacmanRow += 1;
                }
                int ghostI=1;
                int indexForEach=0;
                for (Ghost ghost:ghosts) { //Move ghosts
                    /*for (int j = ghostI; j < ghosts.size(); ++j){
                        if((ghosts.get(j).currentRow == ghost.currentRow) && (ghosts.get(j).currentColumn == ghost.currentColumn)){
                            if(ghosts.get(j).wasThereACoinBefore){
                                ghost.wasThereACoinBefore = true;
                                System.out.println("YES");
                            }
                        }
                        ghostI += 1;
                    }*/
                    //System.out.println(ghost.currentColumn+" "+ghost.currentRow);
                    if(ghost.wasThereACoinBefore)
                        boardCharMatrix[ghost.currentRow][ghost.currentColumn] = "C";
                    else
                        boardCharMatrix[ghost.currentRow][ghost.currentColumn] = "X";
                    switch (ghost.movementPatter[i]){
                        case "L" -> ghost.currentColumn -= 1;
                        case "R" -> ghost.currentColumn += 1;
                        case "U" -> ghost.currentRow -= 1;
                        case "D" -> ghost.currentRow += 1;
                    }
                    if(pacmanRow == ghost.currentRow && pacmanColumn == ghost.currentColumn){ //Check if packman is caugth by ghost
                        System.out.println("Packman is at ghost");
                        packmanIsAlive = false;
                        break outer;
                    }
                    if(boardCharMatrix[ghost.currentRow][ghost.currentColumn].equals("C"))
                        ghost.wasThereACoinBefore = true;
                    else if (boardCharMatrix[ghost.currentRow][ghost.currentColumn].equals("G")) {
                        ghost.wasThereACoinBefore = false;
                        for (int j = 0; j < indexForEach; ++j){
                            if((ghosts.get(j).currentRow == ghost.currentRow) && (ghosts.get(j).currentColumn == ghost.currentColumn)){
                                if(ghosts.get(j).wasThereACoinBefore){
                                    ghost.wasThereACoinBefore = true;
                                    System.out.println("YES");
                                    break;
                                }
                            }
                        }
                    }
                    else
                        ghost.wasThereACoinBefore = false;
                    boardCharMatrix[ghost.currentRow][ghost.currentColumn] = "G";

                    indexForEach += 1;
                }
                //System.out.println();
                if(boardCharMatrix[pacmanRow][pacmanColumn].equals("W")){ //Check if pacman moved into wall
                    System.out.println("Packman moved into wall");
                    packmanIsAlive = false;
                    break;
                }
                ////System.out.println("Packamn Position: Row: "+pacmanRow+" Column: "+pacmanColumn);
                //printBoard(boardCharMatrix, lines);
                if(boardCharMatrix[pacmanRow][pacmanColumn].equals("C")){
                    coinCounter += 1;
                }
                //System.out.println();
                boardCharMatrix[pacmanRow][pacmanColumn] = "X";
                //printBoard(boardCharMatrix, lines);
            }
            System.out.println("Coinds found:"+coinCounter);
            try{
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new FileWriter(file+".out")));
                out.println(coinCounter + " " + (packmanIsAlive ?  "YES" : "NO"));
                out.close();
            }
            catch (IOException e){
                System.out.println("File Error");
                e.printStackTrace();
            }
        }
        catch (IOException e) {
            System.out.println("File Error");
            e.printStackTrace();
        }
    }

    public static void printBoard(String[][] boardMatrix, int rows){
        for (int i =0; i<rows; ++i){
            for (int j=0; j < boardMatrix[i].length; ++j){
                System.out.print(boardMatrix[i][j]);
            }
            System.out.println();
        }
    }
}

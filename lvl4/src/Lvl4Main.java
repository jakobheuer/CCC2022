import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Lvl4Main {
    public static void main(String[] args) {
        String file ="lvl4/files/level4_example";
        int coinCounter=0;
        String pacmanDirection="L"; //Init

        try(BufferedReader reader = new BufferedReader(new FileReader(file+".in"))){
            int lines = Integer.parseInt(reader.readLine());

            String[][] boardCharMatrix = new String[lines][];
            for(int i=0; i < lines; ++i){
                String currentLine = reader.readLine();
                boardCharMatrix[i] = currentLine.split("(?!^)");
            }
            boardCharMatrix = cleanUpGhosts(boardCharMatrix);
            printBoard(boardCharMatrix);

            //---------- Pacman Coords ----------
            String currentLine = reader.readLine();

            String[] pacmanXY;
            pacmanXY = currentLine.split(" ");
            int pacmanRow = Integer.parseInt(pacmanXY[0])-1;
            int pacmanColumn = Integer.parseInt(pacmanXY[1])-1;
            int maxMovementSequenceLength = Integer.parseInt(reader.readLine());
            reader.close();


            for(int i = 0; i <4;++i){
                if(!boardCharMatrix[pacmanRow+1][pacmanColumn].equals("W")){
                    pacmanDirection = "D";
                    break;
                } else if (!boardCharMatrix[pacmanRow-1][pacmanColumn].equals("W")) {
                    pacmanDirection = "U";
                    break;
                }
                else if (!boardCharMatrix[pacmanRow][pacmanColumn+1].equals("W")) {
                    pacmanDirection = "R";
                    break;
                }
                else if (!boardCharMatrix[pacmanRow-1][pacmanColumn-1].equals("W")) {
                    pacmanDirection = "L";
                    break;
                }
            }
            System.out.println("Start direction: "+pacmanDirection);
            Packman packman = new Packman(pacmanRow, pacmanColumn,pacmanDirection);
            int maxCoins = getNumberOfCoins(boardCharMatrix);
            for(int i = 0; i < maxMovementSequenceLength; ++i){
                boardCharMatrix[pacmanRow][pacmanColumn] = "X";
                int nextMoveObstacle = checkNextMove(packman,boardCharMatrix);
                System.out.println("hier");
                if (nextMoveObstacle == 2) { //2 = Coin
                    packman.movePackman();
                    coinCounter += 1;
                }
                else{ //3 = LastPath
                    if(findNextMoveableSpot(packman,boardCharMatrix)){ //Found spot that is coin
                        System.out.println("direction "+packman.direction);
                        packman.movePackman();
                    }else{ //Lets walk back
                        packman.walkBack();
                    }
                }
                if(coinCounter == maxCoins){
                    System.out.println("Got all");
                    break;
                }
                if(i==maxMovementSequenceLength-1){
                    System.out.println("OUT OF TRYS");
                }

                //TODO Check if direction is wall, if wall change direction 4
                //TODO if
            }
            System.out.println(packman.walkedPath);
            /*
            Boolean packmanIsAlive = true;
            outer:
            for(int i = 0; i < packmanMovementPattern.length; ++i){ //Steps
                switch (packmanMovementPattern[i]){ //Move Packman
                    case "L" -> pacmanColumn -= 1;
                    case "R" -> pacmanColumn += 1;
                    case "U" -> pacmanRow -= 1;
                    case "D" -> pacmanRow += 1;
                }
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
            }*/
        }
        catch (IOException e) {
            System.out.println("File Error");
            e.printStackTrace();
        }
    }

    private static int checkNextMove(Packman packman, String[][] boardCharMatrix) {
        int pacmanColumn = packman.pacmanColumn;
        int pacmanRow = packman.pacmanRow;
        switch (packman.direction){ //Move Packman
            case "L" -> pacmanColumn -= 1;
            case "R" -> pacmanColumn += 1;
            case "U" -> pacmanRow -= 1;
            case "D" -> pacmanRow += 1;
        }
        System.out.println("Row: "+pacmanRow+" Column; "+pacmanColumn);
        if(boardCharMatrix[pacmanRow][pacmanColumn].equals("W"))    //1 = Wall
            return 1;
        if(boardCharMatrix[pacmanRow][pacmanColumn].equals("C"))    //2 = Coin
            return 2;
        if(boardCharMatrix[pacmanRow][pacmanColumn].equals("X"))    //3 = LastPath
            return 3;
        return 4;
    }

    private static boolean findNextMoveableSpot(Packman packman, String[][] boardCharMatrix){
        System.out.println("searching");
        for(int i = 0; i <4;++i){
            if(boardCharMatrix[packman.pacmanRow+1][packman.pacmanColumn].equals("C")){
                packman.direction = "D";
                return true;
            } else if (boardCharMatrix[packman.pacmanRow-1][packman.pacmanColumn].equals("C")) {
                packman.direction = "U";
                return true;
            }
            else if (boardCharMatrix[packman.pacmanRow][packman.pacmanColumn+1].equals("C")) {
                packman.direction = "R";
                return true;
            }
            else if (boardCharMatrix[packman.pacmanRow][packman.pacmanColumn-1].equals("C")) {
                packman.direction = "L";
                return true;
            }
        }
        System.out.println("nothing found");
        return false;
    }

    private static String[][] cleanUpGhosts(String[][] boardCharMatrix) {
        for(int i = 0; i < boardCharMatrix.length; ++i){
            for (int j = 0; j < boardCharMatrix[i].length; j++){
                if(boardCharMatrix[i][j].equals("G"))
                    boardCharMatrix[i][j]="W";
            }
        }
        return boardCharMatrix;
    }

    public static void printBoard(String[][] boardMatrix){
        for (int i =0; i<boardMatrix.length; ++i){
            for (int j=0; j < boardMatrix[i].length; ++j){
                System.out.print(boardMatrix[i][j]);
            }
            System.out.println();
        }
    }
    
    public static int getNumberOfCoins(String[][] boardMatrix){
        int counter=0;
        for (int i =0; i<boardMatrix.length; ++i){
            for (int j=0; j < boardMatrix[i].length; ++j){
                System.out.print(boardMatrix[i][j]);
                if(boardMatrix[i][j].equals("C"))
                    counter += 1;
            }
            System.out.println();
        }
        return counter;
    }
}

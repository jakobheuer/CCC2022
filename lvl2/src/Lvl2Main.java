import java.io.*;

public class Lvl2Main {
    public static void main(String[] args) {
        String file ="lvl2files/level2_5";
        int coinCounter=0;
        int colums=10;
        try(BufferedReader reader = new BufferedReader(new FileReader(file+".in"))){
            int lines = Integer.parseInt(reader.readLine());

            String[][] singleChar = new String[lines][colums];
            for(int i=0; i < lines; ++i){
                String currentLine = reader.readLine();
                singleChar[i] = currentLine.split("(?!^)");
            }
            String currentLine = reader.readLine();
            String pacmanXY[];
            pacmanXY = currentLine.split(" ");
            int pacmanRow = Integer.parseInt(pacmanXY[0])-1;
            int pacmanColumn = Integer.parseInt(pacmanXY[1])-1;
            //System.out.println("Packamn Position: Row: "+pacmanRow+" Column: "+pacmanColumn);
            //System.out.println("Char here: "+singleChar[pacmanRow][pacmanColumn]);
            //System.out.println();
            int sequenceLength = Integer.parseInt(reader.readLine());
            String[] moveSequence = reader.readLine().split("(?!^)");
            reader.close();

            for(int i = 0; i < moveSequence.length; ++i){
                switch (moveSequence[i]){
                    case "L" -> pacmanColumn -= 1;
                    case "R" -> pacmanColumn += 1;
                    case "U" -> pacmanRow -= 1;
                    case "D" -> pacmanRow += 1;
                }
                //System.out.println("Packamn Position: Row: "+pacmanRow+" Column: "+pacmanColumn);
                //System.out.println("Char here: "+singleChar[pacmanRow][pacmanColumn]);
                if(singleChar[pacmanRow][pacmanColumn].equals("C")){
                    coinCounter += 1;
                    singleChar[pacmanRow][pacmanColumn] = "P";
                }
            }
            System.out.println(coinCounter);



            try{
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new FileWriter(file+".out")));

                out.println(coinCounter);
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
}

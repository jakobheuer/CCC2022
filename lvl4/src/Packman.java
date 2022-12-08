public class Packman {
    public int pacmanRow;
    public int pacmanColumn;
    public String direction;

    public String walkedPath;
    int walkedSinceLastStop;

    public Packman(int pacmanRow, int pacmanColumn, String direction) {
        this.pacmanRow = pacmanRow;
        this.pacmanColumn = pacmanColumn;
        this.direction = direction;
        this.walkedSinceLastStop = 0;
    }

    public void movePackman(){
        switch (direction){ //Move Packman
            case "L" -> pacmanColumn -= 1;
            case "R" -> pacmanColumn += 1;
            case "U" -> pacmanRow -= 1;
            case "D" -> pacmanRow += 1;
        }
        walkedPath += direction;
        walkedSinceLastStop += 1;
    }

    public void walkBack(){
        for (int i = 0; i < walkedSinceLastStop; ++i){
            switch (walkedPath.charAt(walkedPath.length() - i)){ //Move Packman
                case 'L' -> pacmanColumn += 1;
                case 'R' -> pacmanColumn -= 1;
                case 'U' -> pacmanRow += 1;
                case 'D' -> pacmanRow -= 1;
            }
        }
    }
}

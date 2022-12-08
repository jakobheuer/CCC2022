public class Ghost {
    public int currentColumn;
    public int currentRow;
    public String[] movementPatter;
    public Boolean wasThereACoinBefore;

    public Ghost(int currentColumn, int currentRow, String[] movementPatter) {
        this.currentColumn = currentColumn;
        this.currentRow = currentRow;
        this.movementPatter = movementPatter;
        this.wasThereACoinBefore = false;
    }
}

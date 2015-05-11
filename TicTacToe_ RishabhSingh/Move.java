

public class Move
{
    private int row;
    private int col;
    
    
    public Move(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    
    public Move()
    {
        row = 0;
        col= 0;
    }
    
    public int getRow()
    {
       return row;
    }
    
    public int getCol()
    {
        return col;
    }
}

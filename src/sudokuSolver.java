import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class sudokuSolver
{
	
	int sum = 0;
	
	public void solveSudoku(int[][] grid)
	{
		solve(grid, 0);//check if we can  remove this method
	}
	
	private boolean solve(int[][] grid, int pos)
	{
		if(pos==81)
		{
			sum += (100*grid[0][0]+10*grid[0][1]+grid[0][2]);
			return true; //solved
		}
		
		int row = pos/9;
		int col = pos%9;
		
		if(grid[row][col] != 0) return solve(grid, pos+1);
		else
		{
			for(int i=1;i<=9;i++)
			{
				if(consistent(grid, row, col, i))
				{
					grid[row][col]=i;
					if(solve(grid, pos+1))	return true;
					grid[row][col]=0;
					
					
				}
			}
		}
		return false;	
	}
	
	public boolean consistent (int[][] grid, int row, int col, int c)
	{
		for(int i=0;i<9;i++)
		{
			if(grid[row][i]==c) return false;
			if(grid[i][col]==c) return false;
		}
		
		int rowStart = row - row % 3;
		int colStart = col - col % 3;
		
		for (int m=0;m<3;m++)
		{
			for(int n=0;n<3;n++)
			{
				if(grid[rowStart + n][colStart + m]==c) return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) 
	{
		sudokuSolver e = new sudokuSolver();
	
		try 
		{
			Scanner s = new Scanner(new File("/home/admin/Desktop/sudoku.txt"));
			for (int i=0; i<50; i++)
			{
				s.nextLine();
				int[][] finalArray = new int[9][9];
				for (int j=0; j<9; j++)
				{
					String n = s.nextLine();
					char[] nn = n.toCharArray();
					for (int k=0; k<9; k++)
					{
						finalArray[j][k] = nn[k] - '0';
					}
			
				}
				e.solveSudoku(finalArray);
			}
			System.out.println(e.sum);
	
	
		} catch (FileNotFoundException e1)
		{
		      e1.printStackTrace();
	    }  
	}
}





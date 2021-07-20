import java.util.*;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Spelljinxer
 *
 * Sudoku Rules:
 *  - Every square has to contain a single number
 *  - Only the numbers from 1 through to 9 can be used
 *  - Each 3×3 box can only contain each number from 1 to 9 once
 *  - Each vertical column can only contain each number from 1 to 9 once
 *  - Each horizontal row can only contain each number from 1 to 9 once
 */

public class sudoku
{

    private final int[][] sudoku; //2D array of sudoku

    public sudoku(int[][] sudokuBoard)
    {
        this.sudoku = sudokuBoard;
    }

    /**
     * Clears the sudoku board, setting every element to 0.
     */
    private void clear()
    {
        for(int i = 0; i < sudoku.length; i++)
        {
            for(int j = 0; j < sudoku.length; j++)
            {
                sudoku[i][j] = 0;
            }
        }
    }

    /**
     * Returns the given column from index of 2d array
     * @param array the gameboard
     * @param index the index of specific column being retrieved
     * @return the column from index inside array
     */
    public int[] getColumn(int[][] array, int index)
    {
        int[] column = new int[array[0].length];
        for(int i = 0; i < column.length; i++)
        {
            column[i] = array[i][index];
        }
        return column;
    }

    public int[] getRows(int[][] array, int index)
    {
        int[] row = new int[array[0].length];
        for(int i = 0; i < row.length; i++)
        {
            row[i] = array[index][i];
        }
        return row;
    }

    /**
     * Checks if an element exists in the given row
     * @param row the row to be analysed
     * @param element the element we're checking for
     * @return true iff @param element exists inside @param row
     */

    private boolean checkRowElement(int row, int element)
    {
        for (int i = 0; i < sudoku.length; i++)
        {
            if (sudoku[row][i] == element)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if an element exists in the given column
     * @param column the column to be analysed
     * @param element the element we're checking for
     * @return true iff @param element exists inside @param column
     */
    private boolean checkColumnElement(int column, int element)
    {
        for (int[] ints : sudoku)
        {
            if (ints[column] == element) {
                return true;
            }
        }
        return false;
    }


    /**
     * Check if element exists inside the 3x3 box
     * @param row the row to be analysed
     * @param column the column to be analysed
     * @param element the element we're checking
     * @return true iff element is within that row/column area
     */
    private boolean checkBoxElement(int row, int column, int element)
    {
        int r = row - row % 3;
        int c = column - column % 3;

        for (int i = r; i < r + 3; i++)
        {
            for (int j = c; j < c + 3; j++)
            {
                if (sudoku[i][j] == element)
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if element exists inside a position on row-column
     * @param row to be analysed
     * @param column to be analysed
     * @param element we're checking
     * @return true iff element exists in that position else false
     */
    private boolean check(int row, int column, int element)
    {
        return !checkRowElement(row, element) && !checkBoxElement(row, column, element) && !checkColumnElement(column, element);
    }

    /**
     * Method to check if the given row is valid, that is all elements in that row are unique
     * @param row being checked for any duplicates
     * @return false iff there is a duplicate in that row, false otherwise
     */
    public boolean validRows(int[] row)
    {
        Set<Integer> s = new HashSet<Integer>();
        for(int i : row)
        {
            if(s.contains((i)))
            {
                return false;
            }
            s.add(i);
        }
        return true;
    }

    public boolean validColumns()
    {
        for(int i = 0; i < sudoku.length; i++)
        {
            int[] columns = getColumn(sudoku, i);
            System.out.println(Arrays.toString(columns));
        }
        return true;
    }

    public static void main(String [] args)
    {
        int[][] sudoku = {
                { 1, 2, 2, 4, 5, 6, 7, 8, 9 },
                { 0, 0, 0, 0, 0, 4, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 5, 0, 0 },
                { 0, 0, 3, 0, 0, 0, 0, 0, 0 },
                { 0, 7, 0, 0, 0, 0, 0, 0, 5 },
                { 0, 0, 0, 0, 0, 0, 0, 4, 0 },
                { 0, 0, 0, 0, 0, 0, 2, 0, 0 },
                { 0, 0, 0, 3, 0, 0, 0, 0, 0 },
                { 3, 0, 5, 0, 0, 0, 0, 0, 0 },
        };
        sudoku myprogram = new sudoku(sudoku);
        //System.out.println(myprogram.checkRowElement(1, 3));
        //System.out.println(myprogram.checkColumnElement(1, 3));
        //System.out.println(myprogram.validRows(sudoku[0]));
        //System.out.println(Arrays.toString(myprogram.getColumn(sudoku, 0)));
        System.out.println(myprogram.validColumns());


    }

    //////////////////////////////// sorting algorithms ///////////////////////////////////////////////////////////

    /**
     * MergeSort algorithm
     */
    private void merge(int[] arr, int l, int m, int r)
    {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];


        for (int i = 0; i < n1; ++i) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
        }

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    private void mergesort(int []arr, int l, int r)
    {
        if (l < r)
        {
            int m = l+(r-l)/2;

            mergesort(arr, l, m);
            mergesort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }
}

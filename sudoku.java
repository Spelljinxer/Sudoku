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

    public static void main(String [] args)
    {
        int[][] sudoku = {
                { 0, 1, 0, 0, 0, 0, 0, 0, 0 },
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
        System.out.println(myprogram.checkRowElement(1, 3));
        System.out.println(myprogram.checkColumnElement(1, 3));
        myprogram.clear();

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

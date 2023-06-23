package conwaygame;
/*
 * Class holding various methods of the grid for when the board is being interacted within the driver.
 */

import java.awt.Color;

public class Board extends Rectangle {
    public int rows;              // Number of rows in the board
    public int cols;              // Number of columns in the board
    public boolean[][] board;     // 2D array representing the state of each cell on the board

    public double incX;           // Increment value for the x-coordinate of each cell
    public double incY;           // Increment value for the y-coordinate of each cell
    public double lowerX;         // Lower bound of x-coordinate of the board
    public double upperX;         // Upper bound of x-coordinate of the board
    public double lowerY;         // Lower bound of y-coordinate of the board
    public double upperY;         // Upper bound of y-coordinate of the board

    public Board(int x, int y, int halfWidth, int halfHeight, int rows, int cols, boolean filled, boolean[][] board) {
        super(x, y, halfWidth, halfHeight, filled);  // Call the constructor of the parent class
        this.rows = rows;
        this.cols = cols;
        this.board = board;
        calculateBounds();  // Calculate the bounds of the board based on the provided parameters
    }

    public void calculateBounds() {
        incX = (double)2 * halfWidth / cols;  // Calculate the increment value for x-coordinates
        incY = (double)2 * halfHeight / rows;  // Calculate the increment value for y-coordinates
        lowerX = x - halfWidth + incX / 2;  // Calculate the lower bound of x-coordinate
        upperX = x + halfWidth - incX / 2;  // Calculate the upper bound of x-coordinate
        lowerY = y - halfHeight + incY / 2;  // Calculate the lower bound of y-coordinate
        upperY = y + halfHeight - incY / 2;  // Calculate the upper bound of y-coordinate
    }

    public double[] getCellCM(double pX, double pY) {
        double curCol = lowerX - incX/2;
        while (curCol < pX) {
            curCol += incX;
        }
        
        double curRow = upperY + incY/2;
        while (curRow > pY) {
            curRow -= incY;
        }

        return new double[] {curRow + incY/2, curCol - incX/2};
    }

    public void fillCell(double pX, double pY, Color c) {
        Color prev = StdDraw.getPenColor();
        StdDraw.setPenColor(c);
        StdDraw.filledRectangle(pX, pY, incX/2, incY/2);
        StdDraw.setPenColor(prev);
        drawAxes();
    }

    public void drawGrid() {
        Color c = StdDraw.getPenColor();
        StdDraw.setPenColor(StdDraw.GRAY);

        for (double cellCol = lowerX; cellCol <= upperX + incX/2; cellCol += incX) {  // + incX/2 bc floating point imprecision
            for (double cellRow = upperY; cellRow >= lowerY - incY/2; cellRow -= incY) {
                int col = (int)Math.round((cellCol - lowerX) / incX);
                int row = (int)Math.round((upperY - cellRow) / incY);  // Not upperY - cellRow bc lower row indices have higher y values

                if (board[row][col]) {
                    StdDraw.filledRectangle(cellCol, cellRow, incX/2, incY/2);
                }
            }
        }
        StdDraw.setPenColor(c);
    }

    public void drawAxes() {
        for (double horz = lowerX - incX/2; horz <= upperX + incX/2; horz += incX) {
            StdDraw.line(horz, lowerY - incY/2, horz, upperY + incY/2);
        }

        for (double vert = lowerY - incY/2; vert <= upperY + incY; vert += incY) {
            StdDraw.line(lowerX - incX/2, vert, upperX + incX/2, vert);
        }
    }

    public void draw() {
        calculateBounds();  // Recalculate the bounds before drawing
        drawGrid();  // Draw the grid
        super.draw();  // Call the draw method of the parent class to draw the rectangle
        drawAxes();  // Draw the axes
    }
}

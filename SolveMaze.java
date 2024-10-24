import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class SolveMaze {

  /**
   * Read in the file
   * @param fname the name of the file
   * @return the scanner
   */
  public static Scanner readMaze(String fname){
    Scanner file = null;
    try {
      file = new Scanner(new File(fname));
    } catch (FileNotFoundException e) {
      System.err.println("Cannot locate file.");
      System.exit(-1);  
    }
    return file;
  }

  /**
   * The method that solves maze with recursion.
   * @param currentLocation current location in type MazeLocation
   * @param maze the maze
   * @param reach indicate whether reaches the end of the maze
   * @param mazeViewer maze viewer
   * @return whether has reached the end of the maze
   */
  public static Boolean solve(MazeLocation currentLocation, Maze maze, Boolean reach, MazeViewer mazeViewer){
    // display maze
    mazeViewer = new MazeViewer(maze);
    // set the delayed time
    try { Thread.sleep(100);	} catch (InterruptedException e) {};
    // base case: if reached the end of the maze, set that as part of the PATH and return true
    if (currentLocation.equals(maze.finish)){
      maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.PATH;
      reach = true;
      return reach;
    }
    // if current location is not explorable, return to previous call
    if (!maze.checkExplorable(currentLocation.getRow(), currentLocation.getCol())){
      return reach;
    }
    // check if go south is within boundary and explorable, if so, go south
    if ((currentLocation.neighbor(MazeDirection.SOUTH).getRow() < maze.height) && (maze.checkExplorable(currentLocation.neighbor(MazeDirection.SOUTH).getRow(), currentLocation.neighbor(MazeDirection.SOUTH).getCol()))){
      if (!reach){
        maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.VISITED;
        reach = solve(currentLocation.neighbor(MazeDirection.SOUTH),maze,reach,mazeViewer);
        if (!reach){
          maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.DEAD_END;
        }
        else{
          maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.PATH;
        }
      }
    }
    // check if go north is within boundary and explorable, if so, go north
    if ((currentLocation.neighbor(MazeDirection.NORTH).getRow() >= 0) && (maze.checkExplorable(currentLocation.neighbor(MazeDirection.NORTH).getRow(), currentLocation.neighbor(MazeDirection.NORTH).getCol()))){
      if (!reach){
        maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.VISITED;
        reach = solve(currentLocation.neighbor(MazeDirection.NORTH),maze,reach,mazeViewer);
        if (!reach){
          maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.DEAD_END;
        }
        else{
          maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.PATH;
        }
      }
    }
    // check if go east is within boundary and explorable, if so, go east
    if ((currentLocation.neighbor(MazeDirection.EAST).getCol() < maze.width) && (maze.checkExplorable(currentLocation.neighbor(MazeDirection.EAST).getRow(), currentLocation.neighbor(MazeDirection.EAST).getCol()))){
      if (!reach){
        maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.VISITED;
        reach = solve(currentLocation.neighbor(MazeDirection.EAST),maze,reach,mazeViewer);
        if (!reach){
          maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.DEAD_END;
        }
        else{
          maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.PATH;
        }
      }
    }
    // check if go west is within boundary and explorable, if so, go west
    if ((currentLocation.neighbor(MazeDirection.WEST).getCol() >= 0) && (maze.checkExplorable(currentLocation.neighbor(MazeDirection.WEST).getRow(), currentLocation.neighbor(MazeDirection.WEST).getCol()))){
      if (!reach){
        maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.VISITED;
        reach = solve(currentLocation.neighbor(MazeDirection.WEST),maze,reach,mazeViewer);
        if (!reach){
          maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.DEAD_END;
        }
        else{
          maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.PATH;
        }
      }
    }
    // mark the cell as dead-end if you can't find a way from here
    else{
      if (!reach){
        maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.DEAD_END;
      }
    }
    return reach;
  }
  
  public static void main(String[] args) {
    if(args.length <= 0){
      System.err.println("Please provide the name of the maze file.");
      System.exit(-1);
    }
    Scanner file = readMaze(args[0]);
    
    Maze maze = new Maze();
    maze.initDemoMaze(file);
    MazeViewer mazeViewer = new MazeViewer(maze);
    Boolean solved = SolveMaze.solve(maze.getStart(),maze,false,mazeViewer);
    // print out a message saying whether the solution exits
    if (!solved){
      System.err.println("You can't find a solution for this maze.");
    }
    else{
      System.err.println("You can find a solution for this maze.");
    }
  }
}

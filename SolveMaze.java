import java.io.*;
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
   * Check if current location is within the bounds
   * @param currentLocation current location
   * @param maze maze
   * @return if current location is within the bounds
   */
  public static boolean checkBoundary(MazeLocation currentLocation, Maze maze){
    if ((currentLocation.getRow() >= 0) && (currentLocation.getRow() < maze.height) && (currentLocation.getCol() >= 0) && (currentLocation.getCol() < maze.width)){
      return true;
    }
    else{
      return false;
    }
  }

  /**
   * The method that solves maze with recursion.
   * @param currentLocation current location in type MazeLocation
   * @param maze the maze
   * @param reach indicate whether reaches the end of the maze
   * @param mazeViewer maze viewer
   * @return whether has reached the end of the maze
   */
  public static Boolean solve(MazeLocation currentLocation, Maze maze, Boolean reach){
    // set the delayed time
    try { Thread.sleep(5);	} catch (InterruptedException e) {};
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
    for (MazeDirection d: MazeDirection.values()){
      if ((SolveMaze.checkBoundary(currentLocation.neighbor(d), maze)) && maze.checkExplorable(currentLocation.neighbor(d).getRow(), currentLocation.neighbor(d).getCol())){
        if (!reach){
          maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.VISITED;
          reach = solve(currentLocation.neighbor(d),maze,reach);
          if (!reach){
            maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.DEAD_END;
          }
          else{
            maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.PATH;
          }
        }
      }
      else{
        if (!reach){
          maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.DEAD_END;
        }
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
    Boolean solved = SolveMaze.solve(maze.getStart(),maze,false);
    // print out a message saying whether the solution exits
    if (!solved){
      System.err.println("You can't find a solution for this maze.");
    }
    else{
      System.err.println("You can find a solution for this maze.");
    }
  }
}

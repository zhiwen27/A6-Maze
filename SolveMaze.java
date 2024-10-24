import java.io.*;
import java.util.Scanner;

class SolveMaze {

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

  public static Boolean solve(MazeLocation currentLocation, Maze maze, Boolean reach){
    if (currentLocation.equals(maze.finish)){
      maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.PATH;
      System.err.println("You've reached the end of the maze!");
      reach = true;
      return reach;
    }
    if(!maze.checkExplorable(currentLocation.getRow(), currentLocation.getCol())){
      maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.DEAD_END;
      System.err.println("This is not the correct path to reach the Finish.");
      return reach;
    }
    maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.VISITED;
    if (maze.checkExplorable(currentLocation.neighbor(MazeDirection.SOUTH).getRow(), currentLocation.neighbor(MazeDirection.SOUTH).getCol())){
      if (!reach){
        maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.PATH;
        reach = solve(currentLocation.neighbor(MazeDirection.SOUTH),maze,reach);
      }
    }
    if (maze.checkExplorable(currentLocation.neighbor(MazeDirection.NORTH).getRow(), currentLocation.neighbor(MazeDirection.NORTH).getCol())){
      if (!reach){
        maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.PATH;
        reach = solve(currentLocation.neighbor(MazeDirection.NORTH),maze,reach);
      }
    }
    if (maze.checkExplorable(currentLocation.neighbor(MazeDirection.EAST).getRow(), currentLocation.neighbor(MazeDirection.EAST).getCol())){
      if (!reach){
        maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.PATH;
        reach = solve(currentLocation.neighbor(MazeDirection.EAST),maze,reach);
      }
    }
    if (maze.checkExplorable(currentLocation.neighbor(MazeDirection.WEST).getRow(), currentLocation.neighbor(MazeDirection.WEST).getCol())){
      if (!reach){
        maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.PATH;
        reach = solve(currentLocation.neighbor(MazeDirection.WEST),maze,reach);
      }
    }
    // Animation?
    return reach;
  }
  
  public static void main(String[] args) {
    // if(args.length <= 0){
    //   System.err.println("Please provide the name of the maze file.");
    //   System.exit(-1);
    // }
    // Scanner file = readMaze(args[0]);
    
    Maze maze = new Maze();
    maze.initDemoMaze();
    SolveMaze.solve(maze.getStart(),maze,false);
    if (!SolveMaze.solve(maze.getStart(),maze,false)){
      System.err.println("You can't find a path for this maze.");
    }
    MazeViewer viewer = new MazeViewer(maze);
  }
}

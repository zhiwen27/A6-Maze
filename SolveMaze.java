import java.io.*;
import java.util.ArrayList;
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

  public static Boolean solve(MazeLocation currentLocation, Maze maze, Boolean reach, MazeViewer mazeViewer){
    mazeViewer = new MazeViewer(maze);
    try { Thread.sleep(250);	} catch (InterruptedException e) {};
    if (currentLocation.equals(maze.finish)){
      maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.PATH;
      System.err.println("You've reached the end of the maze!");
      reach = true;
      return reach;
    }
    if(!maze.checkExplorable(currentLocation.getRow(), currentLocation.getCol())){
      return reach;
    }
    //maze.mazeGrid[currentLocation.getRow()][currentLocation.getCol()] = MazeContents.VISITED;
    if (maze.checkExplorable(currentLocation.neighbor(MazeDirection.SOUTH).getRow(), currentLocation.neighbor(MazeDirection.SOUTH).getCol())){
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
    if (maze.checkExplorable(currentLocation.neighbor(MazeDirection.NORTH).getRow(), currentLocation.neighbor(MazeDirection.NORTH).getCol())){
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
    if (maze.checkExplorable(currentLocation.neighbor(MazeDirection.EAST).getRow(), currentLocation.neighbor(MazeDirection.EAST).getCol())){
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
    if (maze.checkExplorable(currentLocation.neighbor(MazeDirection.WEST).getRow(), currentLocation.neighbor(MazeDirection.WEST).getCol())){
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
    if (!solved){
      System.err.println("You can't find a path for this maze.");
    }
  }
}

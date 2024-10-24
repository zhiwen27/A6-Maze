/* This class should implement the DisplayableMaze interface */

import java.util.ArrayList;
import java.util.Scanner;

public class Maze implements DisplayableMaze{

  public int height;
  public int width;
  public MazeContents[][] mazeGrid;
  public MazeLocation start;
  public MazeLocation finish;
  
  /** This DemoMaze method will allow you to generate a simple maze
   * to test your code on as you develop it. Ultimately, you need
   * to accept maze files as command line inputs or standard input.
   * You will need to implement the DisplayableMaze interface before you
   * can run the initDemoMaze method.
   * * @author Tianah Gooden
   * * @version October 17th 2023
   */
  public void initDemoMaze(Scanner file){ //String fileName, 
    ArrayList<String> inputMaze = new ArrayList<>();
    while (file.hasNextLine()) {
      inputMaze.add(file.next());
    }
    this.height = inputMaze.size();
    int line = 0;
    this.width = inputMaze.get(0).length();
    this.mazeGrid = new MazeContents[height][width];
    for(String s: inputMaze){
      for(int i = 0; i < s.length(); i++){
        if (s.charAt(i) == '#'){
          this.mazeGrid[line][i] = MazeContents.WALL;
        }
        if ((s.charAt(i) == '.') || (s.charAt(i) == ' ')){
          this.mazeGrid[line][i] = MazeContents.OPEN;
        }
        if (s.charAt(i) == 'S'){
          this.mazeGrid[line][i] = MazeContents.OPEN;
          this.start = new MazeLocation(line,i);
        }
        if (s.charAt(i) == 'F'){
          this.mazeGrid[line][i] = MazeContents.OPEN;
          this.finish = new MazeLocation(line,i);
        }
      }
      line++;
    }

    // this.height = 10;
    // this.width = 8;
    //this.start = new MazeLocation(1,1);
    //this.finish = new MazeLocation(8,6);

    // this.mazeGrid[0][0] = MazeContents.WALL; this.mazeGrid[0][1] = MazeContents.WALL; this.mazeGrid[0][2] = MazeContents.WALL; this.mazeGrid[0][3] = MazeContents.WALL; this.mazeGrid[0][4] = MazeContents.WALL; this.mazeGrid[0][5] = MazeContents.WALL; this.mazeGrid[0][6] = MazeContents.WALL; this.mazeGrid[0][7] = MazeContents.WALL;
    // this.mazeGrid[1][0] = MazeContents.WALL; this.mazeGrid[1][1] = MazeContents.OPEN; this.mazeGrid[1][2] = MazeContents.OPEN; this.mazeGrid[1][3] = MazeContents.OPEN; this.mazeGrid[1][4] = MazeContents.OPEN; this.mazeGrid[1][5] = MazeContents.OPEN; this.mazeGrid[1][6] = MazeContents.WALL; this.mazeGrid[1][7] = MazeContents.WALL;
    // this.mazeGrid[2][0] = MazeContents.WALL; this.mazeGrid[2][1] = MazeContents.WALL; this.mazeGrid[2][2] = MazeContents.OPEN; this.mazeGrid[2][3] = MazeContents.WALL; this.mazeGrid[2][4] = MazeContents.WALL; this.mazeGrid[2][5] = MazeContents.OPEN; this.mazeGrid[2][6] = MazeContents.WALL; this.mazeGrid[2][7] = MazeContents.WALL;
    // this.mazeGrid[3][0] = MazeContents.WALL; this.mazeGrid[3][1] = MazeContents.OPEN; this.mazeGrid[3][2] = MazeContents.WALL; this.mazeGrid[3][3] = MazeContents.OPEN; this.mazeGrid[3][4] = MazeContents.OPEN; this.mazeGrid[3][5] = MazeContents.OPEN; this.mazeGrid[3][6] = MazeContents.WALL; this.mazeGrid[3][7] = MazeContents.WALL;
    // this.mazeGrid[4][0] = MazeContents.WALL; this.mazeGrid[4][1] = MazeContents.OPEN; this.mazeGrid[4][2] = MazeContents.OPEN; this.mazeGrid[4][3] = MazeContents.OPEN; this.mazeGrid[4][4] = MazeContents.WALL; this.mazeGrid[4][5] = MazeContents.WALL; this.mazeGrid[4][6] = MazeContents.OPEN; this.mazeGrid[4][7] = MazeContents.WALL;
    // this.mazeGrid[5][0] = MazeContents.WALL; this.mazeGrid[5][1] = MazeContents.OPEN; this.mazeGrid[5][2] = MazeContents.WALL; this.mazeGrid[5][3] = MazeContents.OPEN; this.mazeGrid[5][4] = MazeContents.OPEN; this.mazeGrid[5][5] = MazeContents.WALL; this.mazeGrid[5][6] = MazeContents.WALL; this.mazeGrid[5][7] = MazeContents.WALL;
    // this.mazeGrid[6][0] = MazeContents.WALL; this.mazeGrid[6][1] = MazeContents.OPEN; this.mazeGrid[6][2] = MazeContents.WALL; this.mazeGrid[6][3] = MazeContents.WALL; this.mazeGrid[6][4] = MazeContents.OPEN; this.mazeGrid[6][5] = MazeContents.OPEN; this.mazeGrid[6][6] = MazeContents.OPEN; this.mazeGrid[6][7] = MazeContents.WALL;
    // this.mazeGrid[7][0] = MazeContents.WALL; this.mazeGrid[7][1] = MazeContents.OPEN; this.mazeGrid[7][2] = MazeContents.WALL; this.mazeGrid[7][3] = MazeContents.OPEN; this.mazeGrid[7][4] = MazeContents.OPEN; this.mazeGrid[7][5] = MazeContents.WALL; this.mazeGrid[7][6] = MazeContents.OPEN; this.mazeGrid[7][7] = MazeContents.WALL;
    // this.mazeGrid[8][0] = MazeContents.WALL; this.mazeGrid[8][1] = MazeContents.OPEN; this.mazeGrid[8][2] = MazeContents.OPEN; this.mazeGrid[8][3] = MazeContents.WALL; this.mazeGrid[8][4] = MazeContents.OPEN; this.mazeGrid[8][5] = MazeContents.WALL; this.mazeGrid[8][6] = MazeContents.OPEN; this.mazeGrid[8][7] = MazeContents.WALL;
    // this.mazeGrid[9][0] = MazeContents.WALL; this.mazeGrid[9][1] = MazeContents.WALL; this.mazeGrid[9][2] = MazeContents.WALL; this.mazeGrid[9][3] = MazeContents.WALL; this.mazeGrid[9][4] = MazeContents.WALL; this.mazeGrid[9][5] = MazeContents.WALL; this.mazeGrid[9][6] = MazeContents.WALL; this.mazeGrid[9][7] = MazeContents.WALL;
  }

  /** @return height of maze grid */
  public int getHeight(){
    return this.height;
  }

  /** @return width of maze grid */
  public int getWidth(){
    return this.width;
  }

  /** @return contents of maze grid at row i, column j */
  public MazeContents getContents(int i, int j){
    return this.mazeGrid[i][j];
  }

  /** @return return True or False to indicate whether the maze grid is explorable at row i, column j */
  public Boolean checkExplorable(int i, int j){
    if (this.getContents(i, j) == MazeContents.OPEN){
      return true;
    }
    else{
      return false;
    }
  }

  /** @return location of maze start point */
  public MazeLocation getStart(){
    return this.start;
  }

  /** @return location of maze finish point */
  public MazeLocation getFinish(){
    return this.finish;
  }

  // public static void main(String[] args) {
  //   Maze test = new Maze();
  //   Scanner file = SolveMaze.readMaze("maze1");
  //   test.initDemoMaze(file);
  //   MazeViewer mazeViewer = new MazeViewer(test);
  // }
}

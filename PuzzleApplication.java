
import java.util.ArrayList;
import java.util.Scanner;

public class PuzzleApplication {

    public static Node goal;
    public static Node initialNode;
    public static int tilesMaximum = 9;
    public static HeuristicConstraints.HeurType chosenHeuristics;

    private static int[] goalPuzzle = {4,3,2,8,0,1,7,6,5};
    private static int [] initialPuzzle = {3,8,2,4,6,1,7,0,5};

    private static Scanner scanner = new Scanner(System.in);

//  Two choices such as A* misplaced tile and A* manhattan.

    public static void main (String[] args)
    {
        while(true){
            setStrategy();
            System.out.println("\n\n0:Choose another methodology\n1: Stop");
            if(scanner.nextLine().equals("1")){

                break;
            }

        }
    }

    public static void setStrategy(){
        System.out.println("Please choose a solution :\n1:A* search(Misplaced Tile)\n2:A* search(Manhattan)");
        String heur = scanner.nextLine();

        goal = new Node(goalPuzzle);
        initialNode = new Node(initialPuzzle);

        switch(Integer.parseInt(heur)){

            case 1:

                chosenHeuristics = HeuristicConstraints.HeurType.AstarTileCalculator;
                Solutions.costSearch(initialNode,chosenHeuristics);
                break;

            case 2:

                chosenHeuristics = HeuristicConstraints.HeurType.AstarManhattanCalculator;
                Solutions.costSearch(initialNode,chosenHeuristics);
                break;

        }
    }

}

class Node{

//successors : a collection of the possible state nodes.
//parentState : previous node from the current node in the diagram.
//tileList : one dimensional array to hold a set of puzzle tile for current list
// (the node's own storage for the puzzle state)
//blankTile : current blank index (current location)
//cost : cost of the node
//totalCost : mixed total cost of the paths so far
//heuristicCost : prediction of heuristic for the Node
//depth : depth of the node
//direction : which direction was selected to move

public ArrayList<Node> successors = new ArrayList<Node>();
public Node parentState;
public int [] tileList = new int [PuzzleApplication.tilesMaximum];
public int blankTile = 0;
public enum Direction {Left, Right, Up, Down}
public int cost;
public int heuristicCost;
public int totalCost;
public int depth = 0;
public String direction;

//feed the node with tiles here
//@param list that I passed in, which is used to fill tileList
//list is temproary puzzle arrangement passed in during construction
//tileList is permanent storage of that arrangement inside the Node
//And it's updated according to the current puzzle arrangement.

public Node(int[] list){
    for (int i=0; i< list.length; i++){
        this.tileList[i] = list[i];
    }
}

//Make probable move to particular direction
//MakeMove
//The method will see the puzzle borders within array
//It supposes the puzzle is quadratic (2D)
//The cost of the move is the number of the tile moved
//@param direction : Left, Right, Up, and Down
//@param state : array of tiles (current boad state)
//@param i : index of the blank tile

public void makeMove(Direction direction, int [] state, int i) {

//    clone method is for cloning object
    int[] current_state = state.clone();

    int newIndex = 0;
    boolean constraint_confirm = false;
//columns will be a square of 3x3 grid (9), so it should be 3.
    int columns = (int) Math.sqrt(PuzzleApplication.tilesMaximum);
    String nodeDirection = null;

    switch (direction) {

// i%columns refers to the current columns, which calculates the # of the tiles at index
// in the 1D array representing the puzzle.
//        Left
        case Left:
            constraint_confirm = i % columns > 0;
            newIndex = i - 1;
            nodeDirection = "Left";
            break;

//        Right
        case Right:
            constraint_confirm = i % columns < columns - 1;
            newIndex = i + 1;
            nodeDirection = "Right";
            break;

//        Up
        case Up:
            constraint_confirm = i - columns >= 0;
            newIndex = i - 3;
            nodeDirection = "Up";
            break;

//        Down
        case Down:
            constraint_confirm = i + columns < tileList.length;
            newIndex = i + 3;
            nodeDirection = "Down";

    }

    if (constraint_confirm) {
        int tempVal = current_state[newIndex];
        current_state[newIndex] = current_state[i];
        current_state[i] = tempVal;

        Node successor = new Node(current_state);
        successor.depth = depth + 1;
        successor.direction = nodeDirection;

//      The cost is the tile number
        int cost = tileList[newIndex];
        successor.cost = cost;

        successors.add(successor);
        successor.parentState = this;
    }

}

public void expand(){
        for(int i = 0; i< tileList.length; i++){

            if(tileList[i] == 0){
                blankTile = i;
            }
        }
    makeMove(Direction.Left, tileList, blankTile);
    makeMove(Direction.Right, tileList, blankTile);
    makeMove(Direction.Up, tileList, blankTile);
    makeMove(Direction.Down, tileList, blankTile);

    }


//Check if the current node is the goal node
//@param goal node
//@return boolean true if this is a goal node

public boolean isGoalNode(Node goal){

    for(int i = 0; i< tileList.length; i++){

        if(tileList[i] != goal.tileList[i]) {

                return false;
        }
    }

    return true;
}


//Change Node to readable format
//@return Node as string representation

public String toString(){

    String tiles = "";
    if(tileList.length > 0){

        for(int item:tileList){

            tiles += item + "";
        }

        return tiles;

    }

    return null;

}


}



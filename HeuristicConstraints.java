//    This is heuristic object to compute estimated cost

public class HeuristicConstraints {

    public enum HeurType {AstarManhattanCalculator, AstarTileCalculator}

    private Node node;
    private Node goal;
    private int Cost;

//    THis is a constructor to pass node paramters
//    @param node current node
//    @param goal goal node
//    @param type Heuristics type

    HeuristicConstraints(Node node, Node goal, HeurType type){

        this.node = node;
        this.goal = goal;

        switch (type){

            case AstarManhattanCalculator:

                Cost = manhattanDistance();
                break;

            case AstarTileCalculator:

                Cost = misplacedTileCounter();
                break;

            default: Cost = 0;

        }


    }
    public int getCost() {
            return Cost;
    }

//

    public int misplacedTileCounter(){
        int counter = 0;
        for (int i = 0; i < this.node.tileList.length; i += 1)
            if (this.node.tileList[i] != 0 && this.node.tileList[i] != goal.tileList[i])
                counter += 1;
        return counter;
    }

    public int manhattanDistance(){
        int distance = 0;
//        To get the correct number of columns
        int columns = (int) Math.sqrt(PuzzleApplication.tilesMaximum);
        for (int i = 0; i < this.node.tileList.length; i++) {
            int tileNumber = this.node.tileList[i];
            // Skip the blank tile
            if (tileNumber == 0) continue;

            // Search for the index of the tile in the goal state
            int goalIndex = -1; // To show goalIndex has not been found yet
            for (int j = 0; j < goal.tileList.length; j++) {
                if (goal.tileList[j] == tileNumber) {
                    goalIndex = j;
                    break;
                }
            }

            // Calculate the current row and column
//            i (FirstRw(0,1,2), SecondRw(3,4,5), ThirdRw(6,7,8)) / columns (3)
//            i (FirstCl(0,3,6), SecondCl(1,4,7), ThirdCl(2,5,8)) % columns (3)
            int currentRow = i / columns;
            int currentCol = i % columns;

            // Calculate the goal row and column
            int goalRow = goalIndex / columns;
            int goalCol = goalIndex % columns;

            // Add the Manhattan distance to the total
            // Calculate the gap between currentRow/Col and goalRow/Col
            distance += Math.abs(currentRow - goalRow) + Math.abs(currentCol - goalCol);
        }
        return distance;
    }


}

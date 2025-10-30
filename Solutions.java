//Class for possible search strategies

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Solutions {

//    Cost function Strategy
//    This will be used for A* solutions
//    @param root Node
//    @param heurType is Heuristics type

public static void costSearch(Node root, HeuristicConstraints.HeurType heurType){

    int time = 0;
    Set<String> explored = new HashSet<>(); // close list
    PriorityQueue<Node> minHeap = new PriorityQueue<>(10, new NodeComparator()); // open list
    Node currentNode = root;
    long startTime = System.currentTimeMillis();
    int maxQueueSize = 0;
    int nodesDequeued = 0;

    while (currentNode != null && !currentNode.isGoalNode(PuzzleApplication.goal)) {

        explored.add(currentNode.toString());
        currentNode.expand();

        for (Node successor : currentNode.successors) {
            if (explored.contains(successor.toString())) continue;
            explored.add(successor.toString());

            successor.heuristicCost = new HeuristicConstraints(successor, PuzzleApplication.goal, heurType).getCost();

            successor.totalCost = successor.cost  + + currentNode.totalCost + successor.heuristicCost;


            minHeap.add(successor);

            if (minHeap.size() > maxQueueSize) {
                maxQueueSize = minHeap.size();
            }
        }

        currentNode = minHeap.poll();
        nodesDequeued++;
        time++;
    }

    if (currentNode == null) {
        System.out.println("No solution found.");
        return;
    }

    long stopTime = System.currentTimeMillis();
    long passedTime = stopTime - startTime;
    PrintResults(currentNode, time, "A*Search", passedTime, maxQueueSize, nodesDequeued);
}




// Min Heap Comparator

public static class NodeComparator implements Comparator<Node>
{
    public int compare(Node x, Node y){
        return x.totalCost - y.totalCost;
    }
}

// Reverse node to print from the initial state
//@param root is current Node
//@return will be reversed node

public static Node reverseNode(Node root){

    Node previous = null;
    Node current = root;
    Node next = null;

    while (current != null) {
        next = current.parentState;
        current.parentState = previous;
        previous = current;
        current = next;
    }

    root = previous;
    return root;

}

// Print all calculation for chosen strategies
// @param node returned current Node
// @param iterations is the number of iterations
// @param strategy chosen strategy name
// @param timeMS time in millis
// @param maxQueueSize Space is the size of queue of its maximum
// @param nodesDequeued Time is the number of nodes disappeared from the queue

public static void PrintResults(Node node, int iterations, String strategy, long timeMS, int maxQueueSize, int nodesDeququed){

    Node node_ = reverseNode(node);
    int depth = -1;

    while(node_ !=null){
        depth ++;
        System.out.println("Direction :" + node_.direction + " move , node depth : "+ depth + ", node cost :" + node_.cost
        + ", total cost: " + node_.totalCost + ", heuristic estimation : " + (node_.heuristicCost));


        node_ = node_.parentState;

    }



//    To assign correct value from second node to the node before the goal state
    int heuristicShow;
    if (depth >= 2 && depth <= 4) {

    }


    System.out.println("Overall statistics for " + strategy);
    System.out.println("Iterations : " + iterations);
    System.out.println("Time take in ms : " + timeMS);
    System.out.println("Length " + node.depth);
    System.out.println("Cost " + node.totalCost);
    System.out.println("Time " + nodesDeququed);
    System.out.println("Space " + maxQueueSize);

}

}


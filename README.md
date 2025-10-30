

## Table of the development environment

<!-- シールド一覧 -->
<!-- 該当するプロジェクトの中から任意のものを選ぶ-->
<p style="display: inline">
  
  <!-- バックエンドの言語一覧 -->
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white">
  <!-- バックエンドのフレームワーク一覧 -->
</p>

## Table of contents

1. [Project](#Project)
2. [Algorithms](#Algorithms)
3. [Explanation](#Explanation)
4. [Analysis](#Analysis)
5. [Results](#Results)
6. [Observations](#Observations)

<!-- READMEの作成方法のドキュメントのリンク -->
<!-- <br />
<div align="right">
    <a href="READMEの作成方法のリンク"><strong>READMEの作成方法 »</strong></a>
</div>
<br />
<!-- Dockerfileのドキュメントのリンク -->
<!-- <div align="right">
    <a href="Dockerfileの詳細リンク"><strong>Dockerfileの詳細 »</strong></a>
</div>
<br /> 
<!-- プロジェクト名を記載 -->

## 8 Puzzle Problem Solver

<!-- プロジェクトについて -->

## Project

<p>
 This project focuses on A* search algorithm, which is enforced with two heuristics such as misplaced tiles and Manhattan distance. <br/>
The efficiency of the the approach is evaluated with an analysis and visualization of the program’s performance such as iterations, memory usage,　solution cost, and time and space complexities. <br/>
<img width="484" height="614" alt="image" src="https://github.com/user-attachments/assets/0f466541-8a68-48ea-adad-fb8dee3e6f10" /> <br/>
<img width="495" height="580" alt="image" src="https://github.com/user-attachments/assets/2c01f1b2-9c72-4987-a877-1f4d7563ab1b" /> <br/>
<img width="509" height="430" alt="image" src="https://github.com/user-attachments/assets/a499f4af-6b66-43de-98d6-26b315446c2c" />

## Algorithm

Algorithm steps are composed of the following 7 steps: <br/>
1.	Initialisation: Put the initial state in the open list (g = 0). <br/>
2.	Node choosing: Choose the nodes with the lowest f(n) value from the open list. <br/>
3.	Goal Checking: if the chosen node is the goal, recompose the path and finish the procedure. <br/>
4.	Exploration: Create all probable slides of the empty tile. <br/>
5.	Cost Computation: For each successor, calculate g(n) and h(n). <br/>
6.	Refreshing List : Add new nodes to the open list if not explored, or if a cheper way is found. <br/>
7.	Continue the procedures until reaching the goal or the open list is empty. <br/>

## Explanation

 My code is made of Java language and there are 3 files. <br/>
Each java file has each role such as PuzzleApplication for main method, Solutions for implementing A search*, and HeuristicConstraints for offering heuristics for A*. <br/>
In this project, an initial and goal state are defined as figure 1.<br/>
  
 <img width="451" height="56" alt="image" src="https://github.com/user-attachments/assets/7ec50759-10eb-4cf5-8c61-01d5a9ec52e0" /><br/>
Figure 1: intial state and goal state.<br/>
 
The process of finding the solution for 8 puzzle problem is shown with program code step by step as follows<br/>
1.	User runs program: <br/>
Command: java PuzzleApplication<br/>
↓<br/>
PuzzleApplication.main(String[] args)  // Initial point <br/>
-	Begins while loop <br/>
-	Calls setStrategy() method <br/>

2.	Menu to choose heuristic: <br/>
PuzzleApplication.setStrategy() method <br/>
-	Show users choics: <br/>
1: A* search (Misplaced tile) or 2: A* search (Manhattan) <br/>
-	User choose 1 or 2 <br/>

<img width="235" height="70" alt="image" src="https://github.com/user-attachments/assets/645fee5f-b8a2-4989-ab15-8b53ed5c303c" /> <br/>
Figure 2: The choice of heuristic types <br/>

↓<br/>
Starts:<br/>
// PuzzleApplication.java // define initial state and goal state.<br/>
goal = new Node(goalPuzzle) <br/>
initialNode = new Node (initialPuzzle)<br/>
↓<br/>
Relying on user selection:<br/>
// Both methods are in HeuristicConstraints<br/>
-	1 -> chooseHeuristics = AstarTileCalculator <br/>
-	2 -> chooseHeuristics = ManhattanCalculator <br/>
↓ <br/>
Calls: <br/>
Solutions.costSearch(initialNode, chosenHeuristics) // defined in Solutions.java <br/>

3.	A* Search Execution: <br/>
Solution.costSearch(Node root, HeurType heurType) <br/>
-	Starts: <br/>
openList = PriorityQueue<Node> // Nodes ordered by f = g+h <br/>
explored = Hashset<String> // closeList<br/>
root.heuristicCost = HeuristicConstraints.getCost()<br/>
root.totalCost = root.heuristicCost<br/>
openList.add(root)<br/>
-	Iteration loop:<br/>
while (openList not empty):<br/>
current = openlist.poll() // p/u lowest f node<br/>
if current.isGoalNode(goal): <br/>
printSolution(current) // goal state <br/>
return <br/>
explored.add(current.toString()) <br/>
current.expand() // defined in Node.java <br/>
-	Generates successors (makeMove) <br/>
-	Each successor.parentState = current <br/>
-	Successor.cost = tile moved <br/>
For each successor: <br/>
successor.heuristicCost = HeuristicConstraints.getCost() <br/>
successor.totalCost = current.totalCost + successor.cost + heuristicCost <br/>
openList.add(successor) <br/>

4.	Heuristic computation: <br/>
HeuristicConstraints (Node node, Node goal, HeurType type) // Constructor <br/>
-	If user inputs =1 -> misplacedTileCounter() <br/>
-	If user inputs =2 -> manhattanDistance() <br/>
-	Return h(n) to Solutions.costSearch <br/>

5.	Node expansion (successors) <br/>
Node.expand() <br/>
-	Searches for blank tile index <br/>
-	Calls makemove(Direction, state, blankIndex) <br/>
-	Confirm bounds <br/>
-	Swaps tiles <br/>
-	Generates successor Node <br/>
-	Adds to successors list <br/>

<img width="427" height="79" alt="image" src="https://github.com/user-attachments/assets/51af8ab3-f3c8-4520-8180-c91071707e7c" /> <br/>
Figure 3: Direction,node depth & cost, total cost, and heuristic estimation(misplaced tile) <br/>

6.	Solution arrived <br/>
Solutions.PrintResults() <br/>
-	Backtrack using parentState <br/>
-	Shows each Node: <br/>
-	Overall statistics <br/>
-	Iterations <br/>
-	Time it took (in ms) <br/>
-	Length <br/>
-	Total cost <br/>
-	Number of nodes dequeued <br/>
-	Space <br/>

<img width="413" height="102" alt="image" src="https://github.com/user-attachments/assets/561ed948-f743-4ccb-9867-d5e0d9baf638" /> <br/>
Figure 4: The results of A* search (misplaced tile) <br/>

7.	Go back to menu <br/>
PuzzleApplication.main() <br/>
-	Asks user: 0 or 1 <br/>
-	Loop or finish program <br/>

<img width="229" height="45" alt="image" src="https://github.com/user-attachments/assets/8b148d00-b0ad-4ffc-8d22-cfebfa78fd8a" /> <br/>
Figure 5: Choices of next steps for user. <br/>

## Analysis

This section clarifies time complexity and space complexity. <br/>
Let b is branching factor (max 4 for the 8-puzzle), while d is the depth of the optimal solution. <br/>

Time Complexity: <br/>
For uninformed search such as BFS, the worst case is O(bd)=O(4d). On the other hand, the complexity of A* relies on the heuristic’s precision. <br/>
The best case is O(1), which means A* is complete instantly if the initial state is the goal state. <br/>
Worst case is O(bd)=O(4d), which is same value as BFS if the heuristic is poor or all paths have to be explored. <br/>

Space Complexity: <br/>
A* requires to save all explored nodes in memory (e.g., open + closed lists), which leads to O(bd) as a worst-case space usage. <br/>

Trade-offs between Misplaced Tiles and Manhattan Distance: <br/>
Misplaced tiles enables quicker heuristic computation per node but more node exploration, which means higher total runtime. <br/>
On the other hand, Manhattan distance is a bit slower heuristic to compute fewer total nodes, which lead to better entire efficiency. <br/>

## Results

This section illustrates how A* search works in 8-puzzle problem by both heuristics (Misplaced tiles and Manhattan distance) with diagram and table. <br/>
In figure 5 and 6, g(n) is used to stand for the node depth; however, in my program implementation and tables 1 and 2,  <br/>
total cost will be calculated by f(n) = g(n) + h(n), where g(n) refers to the actual accumulated path cost. <br/>

Misplaced Tiles: <br/>
Figure 5 shows the procedure of empty panel’s movement with Misplaced Tiles heuristic. And the below steps are summarized in table 1.  <br/>
A* search chooses the node with the lowest f(n) = g(n) + h(n), where g(n) is the node depth and h(n) = the number of misplaced tiles. <br/>
In result, misplaced tile heuristic explored more nodes because it does not see how far misplaced tiles are.<br/>

 <img width="621" height="591" alt="image" src="https://github.com/user-attachments/assets/4f3e638b-f01c-4304-8674-dfa2bfdb64d8" /> <br/>
Figure 5. The procedure until the goal state (Misplaced Panel) <br/>


 <img width="451" height="110" alt="image" src="https://github.com/user-attachments/assets/4a9a891e-f291-43cd-aaca-3d488174fa2e" /> <br/>
Table 1. The result of Misplaced Panel <br/>


Manhattan distance: <br/>
Next, the steps of empty panel’s movement with Manhattan distance were described with figure 6 and table 2. <br/>
The movement is almost the same as misplaced tiles; however, there are significant difference in Node 3. <br/>
Fewer unnecessary nodes are expanded because this heuristic counts the vertical and horizontal distances of each tile from its goal position, <br/>
so h=5 in the both sides of nodes enabling make it easier to move on to node 3 without accounting for 2 additional nodes (See red rectangle shown in Figure 6).<br/>
 
<img width="577" height="595" alt="image" src="https://github.com/user-attachments/assets/388b2f9d-3d3a-45bf-b0f8-ad1e17971206" /> <br/>
Figure 6. The procedure until the goal state (Manhattan distance) <br/>

 <img width="451" height="112" alt="image" src="https://github.com/user-attachments/assets/e2b44154-793b-4629-a69e-9be81f7ad23c" /> <br/>
Table 2. The result of Manhattan Distance <br/>

## Observations

Table 3 shows the results of my program implementation with key concepts. <br/>

 <img width="451" height="54" alt="image" src="https://github.com/user-attachments/assets/d3da6762-ef97-4a1f-acb6-ea988ec955a0" /> <br/>
Table 3. Comparison between both heuristic approach <br/>

Key concepts: <br/>

Iterations: Number of nodes explored before reaching the goal (how many times A* search explored a node). <br/>
Time (ms): Running time for the search procedure. <br/>
Path Length: Number of moves to get to the goal state. <br/>
Cost: The collected cost of the selected path (Sum of moved tile values). <br/>
Used Space: Maximum number of nodes saved in memory thoroughout the search. <br/>

The following key findings are observed from table 3: <br/>

1. Iteration: As shown in section 5, the worst-case time complexity of A* is O(bd).<br/>
2. On average, branching factor of both heuristics is ~2.67 for 8-puzzle calculated by the following steps: <br/>

Correct moves for each empty position: <br/>
Corners: 2 probable moves <br/>
Edges: (without corners): 3 probable moves <br/>
Center: 4 possible moves <br/>

Count each positions such as corner positions = 4 tiles, edge positions = 4 tiles, and center = 1 tile. <br/>
And then, they multiply by correct moves, and the result is as follows: <br/>

b = ((4 × 2) + (4 × 3) + (1 × 4))/9 = 24/9 ≈ 2.67 <br/>

So, the average branhing factor b ≈ 2.67, and d = 5 (from path length). <br/>
In practice, the Manhattan heuristic decreased the number of explored nodes (iterations) compared to Misplaced Tiles (22 against 25), <br/>

2. Time efficiency: The execution time by Manhattan Distance (1ms) was remarkably lower than Misplaced tiles (9ms), <br/>
which shows that a more informative heuristic can decrease execution time. <br/>

3. Path length and Cost: Both heuristics created the same path length (5 moves), with a slightly different total past cost <br/>
(38 for misplaced tile and 39 for Manhattan Distance), which describes that both heuristics reached optimal solution in terms of moves; <br/>
however, Manhattan Distance moderately larger tile movements because of distance computations. <br/>

4. Memory Usage:  Space complexity is O(bd) as shown in section 5. <br/>
Maximum nodes saved in memory were lower for Manhattan Distance (17 nodes) compared to misplaced tile (20 nodes), which proves that the heuristic can decrease unnecessary node explorations and enhance space efficiency. 


</p>


<p align="right">(<a href="#top">Back to Top</a>)</p>

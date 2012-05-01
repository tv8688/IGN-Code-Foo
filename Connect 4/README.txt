Execute:
You need to install either JDK or JRE version 1.5 or above. To run the program, just click on the ConnectFourRun.jar file. Alternatively, you an execute it in the command prompt (or terminal) by typing:
java ConnectFourGUI
or
java -jar ConnectFourRun.jar

Algorithm: To implement the AI, I used the MiniMax algorithm, a common search algorithm for adversarial search. Although the game is solved and it is possible to implement a sure win strategy, I feel that Minimax is more general and can be applied for other games as well. 
Since MiniMax has to reach the terminal state to calculate the score, it is not feasible in this case, so I modified it with a cut off depth. Thus, it will terminate when it reachs the cut-off depth and returns a heuristic value instead. 
For the heuristic value, I am not sure how well it is because I have never heard of this game before doing this. The heuristic I used is very simple and just counts the number of checkers of the same color in a given move.
The cut-off depth is currently set to 5. Higher depth will probably result in better move but slower performance.
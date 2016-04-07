# HusAI
Artificial Intelligence Project: Hus
Hus is one of the oldest game in human entertainment history. Its rules are quite simple. In general, players need to strategically place and pick up seeds from different pits to capture their opponents’ seeds (detailed rules can be found here: http://mancala.wikia.com/wiki/Hus). The goal of this project is to design an artificial intelligence for this game.

Technical Approach and Discussion
Minimax
	Considering Hus is a two-player game, a minimax algorithm was implemented. Minimax is a widely used decision making algorithm. It can minimize the possible loss for a worst case scenario through studying future possible moves that can be made by the AI and its opponent. In the case of Hus, we want to execute moves that lead to capturing maximum amount of seeds from the opponent. Therefore, we set the evaluation score to be the amount of seeds our AI has after executing a certain move. The higher the score, the more promising the move is. 
Branching Factor Reduction
Alpha-beta pruning was applied on top of minimax to reduce its branching factor. It allows our AI to completely stop deepening on a move when one of its possible future scenario is worse than a previous evaluated move. Alpha-beta pruning can highly reduce branching factors, yet still returns the optimal move.



Figure One. Hus Board
On a Hus Board labeled as above, moves starting from pits with lower indexes are less likely to end in pits on the inner row, so they rarely capture seeds from the opponent. Intuitively, it is not necessary to search on these moves deeply. By reducing the searching depth for these moves, we can search deeper on other moves that are more likely to capture seeds. To implement such a heuristic, moves that start from pits with an index smaller than 5 were set with a searching depth of 0 in the early stage of a game (before the 5th move). As a result, the depth for all other moves can be increased to 4. At a later stage (after the forth move), there are far less tolerance for errors, so we need to deepen even more on moves that are highly likely to be chosen by our AI. The 2nd to the 8th legal moves (i.e. moves with relatively larger indexes) are certainly more promising, because their end pits are most likely to be on or near the inner row, where capturing new seeds is much more likely to happen. Therefore, their searching depth was given a high value of 7, and all other moves are given a depth of 1.
By implementing this heuristic, we managed to search valuable moves deeper, but at the same time we had to drastically decrease the searching depth for other moves, which potentially put us at risk, especially when the optimal move does not start on or near the inner row. In addition, these heuristics only provide us strategies for offense but not defense. 
Other Heuristics
	Two other heuristics were initially implemented, but the AI did not show any improvements, so they are removed.
Firstly, seeds in an outer-row pits are safe from capture when their correspondent inner pits are empty. Therefore, we assumed that moves, which lead to this configuration, should be favored, especially when the protected outer pits have large amount of seeds. The following formula was initially implemented to increase the evaluation score for each protected outer pits with more than 4 seeds.

Secondly, at the beginning of each game, there are often clusters of large amount of seeds in the inner row, and these seeds are quite vulnerable for quick capturing by opponents (Seftel, S. M.). In order to reduce the risk, we implemented another evaluation function to decrease a move’s score, if it can lead to clustering of seeds at the early stage of the game. 
The above two heuristics protect us from losing seeds, but they also sometimes prevent our AI from picking moves that lead to maximum capture. These two heuristics facilitate defense rather than offense. That is potentially the reason why they did not improve our AI, and sometimes even hinder its performance.
Summary and Future Directions
In summary, this Artificial Intelligence is very greedy. It only strategizes for maximum gain, but not minimum loss. We should keep exploring different defense strategies to further improve this agent.
Our AI performs really well when moves start from pits with higher indexes are optimal, and in most of the case they are the optimal moves. However, it puts us at high risk if the optimal moves start from pits with lower indexes.
This AI can be improved by implementing Monte Carlo Tree Search, which allows it to further deepen its searching on promising moves. In addition, machine learning algorithm should also be implemented. This can be done by constructing a neural network, and train it through playing large amount of games against other agents or reviewing professional games. 


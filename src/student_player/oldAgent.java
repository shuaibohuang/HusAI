package student_player;

import hus.HusBoardState;
import hus.HusPlayer;
import hus.HusMove;

import java.util.ArrayList;

import student_player.mytools.oldTools;

/** A Hus player submitted by a student. */
public class oldAgent extends HusPlayer {
	
    /** You must modify this constructor to return your student number.
     * This is important, because this is what the code that runs the
     * competition uses to associate you with your agent.
     * The constructor should do nothing else. */
    public oldAgent() { super("old"); }

    /** This is the primary method that you need to implement.
     * The ``board_state`` object contains the current state of the game,
     * which your agent can use to make decisions. See the class hus.RandomHusPlayer
     * for another example agent. */
    static int counter = 0;
    public HusMove chooseMove(HusBoardState board_state)
    {
    	counter++;
      
        return oldTools.miniMax(8, board_state, player_id, counter);
    }
}

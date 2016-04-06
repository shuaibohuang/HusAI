package student_player.mytools;

import java.util.*;

import hus.HusBoardState;
import hus.HusPlayer;
import hus.HusMove;

public class oldTools {
	
    public static double getSomething(){
        return Math.random();
    }
    
    public static int eval(int me, HusBoardState board_state){
    	int[][] pits = board_state.getPits();
        int[] op_pits = pits[me];
        int score = 0;
    	for (int i=0; i<op_pits.length; i++){
    		score = score + op_pits[i];
    	}
    	return score;
    }
    
    public static HusMove miniMax(int depth, HusBoardState board_state, int me, int counter){
    	
    	ArrayList<HusMove> moves = board_state.getLegalMoves();
    	Collections.reverse(moves);
    	int bestScore = -10;
    	HusMove bestMiniMaxMove = null;
    	//reverse the order, so we can access move for the inner pits first
    	//Collections.reverse(moves);
    	
    	int alpha = -100;
    	int beta = 100;
    	for (int i=0; i<moves.size(); i++){
    			HusBoardState cloned_board_state = (HusBoardState) board_state.clone();
    			cloned_board_state.move(moves.get(i));
    			int minValue=0;
    			if (counter >= 4 && moves.get(i).getPit()<26 && moves.get(i).getPit()>19&&i<=4&&i>=2){
    				minValue = minValue(depth-1, me, cloned_board_state, alpha, beta);
    			}
    			
    			else{
    				minValue = minValue(depth/4-1, me, cloned_board_state, alpha, beta);
    			}
            	if(bestScore < minValue){
            		bestMiniMaxMove = moves.get(i);
            		bestScore = minValue;
            	}
            }
    	return bestMiniMaxMove;
    }
    
    public static int maxValue(int depth, int me, HusBoardState board_state,int alpha, int beta){
    	
    	if (depth == 0 || board_state.gameOver()){
    		return eval(me, board_state);
    	}
    	
    	ArrayList<HusMove> moves = board_state.getLegalMoves();
    	
        for (HusMove move: moves){
        	HusBoardState cloned_board_state = (HusBoardState) board_state.clone();
        	cloned_board_state.move(move);
        	int minValue = minValue(depth-1, me, cloned_board_state, alpha, beta);
        	if (alpha< minValue){
        		alpha = minValue;
        	}
        	if (alpha>=beta) return beta;
        }
    	return alpha;
    }
    
    public static int minValue(int depth, int me, HusBoardState board_state, int alpha, int beta){
    	if (depth == 0 || board_state.gameOver()){
    		return eval(me, board_state);
    	}
    	ArrayList<HusMove> moves = board_state.getLegalMoves();
    	
        for (HusMove move: moves){
        	HusBoardState cloned_board_state = (HusBoardState) board_state.clone();
        	cloned_board_state.move(move);
        	int maxValue = maxValue(depth-1, me, cloned_board_state, alpha, beta);
        	if (beta > maxValue){
        		beta = maxValue;
        	}
        	if (alpha>=beta) return alpha;
        }
    	return beta;
    }
    
}

package student_player.mytools;

import java.util.*;

import hus.HusBoardState;
import hus.HusPlayer;
import hus.HusMove;

public class MyTools {
	
    public static double getSomething(){
        return Math.random();
    }
    
    public static double eval(int me, HusBoardState board_state, int counter){
    	int[][] pits = board_state.getPits();
        int[] op_pits = pits[me];
        double score = 0;

    	for (int i=0; i<op_pits.length; i++){
    		score = score + op_pits[i];
    	}
    	return score;
    }
    
    public static HusMove miniMax(int depth, HusBoardState board_state, int me, int counter){
    	long start = System.nanoTime();
    	ArrayList<HusMove> moves = board_state.getLegalMoves();
    	Collections.reverse(moves);
    	double bestScore = -10;
    	HusMove bestMiniMaxMove = null;
    	//reverse the order, so we can access move for the inner pits first
    	//Collections.reverse(moves);
    	double alpha = -100;
    	double beta = 100;
    	for (int i=0; i<moves.size(); i++){
    			HusBoardState cloned_board_state = (HusBoardState) board_state.clone();
    			cloned_board_state.move(moves.get(i));
    			double minValue=0;
    			if (counter >4){
    					if (i<=8&&i>=2)
    					{
    						minValue = minValue(depth, me, cloned_board_state, alpha, beta, counter, start);
    					}
    					else if (moves.get(i).getPit()<10){
    						minValue = minValue(1, me, cloned_board_state, alpha, beta, counter, start);
    					}
    					else{
    							minValue = minValue(depth/4, me, cloned_board_state, alpha, beta, counter, start);
    						}
    					}
    			else{
    				if (moves.get(i).getPit()<5){
    					minValue = minValue(0, me, cloned_board_state, alpha, beta, counter, start);
    				}
    				else{
    					minValue = minValue(depth/4+3, me, cloned_board_state, alpha, beta, counter, start);
    				}
    			}
            	if(bestScore < minValue){
            		bestMiniMaxMove = moves.get(i);
            		bestScore = minValue;
            	}
            	if (System.nanoTime()-start>=1.99e9){
					break;
				}
            }
    	return bestMiniMaxMove;
    }
    
    public static double maxValue(int depth, int me, HusBoardState board_state,double alpha, double beta, int counter, long start){
    	
    	if (depth == 0 || board_state.gameOver()){
    		return eval(me, board_state, counter);
    	}
    	
    	ArrayList<HusMove> moves = board_state.getLegalMoves();
    	
        for (HusMove move: moves){
        	HusBoardState cloned_board_state = (HusBoardState) board_state.clone();
        	cloned_board_state.move(move);
        	double minValue = minValue(depth-1, me, cloned_board_state, alpha, beta, counter, start);
        	if (alpha< minValue){
        		alpha = minValue;
        	}
        	if (alpha>=beta) return beta;
        	if (System.nanoTime()-start>=1.99e9){
				break;
			}
        }
    	return alpha;
    }
    
    public static double minValue(int depth, int me, HusBoardState board_state, double alpha, double beta, int counter, long start){
    	if (depth == 0 || board_state.gameOver()){
    		return eval(me, board_state, counter);
    	}
    	ArrayList<HusMove> moves = board_state.getLegalMoves();
    	
        for (HusMove move: moves){
        	HusBoardState cloned_board_state = (HusBoardState) board_state.clone();
        	cloned_board_state.move(move);
        	double maxValue = maxValue(depth-1, me, cloned_board_state, alpha, beta, counter, start);
        	if (beta > maxValue){
        		beta = maxValue;
        	}
        	if (alpha>=beta) return alpha;
        	if (System.nanoTime()-start>=1.99e9){
				break;
			}
        }
    	return beta;
    }
    
}

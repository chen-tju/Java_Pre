package exam1;
import java.util.*;
public class Player {
	private final String playerId;
	private final String playerName;
	private int order;
	private float scores[];
	public String getPlayerId(){
		return playerId;
	}
	public String getPlayerName(){
		return playerName;
	}
	public int getOrder(){
		return order;
	}
	public void setOrder(int order){
		this.order = order;
	}
	public Player(String playerId,String playerName){
		this.playerId = playerId;
		this.playerName = playerName;
	}
	public void inputScore(int judgeNum){
		this.scores = new float[judgeNum];
		Scanner in = new Scanner(System.in);
		System.out.println("输入 " + playerId + "号选手的得分:");
		for(int i = 0;i<judgeNum;i++){
			scores[i] = in.nextFloat();
		}
	}
	public float getScore(){
		float totalScore = 0f;
		int mini = 0;
		int maxi = 0;
		for(int i = 1;i<scores.length;i++){
			totalScore +=scores[i];
			if(scores[i]>scores[maxi]){
				maxi = i;
			}else if (scores[i]<scores[mini]){
				mini = i;
			}
		}
		totalScore = (totalScore - scores[maxi] - scores[mini])/(scores.length - 2);
		return totalScore;
	}
	@Override
	public String toString(){
		return String.format("%1$-10s %2$-15s %3$-10.2f",playerId,playerName,getScore());
	}

}

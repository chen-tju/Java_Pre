package exam1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class CMSystem {
	private List<Player>playerList;
	public CMSystem(){
		this.playerList = new ArrayList<Player>();
	}
	public void addPlayer(Player player){
		playerList.add(player);
	}
	public void deletePlayer(int index){
		playerList.remove(index);
	}
	public void sortByScore(){
		Collections.sort(playerList,new Comparator<Object>(){
			@Override
			public int compare(Object o1,Object o2){
				Player p1 = (Player) o1;
				Player p2 = (Player) o2;
				if(p1.getScore()<=p2.getScore()){
					return 1;
				}else{
					return -1;
				}
			}
		});
	}
	private void sortByOrder(){
		Collections.sort(playerList,new Comparator<Object>(){
			@Override
			public int compare(Object o1,Object o2){
				Player p1 = (Player) o1;
				Player p2 = (Player) o2;
				if (p1.getOrder()<p2.getOrder()){
					return 1;
				}else{
					return -1;
				}
			}
		});
	}
	public void inputScore(int judgeNum){
		sortByOrder();
		for(Player p:playerList){
			p.inputScore(judgeNum);
		}
	}
	public void showAllPlayerInfo(){
		for(Player p:playerList){
			System.out.println(p);
		}
	}

}

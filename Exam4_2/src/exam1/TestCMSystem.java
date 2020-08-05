package exam1;

public class TestCMSystem {
	public static void main(String[] args){
		CMSystem cms = new CMSystem();
		Player p1 = new Player("001","zhang");
		Player p2 = new Player("002","wang");
		Player p3 = new Player("003","yang");
		cms.addPlayer(p1);
		cms.addPlayer(p2);
		cms.addPlayer(p3);
		p1.setOrder(2);
		p3.setOrder(1);
		p2.setOrder(3);
		cms.inputScore(5);
		cms.sortByScore();
		cms.showAllPlayerInfo();
	}

}

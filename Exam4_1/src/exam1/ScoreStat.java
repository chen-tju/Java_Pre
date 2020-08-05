package exam1;

import java.util.Scanner;

class ScoreStat {
	private float scores[];
	public ScoreStat(int n){
		scores = new float[n];
	}
	public void input(){
		for(int i=0;i<scores.length;i++){
			Scanner io = new Scanner(System.in);
			scores[i] = io.nextFloat();
		}
	}
	public void output(){
		for(int i=0;i<scores.length;i++){
			System.out.print(scores[i]);
			if(i<scores.length - 1){
				System.out.print(",");
			}else{
				System.out.println(",");
			}
		}
	}
	public void sort(){
		float t;
		for(int i=0;i<scores.length - 1;i++){
			int h = i;
			for(int j = i+1;j<scores.length - 1;j++){
				if(scores[h]<scores[j]){
					h = j;
				}
			}
			if(h!=i){
				t = scores[h];scores[h]=scores[i];scores[i]=t;
			}
		}
	}
	public void stat(){
		int s[] = new int[11];
		for(int i=0;i<scores.length;i++){
			int j = (int)(scores[i]/10);
			s[j]++;
		}
		for(int i = 0;i<11;i++){
			System.out.printf("%3s~%3s:%d\n",i*10,(i+1)*10-1,s[i]);
		}
	}

}

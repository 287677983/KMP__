

public class KMP {
	static int[] next = null;
	static int[] f = null;
	static char[] model = null;
	static String target;
	
	KMP(String target, String model){
		int size = model.length();
		this.model = new char[size + 1];
		this.next = new int[size + 1];
		this.f = new int[size + 1];
		for(int i = 0; i < size; i++)
			this.model[i+1] = model.charAt(i);
		this.target = target;
	}
	
/*p[1...(k-1)] = p[j-(k-1)....j-1]  f[j]取k最大值 k=1,2,..,j-1
 * p[j] != p[f[j]]    next[j] = f[j]
	p[j] = p[f[j]      next[j] =next[f[j]]*/
	public void next(){
		int size = model.length;
		int k = 0;
		int j =0;
		int m = 1;
		for(j = 1; j < size; j++){
			if(j == 1){
				f[j] = 0;
				next[j] = 0;
			}else if(j == 2){
				f[j] = 1;
				next[j] = 1;
			}else{
					for(k = 2; k < j; k++){
						for(int i = 1; i <= k-1; i++){
							if(model[i] != model[j-k+i]){
								break;
							}
							if(i == k - 1)
								m = k;
						}
					}
				f[j] = m;
				m = 1;
				if(model[j] != model[f[j]]){
					next[j] = f[j];
				}else{
					next[j] = next[f[j]];
				}
			}
				
		}
		for(int i = 1; i < 4; i++){
			System.out.print(next[i] + ",");
		}
		System.out.println("\n");
		for(int i = 1; i < 4; i++){
			System.out.print(f[i] + ",");
		}
	}
	
	public void query(){
		int target_size = this.target.length();
		int model_size = this.model.length;
		boolean flag = false;
		int j = 1;
		for(int i = 0 ; i < target_size ; i++ ){
			if(j < model_size){
				if(this.target.charAt(i) == this.model[j]){
					j++;
					if(j  == model_size){
						System.out.println("ok");
						flag = true;
						j = 1;
					}
				}else{
					i = i + next[j];
					j = 1;
				}
			}
		}
		if(!flag){
			System.out.println("erro");
		}
	}
			
	public static void main(String[] args) {
//		String model = "abcabcacab";
//		String target = "babcbabcabcaabcabcabcacabc";
		String model = "abc";
		String target = "abcab";
		KMP kmp = new KMP(target, model);
		kmp.next();
		kmp.query();
	}
}

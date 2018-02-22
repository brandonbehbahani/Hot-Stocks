package hotstocks;

public class OnBalanceVolume {
	private static int[] onBalanceVolumes = new int[500];
	
	public static int[] calculate(double[] prices, int[] volumes) {
		onBalanceVolumes[499] = 0;
		for (int i = onBalanceVolumes.length-1; i > 0; i--) {
			if (prices[i] < prices[i - 1]) {
				onBalanceVolumes[i] =+ volumes[i];
			}
			if (prices[i] > prices[i - 1]) {
				onBalanceVolumes[i] =- volumes[i];
			}
		}
		
		return onBalanceVolumes;
	}
	

}

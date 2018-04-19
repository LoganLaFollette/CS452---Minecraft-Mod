package edu.bradley.llafollette.tutorial.capabilities;

public class BitcoinWallet implements IBitcoin {

	private float bitcoinAmount = 1000.0F;
	
	public void spend(float coins) {
		this.bitcoinAmount -= coins;
		if (this.bitcoinAmount < 0.0F) this.bitcoinAmount = 0.0F;
	}

	public void addCoins(float coins) {
		this.bitcoinAmount += coins;
	}

	public void setCoinAmount(float coins) {
		this.bitcoinAmount = coins;
	}

	public float getCoins() {
		return this.bitcoinAmount;
	}

	

}

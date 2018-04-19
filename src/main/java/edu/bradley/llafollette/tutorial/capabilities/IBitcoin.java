package edu.bradley.llafollette.tutorial.capabilities;

/*
 * Bitcoin capability
 */
public interface IBitcoin {

	public void spend(float coins);
	
	public void addCoins(float coins);
	
	public void setCoinAmount(float coins);
	
	public float getCoins();
}

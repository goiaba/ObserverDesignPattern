package edu.luc.cs.spring2015.cs473;


public class Main {

	public static void main(String[] args) {
		
		TrailingStop tS = new TrailingStop("GOOGL", 543.51f, 0.10f);

		Emulator assetPriceEmulator = new Emulator();
		assetPriceEmulator.addAsset(tS.getAssetQuote(), 480f, 600f);
		assetPriceEmulator.startEmulation();
		try {
			//Ugly hack to know when there is no more observers attached to the subject.
			while (!((AssetQuote) tS.getAssetQuote()).getObservers().isEmpty()) Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assetPriceEmulator.stopEmulation();
	}
	
}

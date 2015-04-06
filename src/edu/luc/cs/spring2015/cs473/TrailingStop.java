package edu.luc.cs.spring2015.cs473;

public class TrailingStop implements Observer {

	private String asset;
	private Float quote;
	private Float lossPercentage;

	private Subject<Float> assetQuote;
	
	public TrailingStop(String asset, Float initialQuote, Float lossPercentage) {
		this.setAsset(asset);
		this.setQuote(initialQuote);
		this.setLossPercentage(lossPercentage);
		setAssetQuote(new AssetQuote(this.getAsset()));
		getAssetQuote().attach(this);
	}
	
	@Override
	public void update() {
		Float currentQuote = getAssetQuote().getState();
		System.out.printf("Current Quote is $%.2f => \n", currentQuote);
		System.out.print("\t");
		if (currentQuote > getQuote()) {
			System.out.println("Great, the asset price is rising.");
		} else if (currentQuote < getQuote() * (1 - getLossPercentage())) {
			System.out.println("The asset price dropped more than permitted by the loss limit: Selling it.");
			assetQuote.detach(this);
		} else {
			System.out.println("The asset price dropped but it is still under the loss limit.");
		}
		setQuote(currentQuote);
	}

	public Subject<Float> getAssetQuote() {
		return assetQuote;
	}

	public void setAssetQuote(Subject<Float> assetQuote) {
		this.assetQuote = assetQuote;
	}

	public String getAsset() {
		return asset;
	}

	public void setAsset(String asset) {
		this.asset = asset;
	}

	public Float getQuote() {
		return quote;
	}

	public void setQuote(Float quote) {
		this.quote = quote;
	}

	public Float getLossPercentage() {
		return lossPercentage;
	}

	public void setLossPercentage(Float lossPercentage) {
		this.lossPercentage = lossPercentage;
	}

}

package edu.luc.cs.spring2015.comp473;

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
			setQuote(currentQuote);
		} else if (currentQuote < getQuote() * (1 - getLossPercentage())) {
			System.out.println("The asset price dropped more than permitted by the loss limit: Selling it.");
			assetQuote.detach(this);
		} else {
			System.out.println("The asset price do not changed or dropped but it is still above the loss limit.");
		}
	}

	public Subject<Float> getAssetQuote() {
		return assetQuote;
	}

	private void setAssetQuote(Subject<Float> assetQuote) {
		this.assetQuote = assetQuote;
	}

	public String getAsset() {
		return asset;
	}

	private void setAsset(String asset) {
		this.asset = asset;
	}

	public Float getQuote() {
		return quote;
	}

	private void setQuote(Float quote) {
		this.quote = quote;
	}

	public Float getLossPercentage() {
		return lossPercentage;
	}

	private void setLossPercentage(Float lossPercentage) {
		this.lossPercentage = lossPercentage;
	}

}

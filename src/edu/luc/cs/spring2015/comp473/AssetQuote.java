package edu.luc.cs.spring2015.comp473;

import java.util.ArrayList;
import java.util.List;

public class AssetQuote implements Subject<Float> {

	private String asset;
	private Float state;
	private List<Observer> observers = new ArrayList<Observer>();
	
	public AssetQuote(String asset) {
		assert(asset != null);
		this.setAsset(asset);
	}
	
	@Override
	public void attach(Observer observer) {
		getObservers().add(observer);
	}

	@Override
	public void detach(Observer observer) {
		getObservers().remove(observer);
	}

	@Override
	public void notifyObserver() {
		observers.forEach(observer -> observer.update());
	}

	@Override
	public Float getState() {
		return state;
	}
	
	@Override
	public void setState(Float state) {
		this.state = state;
		notifyObserver();
	}
	
	public List<Observer> getObservers() {
		return observers;
	}

	public String getAsset() {
		return asset;
	}

	private void setAsset(String asset) {
		this.asset = asset;
	}

}

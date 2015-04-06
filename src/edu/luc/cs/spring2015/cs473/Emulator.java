package edu.luc.cs.spring2015.cs473;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Emulator {

	private static final int POOL_SIZE = 2;
	
	private Map<Subject<Float>, PriceRange> assetsPriceRange = new HashMap<Subject<Float>, Emulator.PriceRange>();
	private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(POOL_SIZE);
	private final List<ScheduledFuture<?>> futures = new ArrayList<ScheduledFuture<?>>();
	
	public Emulator addAsset(Subject<Float> asset, Float minValue, Float maxValue) {
		assert(minValue < maxValue);
		this.getAssetsPriceRange().put(asset, new PriceRange(minValue, maxValue));
		return this;
	}
	
	public void startEmulation() {
		for (Map.Entry<Subject<Float>, PriceRange> entry : getAssetsPriceRange().entrySet()) {
			ScheduledFuture<?> future = executor.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					Float newQuote = 
							entry.getValue().minValue + 
							((entry.getValue().maxValue - entry.getValue().minValue) * new Random().nextFloat());
					entry.getKey().setState(newQuote);
				}
			}, 0, 1, TimeUnit.SECONDS);
			futures.add(future);
		}
	}
	
	public void stopEmulation() {
		futures.forEach(future -> future.cancel(true));
		executor.shutdownNow();
	}
	
	public Map<Subject<Float>, PriceRange> getAssetsPriceRange() {
		return assetsPriceRange;
	}

	public void setAssetsPriceRange(Map<Subject<Float>, PriceRange> assetsPriceRange) {
		this.assetsPriceRange = assetsPriceRange;
	}

	class PriceRange {
		private Float minValue;
		private Float maxValue;
		
		public PriceRange(Float minValue, Float maxValue) {
			this.setMinValue(minValue);
			this.setMaxValue(maxValue);
		}

		public Float getMinValue() {
			return minValue;
		}

		public void setMinValue(Float minValue) {
			this.minValue = minValue;
		}

		public Float getMaxValue() {
			return maxValue;
		}

		public void setMaxValue(Float maxValue) {
			this.maxValue = maxValue;
		}
	}
	
}

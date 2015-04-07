package edu.luc.cs.spring2015.comp473;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AssetQuoteTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	private final Float initialQuote = 500f;
	private final Float newQuote = 510f;
	private final String asset = "GOOGL";
	private TrailingStop tS = null;
	
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
		tS = new TrailingStop(asset, initialQuote, 0.05f);
		tS.getAssetQuote().setState(initialQuote);
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(null);
	}
	
	@Test
	public void testNotifyObserver() {
		assertTrue(initialQuote.equals(tS.getQuote()));
		tS.getAssetQuote().setState(newQuote);
		assertTrue(newQuote.equals(tS.getQuote()));
	}

	@Test
	public void testAttach() {
		assertTrue(tS.getAssetQuote().getObservers().size() == 1);
		assertTrue(tS.getAssetQuote().getObservers().get(0).equals(tS));
	}

	@Test
	public void testDetach() {
		assertTrue(tS.getAssetQuote().getObservers().get(0).equals(tS));
		tS.getAssetQuote().detach(tS);
		assertTrue(tS.getAssetQuote().getObservers().isEmpty());
	}

	@Test
	public void testGetState() {
		assertTrue(initialQuote.equals(tS.getAssetQuote().getState()));
	}

	@Test
	public void testSetState() {
		tS.getAssetQuote().setState(newQuote);
		assertTrue(newQuote.equals(tS.getAssetQuote().getState()));
	}

	@Test
	public void testGetObservers() {
		assertTrue(tS.getAssetQuote().getObservers().size() == 1);
		assertTrue(tS.getAssetQuote().getObservers().get(0).equals(tS));		
	}

	@Test
	public void testGetAsset() {
		assertTrue(asset.equals(((AssetQuote) tS.getAssetQuote()).getAsset()));
	}

}

package edu.luc.cs.spring2015.comp473;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TrailingStopTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	private final Float initialQuote = 500f;
	private final Float newQuote = 510f;
	private final Float lossPercentage = 0.05f;
	private final String asset = "GOOGL";
	private TrailingStop tS = null;
	
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
		tS = new TrailingStop(asset, initialQuote, lossPercentage);
		tS.getAssetQuote().setState(initialQuote);
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(null);
	}

	@Test
	public void testUpdate() {
		outContent.reset();
		tS.getAssetQuote().setState(newQuote);
		assertTrue("Current Quote is $510.00 => \n\tGreat, the asset price is rising.\n".equals(outContent.toString()));
	}

	@Test
	public void testGetAssetQuote() {
		assertTrue(tS.getAssetQuote().equals(tS.getAssetQuote()));
	}

	@Test
	public void testGetAsset() {
		assertTrue(asset.equals(tS.getAsset()));
	}

	@Test
	public void testGetQuote() {
		assertTrue(initialQuote.equals(tS.getQuote()));
		tS.getAssetQuote().setState(newQuote);
		assertTrue(newQuote.equals(tS.getQuote()));
	}

	@Test
	public void testGetLossPercentage() {
		assertTrue(lossPercentage.equals(tS.getLossPercentage()));
	}

}

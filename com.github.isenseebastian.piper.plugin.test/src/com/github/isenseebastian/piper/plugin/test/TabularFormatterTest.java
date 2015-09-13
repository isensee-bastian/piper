package com.github.isenseebastian.piper.plugin.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URL;
import java.nio.charset.Charset;

import org.junit.Test;

import com.github.isenseebastian.piper.plugin.TabularFormatter;
import com.google.common.io.Resources;

public class TabularFormatterTest {

	@Test
	public void shouldFormatSingleLine() throws Exception {
		testFormat("one-line-input", "one-line-expected");
	}

	@Test
	public void shouldFormatMultiLine() throws Exception {
		testFormat("multi-line-input", "multi-line-expected");
	}

	@Test
	public void shouldFormatEmbedded() throws Exception {
		testFormat("embedded-input", "embedded-expected");
	}

	@Test
	public void shouldFormatMultiTable() throws Exception {
		testFormat("multi-table-input", "multi-table-expected");
	}

	@Test
	public void shouldFormatBrokenTable() throws Exception {
		testFormat("broken-table-input", "broken-table-expected");
	}

	private void testFormat(String inputFile, String expectedFile) throws Exception {
		URL inputResource = Resources.getResource(this.getClass(), inputFile);
		URL expectedResource = Resources.getResource(this.getClass(), expectedFile);
		String input = Resources.toString(inputResource, Charset.forName("UTF-8"));
		String expected = Resources.toString(expectedResource, Charset.forName("UTF-8"));
		String result = TabularFormatter.INSTANCE.format(input);
		assertThat(result, is(expected));
	}

}

package com.github.isenseebastian.piper.plugin;

import java.util.List;
import java.util.stream.Collectors;

public final class Row {

	private List<String> cells;

	private Row(List<String> cells) {
		this.cells = cells;
	}

	public List<Integer> getWidths() {
		return cells.stream().map(String::length).collect(Collectors.toList());
	}

	public List<String> getCells() {
		return cells;
	}

	public static Row fromFragments(List<String> fragments) {
		List<String> cleanedValues = fragments.stream().map(String::trim).filter(element -> element.length() > 0)
				.collect(Collectors.toList());
		return new Row(cleanedValues);
	}

}

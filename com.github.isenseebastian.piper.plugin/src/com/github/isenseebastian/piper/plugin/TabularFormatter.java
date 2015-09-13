package com.github.isenseebastian.piper.plugin;

import java.util.Arrays;
import java.util.List;

public enum TabularFormatter {

	INSTANCE;

	private static final String TABLE_ROW = "\\s*\\|.*";
	private static final String LINE_BREAK = "\\r?\\n";

	public String format(String input) {
		StringBuilder builder = new StringBuilder();
		List<String> allLines = Arrays.asList(input.split(LINE_BREAK));
		Table table = new Table();

		for (String line : allLines) {
			if (line.matches(TABLE_ROW)) {
				if (table.isEmpty() && builder.length() > 0) {
					builder.append("\n");
				}
				List<String> fragments = Arrays.asList(line.split("\\|"));
				Row row = Row.fromFragments(fragments);
				table.addRow(row);
			} else {
				builder.append(table.render());
				builder.append(builder.length() > 0 ? "\n" : "");
				builder.append(line);
				table = new Table();
			}
		}
		return builder.append(table.render()).toString();
	}

}

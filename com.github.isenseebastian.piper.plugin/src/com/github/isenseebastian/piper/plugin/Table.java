package com.github.isenseebastian.piper.plugin;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Strings;

public final class Table {

	private List<Integer> columnSizes = new ArrayList<>();
	private List<Row> rows = new ArrayList<>();

	public void addRow(Row row) {
		List<Integer> widths = row.getWidths();
		updateColumnSizes(widths);
		rows.add(row);
	}

	public String render() {
		StringBuilder builder = new StringBuilder();
		for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
			builder.append("|");
			List<String> cells = rows.get(rowIndex).getCells();
			for (int index = 0; index < columnSizes.size(); index++) {
				String content = index < cells.size() ? cells.get(index) : "";
				builder.append(" ");
				builder.append(content);
				int columnSize = columnSizes.get(index).intValue();
				int delta = columnSize - content.length();
				String space = Strings.repeat(" ", delta);
				builder.append(space);
				builder.append(" |");
			}
			if (rowIndex < rows.size() - 1) {
				builder.append("\n");
			}
		}
		return builder.toString();
	}

	public boolean isEmpty() {
		return rows.isEmpty();
	}

	private void updateColumnSizes(List<Integer> widths) {
		for (int index = 0; index < widths.size(); index++) {
			if (index < columnSizes.size()) {
				if (widths.get(index) > columnSizes.get(index)) {
					columnSizes.set(index, Integer.valueOf(widths.get(index)));
				}
			} else {
				columnSizes.add(Integer.valueOf(widths.get(index)));
			}
		}
	}

}

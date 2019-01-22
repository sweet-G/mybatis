package com.zt.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelData implements Serializable {
	public static final int FIRST = 0;

	private Map<Integer, Map> sheets = new HashMap<Integer, Map>();

	private Map<Integer, Map> currentSheet = null;

	private List<int[]> sheetCellSize = new ArrayList<int[]>();

	private int[] currentSheetCellSize = null;

	public int getSheetCount() {
		return sheets.keySet().size();
	}

	public String[][] getSheetDatas(int sheetIndex) {
		int rowCount = getRowCount(sheetIndex);
		int columnCount = getColumnCount(sheetIndex);
		String[][] result = new String[rowCount][columnCount];
		Map sheetDatas = sheets.get(Integer.valueOf(sheetIndex));
		for (int row = 0; row < rowCount; row++) {
			Map rowData = (Map) sheetDatas.get(Integer.valueOf(row));
			for (int column = 0; column < columnCount; column++) {
				if (rowData == null
						|| !rowData.containsKey(Integer.valueOf(column))) {
					result[row][column] = "";
				} else {
					result[row][column] = (String) rowData.get(Integer
							.valueOf(column));
				}
			}
		}
		return result;
	}

	public void addWorkSheet() {
		currentSheet = new HashMap();
		sheets.put(Integer.valueOf(getSheetCount()), currentSheet);
		currentSheetCellSize = new int[] { 0, 0 };
		sheetCellSize.add(currentSheetCellSize);
	}

	public void addString(int row, short column, String value) {
		Map rowData = (Map) currentSheet.get(Integer.valueOf(row));
		if (rowData == null) {
			rowData = new HashMap();
			currentSheet.put(Integer.valueOf(row), rowData);
		}
		rowData.put(Integer.valueOf(column), value);
		updateCellSize(row, column);
	}

	private void updateCellSize(int row, short column) {
		currentSheetCellSize[0] = Math.max(currentSheetCellSize[0], row + 1);
		currentSheetCellSize[1] = Math.max(currentSheetCellSize[1], column + 1);
	}

	private int getColumnCount(int sheetIndex) {
		int[] sizes = sheetCellSize.get(sheetIndex);
		return sizes[1];
	}

	private int getRowCount(int sheetIndex) {
		int[] sizes = sheetCellSize.get(sheetIndex);
		return sizes[0];
	}

	public Map<Integer, Map> getSheets() {
		return sheets;
	}

	public void setSheets(Map<Integer, Map> sheets) {
		this.sheets = sheets;
	}
	// private int getMax

}

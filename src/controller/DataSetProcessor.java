package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class DataSetProcessor {

	private static final String FILE_PATH = "dataset_raw.xls";
	private static final int NUM_OF_COL = 39;

    private void getStudentsListFromExcel() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(FILE_PATH);

            // Using XSSF for xlsx format, for xls use HSSF
            Workbook workbook = new HSSFWorkbook(fis);

            Sheet sheet = workbook.getSheetAt(0);
            for (Row r: sheet) {
            	for (Cell c: r) {
            		if (c != null && c.getRowIndex() > 1) {
                		if (c.getColumnIndex() < NUM_OF_COL-1) System.out.print(c + "\t");
                		else System.out.println(c);
                	}
                }
            }

            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
    	DataSetProcessor dataSetProcessor = new DataSetProcessor();
    	dataSetProcessor.getStudentsListFromExcel();
    }

}

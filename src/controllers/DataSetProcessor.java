package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.csvreader.CsvWriter;

public class DataSetProcessor {
	CsvWriter writer;
	private final int NUM_OF_ROW_GEN_ED_SURVEY = 1542;
	
	public DataSetProcessor(String output_file_path) {
		try {
			writer = new CsvWriter(new FileWriter(output_file_path, false), ',');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    private void processGenEdSurvey(String input_file_path) {
//    	writeCSVHeaders();
    	
    	FileInputStream fis = null;
        try {
            fis = new FileInputStream(input_file_path);

            // Using XSSF for xlsx format, for xls use HSSF
            Workbook workbook = new HSSFWorkbook(fis);

            Sheet sheet = workbook.getSheetAt(0);
            for (Row r: sheet) {
            	for (Cell c: r) {
            		if (c != null && c.getRowIndex() > 1 && c.getRowIndex() < NUM_OF_ROW_GEN_ED_SURVEY) {
            			if (c.getColumnIndex() < 5) {
            				print(c + "\t");
            			} else if (c.getColumnIndex() == 5) {
            				if (c.toString().isEmpty()) {
            					print("Enrollment NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) >= 1000) {
            					print("Enrollment >= 1000" + "\t");
            				} else {
            					print("Enrollment < 1000" + "\t");
            				}
            			} else if (c.getColumnIndex() == 7) {
            				if (c.toString().isEmpty()) {
            					print("Parent Acdemic Expectations NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 8.0) {
            					print("Parent Acdemic Expectations AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 8.0) {
            					print("Parent Acdemic Expectations AT LEAST Average" + "\t");
            				} else {
            					print("Parent Acdemic Expectations BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 8) {
            				if (c.toString().isEmpty()) {
            					print("Student Acdemic Expectations NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 7.4) {
            					print("Student Acdemic Expectations AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 7.4) {
            					print("Student Acdemic Expectations AT LEAST Average" + "\t");
            				} else {
            					print("Student Acdemic Expectations BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 9) {
            				if (c.toString().isEmpty()) {
            					print("Teacher Acdemic Expectations NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 7.6) {
            					print("Teacher Acdemic Expectations AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 7.6) {
            					print("Teacher Acdemic Expectations AT LEAST Average" + "\t");
            				} else {
            					print("Teacher Acdemic Expectations BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 10) {
            				if (c.toString().isEmpty()) {
            					print("Acdemic Expectations NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 7.8) {
            					print("Acdemic Expectations AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 7.8) {
            					print("Acdemic Expectations AT LEAST Average" + "\t");
            				} else {
            					print("Acdemic Expectations BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 11) {
            				if (c.toString().isEmpty()) {
            					print("Parent Communication NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 7.6) {
            					print("Parent Communication AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 7.6) {
            					print("Parent Communication AT LEAST Average" + "\t");
            				} else {
            					print("Parent Communication BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 12) {
            				if (c.toString().isEmpty()) {
            					print("Student Communication NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 6.0) {
            					print("Student Communication AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 6.0) {
            					print("Student Communication AT LEAST Average" + "\t");
            				} else {
            					print("Student Communication BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 13) {
            				if (c.toString().isEmpty()) {
            					print("Teacher Communication NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 6.7) {
            					print("Teacher Communication AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 6.7) {
            					print("Teacher Communication AT LEAST Average" + "\t");
            				} else {
            					print("Teacher Communication BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 14) {
            				if (c.toString().isEmpty()) {
            					print("Communication NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 6.9) {
            					print("Communication AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 6.9) {
            					print("Communication AT LEAST Average" + "\t");
            				} else {
            					print("Communication BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 15) {
            				if (c.toString().isEmpty()) {
            					print("Parent Engagement NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 7.2) {
            					print("Parent Engagement AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 7.2) {
            					print("Parent Engagement AT LEAST Average" + "\t");
            				} else {
            					print("Parent Engagement BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 16) {
            				if (c.toString().isEmpty()) {
            					print("Student Engagement NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 6.7) {
            					print("Student Engagement AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 6.7) {
            					print("Student Engagement AT LEAST Average" + "\t");
            				} else {
            					print("Student Engagement BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 17) {
            				if (c.toString().isEmpty()) {
            					print("Teacher Engagement NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 7.1) {
            					print("Teacher Engagement AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 7.1) {
            					print("Teacher Engagement AT LEAST Average" + "\t");
            				} else {
            					print("Teacher Engagement BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 18) {
            				if (c.toString().isEmpty()) {
            					print("Engagement NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 7.1) {
            					print("Engagement AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 7.1) {
            					print("Engagement AT LEAST Average" + "\t");
            				} else {
            					print("Engagement BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 19) {
            				if (c.toString().isEmpty()) {
            					print("Parent Safety and Respect NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 8.4) {
            					print("Parent Safety and Respect AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 8.4) {
            					print("Parent Safety and Respect AT LEAST Average" + "\t");
            				} else {
            					print("Parent Safety and Respect BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 20) {
            				if (c.toString().isEmpty()) {
            					print("Student Safety and Respect NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 6.6) {
            					print("Student Safety and Respect AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 6.6) {
            					print("Student Safety and Respect AT LEAST Average" + "\t");
            				} else {
            					print("Student Safety and Respect BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 21) {
            				if (c.toString().isEmpty()) {
            					print("Teacher Safety and Respect NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 7.4) {
            					print("Teacher Safety and Respect AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 7.4) {
            					print("Teacher Safety and Respect AT LEAST Average" + "\t");
            				} else {
            					print("Teacher Safety and Respect BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 22) {
            				if (c.toString().isEmpty()) {
            					print("Safety and Respect NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 7.7) {
            					print("Safety and Respect AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 7.7) {
            					print("Safety and Respect AT LEAST Average" + "\t");
            				} else {
            					print("Safety and Respect BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 23) {
            				if (c.toString().isEmpty()) {
            					print("Parent Horizon Academic Expectation NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 0.679) {
            					print("Parent Horizon Academic Expectation AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 0.679) {
            					print("Parent Horizon Academic Expectation AT LEAST Average" + "\t");
            				} else {
            					print("Parent Horizon Academic Expectation BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 24) {
            				if (c.toString().isEmpty()) {
            					print("Student Horizon Academic Expectation NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 0.606) {
            					print("Student Horizon Academic Expectation AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 0.679) {
            					print("Student Horizon Academic Expectation AT LEAST Average" + "\t");
            				} else {
            					print("Student Horizon Academic Expectation BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 25) {
            				if (c.toString().isEmpty()) {
            					print("Teacher Horizon Academic Expectation NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 0.607) {
            					print("Teacher Horizon Academic Expectation AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 0.679) {
            					print("Teacher Horizon Academic Expectation AT LEAST Average" + "\t");
            				} else {
            					print("Teacher Horizon Academic Expectation BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 26) {
            				if (c.toString().isEmpty()) {
            					print("Horizon Academic Expectation NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 0.663) {
            					print("Horizon Academic Expectation AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 0.663) {
            					print("Horizon Academic Expectation AT LEAST Average" + "\t");
            				} else {
            					print("Horizon Academic Expectation BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 27) {
            				if (c.toString().isEmpty()) {
            					print("Parent Horizon Communication NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 0.645) {
            					print("Parent Horizon Communication AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 0.645) {
            					print("Parent Horizon Communication AT LEAST Average" + "\t");
            				} else {
            					print("Parent Horizon Communication BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 28) {
            				if (c.toString().isEmpty()) {
            					print("Student Horizon Communication NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 0.363) {
            					print("Student Horizon Communication AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 0.363) {
            					print("Student Horizon Communication AT LEAST Average" + "\t");
            				} else {
            					print("Student Horizon Communication BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 29) {
            				if (c.toString().isEmpty()) {
            					print("Teacher Horizon Communication NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 0.594) {
            					print("Teacher Horizon Communication AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 0.594) {
            					print("Teacher Horizon Communication AT LEAST Average" + "\t");
            				} else {
            					print("Teacher Horizon Communication BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 30) {
            				if (c.toString().isEmpty()) {
            					print("Horizon Communication NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 0.632) {
            					print("Horizon Communication AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 0.632) {
            					print("Horizon Communication AT LEAST Average" + "\t");
            				} else {
            					print("Horizon Communication BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 31) {
            				if (c.toString().isEmpty()) {
            					print("Parent Horizon Engagement NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 0.696) {
            					print("Parent Horizon Engagement AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 0.696) {
            					print("Parent Horizon Engagement AT LEAST Average" + "\t");
            				} else {
            					print("Parent Horizon Engagement BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 32) {
            				if (c.toString().isEmpty()) {
            					print("Student Horizon Engagement NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 0.674) {
            					print("Student Horizon Engagement AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 0.674) {
            					print("Student Horizon Engagement AT LEAST Average" + "\t");
            				} else {
            					print("Student Horizon Engagement BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 33) {
            				if (c.toString().isEmpty()) {
            					print("Teacher Horizon Engagement NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 0.635) {
            					print("Teacher Horizon Engagement AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 0.635) {
            					print("Teacher Horizon Engagement AT LEAST Average" + "\t");
            				} else {
            					print("Teacher Horizon Engagement BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 34) {
            				if (c.toString().isEmpty()) {
            					print("Horizon Engagement NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 0.673) {
            					print("Horizon Engagement AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 0.673) {
            					print("Horizon Engagement AT LEAST Average" + "\t");
            				} else {
            					print("Horizon Engagement BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 35) {
            				if (c.toString().isEmpty()) {
            					print("Parent Horizon Safety and Respect NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 0.68) {
            					print("Parent Horizon Safety and Respect AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 0.68) {
            					print("Parent Horizon Safety and Respect AT LEAST Average" + "\t");
            				} else {
            					print("Parent Horizon Safety and Respect BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 36) {
            				if (c.toString().isEmpty()) {
            					print("Student Horizon Safety and Respect NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 0.554) {
            					print("Student Horizon Safety and Respect AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 0.554) {
            					print("Student Horizon Safety and Respect AT LEAST Average" + "\t");
            				} else {
            					print("Student Horizon Safety and Respect BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 37) {
            				if (c.toString().isEmpty()) {
            					print("Teacher Horizon Safety and Respect NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 0.595) {
            					print("Teacher Horizon Safety and Respect AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 0.595) {
            					print("Teacher Horizon Safety and Respect AT LEAST Average" + "\t");
            				} else {
            					print("Teacher Horizon Safety and Respect BELOW Average" + "\t");
            				}
            			} else if (c.getColumnIndex() == 38) {
            				if (c.toString().isEmpty()) {
            					println("Horizon Safety and Respect NOT AVAILABLE" + "\t");
            				} else if (Double.parseDouble(c.toString()) > 0.645) {
            					println("Horizon Safety and Respect AT LEAST Average" + "\t");
            				} else if (Double.parseDouble(c.toString()) == 0.645) {
            					println("Horizon Safety and Respect AT LEAST Average" + "\t");
            				} else {
            					println("Horizon Safety and Respect BELOW Average" + "\t");
            				}
            			}
                	}
                }
            }

            fis.close();
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void writeGenEdSurveyHeaders() {
    	try {
    		writer.write("DBN#_1");
        	writer.write("DBN#_2");
        	writer.write("School Name");
        	writer.write("Principal");
        	writer.write("School Type");
        	writer.write("Enrollment");
        	writer.write("Parent Academic Expectations Score");
        	writer.write("Student Academic Expectations Score");
        	writer.write("Teacher Academic Expectations Score");
        	writer.write("Academic Expectations Score");
        	writer.write("Parent Communication Score");
        	writer.write("Student Communication Score");
        	writer.write("Teacher Communication Score");
        	writer.write("Communication Score");
        	writer.write("Parent Engagement Score");
        	writer.write("Student Engagement Score");
        	writer.write("Teacher Engagement Score");
        	writer.write("Engagement Score");
        	writer.write("Parent Safety and Respect Score");
        	writer.write("Student Safety and Respect Score");
        	writer.write("Teacher Safety and Respect Score");
        	writer.write("Safety and Respect Score");
        	writer.write("Parent Horizon Academic Expectations Score");
        	writer.write("Student Horizon Academic Expectations Score");
        	writer.write("Teacher Horizon Academic Expectations Score");
        	writer.write("Horizon Academic Expectations Score");
        	writer.write("Parent Horizon Communication Score");
        	writer.write("Student Horizon Communication Score");
        	writer.write("Teacher Horizon Communication Score");
        	writer.write("Horizon Communication Score");
        	writer.write("Parent Horizon Engagement Score");
        	writer.write("Student Horizon Engagement Score");
        	writer.write("Teacher Horizon Engagement Score");
        	writer.write("Horizon Engagement Score");
        	writer.write("Parent Horizon Safety and Respect Score");
        	writer.write("Student Horizon Safety and Respect Score");
        	writer.write("Teacher Horizon Safety and Respect Score");
        	writer.write("Horizon Safety and Respect Score");
			writer.endRecord();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    void print(String s) {
    	System.out.print(s);
    	try {
			writer.write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    void println(String s) {
    	System.out.println(s);
    	try {
			writer.write(s);
			writer.endRecord();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void main(String args[]) {
    	DataSetProcessor dataSetProcessor = new DataSetProcessor("INTEGRATED-DATASET.csv");
    	dataSetProcessor.processGenEdSurvey("dataset_raw.xls");
    }

}

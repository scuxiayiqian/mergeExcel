package mergeExcel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.util.ArrayList;

public class ReadAndMerge {
	
	private String outputPath = "";
	
	public ArrayList<CompareData> allDataArr = new ArrayList<CompareData>();
	public ArrayList<CompareData> getAllDataArr() {
		return allDataArr;
	}

	public ReadAndMerge(String path) {
		this.outputPath = path;
	}
	
	public void read(String filename) throws IOException
	{	
		
		System.out.println(filename);
		HSSFWorkbook wb = readFile(filename);

		System.out.println("Data dump:\n");

		System.out.println(wb.getNumberOfSheets());
		for (int k = 0; k < wb.getNumberOfSheets(); k++) {
			
			HSSFSheet sheet = wb.getSheetAt(k);
			int rows = sheet.getPhysicalNumberOfRows();
			System.out.println("Sheet " + k + " \"" + wb.getSheetName(k) + "\" has " + rows
					+ " row(s).");
			for (int r = 0; r < rows; r++) {
				HSSFRow row = sheet.getRow(r);
				if (row == null || r == 0) {
					continue;
				}
				
				CompareData data = new CompareData(-1, -1);
				
				int cells = row.getPhysicalNumberOfCells();
				System.out.println("\nROW " + row.getRowNum() + " has " + cells
						+ " cell(s).");
				for (int c = 0; c < cells; c++) {
					HSSFCell cell = row.getCell(c);
					
					if (cell.getColumnIndex() == 1) {
						Double pp = cell.getNumericCellValue();
						int predict = pp.intValue();
						data.setPredictedTimes(predict);
					}
					else if (cell.getColumnIndex() == 2) {
						Double pp = cell.getNumericCellValue();
						int test = pp.intValue();
						data.setTestingTimes(test);
					}
				}
				
				allDataArr.add(data);
			}
		}
		wb.close();
	}
	
	private HSSFWorkbook readFile(String filename) throws IOException {
	    FileInputStream fis = new FileInputStream(filename);
	    try {
	        return new HSSFWorkbook(fis);
	    } finally {
	        fis.close();
	    }
	}
	
	public void exportToExcel() {
		HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("new sheet");
        
        int length = allDataArr.size();
        System.out.println("length = " + length);

        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("predicted");
        row.createCell(1).setCellValue("tested");
        
        for (int i = 0; i < length; i++) {
            HSSFRow dataRow = sheet.createRow(i+1);
            dataRow.createCell(0).setCellValue(allDataArr.get(i).getPredictedTimes());
            dataRow.createCell(1).setCellValue(allDataArr.get(i).getTestingTimes());
            
            System.out.println(allDataArr.get(i).getPredictedTimes() + "..." + allDataArr.get(i).getTestingTimes());
        }

        // Write the output to a file
        FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(outputPath);
			wb.write(fileOut);
	        fileOut.close();
	        wb.close();
	        
	        System.out.println("预测数据导出完成！.....");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
}

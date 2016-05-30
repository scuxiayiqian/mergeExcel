package mergeExcel;
import java.io.IOException;

public class Main {

	public static void main(String args[]) throws IOException
	{	
		
		int times = 11;
		String filenamePrefix = "/Users/xiayiqian/Downloads/result/finalPrediction";		//设置1-输入文件的目录
		ReadAndMerge fileHandler = new ReadAndMerge("/Users/xiayiqian/Downloads/result/wholecompare.xls");   		//设置2-输出文件的path
				
		for (int i = 1; i < times; i++) {
			String fileName = filenamePrefix + Integer.toString(i) + ".xls";
			fileHandler.read(fileName);
		}
		
		fileHandler.exportToExcel();
		System.out.println("所有对比数据导出完成....");
	}
	
}

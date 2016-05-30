package mergeExcel;

public class CompareData {
	
	private int predictedTimes;
	private int testingTimes;
	
	public CompareData(int i, int j) {
		this.predictedTimes = i;
		this.testingTimes = j;
	}
	
	public int getPredictedTimes() {
		return predictedTimes;
	}
	public void setPredictedTimes(int predictedTimes) {
		this.predictedTimes = predictedTimes;
	}
	public int getTestingTimes() {
		return testingTimes;
	}
	public void setTestingTimes(int testingTimes) {
		this.testingTimes = testingTimes;
	}
	
}

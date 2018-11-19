package common;

public class PagingProcess {
	private int numberOfRowAtOnce = 10;
	private int pagingNumberOnScreen = 5;
	private int allPagingSection;
	private int allPagingNumber;
	private int firstPagingNumber;
	private int lastPagingNumber;
	private int firstRow;
	private int lastRow;
	private int currSection = 1;

	public void process(int allRowCount, int currPagingNumber) {
		allPagingNumber = allRowCount / numberOfRowAtOnce + (allRowCount % numberOfRowAtOnce == 0 ? 0 : 1);
		allPagingSection = allPagingNumber / pagingNumberOnScreen + (allPagingNumber % pagingNumberOnScreen == 0 ? 0 : 1);
		currSection = (currPagingNumber - 1) / pagingNumberOnScreen + 1;
		firstPagingNumber = currSection * pagingNumberOnScreen - (pagingNumberOnScreen - 1);
		lastPagingNumber = (currSection != allPagingSection ? currSection * pagingNumberOnScreen : allPagingNumber);
		firstRow = currPagingNumber * numberOfRowAtOnce - (numberOfRowAtOnce - 1);
		lastRow = (currPagingNumber != allPagingNumber ? currPagingNumber * numberOfRowAtOnce : allRowCount);
	}

	public int getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public int getLastRow() {
		return lastRow;
	}

	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}

	public int getFirstPagingNumber() {
		return firstPagingNumber;
	}

	public void setFirstPagingNumber(int firstPagingNumber) {
		this.firstPagingNumber = firstPagingNumber;
	}

	public int getLastPagingNumber() {
		return lastPagingNumber;
	}

	public void setLastPagingNumber(int lastPagingNumber) {
		this.lastPagingNumber = lastPagingNumber;
	}

	public int getAllPagingNumber() {
		return allPagingNumber;
	}

	public void setAllPagingNumber(int allPagingNumber) {
		this.allPagingNumber = allPagingNumber;
	}
	
	
}

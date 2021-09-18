package cooking.vo;

public class Page {
	private int pageNum;
	private int numByPage;
	private int shownPageNum;
	private int firstPage;
	private int lastPage;
	private int realLastPage;
	private boolean prevPage;
	private boolean nextPage;
	public Page() {
		super();
	}
	public Page(int pageNum, int numByPage, int shownPageNum, int firstPage, int lastPage, int realLastPage, boolean prevPage,
			boolean nextPage) {
		super();
		this.pageNum = pageNum;
		this.numByPage = numByPage;
		this.shownPageNum = shownPageNum;
		this.firstPage = firstPage;
		this.lastPage = lastPage;
		this.realLastPage = realLastPage;
		this.prevPage = prevPage;
		this.nextPage = nextPage;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getNumByPage() {
		return numByPage;
	}
	public void setNumByPage(int numByPage) {
		this.numByPage = numByPage;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getRealLastPage() {
		return realLastPage;
	}
	public void setRealLastPage(int realLastPage) {
		this.realLastPage = realLastPage;
	}
	public boolean isPrevPage() {
		return prevPage;
	}
	public void setPrevPage(boolean prevPage) {
		this.prevPage = prevPage;
	}
	public boolean isNextPage() {
		return nextPage;
	}
	public void setNextPage(boolean nextPage) {
		this.nextPage = nextPage;
	}
	public int getShownPageNum() {
		return shownPageNum;
	}
	public void setShownPageNum(int shownPageNum) {
		this.shownPageNum = shownPageNum;
	}
	

}

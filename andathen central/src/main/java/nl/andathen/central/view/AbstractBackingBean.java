package nl.andathen.central.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.component.UICommand;
import javax.faces.event.ActionEvent;

public abstract class AbstractBackingBean  implements Serializable {
	private static final long serialVersionUID = 4808264929952299570L;
	private long rowsPerPage = 100; // Default rows per page (max amount of rows to be displayed at once).
	private long pageRange = 10; // Default page range (max amount of page links to be displayed at once).
	protected long totalRows;
	private long firstRow;
	private long totalPages;
	private long currentPage;
	private Integer[] pages;
	
	protected abstract void load();
	
	@PostConstruct
	public void init()
	{
		firstRow = 0;
	}
	
	// Paging actions
	protected void calculatePageSettings() {
		// Set currentPage, totalPages and pages.
		currentPage = (totalRows / rowsPerPage) - ((totalRows - firstRow) / rowsPerPage) + 1;
		totalPages = (totalRows / rowsPerPage) + ((totalRows % rowsPerPage != 0) ? 1 : 0);
		long pagesLength = Math.min(pageRange, totalPages);
		pages = new Integer[(int) pagesLength];
		
		// firstPage must be greater than 0 and lesser than totalPages-pageLength.
		int firstPage = (int) Math.min(Math.max(0, currentPage - (pageRange / 2)), totalPages - pagesLength);

		// Create pages (page numbers for page links).
		for (int i = 0; i < pagesLength; i++) {
			pages[i] = ++firstPage;
		}
	}
	
	public void pageFirst() {
		page(0);
	}
	public void pageNext() {
		page(firstRow + rowsPerPage);
	}
	public void pagePrevious() {
		page(firstRow - rowsPerPage);
	}
	public void pageLast() {
		page(totalRows - ((totalRows % rowsPerPage != 0) ? totalRows % rowsPerPage : rowsPerPage));
	}
	public void page(ActionEvent event) {
		page(((Integer) ((UICommand) event.getComponent()).getValue() - 1) * rowsPerPage);
	}
	private void page(long firstRow) {
		this.firstRow = firstRow;
		load();
	}
	public long getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}
	public long getFirstRow() {
		return firstRow;
	}
	public void setFirstRow(long firstRow) {
		this.firstRow = firstRow;
	}
	public long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}
	public long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public Integer[] getPages() {
		return pages;
	}
	public void setPages(Integer[] pages) {
		this.pages = pages;
	}

	public long getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(long rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public long getPageRange() {
		return pageRange;
	}

	public void setPageRange(long pageRange) {
		this.pageRange = pageRange;
	}
}

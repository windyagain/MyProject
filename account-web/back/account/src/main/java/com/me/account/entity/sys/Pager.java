package com.me.account.entity.sys;

import java.io.Serializable;

public class Pager
  implements Serializable
{
  private long serialVersionUID = 23111L;
  private int totalRows;
  private int pageSize = 10;
  private int currentPage;
  private int totalPages;
  private int startRow;
  private boolean isGetCount = true;

  public boolean isGetCount() { return this.isGetCount;
  }

  public void setGetCount(boolean isGetCount) {
    this.isGetCount = isGetCount;
  }

  public Pager()
  {
  }

  public Pager(int pageSize, int currentPage)
  {
    this.pageSize = pageSize;
    this.currentPage = currentPage;
  }

  public Pager(int pageSize, int currentPage, int totalPage)
  {
    this.pageSize = pageSize;
    this.currentPage = currentPage;
    this.totalPages = totalPage;
    this.isGetCount = false;
  }

  public Pager(int currentPage)
  {
    this.currentPage = currentPage;
  }

  public int getStartRow()
  {
    return this.startRow;
  }

  public int getTotalPages()
  {
    return this.totalPages;
  }

  private void calTotalPages() {
    this.totalPages = ((this.totalRows % this.pageSize == 0) ? this.totalRows / this.pageSize : this.totalRows / 
      this.pageSize + 1);
  }

  public int getCurrentPage()
  {
    return this.currentPage;
  }

  public int getPageSize()
  {
    return this.pageSize;
  }

  public void setTotalRows(int totalRows) {
    this.totalRows = totalRows;
    calTotalPages();
  }

  public void setStartRow(int startRow) {
    this.startRow = startRow;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public void setCurrentPage(int currentPage) {
    currentPage = (currentPage < 1) ? 1 : currentPage;
    currentPage = (currentPage > this.totalPages) ? this.totalPages : currentPage;
    this.startRow = ((currentPage - 1) * this.pageSize);
    this.startRow = ((this.startRow < 0) ? 0 : this.startRow);
    this.currentPage = currentPage;
  }

  public void setPageSize(int pageSize)
  {
    this.pageSize = pageSize;
  }

  public int getTotalRows()
  {
    return this.totalRows;
  }

  public void first() {
    this.currentPage = 1;
    this.startRow = 0;
  }

  public void previous() {
    if (this.currentPage == 1) {
      return;
    }
    this.currentPage -= 1;
    this.startRow = ((this.currentPage - 1) * this.pageSize);
  }

  public void next() {
    if (this.currentPage < this.totalPages) {
      this.currentPage += 1;
    }
    this.startRow = ((this.currentPage - 1) * this.pageSize);
  }

  public void last() {
    this.currentPage = this.totalPages;
    this.startRow = ((this.currentPage - 1) * this.pageSize);
  }

  public void refresh(int _currentPage) {
    this.currentPage = _currentPage;
    if (this.currentPage > this.totalPages)
      last();
  }

  public boolean isLastPage()
  {
    return (this.currentPage == this.totalPages); }

  public boolean isFirstPage() {
    return (this.currentPage == 1);
  }
}
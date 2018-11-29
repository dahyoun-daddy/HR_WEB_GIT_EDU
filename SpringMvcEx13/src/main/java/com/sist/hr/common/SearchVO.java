package com.sist.hr.common;

public class SearchVO {
	private int	page_size       ;
	private int	page_num        ;
	private String	search_word ;
	private String	search_div  ;
	
	public SearchVO() {}

	/**
	 * @return the page_size
	 */
	public int getPage_size() {
		return page_size;
	}

	/**
	 * @param page_size the page_size to set
	 */
	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}

	/**
	 * @return the page_num
	 */
	public int getPage_num() {
		return page_num;
	}

	/**
	 * @param page_num the page_num to set
	 */
	public void setPage_num(int page_num) {
		this.page_num = page_num;
	}

	/**
	 * @return the search_word
	 */
	public String getSearch_word() {
		return search_word;
	}

	/**
	 * @param search_word the search_word to set
	 */
	public void setSearch_word(String search_word) {
		this.search_word = search_word;
	}

	/**
	 * @return the search_div
	 */
	public String getSearch_div() {
		return search_div;
	}

	/**
	 * @param search_div the search_div to set
	 */
	public void setSearch_div(String search_div) {
		this.search_div = search_div;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchVO [page_size=" + page_size + ", page_num=" + page_num + ", search_word=" + search_word
				+ ", search_div=" + search_div + "]";
	}

	public SearchVO(int page_size, int page_num, String search_word, String search_div) {
		super();
		this.page_size = page_size;
		this.page_num = page_num;
		this.search_word = search_word;
		this.search_div = search_div;
	}
	  
	}

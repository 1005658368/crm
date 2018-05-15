package org.crmframework.core.minidao.pojo;

import java.util.List;

public class MiniDaoPage<T> {
	// 当前页面
	private int page;
	// 每页显示记录数
	private int rows;
	// 总行数
	private int total;
	// 总页数
	private int pages;
	// 结果集
	private List<T> results;
	// 备注1
	private String remark1;
	// 备注2
	private String remark2;

	public int getPage() {
		return page;
	}

	public int getPages() {
		return pages;
	}

	public List<T> getResults() {
		return results;
	}

	public int getRows() {
		return rows;
	}

	public int getTotal() {
		return total;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setTotal(int total) {
		this.total = total;
		this.pages = total / rows + (total % rows > 0 ? 1 : 0);
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	
	
}

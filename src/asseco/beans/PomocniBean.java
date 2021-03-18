package asseco.beans;

import java.io.Serializable;
import java.util.ArrayList;

import asseco.dao.PomocniDAO;

public class PomocniBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sort;
	private String startPaging;
	private String endPaging;
	private String filterCreatedBy;
	
	
	
	@Override
	public String toString() {
		return "PomocniBean [sort=" + sort + ", startPaging=" + startPaging + ", endPaging=" + endPaging
				+ ", filterCreatedBy=" + filterCreatedBy + "]";
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getStartPaging() {
		return startPaging;
	}
	public void setStartPaging(String startPaging) {
		this.startPaging = startPaging;
	}
	public String getEndPaging() {
		return endPaging;
	}
	public void setEndPaging(String endPaging) {
		this.endPaging = endPaging;
	}
	public String getFilterCreatedBy() {
		return filterCreatedBy;
	}
	public void setFilterCreatedBy(String filterCreatedBy) {
		this.filterCreatedBy = filterCreatedBy;
	}
	public PomocniBean(String sort, String startPaging, String endPaging, String filterCreatedBy) {
		super();
		this.sort = sort;
		this.startPaging = startPaging;
		this.endPaging = endPaging;
		this.filterCreatedBy = filterCreatedBy;
	}
	public PomocniBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<String> getClients(){
		return PomocniDAO.getClients();
	}
	
	public ArrayList<String> getProject(){
		return PomocniDAO.getProject();
	}
	
	public ArrayList<String> getProductBSW(){
		return PomocniDAO.getProductBSW();
	}
	
	public void insertPorject(String projekat) {
		PomocniDAO.insertProject(projekat);
	}
	public void insertClient(String client) {
		PomocniDAO.insertClient(client);
	}
	
	
	

}

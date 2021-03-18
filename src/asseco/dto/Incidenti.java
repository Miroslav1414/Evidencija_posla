package asseco.dto;

import java.io.Serializable;

public class Incidenti implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String client;
	private String project;
	private String brojZahteva;
	private String taskType;
	private String taskName;
	private String work;
	private String startDate;
	private String createdBy;
	private String status;
	private String paymentStatus;
	private String productBSW;
	private String modificationDate;
	private String modifiedBy;
	
	
	
	
	public Incidenti(int id, String client, String project, String brojZahteva, String taskType, String taskName,
			String work, String startDate, String createdBy, String status, String paymentStatus, String productBSW,
			String modificationDate, String modifiedBy) {
		super();
		this.id = id;
		this.client = client;
		this.project = project;
		this.brojZahteva = brojZahteva;
		this.taskType = taskType;
		this.taskName = taskName;
		this.work = work;
		this.startDate = startDate;
		this.createdBy = createdBy;
		this.status = status;
		this.paymentStatus = paymentStatus;
		this.productBSW = productBSW;
		this.modificationDate = modificationDate;
		this.modifiedBy = modifiedBy;
	}
	public Incidenti() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Incidenti [id=" + id + ", client=" + client + ", project=" + project + ", brojZahteva=" + brojZahteva
				+ ", taskType=" + taskType + ", taskName=" + taskName + ", work=" + work + ", startDate=" + startDate
				+ ", createdBy=" + createdBy + ", status=" + status + ", paymentStatus=" + paymentStatus
				+ ", productBSW=" + productBSW + ", modificationDate=" + modificationDate + ", modifiedBy=" + modifiedBy
				+ "]";
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getBrojZahteva() {
		return brojZahteva;
	}
	public void setBrojZahteva(String brojZahteva) {
		this.brojZahteva = brojZahteva;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getProductBSW() {
		return productBSW;
	}
	public void setProductBSW(String productBSW) {
		this.productBSW = productBSW;
	}
	public String getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

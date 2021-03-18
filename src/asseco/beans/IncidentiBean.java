package asseco.beans;

import java.io.Serializable;
import java.util.ArrayList;

import asseco.dao.IncidentiDAO;
import asseco.dto.Incidenti;

public class IncidentiBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Incidenti> lista;
	private Incidenti inc;
	
	public  void setIncidentZaModifikaciju(String id) {
		this.setInc(IncidentiDAO.getIncidentById(id));		
	}

	
	
	public ArrayList<Incidenti> getLista() {
		return lista;
	}



	public void setLista(ArrayList<Incidenti> lista) {
		this.lista = lista;
	}



	public ArrayList<Incidenti> getTestIncidenti() {
		return IncidentiDAO.test();
	}
	
	public void getIncidents(String sort, String pocetak, String kraj, String filterByName) {
		
		String sortType = "";
		if ("&uarr;".equals(sort))
			sortType = "desc";
		else 
			sortType = "asc";
		
		
		if ("All".equals(filterByName))
			this.lista = IncidentiDAO.getIncidenti(sortType, pocetak, kraj);
		else 
			this.lista = IncidentiDAO.getIncidenti(sortType, pocetak, kraj, filterByName);
		
	}



	public Incidenti getInc() {
		return inc;
	}



	public void setInc(Incidenti inc) {
		this.inc = inc;
	}
	
	public void getIncidentsForReport() {
		this.lista = IncidentiDAO.getIncidentiForReport();
	}




}

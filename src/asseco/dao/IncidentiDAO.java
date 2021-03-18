package asseco.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import asseco.dto.Incidenti;

public class IncidentiDAO implements Serializable {

	/**
	 * 
	 */
	public static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final long serialVersionUID = 1L;
	public static final String TEST ="Select top 10 * from Incidenti";
	public static final String TEST2 ="SELECT *\r\n" + 
			"FROM (SELECT *,ROW_NUMBER() OVER (ORDER BY Id) AS RowNum FROM  incidenti ) sub\r\n" + 
			"WHERE RowNum between ? and ?";
	
	public static final String	GET_INCIDENTI = "SELECT sub.id, isnull(Client,'') as Client,Project,isnull([broj zahteva],'') as [Broj zahteva], [TaskType],TaskName,Work,convert(varchar, StartDate, 103) as StartDate,\r\n" + 
			"ImeiPrezime as [Created By], Status, isnull([Payment Status],'')as [Payment Status], isnull([Product BSW],'') as [Product BSW],\r\n" + 
			"convert(varchar, isnull(ModificationDate,StartDate), 103) as ModificationDate, isnull(imeiprezime2,imeiprezime) as ModifiedBy \r\n" + 
			"FROM (SELECT i.*,k.Imeiprezime as Imeiprezime, k2.ImeIprezime as ImeIprezime2 ,ROW_NUMBER() OVER (ORDER BY startDate ";
	
	public static final String GET_INCIDENTI_2 = ") AS RowNum FROM  incidenti i with (nolock) \r\n" + 
			"inner join korisnici k  with (nolock) on i.[created by] = k.id \r\n" + 
			"left join korisnici k2 with (nolock) on i.[modifiedby] = k2.id\r\n" + 
			"where k.Imeiprezime = ?) sub \r\n" + 
			"WHERE RowNum between ? and ?";
	
	public static final String GET_INCIDENTI_NOFILTER = "SELECT sub.id, isnull(Client,'') as Client,Project,isnull([broj zahteva],'') as [Broj zahteva], [TaskType],TaskName,Work,convert(varchar, StartDate, 103) as StartDate,\r\n" + 
			"ImeiPrezime as [Created By], Status, isnull([Payment Status],'')as [Payment Status], isnull([Product BSW],'') as [Product BSW],\r\n" + 
			"convert(varchar, isnull(ModificationDate,StartDate), 103) as ModificationDate, isnull(imeiprezime2,imeiprezime) as ModifiedBy \r\n" + 
			"FROM (SELECT i.*,k.Imeiprezime as Imeiprezime, k2.ImeIprezime as ImeIprezime2 ,ROW_NUMBER() OVER (ORDER BY startDate ";
			
	public static final String GET_INCIDENTI_NOFILTER_2 =") AS RowNum FROM  incidenti i with (nolock) \r\n" + 
			"inner join korisnici k with (nolock) on i.[created by] = k.id \r\n" + 
			"left join korisnici k2 with (nolock) on i.[modifiedby] = k2.id) sub \r\n" + 
			"WHERE RowNum between ? and ?";
	
	public static final String INSERT_INCIDENT = "  insert into Incidenti (Client,Project,[Broj zahteva],[TaskType],[TaskName],[Work],[StartDate],"
			+ "[Created By],[Status],[Payment Status],[Product BSW],[ModificationDate],[ModifiedBy]) \r\n" + 
			"  values (?,?,?,?,?,?,convert(datetime,?),?,?,?,?,convert(datetime,?),?)";
	
	public static final String GET_INCIDNET_ID = "select i.*,u.imeiPrezime as 'mod' , uk.imeiprezime as 'cre' from incidenti i left join korisnici u on i.modifiedBy = u.id  "
			+ "inner join korisnici uk  on uk.id = i.[created by] where i.id = ?";
	public static final String DELETE_INCIDENT_ID = "delete from incidenti where id = ?";
	
	public static final String UPDATE_INCIDENT_ID = "update incidenti set Client = ?,Project  = ?,[Broj zahteva] = ?,[TaskType]  = ?"
			+ ",[TaskName]  = ?,[Work] = ?,[StartDate]  = ?,[Status]  = ? ,[Payment Status]  = ?,[Product BSW]  = ?"
			+ ",[ModificationDate]  = ?,[ModifiedBy]  = ?  where id = ?";
	
	public static final String GET_INCIDENTI_REPORT = " select  i.id, isnull(Client,'') as Client,Project,isnull([broj zahteva],'') as [Broj zahteva], [TaskType],TaskName,Work,convert(varchar, StartDate, 101) as StartDate, \r\n" + 
			"			 uk.ImeiPrezime as [Created By], Status, isnull([Payment Status],'')as [Payment Status], isnull([Product BSW],'') as [Product BSW], \r\n" + 
			"			 convert(varchar, isnull(ModificationDate,StartDate), 101) as ModificationDate, isnull(u.imeiprezime,uk.imeiprezime) as ModifiedBy \r\n" + 
			"			 \r\n" + 
			"			 from incidenti i \r\n" + 
			"			 left join korisnici u on i.modifiedBy = u.id \r\n" + 
			"			 inner join korisnici uk  on uk.id = i.[created by] \r\n" + 
			"			 where status = 'new' order by uk.ImeiPrezime asc, i.StartDate desc";
	public static final String UPDATE_STATUS = "update incidenti set status = ? where id = ?";
	
	public static void deleteIncident(String id) {
		Connection conn = null;
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = conn.prepareStatement(DELETE_INCIDENT_ID);
			ps.setObject(1, id);
			ps.executeUpdate();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
	}
	
	public static void updateStatus(String id,String status) {
		Connection conn = null;
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = conn.prepareStatement(UPDATE_STATUS);
			ps.setObject(1, status);
			ps.setObject(2, id);
			ps.executeUpdate();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
	}
	
	
	
	public static Incidenti getIncidentById (String id) {
		Incidenti inc = null;
		Connection conn = null;
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = conn.prepareStatement(GET_INCIDNET_ID);
			ps.setObject(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				inc = new Incidenti(rs.getInt("id"),rs.getString("client"),rs.getString("project"),rs.getString("broj zahteva"),rs.getString("tasktype"),rs.getString("taskname"),
						rs.getString("work"),rs.getString("startdate"),rs.getString("cre"),rs.getString("Status"),rs.getString("payment status"),rs.getString("product bsw"),
						rs.getString("modificationdate"), rs.getString("mod"));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return inc;
	}
	
	public static void insertIncident(String client, String project,String brojZahtjeva,String taskType, String taskName, String work, String startDate,
			int createdBy, String status,String paymentStatus, String productBSW) {
			Connection conn = null;
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = conn.prepareStatement(INSERT_INCIDENT);
			ps.setObject(1, client);
			ps.setObject(2, project);
			ps.setObject(3, brojZahtjeva);
			ps.setObject(4, taskType);
			ps.setObject(5, taskName);
			ps.setObject(6, work);
			ps.setObject(7, startDate);
			ps.setObject(8, createdBy);
			ps.setObject(9, status);
			ps.setObject(10, paymentStatus);
			ps.setObject(11, productBSW);
			ps.setObject(12, startDate);
			ps.setObject(13, createdBy);
			
			ps.executeUpdate();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		//System.out.println("uspjesan upis incidenta");
	}
	
	public static void updateIncident(String client, String project,String brojZahtjeva,String taskType, String taskName, String work, String startDate,
			 String status,String paymentStatus, String productBSW,int modifiedBy  ,int id) {
			Connection conn = null;
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = conn.prepareStatement(UPDATE_INCIDENT_ID);
			ps.setObject(1, client);
			ps.setObject(2, project);
			ps.setObject(3, brojZahtjeva);
			ps.setObject(4, taskType);
			ps.setObject(5, taskName);
			ps.setObject(6, work);
			ps.setObject(7, startDate);
			ps.setObject(8, status);
			ps.setObject(9, paymentStatus);
			ps.setObject(10, productBSW);
			ps.setObject(11, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			ps.setObject(12, modifiedBy);
			ps.setObject(13, id);
			
			ps.executeUpdate();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		System.out.println("uspjesan update incidenta");
	}
	
	public static ArrayList<Incidenti> test() {
		Connection conn = null;
		ArrayList<Incidenti> rezultat = new ArrayList<Incidenti>();
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = conn.prepareStatement(TEST);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rezultat.add(new Incidenti(rs.getInt(12),
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(11),
						rs.getString(7),
						rs.getString(12)));
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return rezultat;
	}	
	
	public static ArrayList<Incidenti> paging(String pocetak, String kraj) {
		Connection conn = null;
		ArrayList<Incidenti> rezultat = new ArrayList<Incidenti>();
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = conn.prepareStatement(TEST2);
			ps.setObject(1, pocetak);
			ps.setObject(2, kraj);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rezultat.add(new Incidenti(rs.getInt(12),
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(11),
						rs.getString(7),
						rs.getString(12)));
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return rezultat;
	}
	
	public static ArrayList<Incidenti> getIncidentiForReport(){
		Connection conn = null;
		ArrayList<Incidenti> rezultat = new ArrayList<Incidenti>();
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = conn.prepareStatement(GET_INCIDENTI_REPORT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rezultat.add(new Incidenti(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(11),
						rs.getString(12),
						rs.getString(13),
						rs.getString(14)));
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return rezultat;
	}
	
	public static ArrayList<Incidenti> getIncidenti(String sort,String pocetak, String kraj, String filterByName) {
		Connection conn = null;
		ArrayList<Incidenti> rezultat = new ArrayList<Incidenti>();
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = conn.prepareStatement(GET_INCIDENTI + sort + GET_INCIDENTI_2);
			ps.setObject(2, pocetak);
			ps.setObject(3, kraj);
			ps.setObject(1, filterByName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rezultat.add(new Incidenti(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(11),
						rs.getString(12),
						rs.getString(13),
						rs.getString(14)));
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return rezultat;
	}
	
	
	public static ArrayList<Incidenti> getIncidenti(String sort,String pocetak, String kraj) {
		Connection conn = null;
		ArrayList<Incidenti> rezultat = new ArrayList<Incidenti>();
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = conn.prepareStatement(GET_INCIDENTI_NOFILTER + sort + GET_INCIDENTI_NOFILTER_2);
			ps.setObject(1, pocetak);
			ps.setObject(2, kraj);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rezultat.add(new Incidenti(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(11),
						rs.getString(12),
						rs.getString(13),
						rs.getString(14)));
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return rezultat;
	}

}

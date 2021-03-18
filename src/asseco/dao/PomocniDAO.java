package asseco.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PomocniDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	public static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	public static final String GET_CLIENT = "select imeKlijenta from client";
	public static final String GET_PROJECT = "select ImeProjekta from project order by ImeProjekta";
	public static final String GET_PRODUCT_BSW = "select ProductBSWName from productBsw";
	public static final String INSERT_PROJECT = "insert into project (id,imeProjekta) values ((  select max(id)+1 from project),?)";
	public static final String INSERT_CLIENT = "insert into client (id,imeKlijenta) values ((  select max(id)+1 from client),?)";
	
	
	public static void insertClient(String cleint) {
		Connection conn = null;
	try {
		conn = connectionPool.checkOut();
		PreparedStatement ps = conn.prepareStatement(INSERT_CLIENT);
		ps.setObject(1, cleint);			
		ps.executeUpdate();
		
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		connectionPool.checkIn(conn);
	}
}
	
	public static void insertProject(String projekat) {
			Connection conn = null;
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = conn.prepareStatement(INSERT_PROJECT);
			ps.setObject(1, projekat);			
			ps.executeUpdate();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
	}
	
	public static ArrayList<String> getClients() {
		Connection conn = null;
		ArrayList<String> mapa = new ArrayList<String>();
		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = conn.prepareStatement(GET_CLIENT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				mapa.add(rs.getString(1));
			}
			rs.close();
			ps.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return mapa;
		
	}
	
	public static ArrayList<String> getProject() {
		Connection conn = null;
		ArrayList<String> mapa = new ArrayList<String>();
		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = conn.prepareStatement(GET_PROJECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				mapa.add(rs.getString(1));
			}
			rs.close();
			ps.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return mapa;
		
	}
	
	public static ArrayList<String> getProductBSW() {
		Connection conn = null;
		ArrayList<String> mapa = new ArrayList<String>();
		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = conn.prepareStatement(GET_PRODUCT_BSW);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				mapa.add(rs.getString(1));
			}
			rs.close();
			ps.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return mapa;
		
	}

}

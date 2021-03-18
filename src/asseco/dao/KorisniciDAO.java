package asseco.dao;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import asseco.dto.Korisnici;

public class KorisniciDAO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	public static final String TEST ="Select top 10 * from Korisnici";
	public static final String LOGIN = "Select username,lozinka,imeiprezime,admin,id from Korisnici where username = ? COLLATE SQL_Latin1_General_CP1_CS_AS"
			+ " and lozinka = ? COLLATE SQL_Latin1_General_CP1_CS_AS";
	public static final String SVI_KORISNICI = "select imeiprezime from korisnici";
	public static final String PROMJENA_LOZINKE = "update korisnici set lozinka = ? where id = ?";
	public static final String INSERT_KORISNIK = "insert into korisnici (username,imeiprezime,admin,lozinka) values (?,?,?,'e10adc3949ba59abbe56e057f20f883e')";
	public static final String USERNAME_EXISTS = "select id from korisnici where username = ?";
	
	public static boolean usernameExists(String username) {
		boolean rez = false;
		Connection conn = null;
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = conn.prepareStatement(USERNAME_EXISTS);
			ps.setObject(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return true;
			}
			
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return rez;
	}
	
	
	public static void insertKorisnik(String username,String imeIPrezime, int admin) {
		Connection conn = null;
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = conn.prepareStatement(INSERT_KORISNIK);
			ps.setObject(1, username);	
			ps.setObject(2, imeIPrezime);
			ps.setObject(3, admin);
			ps.executeUpdate();
			
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
	}
	public static void changePassword(int id, String password) {		
		Connection conn = null;		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = conn.prepareStatement(PROMJENA_LOZINKE);
			
	        StringBuffer hexString = null;
			try {
				MessageDigest mdAlgorithm = MessageDigest.getInstance("MD5");
				mdAlgorithm.update(password.getBytes());

				byte[] digest = mdAlgorithm.digest();
				hexString = new StringBuffer();

				for (int i = 0; i < digest.length; i++) {
					password = Integer.toHexString(0xFF & digest[i]);

				    if (password.length() < 2) {
				    	password = "0" + password;
				    }

				    hexString.append(password);
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			ps.setObject(1, hexString.toString());
	        ps.setObject(2, id);
	        ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
	}
	
	public static Korisnici login(String username, String password) {
		Korisnici korisnik = null;
		Connection conn = null;
		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = conn.prepareStatement(LOGIN);
			
	        StringBuffer hexString = null;
			try {
				MessageDigest mdAlgorithm = MessageDigest.getInstance("MD5");
				mdAlgorithm.update(password.getBytes());

				byte[] digest = mdAlgorithm.digest();
				hexString = new StringBuffer();

				for (int i = 0; i < digest.length; i++) {
					password = Integer.toHexString(0xFF & digest[i]);

				    if (password.length() < 2) {
				    	password = "0" + password;
				    }

				    hexString.append(password);
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	        ps.setObject(1, username);
			ps.setObject(2, hexString.toString());
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				korisnik = new Korisnici(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		return korisnik;	
	}
	
	public static ArrayList<String> sviKorisnici (){
		ArrayList<String> lista = new ArrayList<String>();
		Connection conn = null;
		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = conn.prepareStatement(SVI_KORISNICI);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lista.add((String)rs.getObject(1));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return lista;
	}

}

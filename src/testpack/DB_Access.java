package testpack;

import java.sql.*;
import java.util.ArrayList;


public class DB_Access {
	private String url = "jdbc:mysql://localhost:3306/test";
	private String driver = "com.mysql.jdbc.Driver";
	private String uname = "root";
	private String upass = "";
	
	private Connection c;
	private Statement st;
	private PreparedStatement pst;
	
//constructor
	public DB_Access() {
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(url, uname, upass);
			st = c.createStatement();
		} catch (Exception e) {
			System.out.println(e.toString());
		} 
	}
	
//validate login
	public int validateLogin(String un, String up) {
		int uid = -1; // -1 is for invalid user login
		
		String sql = "select uid from t_users where LoginName = ? and LoginPass = ?";
		try {
			pst = c.prepareStatement(sql);
			pst.setString(1, un);
			pst.setString(2, up);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				uid = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return uid;
	}
	
//get username
	public String getUserName(int uid) {
		String sql = "select name from t_users where uid = " + uid;
		String uname = "";
		try {
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) uname = rs.getString(1);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return uname;
	}
	
//get user items
	public ArrayList<Item> getAllUserItems(int uid) {
		ArrayList<Item> all = new ArrayList<Item>();
		
		String sql = "select iid, itemname, qty from t_items where uid = " + uid;
		
		try {
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Item i = new Item(rs.getInt(1), rs.getString(2), rs.getInt(3));
				all.add(i);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
			
		return all;
	}
	
//create account
	public int createUserAccount(User u) {
		// 0 means everything is OK, user is created
		// 1 means values are too long
		// 2 means unique constraint on the login name has been violated
		// 3 means that an empty form field was submitted
		// 4 means that the passwords are not the same
		int status = 0;
		
		if(u.getLoginName().trim().equals("") || 
				u.getName().trim().equals("") || 
				u.getLoginPass1().trim().equals("") ||
				u.getLoginPass2().trim().equals("")) return 3;
		if(u.getLoginName().trim().length() > 20 || 
				u.getName().trim().length() > 20 || 
				u.getLoginPass1().trim().length()> 20 ||
				u.getLoginPass2().trim().length()>20) return 1;
		
		if(!u.getLoginPass1().trim().equals(u.getLoginPass2().trim())) return 4;
		
		String sql = "insert into t_users (LoginName, Name, LoginPass) values (?, ?, ?)";
		
		try {
			pst = c.prepareStatement(sql);
			pst.setString(1, u.getLoginName());
			pst.setString(2, u.getName());
			pst.setString(3, u.getLoginPass1());
			try{pst.executeUpdate();}
			catch(Exception MySQLIntegrityConstraintViolationException) {
				status = 5;
				return status;
			}
		} catch (SQLException e) {
			status = 2;
			System.out.println(e.toString());
		}

		return status;
	}
	
//add item	
	public int addItem(String iname, String iqty, Integer uid) {
		int res = 0;
		// 0 - OK - item was inserted
		// 1 - item name was not given
		// 2 - item qty was either not given or not a valid int
		int qty = 0;
		if(iname == null || iname.trim().equals("")) return 1;
		if(iqty == null || iqty.trim().equals("")) return 1;
		try {
			qty = Integer.parseInt(iqty);
		}catch(Exception e) {return 2;}
		
		String sql = "insert into t_items (ItemName, Qty, uid) values (?, ?, ?)";
		try {
			pst = c.prepareStatement(sql);
			pst.setString(1, iname);
			pst.setInt(2, qty);
			pst.setInt(3, uid);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return res;
	}

//view item
	public Item getItem(int id) {
		Item item = new Item();
		String sql = "select iid, itemname, qty from t_items where iid = " + id;
		
		try {
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				item = new Item(rs.getInt(1), rs.getString(2), rs.getInt(3));
				
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return item;
	}
	
//edit item
	public int editItem(String iname, String iqty, int iid) {
		
		int result = 0;
		
		// 0 - OK - item was inserted
		// 1 - item name was not given
		// 2 - item qty was either not given or not a valid int
		int qty = 0;
		if(iname == null || iname.trim().equals("")|| iqty == null || iqty.trim().equals("")) {
			result = 1;
			return result;
		} 
		
		try {
			qty = Integer.parseInt(iqty);
		}catch(Exception e) {
			result = 2;
			return result;
			}
		String sql = "update t_items set ItemName = ?, Qty = ? where iid = ?";
		
		try {
			pst = c.prepareStatement(sql);
			pst.setString(1, iname);
			pst.setInt(2, qty);
			pst.setInt(3, iid);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return result;
	}

//delete item
	public void deleteItem(int id) {
		String sql = "delete from t_items where iid = ?";
				
		try {
			pst = c.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
	}
	
//change username
	public void changeUsername(int uid, String username) {
		String sql = "update t_users set Name = ? where uid = ?";
		try {
			pst = c.prepareStatement(sql);
			pst.setString(1, username);
			pst.setInt(2, uid);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//get loginname
	public String getLoginName(int uid) {
		String sql = "select LoginName from t_users where uid = " + uid;
		String loginname = "";
		try {
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) loginname = rs.getString(1);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return loginname;
	}
//change username
	public int changeLoginname(int uid, String loginname) {
		int result =0;
		
		String sql = "update t_users set LoginName = ? where uid = ?";
		try {
			pst = c.prepareStatement(sql);
			pst.setString(1, loginname);
			pst.setInt(2, uid);
			try{pst.executeUpdate();}
			catch(Exception e){
				result =1;
				return result;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
//update password
	public int updatePassword(String currentPassword, String newPassword1, String newPassword2, int uid) {
		int status = 0;
		//0 - success
		//1 - password is not correct
		//2 - new passwords do not match
		
		String sql = "select LoginPass from t_users where uid = "+uid;

		
		try {
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				String password = rs.getString(1);
				 if(!password.equals(currentPassword)) {
						status =1;
						return status;
					}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(!newPassword1.equals(newPassword2)) {
			status = 2;
			return status;
		}
		
		sql = "update t_users set LoginPass= '"+newPassword1+"' where uid ="+uid;
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
	
}













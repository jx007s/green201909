package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CenterDAO {
	
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	String sql;
	
	public CenterDAO() {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:/comp/env/mmm");
			
			con = ds.getConnection();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public int total(){
		int res =0;
		
		try {
			sql = "select count(*) from center";
			ptmt = con.prepareStatement(sql);
			
			rs = ptmt.executeQuery();
			
			rs.next();
			
			res = rs.getInt(1);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		
		return res;
	}
	
	
	
	
	public ArrayList<CenterDTO> list(int start, int limit){
		ArrayList<CenterDTO> res = new ArrayList<CenterDTO>();
		
		try {
			sql = "select * from center "
					+ " order by gid desc, seq "
					+ " limit ? , ?";
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, start);
			ptmt.setInt(2, limit);
			
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				CenterDTO dto = new CenterDTO();
				dto.setId(rs.getInt("id"));
				dto.setGid(rs.getInt("gid"));
				dto.setSeq(rs.getInt("seq"));
				dto.setLev(rs.getInt("lev"));
				dto.setCnt(rs.getInt("cnt"));
				
				dto.setTitle(rs.getString("title"));
				dto.setPname(rs.getString("pname"));
				
				dto.setRegdate(rs.getTimestamp("regdate"));
				
				
				res.add(dto);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		
		return res;
	}
	
	
	
	
	public void addCount(int id){	
		try {
			
			sql = "update center set cnt = cnt +1 where id = ?";
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, id);
			
			ptmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	
	public CenterDTO detail(int id){
		CenterDTO dto = null;
		
		try {
			sql = "select * from center where id = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				dto = new CenterDTO();
				dto.setId(rs.getInt("id"));
				dto.setGid(rs.getInt("gid"));
				dto.setSeq(rs.getInt("seq"));
				dto.setLev(rs.getInt("lev"));
				dto.setCnt(rs.getInt("cnt"));
				
				dto.setTitle(rs.getString("title"));
				dto.setPname(rs.getString("pname"));
				
				dto.setRegdate(rs.getTimestamp("regdate"));
				dto.setContent(rs.getString("content"));
				dto.setUpfile(rs.getString("upfile"));
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		
		return dto;
	}
	
	
	
	public void insert(CenterDTO dto){
		 
		
		try {
			//id의 가장 큰값 가져와 +1 하여 id 구하기
			sql = "select max(id) from center";
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			rs.next();
			dto.setId(rs.getInt(1)+1);
			
			
			///저장
			sql = "insert into center (id,gid,lev,seq, cnt, title, pname, pw, content, upfile, regdate  , cate) "
					         + "values( ?  ,?  ,0  ,0 , -1 ,   ?  ,   ?,    ? ,    ?,      ?,   sysdate(),'notice' ) ";
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, dto.id);
			ptmt.setInt(2, dto.id);
			
			ptmt.setString(3, dto.title);
			ptmt.setString(4, dto.pname);
			ptmt.setString(5, dto.pw);
			ptmt.setString(6, dto.content);
			ptmt.setString(7, dto.upfile);
			ptmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		
	}
	
	
	public CenterDTO fileSch(CenterDTO dto){
		 
		CenterDTO res = null;
		try {
			
			sql = "select * from center where id = ? and pw = ?";
			ptmt = con.prepareStatement(sql);

			ptmt.setInt(1, dto.id);
			ptmt.setString(2, dto.pw);
			
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				res = new CenterDTO();
				res.setUpfile(rs.getString("upfile"));
				
				sql = "update center set  upfile = null "
				         + "where id = ? ";
				ptmt = con.prepareStatement(sql);
				
				ptmt.setInt(1, dto.id);
				ptmt.executeUpdate();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return res;
	}
	
	
	
	public int delete(CenterDTO dto){
		 
		int res =0;
		try {
			
			sql = "delete from center where id = ? and pw = ?";
			ptmt = con.prepareStatement(sql);

			ptmt.setInt(1, dto.id);
			ptmt.setString(2, dto.pw);
			
			res = ptmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return res;
	}
	
	
	
	public int modify(CenterDTO dto){
		 
		int res = 0;
		try {

			///수정
			sql = "update center set title = ?, pname = ?, content = ? , upfile = ? "
					         + "where id = ? and pw = ? ";
			ptmt = con.prepareStatement(sql);
			
			ptmt.setString(1, dto.title);
			ptmt.setString(2, dto.pname);
			ptmt.setString(3, dto.content);
			ptmt.setString(4, dto.upfile);
			
			ptmt.setInt(5, dto.id);
			ptmt.setString(6, dto.pw);
			
			res = ptmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return res;
	}
	
	
	
	public void reply(CenterDTO dto){
		 
		
		try {
			
			
			////기존글 업데이트
			sql = "update center set seq = seq +1 where "
			         + " gid = ? and seq > ? ";
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, dto.gid);
			ptmt.setInt(2, dto.seq);
			ptmt.executeUpdate();
			
			
			///새글
			sql = "insert into center (gid,lev,seq, cnt,  pname, pw, content, title, regdate  , cate) "
					         + "values(?    ,?  ,? , -1 ,   ?  , ?,    ? ,    ?,    sysdate(),'notice' ) ";
			ptmt = con.prepareStatement(sql);
			
			
			ptmt.setInt(1, dto.gid);
			ptmt.setInt(2, dto.lev+1);
			ptmt.setInt(3, dto.seq+1);
			ptmt.setString(4, dto.pname);
			ptmt.setString(5, dto.pw);
			ptmt.setString(6, dto.content);
			ptmt.setString(7, dto.title);
			ptmt.executeUpdate();

			//현재 id 구하기
			sql = "select max(id) from center";
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			rs.next();
			dto.setId(rs.getInt(1));
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		
	}
	
	
	
	public void close() {
		
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(ptmt!=null) try {ptmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
		
	}

}

package com.study.board.service;

import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class MemberService {
    public int login(String userID,String userPassword){
        int insertCount=0;
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mariadb://localhost:3306/board","root","kkjjss103@");
            String SQL="SELECT userpassword FROM member WHERE userid=?";
            ps=conn.prepareStatement(SQL);
            ps.setString(1,userID);
            rs=ps.executeQuery();
            if(rs.next()){
                if (rs.getString(1).equals(userPassword)) {
                    return 1;
                }
                else {
                    return insertCount;
                }
            }
            return -1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return -2;
    }

}

package com.reachyourdream.reebot.database;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.Calendar;

public class PostgreAdapter {

    private String DB_URL;
    private String DB_USERNAME;
    private String DB_PASSWORD;
    private Connection c;

    public PostgreAdapter(String DB_URL, String DB_USERNAME, String DB_PASSWORD) {
        this.DB_URL = DB_URL;
        this.DB_USERNAME = DB_USERNAME;
        this.DB_PASSWORD = DB_PASSWORD;
    }

    private Connection createConnection() {
        try {
            return DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConnection(){
        if(c == null){
            c = createConnection();
        }
        return c;
    }

    public ResultSet select(String sql, Object... params) throws SQLException {
        PreparedStatement query;
        query = getConnection().prepareStatement(sql);
        resolveParameters(query, params);
        return query.executeQuery();
    }

    public int query(String sql) throws SQLException {
        try (Statement statement = getConnection().createStatement()){
            return statement.executeUpdate(sql);
        }
    }

    public int query(String sql, Object... params) throws SQLException {
        try(PreparedStatement query = getConnection().prepareStatement(sql)){
            resolveParameters(query, params);
            return query.executeUpdate();
        }
    }

    public int insert(String sql, Object... params) throws SQLException {
        try (PreparedStatement query = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            resolveParameters(query, params);
            query.executeUpdate();
            ResultSet rs = query.getGeneratedKeys();

            if(rs.next()){
                return rs.getInt(1);
            }
        }
        return -1;
    }


    private void resolveParameters(PreparedStatement query, Object... params) throws SQLException {
        int index = 1;
        for (Object p : params){
            if (p instanceof String){
                query.setString(index, (String) p);
            } else if (p instanceof Integer){
                query.setInt(index, (int) p );
            } else if (p instanceof Long){
                query.setLong(index, (Long) p);
            } else if (p instanceof Double) {
                query.setDouble(index, (double) p);
            } else if (p instanceof java.sql.Date){
                java.sql.Date d = (java.sql.Date) p;
                Timestamp ts = new Timestamp(d.getTime());
                query.setTimestamp(index, ts);
            } else if (p instanceof Calendar){
                Calendar cal = (Calendar) p;
                Timestamp ts = new Timestamp(cal.getTimeInMillis());
                query.setTimestamp(index, ts);
            } else if (p == null){
                query.setNull(index, Types.NULL);
            }
            index++;
        }
    }


}

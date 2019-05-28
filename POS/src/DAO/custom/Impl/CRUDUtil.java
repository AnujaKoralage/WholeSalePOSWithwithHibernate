package DAO.custom.Impl;

import UtilityClasses.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CRUDUtil {

    public static <T> T execute(String sql,Object...params) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i=0;i<params.length;i++){
            preparedStatement.setObject(i+1,params[i]);
        }
        return (sql.trim().startsWith("SELECT")) ? (T)preparedStatement.executeQuery(): (T)(Boolean)(preparedStatement.executeUpdate()>0);
    }

}

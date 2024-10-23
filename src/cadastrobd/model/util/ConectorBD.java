package cadastrobd.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class ConectorBD {
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        final String url = "jdbc:sqlserver://localhost:1433;databaseName=loja;encrypt=true;trustServerCertificate=true";
        final String user = "loja";
        final String password = "loja";
        return DriverManager.getConnection(url, user, password);
    }
    
    public static PreparedStatement getPrepared(Connection conexao, String sql) throws SQLException{
        return conexao.prepareStatement(sql);
    }  
    
    public static ResultSet getSelect(PreparedStatement ps) throws SQLException, ClassNotFoundException{
        return ps.executeQuery();
    }
    
    public static void close(ResultSet consulta) throws SQLException{
        if(!consulta.isClosed()){
            consulta.close();
        }
    }
    
    public static void close(PreparedStatement st) throws SQLException{
        if(!st.isClosed()){
            st.close();
        }
    }
    
    public static void close(Connection conexao) throws SQLException{
        if(conexao.isClosed()){
            conexao.close();
        }
    }    
}
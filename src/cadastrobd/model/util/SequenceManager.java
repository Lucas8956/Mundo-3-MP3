package cadastrobd.model.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager {
    
    public static int getValue(String sequencia_nome) throws SQLException, ClassNotFoundException{
        int id = 0;
        //Conectando com o banco
        Connection conexao = ConectorBD.getConnection();
        //System.out.println("Conexão criada");
        
        //Criando preparedStatement
        String sql = "SELECT NEXT VALUE FOR " + sequencia_nome + " AS ID;";
        PreparedStatement ps = ConectorBD.getPrepared(conexao, sql);
        //System.out.println("PreparedStatement criado");
            
        //Recuperando valor
        ResultSet consulta = ConectorBD.getSelect(ps);
        //System.out.println("consulta retornada");            
        if(consulta.next()){
            id = consulta.getInt("id");                
        }
        else{
            System.out.println("Valor não encontrado");
        }
            
        //Fechando conexões
        ConectorBD.close(consulta);
        ConectorBD.close(ps);
        ConectorBD.close(conexao);
        //System.out.println("conexões fechadas");
            
        return id;
    }
}
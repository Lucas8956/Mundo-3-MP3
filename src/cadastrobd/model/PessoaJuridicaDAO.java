package cadastrobd.model;

import cadastrobd.model.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PessoaJuridicaDAO {
    
    public static PessoaJuridica getPessoa(int id) throws SQLException, ClassNotFoundException{
        PessoaJuridica pessoa = new PessoaJuridica();
        
        //Conectando com o banco
        Connection conexao = ConectorBD.getConnection();
        //System.out.println("conexão criada");
           
        //Criando preparedStatement
        String sql = "SELECT p.idPessoa, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pj.cnpj\n" +
"FROM Pessoa p JOIN PessoaJuridica pj ON (p.idPessoa = pj.idPessoa) WHERE p.idPessoa = ?;";
        PreparedStatement ps = ConectorBD.getPrepared(conexao, sql);
        //System.out.println("preparedStatement criado");
           
        //Consultando o banco
        ps.setInt(1, id);
        ResultSet consulta = ConectorBD.getSelect(ps);
        //System.out.println("consulta retornada");
           
        //Criando objeto pessoaJuridica
        if(consulta.next()){               
            String nome = consulta.getString("nome");
            String logradouro = consulta.getString("logradouro");
            String cidade = consulta.getString("cidade");
            String estado = consulta.getString("estado");
            String telefone = consulta.getString("telefone");
            String email = consulta.getString("email");
            String cnpj = consulta.getString("cnpj");
            pessoa = new PessoaJuridica(id, nome, logradouro, cidade, estado, telefone, email, cnpj);
            //System.out.println("Objeto criado");
        }
        else{
            System.out.println("Pessoa não existe.");
        }
                  
        //Fechando conexões
        ConectorBD.close(consulta);
        ConectorBD.close(ps);
        ConectorBD.close(conexao);
        //System.out.println("conexões fechadas");
        
        return pessoa;
    }
    
    public static ArrayList<PessoaJuridica> getPessoas() throws SQLException, ClassNotFoundException{
        ArrayList<PessoaJuridica> lista = new ArrayList<PessoaJuridica>();
        
        //Conectando com o banco
        Connection conexao = ConectorBD.getConnection();
        //System.out.println("conexão criada");
            
        //Criando preparedStatement
        String sql = "SELECT p.idPessoa, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pj.cnpj\n" +
"FROM Pessoa p JOIN PessoaJuridica pj ON (p.idPessoa = pj.idPessoa);";
        PreparedStatement ps = ConectorBD.getPrepared(conexao, sql);
        //System.out.println("preparedStatement criado");
            
        //Consultando o banco
        ResultSet consulta = ConectorBD.getSelect(ps);
        //System.out.println("consulta retornada");
            
        //Preenchendo lista
        while(consulta.next()){
            int id = consulta.getInt("idPessoa");
            String nome = consulta.getString("nome");
            String logradouro = consulta.getString("logradouro");
            String cidade = consulta.getString("cidade");
            String estado = consulta.getString("estado");
            String telefone = consulta.getString("telefone");
            String email = consulta.getString("email");
            String cnpj = consulta.getString("cnpj");
            lista.add(new PessoaJuridica(id, nome, logradouro, cidade, estado, telefone, email, cnpj));
        }
            
        //Fechando conexões
        ConectorBD.close(consulta);
        ConectorBD.close(ps);
        ConectorBD.close(conexao);
        //System.out.println("conexões fechadas");
        
        return lista;
    }
    
    public static void incluir(PessoaJuridica pessoa) throws SQLException, ClassNotFoundException{
        //Pegando o próximo id da sequência
        int id = SequenceManager.getValue("sequenciaIdPessoa");
            
        if(id != 0){
            //Conectando com o banco
            Connection conexao = ConectorBD.getConnection();
            //System.out.println("conexão criada");            
            
            //Início
            conexao.setAutoCommit(false);
            
            //Adicionando registro a tabela Pessoa
            String sqlPessoa = "INSERT INTO Pessoa (idPessoa, nome, logradouro, cidade, estado, telefone, email)\n" +
"VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement psPessoa = ConectorBD.getPrepared(conexao, sqlPessoa);
            psPessoa.setInt(1, id);
            psPessoa.setString(2, pessoa.get_nome());
            psPessoa.setString(3, pessoa.get_logradouro());
            psPessoa.setString(4, pessoa.get_cidade());
            psPessoa.setString(5, pessoa.get_estado());
            psPessoa.setString(6, pessoa.get_telefone());
            psPessoa.setString(7, pessoa.get_email());
            psPessoa.executeUpdate();
           
            //Adicionando registro a tabela PessoaJuridica
            String sqlPessoaJuridica = "INSERT INTO PessoaJuridica (idPessoa, cnpj) VALUES (?, ?);";
            PreparedStatement psPessoaJuridica = ConectorBD.getPrepared(conexao, sqlPessoaJuridica);
            psPessoaJuridica.setInt(1,id);
            psPessoaJuridica.setString(2,pessoa.get_cnpj());
            psPessoaJuridica.executeUpdate();
            
            conexao.commit();     
            //Fim
            
            System.out.println("Inclusão concluida");
            
            //Fechando conexões
            ConectorBD.close(psPessoa);
            ConectorBD.close(psPessoaJuridica);
            ConectorBD.close(conexao);
            //System.out.println("conexões fechadas");
        }
        else{
            System.out.println("Pessoa não incluída no registro, problema no id");
        }
    }
    
    public static void alterar(PessoaJuridica pessoa) throws SQLException, ClassNotFoundException{
        //Conectando com o banco
        Connection conexao = ConectorBD.getConnection();
        //System.out.println("conexão criada");
                        
        //Início
        conexao.setAutoCommit(false);
            
        //Atualizando tabela Pessoa
        String sqlPessoa = "UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE idPessoa = ?;";
        PreparedStatement psPessoa = ConectorBD.getPrepared(conexao, sqlPessoa);
        psPessoa.setString(1, pessoa.get_nome());
        psPessoa.setString(2, pessoa.get_logradouro());
        psPessoa.setString(3, pessoa.get_cidade());
        psPessoa.setString(4, pessoa.get_estado());
        psPessoa.setString(5, pessoa.get_telefone());
        psPessoa.setString(6, pessoa.get_email());
        psPessoa.setInt(7, pessoa.get_id());            
        psPessoa.executeUpdate();
            
        //Atualizando tabela PessoaJuridica
        String sqlPessoaJuridica = "UPDATE PessoaJuridica SET cnpj = ? WHERE idPessoa = ?;";
        PreparedStatement psPessoaJuridica = ConectorBD.getPrepared(conexao, sqlPessoaJuridica);
        psPessoaJuridica.setString(1,pessoa.get_cnpj());
        psPessoaJuridica.setInt(2,pessoa.get_id());            
        psPessoaJuridica.executeUpdate();
            
        conexao.commit();   
        //Fim
            
        System.out.println("Atualização concluida");
           
        //Fechando conexões
        ConectorBD.close(psPessoa);
        ConectorBD.close(psPessoaJuridica);
        ConectorBD.close(conexao);
        //System.out.println("conexões fechadas");
    }
    
    public static void excluir(int id) throws SQLException, ClassNotFoundException{
        //Conectando com o banco
        Connection conexao = ConectorBD.getConnection();
        //System.out.println("conexão criada");
            
        //Início
        conexao.setAutoCommit(false);
            
        //Excluindo registro da tabela PessoaJuridica
        String sqlPessoaJuridica = "DELETE FROM PessoaJuridica WHERE idPessoa = ?;";
        PreparedStatement psPessoaJuridica = ConectorBD.getPrepared(conexao, sqlPessoaJuridica);
        psPessoaJuridica.setInt(1,id); 
        psPessoaJuridica.executeUpdate();
            
        //Excluindo registro da tabela Pessoa
        String sqlPessoa = "DELETE FROM Pessoa WHERE idPessoa = ?";
        PreparedStatement psPessoa = ConectorBD.getPrepared(conexao, sqlPessoa);
        psPessoa.setInt(1, id);       
        psPessoa.executeUpdate();
           
        conexao.commit();   
        //Fim            
            
        System.out.println("Exclusão concluida");
           
        //Fechando conexões
        ConectorBD.close(psPessoa);
        ConectorBD.close(psPessoaJuridica);
        ConectorBD.close(conexao);
        //System.out.println("conexões fechadas");
    }   
}
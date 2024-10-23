package cadastrobd.model;

import cadastrobd.model.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PessoaFisicaDAO {
    
    public static PessoaFisica getPessoa(int id) throws SQLException, ClassNotFoundException{
        PessoaFisica pessoa = new PessoaFisica();
        
        //Conectando com o banco
        Connection conexao = ConectorBD.getConnection();
        //System.out.println("conexão criada");
           
        //Criando preparedStatement
        String sql = "SELECT p.idPessoa, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pf.cpf\n" +
"FROM Pessoa p JOIN PessoaFisica pf ON (p.idPessoa = pf.idPessoa) WHERE p.idPessoa = ?;";
        PreparedStatement ps = ConectorBD.getPrepared(conexao, sql);
        //System.out.println("preparedStatement criado");
           
        //Consultando o banco
        ps.setInt(1, id);
        ResultSet consulta = ConectorBD.getSelect(ps);
        //System.out.println("consulta retornada");
           
        //Criando objeto pessoaFisica
        if(consulta.next()){               
            String nome = consulta.getString("nome");
            String logradouro = consulta.getString("logradouro");
            String cidade = consulta.getString("cidade");
            String estado = consulta.getString("estado");
            String telefone = consulta.getString("telefone");
            String email = consulta.getString("email");
            String cpf = consulta.getString("cpf");
            pessoa = new PessoaFisica(id, nome, logradouro, cidade, estado, telefone, email, cpf);
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
    
    public static ArrayList<PessoaFisica> getPessoas() throws SQLException, ClassNotFoundException{
        ArrayList<PessoaFisica> lista = new ArrayList<PessoaFisica>();
        
        //Conectando com o banco
        Connection conexao = ConectorBD.getConnection();
        //System.out.println("conexão criada");
            
        //Criando preparedStatement
        String sql = "SELECT p.idPessoa, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pf.cpf\n" +
"FROM Pessoa p JOIN PessoaFisica pf ON (p.idPessoa = pf.idPessoa);";
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
            String cpf = consulta.getString("cpf");
            lista.add(new PessoaFisica(id, nome, logradouro, cidade, estado, telefone, email, cpf));
        }
            
        //Fechando conexões
        ConectorBD.close(consulta);
        ConectorBD.close(ps);
        ConectorBD.close(conexao);
        //System.out.println("conexões fechadas");
        
        return lista;
    }
    
    public static void incluir(PessoaFisica pessoa) throws SQLException, ClassNotFoundException{
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
           
            //Adicionando registro a tabela PessoaFisica
            String sqlPessoaFisica = "INSERT INTO PessoaFisica (idPessoa, cpf) VALUES (?, ?);";
            PreparedStatement psPessoaFisica = ConectorBD.getPrepared(conexao, sqlPessoaFisica);
            psPessoaFisica.setInt(1,id);
            psPessoaFisica.setString(2,pessoa.get_cpf());
            psPessoaFisica.executeUpdate();
            
            conexao.commit();     
            //Fim
            
            System.out.println("Inclusão concluida");
            
            //Fechando conexões
            ConectorBD.close(psPessoa);
            ConectorBD.close(psPessoaFisica);
            ConectorBD.close(conexao);
            //System.out.println("conexões fechadas");
        }
        else{
            System.out.println("Pessoa não incluída no registro, problema no id");
        }
    }
    
    public static void alterar(PessoaFisica pessoa) throws SQLException, ClassNotFoundException{
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
            
        //Atualizando tabela PessoaFisica
        String sqlPessoaFisica = "UPDATE PessoaFisica SET cpf = ? WHERE idPessoa = ?;";
        PreparedStatement psPessoaFisica = ConectorBD.getPrepared(conexao, sqlPessoaFisica);
        psPessoaFisica.setString(1,pessoa.get_cpf());
        psPessoaFisica.setInt(2,pessoa.get_id());            
        psPessoaFisica.executeUpdate();
            
        conexao.commit();   
        //Fim
            
        System.out.println("Atualização concluida");
           
        //Fechando conexões
        ConectorBD.close(psPessoa);
        ConectorBD.close(psPessoaFisica);
        ConectorBD.close(conexao);
        //System.out.println("conexões fechadas");
    }
    
    public static void excluir(int id) throws SQLException, ClassNotFoundException{
        //Conectando com o banco
        Connection conexao = ConectorBD.getConnection();
        //System.out.println("conexão criada");
            
        //Início
        conexao.setAutoCommit(false);
            
        //Excluindo registro da tabela PessoaFisica
        String sqlPessoaFisica = "DELETE FROM PessoaFisica WHERE idPessoa = ?;";
        PreparedStatement psPessoaFisica = ConectorBD.getPrepared(conexao, sqlPessoaFisica);
        psPessoaFisica.setInt(1,id); 
        psPessoaFisica.executeUpdate();
            
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
        ConectorBD.close(psPessoaFisica);
        ConectorBD.close(conexao);
        //System.out.println("conexões fechadas");
    }   
}
import cadastrobd.model.*;
import cadastrobd.model.util.SequenceManager;
import java.sql.SQLException;


public class CadastroBDTeste {
    
    public static void main(String[] args){
        try{  
            //Instanciar e persistir pessoa física no banco
            PessoaFisica pessoa1 = new PessoaFisica(0, "xxxxx", "xxxx", "xxx", "xx", "xxxx-xxxx", "x@x.com", "xxxxxxxxxxx");
            PessoaFisicaDAO.incluir(pessoa1);
            
            //Atualizar pessoa física
            int id = SequenceManager.getValue("sequenciaIdPessoa") - 1;
            PessoaFisica pessoa2 = new PessoaFisica(id, "yyyyy", "yyyy", "yyy", "yy", "yyyy-yyyy", "y@y.com", "yyyyyyyyyyy");
            PessoaFisicaDAO.alterar(pessoa2);
            
            //Cinsultar e exibir todas as pessoas físicas
            PessoaFisicaDAO.getPessoas().forEach(pessoa -> pessoa.exibir());
            
            //Excluir pessoa física
            PessoaFisicaDAO.excluir(id);
            
            //Instanciar e persistir pessoa jurídica no banco
            PessoaJuridica pessoa3 = new PessoaJuridica(0, "xxxxx", "xxxx", "xxx", "xx", "xxxx-xxxx", "x@x.com", "xxxxxxxxxxxxxx");
            PessoaJuridicaDAO.incluir(pessoa3);
            
            //Atualizar pessoa jurídica
            id = SequenceManager.getValue("sequenciaIdPessoa") - 1;
            PessoaJuridica pessoa4 = new PessoaJuridica(id, "yyyyy", "yyyy", "yyy", "yy", "yyyy-yyyy", "y@y.com", "yyyyyyyyyyyyyy");
            PessoaJuridicaDAO.alterar(pessoa4);
            
            //Cinsultar e exibir todas as pessoas juridica
            PessoaJuridicaDAO.getPessoas().forEach(pessoa -> pessoa.exibir());
            
            //Excluir pessoa juridica
            PessoaJuridicaDAO.excluir(id);            
        }
        catch(SQLException e){
            System.out.println("Algum problema com o SQL");
        }
        catch(Exception e){
            System.out.println("Erro");
        }
    }
}
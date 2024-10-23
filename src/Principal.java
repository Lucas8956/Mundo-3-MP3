import cadastrobd.model.*;
import java.sql.SQLException;
import java.util.Scanner;

public class Principal {
    
    public static Pessoa cadastroPessoa(Scanner teclado) throws SQLException, ClassNotFoundException{
        System.out.println("Digite o nome: ");
        String nome = teclado.nextLine();
        System.out.println("Digite o logradouro: ");
        String logradouro = teclado.nextLine();
        System.out.println("Digite a cidade: ");
        String cidade = teclado.nextLine();
        System.out.println("Digite o estado: ");
        String estado = teclado.nextLine();
        System.out.println("Digite o telefone: ");
        String telefone = teclado.nextLine();
        System.out.println("Digite o email: ");
        String email = teclado.nextLine();
        return new Pessoa(0, nome, logradouro, cidade, estado, telefone, email);
                    
    }
    public static void main(String[] args) {
        boolean ligado = true;
	Scanner teclado = new Scanner(System.in);
		
	while(ligado) {
            //Display menu
            System.out.println("====================================================================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("0 - Finalizar Programa");
            System.out.println("====================================================================");
			
            //Recebendo instrução selecionada
            int instrucao1 = -1;
                        
            try {
                instrucao1 = Integer.parseInt(teclado.nextLine());
            } 
            catch (Exception e) {
            	System.out.println("Entrada inválida.");
            }
            
            //Recebendo o tipo de pessoa
            String tipo = "?";
            if(instrucao1 > 0 & instrucao1 < 6) {
            	while(true) {
                    System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
                    tipo = teclado.nextLine();
                    if(tipo.equals("F") | tipo.equals("J")) {
                        break;
                    }
                    else {
                        System.out.println("Não entendi, por favor tente novamente.");
                    }
                }
            }
            			
            switch(instrucao1) {
            case 1:
                try{
                    Pessoa pessoa = cadastroPessoa(teclado);
                    if(tipo.equals("F")){
                        System.out.println("Digite o cpf: ");
                        String cpf = teclado.nextLine();
                        PessoaFisicaDAO.incluir(new PessoaFisica(0, pessoa.get_nome(), pessoa.get_logradouro(), pessoa.get_cidade(), pessoa.get_estado(), pessoa.get_telefone(), pessoa.get_email(), cpf));
                    }
                    else{
                        System.out.println("Digite o cnpj: ");
                        String cnpj = teclado.nextLine();
                        PessoaJuridicaDAO.incluir(new PessoaJuridica(0, pessoa.get_nome(), pessoa.get_logradouro(), pessoa.get_cidade(), pessoa.get_estado(), pessoa.get_telefone(), pessoa.get_email(), cnpj));
                    }
                }
                catch(SQLException e){
                    System.out.println("Algum problema com o SQL, inclusão não realizada");
                }
                catch(Exception e){
                    System.out.println("Não foi possível regidtrar a pessoa");
                }
                break;
                
            case 2:
                try{
                    System.out.println("Digite o id: ");
                    int id = Integer.parseInt(teclado.nextLine());
                    if(tipo.equals("F")){
                        PessoaFisicaDAO.getPessoa(id).exibir();
                        Pessoa pessoa = cadastroPessoa(teclado);
                        System.out.println("Digite o cpf: ");
                        String cpf = teclado.nextLine();
                        PessoaFisicaDAO.alterar(new PessoaFisica(id, pessoa.get_nome(), pessoa.get_logradouro(), pessoa.get_cidade(), pessoa.get_estado(), pessoa.get_telefone(), pessoa.get_email(), cpf));
                    }
                    else{
                        PessoaJuridicaDAO.getPessoa(id).exibir();
                        Pessoa pessoa = cadastroPessoa(teclado);
                        System.out.println("Digite o cnpj: ");
                        String cnpj = teclado.nextLine();
                        PessoaJuridicaDAO.alterar(new PessoaJuridica(id, pessoa.get_nome(), pessoa.get_logradouro(), pessoa.get_cidade(), pessoa.get_estado(), pessoa.get_telefone(), pessoa.get_email(), cnpj));
                    }
                }
                catch(SQLException e){
                    System.out.println("Algum problema com o SQL, inclusão não realizada");
                }
                catch(Exception e){
                    System.out.println("Não foi possível atualizar o registro");
                }
                break;
                
            case 3:
                try{
                    System.out.println("Digite o id: ");
                    int id = Integer.parseInt(teclado.nextLine());
                    if(tipo.equals("F")){
                        PessoaFisicaDAO.excluir(id);
                    }
                    else{
                        PessoaJuridicaDAO.excluir(id);
                    }
                }
                catch(SQLException e){
                    System.out.println("Algum problema com o SQL, inclusão não realizada");
                }
                catch(Exception e){
                    System.out.println("Não foi possível excluir a pessoa");
                }
                break;
                	
            case 4:
                try{
                    System.out.println("Digite o id: ");
                    int id = Integer.parseInt(teclado.nextLine());
                    if(tipo.equals("F")){
                        PessoaFisicaDAO.getPessoa(id).exibir();
                    }
                    else{
                        PessoaJuridicaDAO.getPessoa(id).exibir();
                    }
                }
                catch(SQLException e){
                    System.out.println("Algum problema com o SQL, inclusão não realizada");
                }
                catch(Exception e){
                    System.out.println("Não foi possível encontrar a pessoa");
                }
                break;
                
            case 5:
                try{
                    if(tipo.equals("F")){
                        PessoaFisicaDAO.getPessoas().forEach(pesssoa -> pesssoa.exibir());
                    }
                    else{
                        PessoaJuridicaDAO.getPessoas().forEach(pesssoa -> pesssoa.exibir());
                    }
                }
                catch(SQLException e){
                    System.out.println("Algum problema com o SQL, inclusão não realizada");
                }
                catch(Exception e){
                    System.out.println("Não foi possível encontrar a pessoa");
                }
                break;
                
            case 0:
                ligado = false;
		System.out.println("Fechando programa.");
		break;
                                        
            default:
                System.out.println("Por favor tente novamente.");
                break;
            }
        }
	teclado.close();
    }
}
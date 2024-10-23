package cadastrobd.model;

public class PessoaFisica extends Pessoa {
	//Atributos
	private String cpf;
	
	//Construtores
	public PessoaFisica() {}
	
	public PessoaFisica(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cpf) {
		super(id, nome, logradouro, cidade, estado, telefone, email);
		this.cpf = cpf;
	}
	
	//getters e setters
	public void set_cpf(String cpf) {
		this.cpf = cpf;
		System.out.println("CPF Atualizado.");
	}
	public String get_cpf() {
		return this.cpf;
	}
	
	//Métodos
	public void exibir() {
		super.exibir();
		System.out.println("CPF: " + this.cpf);
	}
}
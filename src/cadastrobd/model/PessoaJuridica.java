package cadastrobd.model;

public class PessoaJuridica extends Pessoa {
	//Atributos
	private String cnpj;
	
	//Construtores
	public PessoaJuridica() {}
	
	public PessoaJuridica(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cnpj) {
		super(id, nome, logradouro, cidade, estado, telefone, email);
		this.cnpj = cnpj;
	}
	
	//getters e setters
	public void set_cnpj(String cnpj) {
		this.cnpj = cnpj;
		System.out.println("CNPJ Atualizado.");
	}
	public String get_cnpj() {
		return this.cnpj;
	}
	
	//Métodos
	public void exibir() {
		super.exibir();
		System.out.println("CNPJ: " + this.cnpj);
	}
}
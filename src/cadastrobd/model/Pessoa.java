package cadastrobd.model;

public class Pessoa {
	//Atributos
	private int id;
	private String nome;
        private String logradouro;
        private String cidade;
        private String estado;
        private String telefone;
        private String email;
	
	//Construtores
	public Pessoa() {}
	
	public Pessoa(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email) {
		this.id  = id;
		this.nome = nome;
                this.logradouro = logradouro;
                this.cidade = cidade;
                this.estado = estado;
                this.telefone = telefone;
                this.email = email;
	}
	
	//getters e setters
	public void set_id(int id) {
		this.id = id;
		System.out.println("ID Atualizado.");
	}
	public int get_id() {
		return this.id;
	}
	public void set_nome(String nome) {
		this.nome = nome;
		System.out.println("Nome Atualizado.");
	}
	public String get_nome() {
		return this.nome;
	}
        public void set_logradouro(String logradouro) {
		this.logradouro = logradouro;
		System.out.println("Logradouro Atualizado.");
	}
	public String get_logradouro() {
		return this.logradouro;
	}
        public void set_cidade(String cidade) {
		this.cidade = cidade;
		System.out.println("Cidade Atualizada.");
	}
	public String get_cidade() {
		return this.cidade;
	}
        public void set_estado(String estado) {
		this.estado = estado;
		System.out.println("Estado Atualizado.");
	}
	public String get_estado() {
		return this.estado;
	}
        public void set_telefone(String telefone) {
		this.telefone = telefone;
		System.out.println("Telefone Atualizado.");
	}
	public String get_telefone() {
		return this.telefone;
	}
        public void set_email(String email) {
		this.email = email;
		System.out.println("Email Atualizado.");
	}
	public String get_email() {
		return this.email;
	}
	
	//Métodos
	public void exibir() {
		System.out.println("ID: " + this.id);
		System.out.println("Nome: " + this.nome);
                System.out.println("Logradouro: " + this.logradouro);
                System.out.println("Cidade: " + this.cidade);
                System.out.println("Estado: " + this.estado);
                System.out.println("Telefone: " + this.telefone);
                System.out.println("Email: " + this.email);
	}
}
package maven.demo;

public class Livro {
    private int id;
    private String nome;
    private int quantidadeDePaginas;

    // Construtor padrão
    public Livro() {
        this.id = -1;
        this.nome = "";
        this.quantidadeDePaginas = 0;
    }

    // Construtor com parâmetros
    public Livro(int id, String nome, int quantidadeDePaginas) {
        this.id = id;
        this.nome = nome;
        this.quantidadeDePaginas = quantidadeDePaginas;
    }

    // Getter e setter para id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter e setter para nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e setter para quantidadeDePaginas
    public int getQuantidadeDePaginas() {
        return quantidadeDePaginas;
    }

    public void setQuantidadeDePaginas(int quantidadeDePaginas) {
        this.quantidadeDePaginas = quantidadeDePaginas;
    }

    @Override
    public String toString() {
        return "Livro [id=" + id + ", nome=" + nome + ", quantidadeDePaginas=" + quantidadeDePaginas + "]";
    }
}

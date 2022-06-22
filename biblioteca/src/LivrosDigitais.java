public class LivrosDigitais extends Livros {
    private int visualizacao = 0;

    public LivrosDigitais(String autor, String editora, String titulo) {
        super(autor, editora, titulo);
    }

    @Override
    public int getVisualizacao() {
        return this.visualizacao;
    }

    @Override
    public void visualizar() {
        this.visualizacao++;
    }

    @Override
    public String ToString() {
        return super.getAutor() + "|" + super.getEditora() + "|" + super.getTitulo() + "  Tipo: Livro Digital";
    }
}

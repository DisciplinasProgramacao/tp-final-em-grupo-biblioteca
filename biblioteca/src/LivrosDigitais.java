public class LivrosDigitais extends Livros {
    public static final int DATA_FUTURA = 2800;
    private int visualizacao = 0;

    public LivrosDigitais(String autor, String editora, String titulo) {
        super(autor, editora, titulo);
    }

    @Override
    public int getVisualizacao() {
        return this.visualizacao;
    }

    public void visualizar() {
        this.visualizacao++;
    }
}

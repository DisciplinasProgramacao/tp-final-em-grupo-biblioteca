public class LivrosFisicosPrioritarios extends LivrosFisicos{
    private int qtdeDiasRetencao = 3;

    public LivrosFisicosPrioritarios(String autor, String editora, String titulo, int qtdeDiasRetencao) {
        this.autor = autor;
        this.editora = editora;
        this.titulo = titulo;
        this.qtdeDiasRetencao = qtdeDiasRetencao;
    }
}

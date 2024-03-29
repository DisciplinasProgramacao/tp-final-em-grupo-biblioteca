import java.io.Serializable;
import java.time.LocalDate;

public class Emprestimo implements Serializable {
    private Livros livro;
    private Usuarios usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private LocalDate dataPrevistaDevolucao;

    public Emprestimo(Usuarios novoUsuario, Livros novoLivro) {
        this.usuario = novoUsuario;
        this.livro = novoLivro;
        novoLivro.addEmprestimos();

        // Data hoje = new Data(LocalDate.now().getDayOfMonth(),
        // LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        int dia = LocalDate.now().getDayOfMonth();
        int mes = LocalDate.now().getMonthValue();
        int ano = LocalDate.now().getYear();

        this.dataEmprestimo = LocalDate.of(ano, mes, dia);
        this.dataPrevistaDevolucao = this.dataEmprestimo.plusDays(novoUsuario.getDiasDevolucao());
        this.dataDevolucao = dataPrevistaDevolucao;
    }

    public Livros getLivro() {
        return this.livro;
    }

    public Usuarios getUsuario() {
        return this.usuario;
    }

    public LocalDate getDataEmprestimo() {
        return this.dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return this.dataDevolucao;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return this.dataPrevistaDevolucao;
    }

    private void setDataPrevistaDevolucao() {
        this.dataPrevistaDevolucao = this.getDataEmprestimo().plusDays(this.getUsuario().getDiasDevolucao());
    }

    public void setDataEmprestimo(LocalDate novaDataEmprestimo) {
        this.dataEmprestimo = novaDataEmprestimo;
        this.setDataPrevistaDevolucao();
        this.setDataDevolucao();
    }

    private void setDataDevolucao() {
        this.dataDevolucao = this.getDataPrevistaDevolucao();
    }

    public String ToString(Emprestimo emprestimo) {

        System.out.println("-----------------------------------------------------------");
        System.out.println("Titulo: " + emprestimo.getLivro().getTitulo());
        System.out.println("Autor: " + emprestimo.getLivro().getAutor());
        System.out.println("Editora: " + emprestimo.getLivro().getEditora());

        String dataDevolucaoAuxiliar = "";
        String dataPrevistaDevolucaoAuxiliar = "";

        if (emprestimo.getDataDevolucao() != null)
            dataDevolucaoAuxiliar = emprestimo.getDataDevolucao().toString();

        if (emprestimo.getDataPrevistaDevolucao() != null)
            dataPrevistaDevolucaoAuxiliar = emprestimo.getDataPrevistaDevolucao().toString();

        return "Data do Emprestimo: " +
                emprestimo.getDataEmprestimo().toString() + " | "
                + "Data Prevista Devolução: " + dataPrevistaDevolucaoAuxiliar + " | " + "Data de Devolução: "
                + dataDevolucaoAuxiliar;
    }

    public void setDataDevolucao(LocalDate novaDataDevolucao) {
        this.dataDevolucao = novaDataDevolucao;
    }

}
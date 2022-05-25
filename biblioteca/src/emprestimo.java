import java.time.LocalDate;

public class Emprestimo {
    private Livros livro;
    private IUsuarios usuario;
    private Data dataEmprestimo;
    private Data dataDevolucao;
    private Data dataPrevistaDevolucao;

    public Emprestimo(Usuarios novoUsuario, Livros novoLivro) {
        this.usuario = novoUsuario;
        this.livro = novoLivro;

        this.dataEmprestimo = new Data(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(),
                LocalDate.now().getYear());
        this.dataPrevistaDevolucao = new Data().acrescentaDias(novoUsuario.getDiasDevolucao());
    }

    public Livros getLivro()
    {
        return this.livro;
    }

    public IUsuarios getUsuario()
    {
        return this.usuario;
    }

    public Data getDataEmprestimo()
    {
        return this.dataEmprestimo;
    }

    public Data getDataDevolucao()
    {
        return this.dataDevolucao;
    }

    public Data getDataPrevistaDevolucao()
    {
        return this.dataPrevistaDevolucao;
    }

    public String ToString() {
        String dataDevolucaoAuxiliar = "";
        String dataPrevistaDevolucaoAuxiliar = "";
        if(this.dataDevolucao != null)
            dataDevolucaoAuxiliar = this.dataDevolucao.toString();

        if(this.dataPrevistaDevolucao != null)
            dataPrevistaDevolucaoAuxiliar = this.dataPrevistaDevolucao.toString();

        return livro.getTitulo() +"|"+ Integer.toString(this.usuario.getMatricula()) +"|"+ this.dataEmprestimo.toString() +"|"+ dataDevolucaoAuxiliar +"|"+ dataPrevistaDevolucaoAuxiliar  ;
    }

    public boolean saberAtraso() {
        return true;
    }

    public boolean punicao() {
        return true;
    }
}
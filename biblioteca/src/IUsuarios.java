public interface IUsuarios {
    public int getDiasDevolucao();
    public int getDiasSuspensao();
    public int suspensao(int diasAtrazo);
    public int getMatricula(); 
    public String getNome();
    public String ToString();
}

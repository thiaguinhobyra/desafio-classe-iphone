package telefone.service;

public interface TelefoneService {
    void addContato(String nome, String numero);
    void listarContatos();
    void buscarContato(String nome);
    void ligar(String nome, String numero);
    void atenderChamada(String nome);
    void correioVoz(String msg);
    void listarCorreioVoz();
    void listarChamadas();
    void listarEsperas();
}

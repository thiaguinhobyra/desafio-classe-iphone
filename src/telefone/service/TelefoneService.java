package telefone.service;

import telefone.model.Contato;

public interface TelefoneService {
    void addContato(String nome, String numero);
    void listarContatos();
    void buscarContato(String nome);
    void ligar(String nomeo);
    void atenderChamada();
    void correioVoz(String msg);
    void listarCorreioVoz();
    void listarChamadas();
}

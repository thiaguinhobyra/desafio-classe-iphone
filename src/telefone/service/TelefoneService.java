package telefone.service;

import telefone.Telefone;

public interface TelefoneService {
    void addContato(String nome, String numero);
    void listarContatos();
    void buscarContato(String nome);
    void ligar(Telefone telefone);
    void atender(Telefone telefone);
    void correioVoz(Telefone telefone, String msg);
    void listarCorreioVoz(Telefone telefone);
}

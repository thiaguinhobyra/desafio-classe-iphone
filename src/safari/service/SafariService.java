package safari.service;

import safari.model.Aba;

public interface SafariService {
    void abaNova(String nomeSite, String url);
    void listarAbasAbertas();
    void selecionarAba(String nomeSite);
    void exibirPagina(String nomeSite);
    void atualizarPagina(String nomeSite);

}

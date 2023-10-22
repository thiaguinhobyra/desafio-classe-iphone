package iphone.services;

public interface SafariService {
    void abaNova(String nomeSite, String url);
    void listarAbasAbertas();
    void selecionarAba(String nomeSite);
    void atualizarPagina(String nomeSite);

}

package safari.service;

import safari.model.Aba;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SafariServiceImpl implements SafariService{

    private Map<String, Aba> abasAbertas = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private String paginaAtual = null;


    @Override
    public void abaNova(String nomeSite, String url) {
        Aba aba = new Aba(nomeSite, url);
        abasAbertas.put(nomeSite, aba);
        System.out.println("Nova aba adicionada! ");
        paginaAtual = nomeSite;
        listarAbasAbertas();
    }

    @Override
    public void listarAbasAbertas() {
        System.out.println("Todas as abas abertas: ");
        for (Aba aba : abasAbertas.values()) {
            System.out.println(aba.getNomeSite() + " - " + aba.getUrl());
        }
        if (abasAbertas.size() == 1) {
            System.out.println("Aba atual: " + paginaAtual);
            opcoes(paginaAtual);
        } else {
            opcoes(paginaAtual);
        }

    }

    public void opcoes(String nomeSite) {
        if (paginaAtual != null) {

            //SafariServiceImpl safariServiceImpl = new SafariServiceImpl();
            int opcao;

            do {
                System.out.println("1. Nova aba");
                System.out.println("2. Trocar página");
                System.out.println("3. Fechar página");
                System.out.println("4. Fechar todas as páginas");
                System.out.println("5. Sair");
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        System.out.println("Abra uma nova aba digite o nome: ");
                        String nome = scanner.nextLine();
                        System.out.println("Abra uma nova aba digite a URL: ");
                        String url = scanner.nextLine();
                        abaNova(nome, url);
                        listarAbasAbertas();
                        break;
                    case 2:
                        listarAbasAbertas();
                        System.out.println("Selecione a aba desejada: ");
                        String abaSelecionada = scanner.nextLine();
                        selecionarAba(abaSelecionada);
                        break;
                    case 3:
                        System.out.println("Aba selecionada foi fechada: " + paginaAtual);
                        fecharAba();
                        break;
                    case 4:
                        fecharTodasAsAbas();
                        break;
                    case 5:
                        System.out.println("Saindo do navegador.");
                        break;
                    default:
                        System.out.println("Opção inválida. ");
                }
            } while (opcao != 5);
        } else {
            System.out.println("Não há página aberta. Abra uma página primeiro.");
        }
            /*while (!abasAbertas.isEmpty()) {
                System.out.println("1. Nova aba");
                System.out.println("2. Trocar página");
                System.out.println("3. Fechar página");
                System.out.println("4. Fechar todas as páginas");
                int opcao = scanner.nextInt();
                //scanner.nextLine();
                switch (opcao) {
                    case 1:
                        System.out.println("Abra uma nova aba digite o nome: ");
                        String nome = safariServiceImpl.scanner.nextLine();
                        System.out.println("Abra uma nova aba digite a url: ");
                        String url = safariServiceImpl.scanner.nextLine();
                        safariServiceImpl.abaNova(nome, url);

                        //safariServiceImpl.listarAbasAbertas();
                        break;
                    case 2:
                        safariServiceImpl.listarAbasAbertas();
                        System.out.println("Seleciona a aba desejada: ");
                        String abaSelecionada = safariServiceImpl.scanner.nextLine();
                        safariServiceImpl.selecionarAba(abaSelecionada);
                        break;
                    case 3:
                        fecharAba();
                        break;
                    case 4:
                        fecharTodasAsAbas();
                        break;
                    default:
                        System.out.println("Opção inválida. ");
                }
            }

        }*/
    }

    @Override
    public void selecionarAba(String nomeSite) {
        if (abasAbertas.containsKey(nomeSite)) {
            exibirPagina(nomeSite);
        } else {
            System.out.println("Página não encontrada. ");
        }
    }

    public void fecharAba() {
        if (paginaAtual != null) {
            System.out.println("Aba " + paginaAtual +" fechada.");
            abasAbertas.remove(paginaAtual);
            paginaAtual = null;
        } else {
            System.out.println("Nenhuma aba selecionada para fechar.");
        }
    }

    public void fecharTodasAsAbas() {
        abasAbertas.clear();
        paginaAtual = null;
        System.out.println("Todas as abas foram fechadas.");
    }

    @Override
    public void exibirPagina(String nomeSite) {
        SafariServiceImpl safariServiceImpl = new SafariServiceImpl();
        if (paginaAtual == null) {
            paginaAtual = nomeSite;
            System.out.println("Abra uma nova aba digite o nome: ");
            String nome = safariServiceImpl.scanner.nextLine();
            System.out.println("Abra uma nova aba digite a url: ");
            String url = safariServiceImpl.scanner.nextLine();
            safariServiceImpl.abaNova(nome, url);
        } else {
            paginaAtual = nomeSite;
            System.out.println("Nova aba selecionada: " + paginaAtual);
        }
    }

    @Override
    public void atualizarPagina(String nomeSite) {
        System.out.println("Página: " + paginaAtual);
        System.out.println("Recarregando página...");
        System.out.println("Página atualizada");
        System.out.println("Página: " + paginaAtual);
        listarAbasAbertas();
    }

    public static void main(String[] args) {
        SafariServiceImpl safariServiceImpl = new SafariServiceImpl();

        System.out.println("Abra uma nova aba digite o nome: ");
        String nome = safariServiceImpl.scanner.nextLine();
        System.out.println("Abra uma nova aba digite a url: ");
        String url = safariServiceImpl.scanner.nextLine();
        safariServiceImpl.abaNova(nome, url);

        safariServiceImpl.listarAbasAbertas();
    }
}

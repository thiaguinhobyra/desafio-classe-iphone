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
        paginaAtual = nomeSite;
        System.out.println("Nova aba aberta! " + paginaAtual);
        //listarAbasAbertas();
    }

    @Override
    public void listarAbasAbertas() {
        System.out.println("Todas as abas abertas: ");
        for (Aba aba : abasAbertas.values()) {
            System.out.println(aba.getNomeSite() + " - " + aba.getUrl());
        }
        if (abasAbertas.size() > 0) {
            System.out.println("Aba atual: " + paginaAtual);
            //opcoes(paginaAtual);
        } else {
            //opcoes(paginaAtual);
            System.out.println("Nenhuma aba aberta.");
        }

    }

    public void opcoes() {
            int opcao;

            do {
                if (abasAbertas.isEmpty()) {
                    System.out.println("Nenhuma aba aberta.");
                } else {
                    System.out.println("Todas as abas abertas: ");
                    listarAbasAbertas();
                }
                System.out.println("1. Nova aba");
                System.out.println("2. Trocar página");
                System.out.println("3. Fechar página");
                System.out.println("4. Fechar todas as páginas e sair");
                if(!abasAbertas.isEmpty()) System.out.println("5. Atualizar página");

                String entrada = scanner.nextLine();

                try {
                    opcao = Integer.parseInt(entrada);
                } catch (NumberFormatException e) {
                    opcao = 0;
                }

                switch (opcao) {
                    case 1:
                        System.out.println("Abra uma nova aba digite o nome: ");
                        String nome = scanner.nextLine();
                        System.out.println("Abra uma nova aba digite a URL: ");
                        String url = scanner.nextLine();
                        abaNova(nome, url);
                        break;
                    case 2:
                        listarAbasAbertas();
                        System.out.println("Selecione a aba desejada: ");
                        String abaSelecionada = scanner.nextLine();
                        selecionarAba(abaSelecionada);
                        break;
                    case 3:
                        fecharAba();
                        break;
                    case 4:
                        fecharTodasAsAbas();
                        break;
                    case 5:
                        if (!abasAbertas.isEmpty()) {
                            atualizarPagina(paginaAtual);
                        } else {
                            opcoes();
                        }
                        break;
                    default:
                        System.out.println("Opção inválida. ");
                }
            } while (!abasAbertas.isEmpty());
    }

    @Override
    public void selecionarAba(String nomeSite) {
        if (abasAbertas.containsKey(nomeSite)) {
            paginaAtual = nomeSite;
            System.out.println("Aba selecionada: " + nomeSite);
        } else {
            System.out.println("Aba não encontrada. ");
        }
    }

    public void fecharAba() {
        if (paginaAtual != null) {
            System.out.println("Aba " + paginaAtual +" fechada.");
            abasAbertas.remove(paginaAtual);
            if (!abasAbertas.isEmpty()) {
                paginaAtual = abasAbertas.keySet().iterator().next();
            } else {
                paginaAtual = null;
            }
        } else {
            System.out.println("Nenhuma aba selecionada para fechar.");
        }
    }

    public void fecharTodasAsAbas() {
        abasAbertas.clear();
        paginaAtual = null;
        System.out.println("Todas as abas foram fechadas.");
        System.out.println("Saindo do navegador.");
    }

    @Override
    public void atualizarPagina(String nomeSite) {
        System.out.println("Aba atual: " + paginaAtual);
        System.out.println("Recarregando página...");
        System.out.println("Página atualizada");
    }

    public static void main(String[] args) {
        SafariServiceImpl safariService = new SafariServiceImpl();
        safariService.opcoes();
        /*SafariServiceImpl safariServiceImpl = new SafariServiceImpl();

        System.out.println("Abra uma nova aba digite o nome: ");
        String nome = safariServiceImpl.scanner.nextLine();
        System.out.println("Abra uma nova aba digite a url: ");
        String url = safariServiceImpl.scanner.nextLine();
        safariServiceImpl.abaNova(nome, url);

        safariServiceImpl.listarAbasAbertas();*/
    }
}

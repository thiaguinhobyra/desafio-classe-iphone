package iphone.services.servicesOmpl;

import iphone.models.Musica;
import iphone.services.IpodService;

import java.util.*;

public class IpodServiceImpl implements IpodService {

    private final Map<String, Musica> biblioteca = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    private String musicaAtual = null;
    private boolean tocando = false;

    @Override
    public void adicionarMusica(String titulo, String artista, int tempo) {
        Musica musica = new Musica(titulo, artista, tempo);
        biblioteca.put(titulo, musica);
        System.out.println("Música adicionada com sucesso! ");
    }

    public void homeIpod() {
        if (musicaAtual != null){
            opcoesIpod();
        } else {
            listarMusicas();
        }
    }

    @Override
    public void listarMusicas() {
        System.out.println("Biblioteca de Músicas: ");
        if (biblioteca.isEmpty()) {
            System.out.println("A biblioteca está vazia. ");
        } else {
            for (Musica musica : biblioteca.values()) {
                System.out.println(musica.getTitulo() + " - " + musica.getArtista() + " - " + musica.getTempo() + "min");
            }
        }
        String opcao;
        System.out.println("Escolha o titulo da música ou digite 'voltar': ");
        if (scanner.hasNextLine()) {
            opcao = scanner.nextLine();

            if (opcao.equalsIgnoreCase("voltar")) {
                return;
            }
            selecionarMusica(opcao);
        } else {
            System.out.println("Opção inválida. Tente novamente. ");
        }
    }

    @Override
    public void selecionarMusica(String titulo) {
        if (tocando) {
            pausar();
        }
        if (biblioteca.containsKey(titulo)) {
            musicaAtual = titulo;
            tocar();
        } else {
            System.out.println("Música não encontrada na biblioteca. ");
        }
        opcoesIpod();
    }

    @Override
    public void tocar() {
        if (musicaAtual != null && tocando) {
            System.out.println("A música " + musicaAtual + " já está tocando.");
        } else if (biblioteca.containsKey(musicaAtual)) {
            tocando = true;
            System.out.println("Tocando a música: " + musicaAtual);
            opcoesIpod();
        }
    }

    @Override
    public void pausar() {
        if (tocando) {
            tocando = false;
            System.out.println("Pausando : " + musicaAtual);
            opcoesIpod();
        } else {
            System.out.println("Nenhuma música tocando no momento.");
        }
    }


    public void opcoesIpod() {
        if (tocando) {
            while (true) {
                System.out.println("Musica " + musicaAtual + " tocando.");
                System.out.println();
                System.out.println("Opções");
                System.out.println("1. Pausar");
                System.out.println("2. Parar");
                System.out.println("3. Biblioteca");
                System.out.println("4. Voltar");

                int opcao = obterOpcaoUsuario();

                switch (opcao) {
                    case 1:
                        pausar();
                        break;
                    case 2:
                        tocando = false;
                        musicaAtual = null;
                        return;
                    case 3:
                        listarMusicas();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamentes.");
                }
            }
        } else if (musicaAtual != null && !tocando){
            while (true) {
                System.out.println("Musica " + musicaAtual + " pausada.");
                System.out.println();
                System.out.println("Opções");
                System.out.println("1. Tocar");
                System.out.println("2. Parar");
                System.out.println("3. Voltar");

                int opcao = obterOpcaoUsuario();

                switch (opcao) {
                    case 1:
                        tocar();
                        tocando = true;
                        return;
                    case 2:
                        tocando = false;
                        musicaAtual = null;
                        return;
                    case 3:
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente. ");
                }
            }
        }
    }

    private int obterOpcaoUsuario() {
        int opcao;
        while (true) {
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
                return opcao;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
                scanner.nextLine();
            }
        }
    }
}

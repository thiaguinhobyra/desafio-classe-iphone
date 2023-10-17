package ipod.service;


import ipod.model.Musica;

import java.util.*;

public class IpodServiceImpl implements IpodService {

    private Map<String, Musica> biblioteca = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    private String musicaAtual = null;
    private boolean tocando = false;

    @Override
    public void adicionarMusica(String titulo, String artista, int tempo) {
        Musica musica = new Musica(titulo, artista, tempo);
        biblioteca.put(titulo, musica);
        System.out.println("Música adicionada com sucesso! ");
    }

    @Override
    public void listarMusicas() {
        System.out.println("Biblioteca de Músicas: ");
        for (Musica musica : biblioteca.values()) {
            System.out.println(musica.getTitulo() + " - " + musica.getArtista());
        }
    }

    @Override
    public void selecionarMusica(String titulo) {
            if (biblioteca.containsKey(titulo)) {
                tocar(titulo);
            } else {
                System.out.println("Música não encontrada na biblioteca. ");
            }
       // }
    }

    @Override
    public void tocar(String titulo) {
        //for (Ipod musica : biblioteca) {
            if (musicaAtual == null) {
                musicaAtual = titulo;
                tocando = true;
            } else {
                musicaAtual = titulo;
                System.out.println("Nova música selecionada: " + musicaAtual);
                tocando = true;
            }
        //}
        if (tocando) {
            System.out.println("Tocando a música: " + musicaAtual);
            opcoes(titulo);
        }
    }

    @Override
    public void pausar(String musica) {
        if (tocando) {
            tocando = false;
            System.out.println("Música pausada: " + musicaAtual);
            opcoes(musica);
        } else {
            System.out.println("Nenhuma música tocando no momento.");
        }
    }

    public void opcoes(String titulo) {
        if (tocando) {
            System.out.println("Musica " + titulo + ":");
            System.out.println("1. Pausar");
            System.out.println("2. Parar");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    pausar(titulo);
                    break;
                case 2:
                    tocando = false;
                    musicaAtual = null;
                    break;
                default:
                    System.out.println("Opção inválida. ");
            }
        } else if (musicaAtual != null && tocando == false){
            System.out.println("1. Tocar");
            System.out.println("2. Parar");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    tocar(titulo);
                    tocando = true;
                    break;
                case 2:
                    tocando = false;
                    musicaAtual = null;
                    break;
                default:
                    System.out.println("Opção inválida. ");
            }
        }
    }
    public static void main(String[] args) {
        IpodServiceImpl ipodServiceImpl = new IpodServiceImpl();

        ipodServiceImpl.adicionarMusica("Música 1", "Artista 1", 3);
        ipodServiceImpl.adicionarMusica("Música 2", "Artista 2", 4);
        ipodServiceImpl.adicionarMusica("Música 3", "Artista 3", 3);

        while (true) {
            ipodServiceImpl.listarMusicas();
            System.out.println("Escolha o titulo da música ou digite 'sair': ");
            String opcao = ipodServiceImpl.scanner.nextLine();

            if (opcao.equalsIgnoreCase("sair")) {
                break;
            }

            ipodServiceImpl.selecionarMusica(opcao);
        }
    }
}

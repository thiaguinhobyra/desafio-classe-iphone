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
        System.out.println("Selecione a música: ");
        String novaMusica = scanner.nextLine();
        selecionarMusica(novaMusica);
    }

    @Override
    public void selecionarMusica(String titulo) {
            if (biblioteca.containsKey(titulo)) {
                tocar(titulo);
            } else {
                System.out.println("Música não encontrada na biblioteca. ");
            }
            opcoesIpod(titulo);
    }

    @Override
    public void tocar(String titulo) {
            if (musicaAtual == null) {
                musicaAtual = titulo;
                tocando = true;
            } else {
                musicaAtual = titulo;
                System.out.println("Nova música selecionada: " + musicaAtual);
                tocando = true;
            }
        if (tocando) {
            System.out.println("Tocando a música: " + musicaAtual);
            opcoesIpod(titulo);
        }
    }

    @Override
    public void pausar(String musica) {
        if (tocando) {
            tocando = false;
            System.out.println("Música pausada: " + musicaAtual);
            opcoesIpod(musica);
        } else {
            System.out.println("Nenhuma música tocando no momento.");
        }
    }


    public void opcoesIpod(String titulo) {
        if (tocando) {
            System.out.println("Musica " + titulo + ":");
            System.out.println("1. Pausar");
            System.out.println("2. Parar");
            System.out.println("3. Biblioteca");
            System.out.println("4. Selecionar nova música");
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
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    listarMusicas();
                    System.out.println("Selecione a música: ");
                    String novaMusica = scanner.nextLine();
                    selecionarMusica(novaMusica);
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
}

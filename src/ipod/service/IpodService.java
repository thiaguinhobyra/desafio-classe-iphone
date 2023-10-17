package ipod.service;

import ipod.model.Musica;

import java.util.Scanner;

public interface IpodService {
    Scanner scanner = new Scanner(System.in);

    void adicionarMusica(String titulo, String artista, int tempo);
    void listarMusicas();
    void selecionarMusica(String titulo);
    void tocar(String titulo);
    void pausar(String titulo);

}

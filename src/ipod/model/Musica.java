package ipod.model;

public class Musica {
    private String titulo;
    private String artista;
    private int tempo;

    public Musica(String titulo, String artista, int tempo) {
        this.titulo = titulo;
        this.artista = artista;
        this.tempo = tempo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    @Override
    public String toString() {
        return "Ipod{" +
                "titulo='" + titulo + '\'' +
                ", artista='" + artista + '\'' +
                ", tempo=" + tempo +
                '}';
    }
}

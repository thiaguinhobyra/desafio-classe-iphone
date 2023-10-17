package safari.model;

public class Aba {
    private String nomeSite;
    private String url;

    public Aba(String nomeSite, String url) {
        this.nomeSite = nomeSite;
        this.url = url;
    }

    public String getNomeSite() {
        return nomeSite;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return  "nomeSite='" + nomeSite + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

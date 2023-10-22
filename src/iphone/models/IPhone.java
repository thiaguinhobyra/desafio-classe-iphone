package iphone.models;

import iphone.services.servicesOmpl.IpodServiceImpl;
import iphone.services.servicesOmpl.SafariServiceImpl;
import iphone.services.servicesOmpl.TelefoneServiceImpl;

public class IPhone {


    private String cor;
    private String marca;
    private String modelo;
    private Integer armazenamento;
    private Integer memoria;
    private String sistema;

    private IpodServiceImpl iPod;
    private TelefoneServiceImpl telefone;
    private SafariServiceImpl safari;

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getArmazenamento() {
        return armazenamento;
    }

    public void setArmazenamento(Integer armazenamento) {
        this.armazenamento = armazenamento;
    }

    public Integer getMemoria() {
        return memoria;
    }

    public void setMemoria(Integer memoria) {
        this.memoria = memoria;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public IpodServiceImpl getiPod() {
        return iPod;
    }

    public void setiPod(IpodServiceImpl iPod) {
        this.iPod = iPod;
    }

    public TelefoneServiceImpl getTelefone() {
        return telefone;
    }

    public void setTelefone(TelefoneServiceImpl telefone) {
        this.telefone = telefone;
    }

    public SafariServiceImpl getSafari() {
        return safari;
    }

    public void setSafari(SafariServiceImpl safari) {
        this.safari = safari;
    }

    public IPhone(String cor, String marca, String modelo, Integer armazenamento, Integer memoria, String sistema) {
        this.cor = cor;
        this.marca = marca;
        this.modelo = modelo;
        this.armazenamento = armazenamento;
        this.memoria = memoria;
        this.sistema = sistema;
        this.iPod = new IpodServiceImpl();
        this.telefone = new TelefoneServiceImpl();
        this.safari = new SafariServiceImpl();
    }

    @Override
    public String toString() {
        return "IPhone{" +
                "cor='" + cor + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", armazenamento=" + armazenamento +
                ", memoria=" + memoria +
                ", sistema='" + sistema + '\'' +
                ", iPod=" + iPod +
                ", telefone=" + telefone +
                ", safari=" + safari +
                '}';
    }
}

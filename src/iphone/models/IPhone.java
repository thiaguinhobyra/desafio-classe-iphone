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


}

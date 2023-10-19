import ipod.service.IpodServiceImpl;
import safari.service.SafariServiceImpl;
import telefone.model.Contato;
import telefone.service.TelefoneServiceImpl;

import java.util.*;

public class IPhone {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean chamando = false;

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

    public static void main(String[] args) {
        TelefoneServiceImpl telefoneServiceImpl = new TelefoneServiceImpl();
        telefoneServiceImpl.addContato("Contato 1", "99999999999");
        telefoneServiceImpl.addContato("Contato 2", "98999999999");
        telefoneServiceImpl.correioVoz("Mensagem de voz 2");
        telefoneServiceImpl.correioVoz("Mensagem de voz 1");
        chamando = true;

        IpodServiceImpl ipodServiceImpl = new IpodServiceImpl();
        ipodServiceImpl.adicionarMusica("Música 1", "Artista 1", 3);
        ipodServiceImpl.adicionarMusica("Música 2", "Artista 2", 4);
        ipodServiceImpl.adicionarMusica("Música 3", "Artista 3", 3);

        SafariServiceImpl safariService = new SafariServiceImpl();
        int app;

        do {
            System.out.println("Tela inicial");
            System.out.println("1. Ipod");
            System.out.println("2. Telefone");
            System.out.println("3. Safari");

            String entrada = scanner.nextLine();

            try {
                app = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                app = 0;
            }

            switch (app) {
                case 1:
                    while (true) {
                        ipodServiceImpl.listarMusicas();
                        System.out.println("Escolha o titulo da música ou digite 'sair': ");
                        String opcao = scanner.nextLine();

                        if (opcao.equalsIgnoreCase("sair")) {
                            break;
                        }
                        ipodServiceImpl.selecionarMusica(opcao);
                    }
                    break;
                case 2:
                    while (true) {
                        if (chamando) {
                            telefoneServiceImpl.atenderChamada("Contato 1");
                            chamando = true;
                            telefoneServiceImpl.atenderChamada("Contato 2");
                        }

                        System.out.println("'1' para abrir Telefone ou 'sair' para voltar para a lista de aplicativos: ");
                        String nome = scanner.nextLine();

                        if (nome.equalsIgnoreCase("1")) {
                            telefoneServiceImpl.OpcoesTelefone();
                        }

                        if (nome.equalsIgnoreCase("sair")) {
                            break;
                        }
                    }
                    break;
                case 3:
                    while (true) {
                        safariService.opcoes();
                        break;
                    }
                    break;
                default:
                    System.out.println("Opção inválida. ");
            }
        } while (app != 4);
    }
}

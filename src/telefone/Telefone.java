package telefone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Telefone {
    private boolean chamadaAtiva = false;
    private List<String> correioVoz = new ArrayList<>();
    private List<String> conferencia = new ArrayList<>();
    private Map<String, String> listaDeContatos = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private boolean chamadaEmEspera = false;
    private String contatoEmChamada1 = null;
    private String contatoEmChamada2 = null;
    private String contatoEmEspera = null;
    private String chamadaAndamento = null;


    public void addContato(String nome, String numero){
        listaDeContatos.put(nome, numero);
        System.out.println("Contato adicionado com sucesso! ");

    }

    public void listarContatos() {
        System.out.println("Contatos");
        for (String nome : listaDeContatos.keySet()) {
            System.out.println(nome + " - " + listaDeContatos.get(nome));
        }
    }

    public void selecionarContato(String nome) {
        if (listaDeContatos.containsKey(nome)) {
            String numero = listaDeContatos.get(nome);
            System.out.println(nome + " - " + numero);
            opcoes(nome, numero);
        } else {
            System.out.println("Contato não encontrado. ");
        }
    }

    public void opcoes(String nome, String numero) {
        System.out.println("Contato " + numero + ":");
        System.out.println("1. Ligar");
        System.out.println("2. Voltar");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                ligar(numero);
                break;
            case 2:
                break;
            default:
                System.out.println("Opção inválida. ");
        }
    }

    public void ligar(String numero) {
        if (chamadaAtiva) {
            System.out.println("Deixando chamada ativa em espera. ");
            chamadaEmEspera = true;
            contatoEmEspera = listaDeContatos.get(numero);
            System.out.println(numero + " em espera. ");
        } else {
            String contato = listaDeContatos.get(numero);
            chamadaAtiva = true;
            contatoEmChamada1 = contato;
            System.out.println("Ligando para " + numero);
        }
    }

    public void recebendoChamada(String nome) {
        chamadaAndamento = listaDeContatos.get(nome);
        System.out.println("O telefone" + chamadaAndamento + " está tocando... Digite 1 para atender ou 2 para recusar: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 2 ) {
            if (chamadaAtiva) {
                contatoEmChamada1 = chamadaAndamento;
                chamadaAtiva = true;
            }
            desligarChamada(chamadaAndamento);
        } else {
            atender();
        }
    }

    public void atender() {
        if (!chamadaAtiva) {
            contatoEmChamada1 = chamadaAndamento;
            System.out.println("Em chamada com: " + chamadaAndamento);
            chamadaAtiva = true;
            chamadaEmEspera = false;
            contatoEmEspera = null;
        } else if (chamadaAtiva) {
            contatoEmEspera = chamadaAndamento;
            System.out.println("Atendendo chamada em espera: " + contatoEmEspera);
            chamadaAtiva = true;
            chamadaEmEspera = true;
            contatoEmChamada2 = contatoEmEspera;
            contatoEmEspera = contatoEmChamada1;
        } else {
            System.out.println("Não tem chamada. ");
        }
        while (chamadaAtiva && contatoEmEspera != null) {
            System.out.println("1 para desligar chamada em andamento: " + chamadaAndamento);
            System.out.println("2 para reativar chamada em espera: " + contatoEmEspera);
            System.out.println("3 para conferência entre chamadas: " + contatoEmEspera + " e " + chamadaAndamento);
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    desligarChamada(chamadaAndamento);
                    chamadaAndamento = contatoEmEspera;
                    break;
                case 2:
                    String espera = chamadaAndamento;
                    chamadaAndamento = contatoEmEspera;
                    contatoEmEspera = espera;
                    System.out.println("Chamada em andamento: " + chamadaAndamento);
                    System.out.println("Chamada em espera: " + contatoEmEspera);
                    break;
                case 3:
                    Telefone telefone = new Telefone();
                    telefone.conferencia(chamadaAndamento);
                    telefone.conferencia(contatoEmEspera);
                    listaConferencia();
                    break;
                default:
                    System.out.println("Opção inválida. ");
            }
            desligarChamada(chamadaAndamento);

        }
    }

    public void desligarChamada(String numero) {
        if (contatoEmEspera != null) {
            System.out.println("Chamada " + chamadaAndamento + " encerrada. ");
            String nulo = chamadaAndamento;
            if (contatoEmEspera == contatoEmChamada2) {
                contatoEmChamada1 = null;
                chamadaAtiva = true;
                chamadaAndamento = contatoEmChamada2;
                System.out.println("Chamada ativa: " + chamadaAndamento);
            } else if (contatoEmEspera == contatoEmChamada1) {
                chamadaAndamento = contatoEmChamada1;
                System.out.println("Chamada ativa: " + chamadaAndamento);
                contatoEmChamada2 = null;
                chamadaAtiva = true;
            } else {
                System.out.println("Nenhuma chamada em andamento. ");
            }
            chamadaAndamento = contatoEmEspera;

            if (chamadaEmEspera) {
                System.out.println("Chamada ativa: " + chamadaAndamento);
                chamadaAtiva = true;
                chamadaEmEspera = false;
                if (chamadaAndamento == contatoEmChamada1) {
                    contatoEmChamada2 = null;
                } else {
                    contatoEmChamada1 = null;
                }
                contatoEmEspera = null;
            }
        } else {
            chamadaAtiva = false;
            contatoEmChamada1 = null;
            contatoEmChamada2 = null;
            chamadaAndamento = null;
            contatoEmEspera = null;
            chamadaEmEspera = false;
            System.out.println("Chamada " + chamadaAndamento + " encerrada. ");
        }
    }

    public void chamadaEmEspera() {
        if (chamadaAtiva) {
            System.out.println("Você já tem uma chamada ativa. ");
            chamadaEmEspera = true;
            chamadaAtiva = false;
            if (chamadaAndamento == contatoEmChamada1) {
                contatoEmEspera = contatoEmChamada2;
            } else if (chamadaAndamento == contatoEmChamada2){
                contatoEmEspera = contatoEmChamada1;
            } else {
                contatoEmChamada1 = null;
                contatoEmChamada2 = null;
            }
        } else if (chamadaEmEspera) {
            System.out.println("Já tem uma chamada em espera. ");
        } else {
            System.out.println("Colocando chamada em espera. ");
            chamadaEmEspera = true;
        }
    }

    public  void conferencia(String chamada) {
        conferencia.add(chamada);
        System.out.println("Chamada adicionada a conferência: " + chamada);
    }

    public void listaConferencia() {
        System.out.println("Chamadas em conferência: ");
        for (String chamada : conferencia) {
            System.out.println("- " + chamada);
        }
        while (!conferencia.isEmpty()) {
            System.out.println("Digite o número da chamada para desligar ou '0' para sair da conferencia:  ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            if (opcao == 0) {
                break;
            } else if (opcao >= 1 && opcao <= conferencia.size()) {
                System.out.println("Chamada " + opcao + ": " + conferencia.get(opcao - 1) + " encerrada. ");
                conferencia.remove(opcao - 1);
            } else {
                System.out.println("Opção inválido. ");
            }
        }
    }

    public void correioVoz(String msg) {
        correioVoz.add(msg);
        System.out.println("Mensagem de voz recebida: ");
    }

    public void listaCorreioVoz() {
        System.out.println("Mensagens de voz");
        for (String msg : correioVoz) {
            System.out.println("- " + msg);
        }
        ouvirCorreioVoz();
    }

    public void ouvirCorreioVoz() {
        if (correioVoz.isEmpty()) {
            System.out.println("Nenhuma mesagem de voz. ");
        } else {

            System.out.println("Mensagens de voz: ");
            for (int i = 0; i < correioVoz.size(); i++) {
                System.out.println((i + 1) + "- " + correioVoz.get(i));
            }
            while (!correioVoz.isEmpty()) {
                System.out.println("Digite o número da mesagem de voz para ouvir ou '0' para sair:  ");
                int numMsg = scanner.nextInt();
                scanner.nextLine();

                if (numMsg == 0) {
                    break;
                } else if (numMsg >= 1 && numMsg <= correioVoz.size()) {
                    System.out.println("Mensagem de voz " + numMsg + ": " + correioVoz.get(numMsg - 1) + " em execução.");

                    correioVoz.remove(numMsg - 1);


                } else {
                    System.out.println("Opção inválido. ");
                }
            }
        }
    }

    public static void main(String[] args) {
        Telefone telefone = new Telefone();
        telefone.addContato("Contato 1", "99999999999");
        telefone.addContato("Contato 2", "98999999999");
        telefone.correioVoz("Mensagem de voz 2");
        telefone.correioVoz("Mensagem de voz 1");

        while (true) {
            telefone.recebendoChamada("Contato 1");
            telefone.recebendoChamada("Contato 2");
            telefone.listarContatos();
            System.out.println("Buscar contato, '1' para correio de voz ou 'sair': ");
            String nome = telefone.scanner.nextLine();

            if (nome.equalsIgnoreCase("1")) {
                telefone.listaCorreioVoz();
            }

            if (nome.equalsIgnoreCase("sair")) {
                break;
            }

            telefone.selecionarContato(nome);
        }
        telefone.chamadaEmEspera();
        telefone.atender();
        //telefone.desligarChamada();


    }
}

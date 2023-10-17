package telefone.service;

import telefone.model.Contato;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TelefoneServiceImpl {
    private boolean chamadaAtiva = false;
    private List<String> correioVoz = new ArrayList<>();
    private List<String> conferencia = new ArrayList<>();
    private Map<String, Contato> listaDeContatos = new HashMap<>();
    private Map<String, String> listaDeChamadas = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private boolean chamadaEmEspera = false;
    private String contatoEmChamada1 = null;
    private String contatoEmChamada2 = null;
    private String contatoEmEspera = null;
    private String chamadaAndamento = null;
    private boolean chamando = false;
    private boolean ligando = false;


    public void addContato(String nome, String numero){
        Contato contato = new Contato(nome, numero);
        listaDeContatos.put(nome, contato);
        System.out.println("Contato adicionado com sucesso! ");

    }

    public void listarContatos() {
        System.out.println("Contatos");
        for (Contato contato : listaDeContatos.values()) {
            System.out.println(contato.getNome() + " - " + contato.getNumero());
        }
    }

    public void buscarContato(String nome) {
        if (listaDeContatos.containsKey(nome)) {
            Contato contato = listaDeContatos.get(nome);
            System.out.println(contato.getNome() + " - " + contato.getNumero());
            opcoes(nome);
        } else {
            System.out.println("Contato não encontrado. ");
        }
    }

    public void addChamada(String nome, String numero){
        listaDeChamadas.put(nome, numero);
        System.out.println("Contato adicionado com sucesso! ");

    }

    public void listarChamadas() {
        System.out.println("Contatos");
        for (String nome : listaDeChamadas.keySet()) {
            System.out.println(nome + " - " + listaDeChamadas.get(nome));
        }
    }

    public void buscarChamada(String nome) {
        if (listaDeChamadas.containsKey(nome)) {
            String numero = listaDeChamadas.get(nome);
            System.out.println(nome + " - " + numero);
            opcoes(nome);
        } else {
            System.out.println("Chamada não encontrada. ");
        }
    }

    public void opcoes(String nome) {
        System.out.println("Contato " + nome + ":");
        System.out.println("1. Ligar");
        System.out.println("2. Voltar");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                ligar(nome);
                break;
            case 2:
                break;
            default:
                System.out.println("Opção inválida. ");
        }
    }

    public void ligar(String nome) {
        if (chamadaAtiva && chamadaAndamento != null) {
            System.out.println("Deixando chamada ativa em espera. ");
            chamadaEmEspera = true;
            // String contato = listaDeContatos.get(numero);
            // contatoEmEspera = listaDeContatos.get(numero);
            contatoEmEspera = chamadaAndamento;
            //contatoEmChamada2 = contato;
            System.out.println(contatoEmEspera + " em espera. ");
            ligando = true;
        } else {
            String contato = nome;
            chamadaAtiva = true;
            contatoEmChamada1 = contato;
            TelefoneServiceImpl telefoneServiceImpl = new TelefoneServiceImpl();
            telefoneServiceImpl.buscarChamada(nome);
            System.out.println("Ligando para " + chamadaAndamento);
            System.out.println("Em chamada com " + contatoEmChamada1);
            ligando = true;
        }
    }

    public void recebendoChamada(String nome) {
        chamadaAndamento = nome;
        System.out.println("Recebendo ligação de " + chamadaAndamento + "... Digite 1 para atender ou 2 para recusar: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        chamando = true;
        if (opcao == 2 ) {
            if (chamadaAtiva) {
                contatoEmChamada1 = chamadaAndamento;
                chamadaAtiva = true;
            }
            desligarChamada(chamadaAndamento);
        } else {
            atenderChamada();
            // TODO: IMPLEMENTAR NOVA CHAMADA
        }
    }

    public void atenderChamada() {
        OpcoesChamada();
    }

    public void OpcoesChamada() {
        if (!chamadaAtiva && chamando) {
            chamando = false;
            contatoEmChamada1 = chamadaAndamento;
            System.out.println("Em chamada com: " + chamadaAndamento);
            chamadaAtiva = true;
            chamadaEmEspera = false;
            contatoEmEspera = null;
        } else if (chamadaAtiva) {
            if (chamando) {
                chamando = false;
                contatoEmEspera = contatoEmChamada1;
                System.out.println("Em chamada: " + chamadaAndamento + " e chamada em epsera: " + contatoEmEspera);
                chamadaAtiva = true;
                chamadaEmEspera = true;
                contatoEmChamada2 = contatoEmEspera;
                contatoEmEspera = contatoEmChamada1;
            } else {
                chamando = false;
                chamadaAndamento = contatoEmChamada1;
                System.out.println("Em chamada com: " + chamadaAndamento);
                chamadaEmEspera = false;
                contatoEmEspera = null;
                chamadaAtiva = true;
            }
        } else {
            System.out.println("Não tem chamada. ");
            System.out.println("chamando atender 1 else: "+chamando);
        }
        while (chamadaAtiva && chamadaEmEspera) {
            // TODO: CORRIGIR WHILE PARA DAR OPÇÃO DE DESLIGAR CHAMADA APOS UMA FOR ESCOLHIDA
                System.out.println("1 para desligar chamada em andamento: " + chamadaAndamento);
                System.out.println("2 para desligar chamada em espera: " + contatoEmEspera);
                System.out.println("3 para reativar chamada em espera: " + contatoEmEspera);
                System.out.println("4 para conferência entre chamadas: " + contatoEmEspera + " e " + chamadaAndamento);
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        desligarChamada(chamadaAndamento);
                        //chamadaAndamento = contatoEmEspera;
                        //chamadaEmEspera = false;
                        //contatoEmEspera = null;
                        //System.out.println("Chamada em andamento: " + chamadaAndamento);
                        break;
                    case 2:
                        desligarChamada(contatoEmEspera);
                        chamadaEmEspera = false;
                        contatoEmEspera = null;
                        System.out.println("Chamada em andamento: " + chamadaAndamento);
                        break;
                    case 3:
                        String espera = chamadaAndamento;
                        chamadaAndamento = contatoEmEspera;
                        contatoEmEspera = espera;
                        System.out.println("Chamada em andamento: " + chamadaAndamento);
                        System.out.println("Chamada em espera: " + contatoEmEspera);
                        break;
                    case 4:
                        TelefoneServiceImpl telefoneServiceImpl = new TelefoneServiceImpl();
                        telefoneServiceImpl.conferencia(chamadaAndamento);
                        telefoneServiceImpl.conferencia(contatoEmEspera);
                        chamadaEmEspera = false;
                        contatoEmEspera = null;
                        chamadaAndamento = null;
                        chamando = false;
                        telefoneServiceImpl.listaConferencia();
                        break;
                    default:
                        System.out.println("Opção inválida. ");
                }
        }
        while (ligando == true) {
                System.out.println("1 para desligar chamada em andamento: " + chamadaAndamento);
                System.out.println("2 para ligar para outro contato: ");
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        desligarChamada(chamadaAndamento);
                        chamadaEmEspera = false;
                        contatoEmEspera = null;
                        System.out.println("Chamada em andamento: " + chamadaAndamento);
                        break;
                    case 2:
                        TelefoneServiceImpl telefoneServiceImpl = new TelefoneServiceImpl();
                        telefoneServiceImpl.listarContatos();
                        System.out.println("Buscar contato, '1' para correio de voz ou 'sair': ");
                        String nome = telefoneServiceImpl.scanner.nextLine();

                        telefoneServiceImpl.buscarContato(nome);
                        chamando = false;
                        break;
                    default:
                        System.out.println("Opção inválida. ");
                }
        }
    }

    //criar opcaoChamada

    public void desligarChamada(String numero) {
        if (contatoEmEspera != null) {
            System.out.println("Chamada " + numero + " encerrada. ");
            //String nulo = chamadaAndamento;
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
                chamadaAtiva = false;
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
            System.out.println("Chamada " + numero + " encerrada, sem chamada em espera. ");
            /*TODO: CORRIGIR MSG */
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

    public void conferencia(String chamada) {
        conferencia.add(chamada);
        System.out.println("Chamada adicionada a conferência: " + chamada);
    }

    public void listaConferencia() {
        while (!conferencia.isEmpty()) {
            System.out.println("Chamadas em conferência: ");
            for (int i = 0; i < conferencia.size(); i++) {
                System.out.println((i + 1) + "- " + conferencia.get(i));
            }
            System.out.println("Digite o número da chamada para desligar ou '0' para sair da conferencia:  ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            if (opcao == 0) {
                conferencia.removeAll(conferencia);
                // chamar desligar todas
                for (int i = 0; i < conferencia.size(); i++) {
                    desligarChamada(conferencia.get(i));
                }
                break;
            } else if (opcao >= 1 && opcao <= conferencia.size()) {
                String conferenciaOpcao = conferencia.get(opcao - 1);
                conferencia.remove(conferenciaOpcao);
                desligarChamada(conferenciaOpcao);
                if (conferencia.size() < 1) {
                    chamadaAndamento = conferencia.toString();
                    chamadaAtiva = false;
                    System.out.println("Conferência finalizada. ");
                    break;
                } else if (conferencia.size() == 1) {
                    chamadaAndamento = conferencia.toString();
                    System.out.println("Chamada em andamento: " + chamadaAndamento.toString());
                    // TODO: CORRIGIR LOOP AQUI
                }
            } else {
                System.out.println("Opção inválida. ");
            }
        }
        chamadaAtiva = false;
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
                int opcao = scanner.nextInt();
                scanner.nextLine();

                if (opcao == 0) {
                    break;
                } else if (opcao >= 1 && opcao <= correioVoz.size()) {
                    System.out.println("Mensagem de voz " + opcao + ": " + correioVoz.get(opcao - 1) + " em execução.");

                    correioVoz.remove(opcao - 1);


                } else {
                    System.out.println("Opção inválido. ");
                }
            }
        }
    }

    public static void main(String[] args) {
        TelefoneServiceImpl telefoneServiceImpl = new TelefoneServiceImpl();
        telefoneServiceImpl.addContato("Contato 1", "99999999999");
        telefoneServiceImpl.addContato("Contato 2", "98999999999");
        telefoneServiceImpl.correioVoz("Mensagem de voz 2");
        telefoneServiceImpl.correioVoz("Mensagem de voz 1");
        telefoneServiceImpl.chamando = true;
        while (true) {
            if (telefoneServiceImpl.chamando) {
                telefoneServiceImpl.recebendoChamada("Contato 1");
                telefoneServiceImpl.recebendoChamada("Contato 2");
            }

            telefoneServiceImpl.listarContatos();
            System.out.println("Buscar contato, '1' para correio de voz ou 'sair': ");
            String nome = telefoneServiceImpl.scanner.nextLine();

            if (nome.equalsIgnoreCase("1")) {
                telefoneServiceImpl.listaCorreioVoz();
            }

            if (nome.equalsIgnoreCase("sair")) {
                break;
            }

            telefoneServiceImpl.buscarContato(nome);

        }
    }
}

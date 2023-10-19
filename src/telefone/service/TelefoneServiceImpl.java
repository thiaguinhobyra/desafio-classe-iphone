package telefone.service;

import telefone.model.Contato;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TelefoneServiceImpl implements TelefoneService{
    private boolean chamadaAtiva = false;
    private List<String> correioVoz = new ArrayList<>();
    private List<String> conferencia = new ArrayList<>();
    private Map<String, Contato> listaDeContatos = new HashMap<>();
    private Map<String, Contato> listaDeChamadas = new HashMap<>();
    private Map<String, Contato> listaDeEspera = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private boolean chamadaEmEspera = false;
    private String contatoEmEspera = null;
    private String chamadaAndamento = null;
    private static boolean chamando = true;
    private boolean ligando = false;

    @Override
    public void addContato(String nome, String numero){
        Contato contato = new Contato(nome, numero);
        listaDeContatos.put(nome, contato);
        System.out.println("Contato adicionado com sucesso! ");

    }

    @Override
    public void listarContatos() {
        System.out.println("Contatos");
        for (Contato contato : listaDeContatos.values()) {
            System.out.println(contato.getNome() + " - " + contato.getNumero());
        }
    }
    @Override
    public void buscarContato(String nome) {
        if (listaDeContatos.containsKey(nome)) {
            Contato contato = listaDeContatos.get(nome);
            System.out.println(contato.getNome() + " - " + contato.getNumero());
            opcoes(contato.getNome(), contato.getNumero());
        } else {
            System.out.println("Contato não encontrado. ");
        }
    }

    @Override
    /*public void listarChamadas() {
        System.out.println("Chamadas em andamento: ");
        for (Contato contato : listaDeChamadas.values()) {
            System.out.println(contato.getNome() + " - " + contato.getNumero());
        }
        if (listaDeChamadas.size() > 0) {
            System.out.println("Chamada atual: " + listaDeChamadas.values());
        } else {
            System.out.println("Nenhuma chamada em andamento");
        }
    }*/
    public void listarChamadas() {
        System.out.println("Chamadas em andamento: ");

        if (listaDeChamadas.isEmpty()) {
            System.out.println("Nenhuma chamada em andamento");
        } else {
            for (Contato contato : listaDeChamadas.values()) {
                System.out.println(contato.getNome() + " - " + contato.getNumero());
            }
        }
    }


    @Override
    public void listarEsperas() {
        System.out.println("Contatos em espera: ");
        for (Contato contato : listaDeEspera.values()) {
            System.out.println(contato.getNome() + " - " + contato.getNumero());
        }
    }

    public void buscarChamada(String nome, String numero) {
        if (listaDeChamadas.containsKey(nome)) {
            chamadaAndamento = nome;
            System.out.println(nome + " - " + nome);
            opcoes(nome, numero);
        } else {
            System.out.println("Chamada não encontrada. ");
        }
    }

    public void opcoes(String nome, String numero) {
        //System.out.println("Contato " + nome + ":");
        System.out.println("1. Ligar");
        System.out.println("2. Voltar");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                ligar(nome, numero);
                break;
            case 2:
                break;
            default:
                System.out.println("Opção inválida. ");
        }
    }

    @Override
    public void ligar(String nome, String numero) {
        if (chamadaAtiva && chamadaAndamento != null) {
            System.out.println("Deixando chamada ativa em espera. ");
            System.out.println("Deixando chamada ativa em espera. ");
            chamadaEmEspera = true;
            // String contato = listaDeContatos.get(numero);
            // contatoEmEspera = listaDeContatos.get(numero);
            contatoEmEspera = chamadaAndamento;
            Contato contato = new Contato(nome, numero);
            listaDeChamadas.put(nome, contato);
            chamadaAndamento = nome;
            //contatoEmChamada2 = contato;
            System.out.println(contatoEmEspera + " em espera. ");
            System.out.println(chamadaAndamento + " em andamento. ");
            ligando = true;
        } else {
            Contato contato = new Contato(nome, numero);
            listaDeChamadas.put(nome, contato);
            chamadaAtiva = true;
            //buscarChamada(contato.getNome(), contato.getNumero());
            chamadaAndamento = contato.getNome();
            System.out.println("Ligando para " + chamadaAndamento);
            //System.out.println("Em chamada com " + chamadaAndamento);
            ligando = true;
        }
    }

    @Override
    public void atenderChamada(String nome) {
        System.out.println("Recebendo chamada de " + nome);
        System.out.println("1. Atender: ");
        System.out.println("2. Recusar: ");
        int opcaoAtender = scanner.nextInt();

        switch (opcaoAtender) {
            case 1:
                if(!chamadaAtiva) chamando = true;
                OpcoesChamada(nome);
                break;
            case 2:
                chamadaAndamento = nome;
                desligarChamada(nome);
                break;
        }
    }

    public void OpcoesChamada(String nome) {
        Contato contato = listaDeContatos.get(nome);
                if (!chamadaAtiva && chamando) {
                    listaDeChamadas.put(contato.getNome(), contato);
                    chamadaAndamento = contato.getNome();
                    //chamando = false;
                    System.out.println("Em chamada com: " + chamadaAndamento);
                    chamadaAtiva = true;
                    chamadaEmEspera = false;
                    contatoEmEspera = null;
                } else if (chamadaAtiva) {
                    if (chamando) {
                        contatoEmEspera = chamadaAndamento;
                        listaDeEspera.put(chamadaAndamento, contato);
                        listaDeChamadas.put(contato.getNome(), contato);
                        chamadaAndamento = contato.getNome();
                        chamando = false;
                        System.out.println("Em chamada: " + chamadaAndamento + " e chamada(s) em epsera: " + contatoEmEspera);
                        chamadaAtiva = true;
                        chamadaEmEspera = true;
                    } else if (chamadaEmEspera) {
                        while (chamadaAtiva && chamadaEmEspera) {
                            // TODO: CORRIGIR WHILE PARA DAR OPÇÃO DE DESLIGAR CHAMADA APOS UMA FOR ESCOLHIDA
                            System.out.println("1. para desligar chamada em andamento: " + chamadaAndamento);
                            System.out.println("2. para desligar chamada em espera: " + contatoEmEspera);
                            System.out.println("3. para reativar chamada em espera: " + contatoEmEspera);
                            System.out.println("4. para conferência entre chamadas: " + contatoEmEspera + " e " + chamadaAndamento);
                            int opcaoChamadas = scanner.nextInt();

                            switch (opcaoChamadas) {
                                case 1:
                                    desligarChamada(chamadaAndamento);
                                    //chamadaAndamento = contatoEmEspera;
                                    //chamadaEmEspera = false;
                                    //contatoEmEspera = null;
                                    //System.out.println("Chamada em andamento: " + chamadaAndamento);
                                    break;
                                case 2:
                                    desligarChamada(contatoEmEspera);
                                    //chamadaEmEspera = false;
                                    //contatoEmEspera = null;
                                    //System.out.println("Chamada em andamento: " + chamadaAndamento);
                                    break;
                                case 3:
                                    String espera = chamadaAndamento;
                                    chamadaAndamento = contatoEmEspera;
                                    contatoEmEspera = espera;
                                    System.out.println("Chamada em andamento: " + chamadaAndamento);
                                    System.out.println("Chamada em espera: " + contatoEmEspera);
                                    break;
                                case 4:
                                    for (Contato contatoAdd : listaDeContatos.values()) {
                                        conferencia(contatoAdd.getNome());
                                        listaDeChamadas.remove(contatoAdd.getNome());
                                        listaDeEspera.remove(contatoAdd.getNome());
                                    }
                                    //conferencia(contatoEmEspera);
                                    chamadaEmEspera = false;
                                    contatoEmEspera = null;
                                    chamadaAndamento = null;
                                    chamando = false;
                                    listaConferencia();
                                    break;
                                default:
                                    System.out.println("Opção inválida. ");
                            }
                        }
                    } else {
                        chamando = false;
                        //chamadaAndamento = conferencia.toString();
                        if (!conferencia.isEmpty())chamadaAndamento = conferencia.toString();
                        if (!chamadaAndamento.isEmpty())chamadaAndamento = listaDeChamadas.toString();
                        System.out.println("Em chamada com: " + chamadaAndamento);
                        chamadaEmEspera = false;
                        contatoEmEspera = null;
                        chamadaAtiva = true;
                    }
                /*} else {
                    System.out.println("Não tem chamada. ");
                    System.out.println("chamando atender 1 else: "+ chamando);*/
                }
                System.out.println("1. Teclado: ");
                System.out.println("2. Mudo: ");
                System.out.println("3. Alto-falante: ");
                if (!chamadaEmEspera) System.out.println("4. Em espera: ");
                if (chamadaEmEspera) System.out.println("4. Reativa chamada em espera: ");
                System.out.println("5. Nova Chamada: ");
                System.out.println("6. Desligar: ");
                int opcaoChamada = scanner.nextInt();

                switch (opcaoChamada) {
                    case 1:
                        System.out.println("1   2   3");
                        System.out.println("4   5   6");
                        System.out.println("7   8   9");
                        System.out.println("*   0   #");
                        int teclado = scanner.nextInt();
                        break;
                    case 2:
                        System.out.println("Mudo...");
                        break;
                    case 3:
                        System.out.println("Alto-falante...");
                        break;
                    case 4:
                        if (chamadaEmEspera) {
                            chamadaAndamento = contatoEmEspera;
                            System.out.println("Chamada reativa: " + chamadaAndamento);
                            listaDeEspera.remove(contatoEmEspera);
                            chamadaEmEspera = false;
                            contatoEmEspera = null;
                            chamadaAtiva = true;
                        } else
                            EmEspera(chamadaAndamento);
                        break;
                    case 5:
                        OpcoesTelefone();
                        break;
                    case 6:
                        desligarChamada(nome);
                        break;
                }


    }

    public void OpcoesTelefone() {
        int opcao;

        do {
            if (listaDeChamadas.isEmpty()) {
                System.out.println("Nenhuma chamada. ");
            } else {
                listarChamadas();
            }
            System.out.println("1. Buscar contato");
            System.out.println("2. Adicionar contato");
            System.out.println("3. Correio de voz");
            if(!listaDeChamadas.isEmpty() || !conferencia.isEmpty()) System.out.println("4. Opções de chamada");


            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    if (ligando == true) {
                        System.out.println("1 para desligar chamada em andamento: " + chamadaAndamento);
                        System.out.println("2 para ligar para outro contato: ");
                        int opcaoLiga = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcaoLiga) {
                            case 1:
                                desligarChamada(chamadaAndamento);
                                chamadaEmEspera = false;
                                contatoEmEspera = null;
                                System.out.println("Chamada em andamento: " + chamadaAndamento);
                                break;
                            case 2:
                                listarContatos();
                                System.out.println("Digite o nome para buscar contato: ");
                                String BucarContato = scanner.nextLine();

                                buscarContato(BucarContato);
                                chamando = false;
                                break;
                            default:
                                System.out.println("Opção inválida. ");
                        }
                    } else {
                        listarContatos();
                        System.out.println("Digite o nome para buscar contato: ");
                        String BucarContato = scanner.nextLine();

                        buscarContato(BucarContato);
                        chamando = false;
                    }
                    break;
                case 2:
                    System.out.println("Nome do contato: ");
                    String nomeContato = scanner.nextLine();
                    System.out.println("Número do contato: ");
                    String numContato = scanner.nextLine();
                    addContato(nomeContato, numContato);
                    break;
                case 3:
                    listarCorreioVoz();
                    break;
                case 4:
                    OpcoesChamada(chamadaAndamento);
                    break;
            }
        } while (opcao != 5);
    }

    public void desligarChamada(String nome) {
        System.out.println("desligando " + nome);
        if (!conferencia.isEmpty()) {
            //System.out.println("Nenhuma chamada em andamento.");
            //listaDeChamadas.remove(nome);
            //chamadaAndamento = listaDeChamadas.keySet().iterator().next();
            //chamadaAtiva = false;
            contatoEmEspera = null;
            chamadaEmEspera = false;
            System.out.println("Chamada " + nome + " encerrada, sem chamadas em espera. ");
            /*TODO: CORRIGIR MSG */
        } else if (!chamadaAndamento.isEmpty()) {
            System.out.println("Chamada " + nome + " encerrada. ");
            listaDeChamadas.remove(chamadaAndamento);
            if (!listaDeEspera.isEmpty()) {
                chamadaAndamento = listaDeEspera.keySet().iterator().next();
                System.out.println("Chamada ativa: " + chamadaAndamento);
                chamadaAtiva = true;
            } else {
                chamadaAndamento = null;
                chamadaAtiva = false;
                //contatoEmEspera = null;
                // TODO: contatoEmEspera substituir por listaEspera;
            }
        } else if (!contatoEmEspera.isEmpty()) {
            System.out.println("Chamada ativa: " + chamadaAndamento);
            chamadaAtiva = true;
            chamadaEmEspera = false;
            contatoEmEspera = null;
        } else {
            System.out.println("Nenhuma chamada em andamento.");
            chamadaAtiva = false;
            chamadaAndamento = null;
            contatoEmEspera = null;
            chamadaEmEspera = false;
            System.out.println("Chamada " + nome + " encerrada, sem chamadas em espera. ");
            /*TODO: CORRIGIR MSG */
        }
        chamando = false;
    }

    public void EmEspera(String nome) {
        Contato contato = listaDeChamadas.get(nome);
        if (chamadaAtiva) {
            //System.out.println("Chamada " + contato + " em espera. ");
            if (listaDeEspera.isEmpty()) {
                listaDeEspera.put(chamadaAndamento, contato);
                contatoEmEspera = chamadaAndamento;
                System.out.println("Contato em espera: " + contatoEmEspera);
                chamadaAndamento = null;
                chamadaAtiva = false;
                //contatoEmEspera = null;
                // TODO: contatoEmEspera substituir por listaEspera;
            } else {
                contatoEmEspera = chamadaAndamento;
                chamadaAndamento = listaDeEspera.keySet().iterator().next();
                chamadaAtiva = true;
                System.out.println("Contato em espera: " + contatoEmEspera);
            }
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
                System.out.println("Chamada em andamento: " + conferencia.toString());

                if (conferencia.size() <= 1) {
                    System.out.println("Conferência finalizada. ");
                    chamadaAndamento = conferencia.toString();
                    conferencia.removeAll(conferencia);
                    OpcoesTelefone();
                //} else if (conferencia.size() > 1) {
                  //  System.out.println("Chamada em andamento: " + conferencia.toString());
                    // TODO: CORRIGIR LOOP AQUI
                }
                break;
            } else {
                System.out.println("Opção inválida. ");
            }
        }
        //chamadaAtiva = false;
    }

    @Override
    public void correioVoz(String msg) {
        correioVoz.add(msg);
        System.out.println("Mensagem de voz recebida: ");
    }

    @Override
    public void listarCorreioVoz() {
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
}

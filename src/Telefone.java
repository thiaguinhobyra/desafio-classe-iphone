import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Telefone {
    private boolean chamadaAndamento = false;
    private List<String> correioVoz = new ArrayList<>();
    private Map<String, String> listaDeContatos = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private boolean chamadaEmEspera = false;
    private String contatoEmChamada = null;
    private String contatoEmEspera = null;


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
        if (chamadaAndamento) {
            System.out.println("Deixando chamada ativa em espera. ");
            chamadaEmEspera = true;
            contatoEmEspera = listaDeContatos.get(numero);
            System.out.println(numero + " em espera. ");
        } else {
            String contato = listaDeContatos.get(numero);
            chamadaAndamento = true;
            contatoEmChamada = contato;
            System.out.println("Ligando para " + numero);
        }
    }

    public void atender() {
        if (chamadaAndamento) {
            System.out.println("Em chamada com: " + contatoEmChamada);
        } else if (chamadaEmEspera) {
            System.out.println("Atendendo chamada em espera: " + contatoEmEspera);
            chamadaAndamento = true;
            chamadaEmEspera = false;
            contatoEmChamada = contatoEmEspera;
            contatoEmEspera = null;
        } else {
            System.out.println("Não tem chamada. ");
        }
    }

    public void desligarChamada() {
        if (chamadaAndamento) {
            System.out.println("Chamada encerrada. ");
            chamadaAndamento = false;
            contatoEmChamada = null;
            if (chamadaEmEspera) {
                System.out.println("Chamada em espera agora ativa. ");
                chamadaAndamento = true;
                chamadaEmEspera = false;
                contatoEmChamada = contatoEmEspera;
                contatoEmEspera = null;
            }
        } else {
            System.out.println("Não há chamada. ");
        }
    }

    public void chamadaEmEspera() {
        if (chamadaAndamento) {
            System.out.println("Você já tem uma chamada ativa. ");
            chamadaEmEspera = true;
            chamadaAndamento = false;
            contatoEmEspera = contatoEmChamada;
            contatoEmChamada = null;
        } else if (chamadaEmEspera) {
            System.out.println("Já tem uma chamada em espera. ");
        } else {
            System.out.println("Chamada em espera. ");
            chamadaEmEspera = true;
        }
    }

    public void correioVoz(String msg) {
        correioVoz.add(msg);
        System.out.println("Mensagem de voz recebida. ");
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
        telefone.desligarChamada();


    }
}

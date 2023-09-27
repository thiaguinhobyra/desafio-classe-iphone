import java.util.*;

public class Telefone {
    private boolean chamadaAndamento = false;
    private List<String> correioVoz = new ArrayList<>();
    private Map<String, String> listaDeContatos = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

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
        System.out.println("1. Ligar");
        System.out.println("2. Voltar");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                chamada(numero);
                break;
            case 2:
                break;
            default:
                System.out.println("Opção inválida. ");
        }
    }

    public void chamada(String numero) {
        if (chamdaAtiva) {
            System.out.println("Chamda em andamento");
            opcoesAtender();
        } else {
            chamadaAtiva = true;
            contatoEmChamada = numero;
        }
    }

    public void opcoesAtender() {
        System.out.println("1. Desligar");
        System.out.println("2. Espera");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                desligarChamada();
                break;
            case 2:
                espera();
                break;
            default:
                System.out.println("Opção inválida. ");
        }
    }

    public void ligar() {

    }
    public static void main(String[] args) {

    }
}

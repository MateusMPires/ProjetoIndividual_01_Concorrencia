
// Representa uma loja que possui uma conta, funcionários e interage com um banco para pagar salários
class Loja {

    private Conta conta;  // Conta da loja
    private Funcionario[] funcionarios; // Array de funcionários da loja
    private Banco banco; // Referência ao banco com o qual a loja interage

    // Construtor
    public Loja(Conta conta, Funcionario[] funcionarios, Banco banco) {
        this.conta = conta;
        this.funcionarios = funcionarios;
        this.banco = banco;
    }

    // Função para pagar os salários dos funcionários
    public synchronized void pagarSalarios() {

        // Verifica se a conta da loja possui saldo suficiente para pagar os salários
        if (conta.getSaldo() >= Funcionario.salarioBase) {

            // Realiza a transferência do salário para a conta do primeiro funcionário
            banco.transferencia(conta, funcionarios[0].getContaSalario(), Funcionario.salarioBase);

            // O primeiro funcionário investe parte do seu salário
            funcionarios[0].investir();

            System.out.println("Salário pago a " + funcionarios[0].getName());

            // Troca os funcionários de posição no array
            Funcionario temp = funcionarios[0];
            funcionarios[0] = funcionarios[1];
            funcionarios[1] = temp;
        } else {
            // Caso a loja não tenha saldo suficiente, exibe uma mensagem de aviso
            System.out.println("A loja não possui saldo suficiente para pagar os salários.");
        }
    }

    // Método para obter a conta da loja
    public Conta getContaLoja() {
        return conta;
    }
}
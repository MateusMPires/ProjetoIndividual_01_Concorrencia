import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


// Representa um funcionário que realiza investimentos
class Funcionario extends Thread {

    // Salário base do funcionário
    public static final double salarioBase = 1400.0;

    // Conta de salário do funcionário
    private Conta contaSalario;

    // Conta de investimentos do funcionário
    public Conta contaInvestimentos;

    private Lock trava = new ReentrantLock();

    // Construtor
    public Funcionario(Conta contaSal, Conta contaInvest) {
        this.contaSalario = contaSal;
        this.contaInvestimentos = contaInvest;
    }

    // Função que realiza investimentos
    public void investir() {
        trava.lock(); // Bloqueando o acesso ao investimento
        try {

            // Calcula o valor do investimento (20% do salário base)
            double valorInvestimento = salarioBase * 0.2;

            // Transfere o valor para a conta de investimentos
            contaSalario.transferir(contaInvestimentos, valorInvestimento);

            System.out.println(Thread.currentThread().getName() + " investiu R$" + valorInvestimento + " na conta de investimentos.");
        } finally {
            trava.unlock(); // Libera o acesso ao investimento
        }
    }

    // Função para obter a conta de salário do funcionário
    public Conta getContaSalario() {
        return contaSalario;
    }
}
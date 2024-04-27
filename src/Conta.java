import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Representa uma conta bancária
class Conta {

    private double saldo; // Saldo da conta
    private Lock lock = new ReentrantLock(); // Instância de Lock

    // Construtor
    public Conta(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    // Função para saber o saldo atual
    public double getSaldo() {
        return saldo;
    }

    // Função para retirar dinheiro da Conta
    public void sacar(double valorDeSaque) {
        lock.lock(); // Bloqueia o acesso à conta
        try {
            saldo -= valorDeSaque;
        } finally {
            lock.unlock(); // Desbloqueando acesso à conta
        }
    }

    // Função de adicionar um valor na Conta
    public void depositar(double valorDeDeposito) {
        lock.lock(); // Bloqueia o acesso à conta
        try {
            saldo += valorDeDeposito;
        } finally {
            lock.unlock(); // Desbloqueando acesso à conta
        }
    }
}

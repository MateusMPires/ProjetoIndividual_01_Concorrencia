import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Representa uma conta bancária
class Conta {

    private double saldo; // Saldo da conta
    private Lock trava = new ReentrantLock(); // Instância de Lock

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
        trava.lock(); // Bloqueia o acesso à conta
        try {
            saldo -= valorDeSaque;
        } finally {
            trava.unlock(); // Desbloqueando acesso à conta
        }
    }

    // Função de adicionar um valor na Conta
    public void depositar(double valorDeDeposito) {
        trava.lock(); // Bloqueia o acesso à conta
        try {
            saldo += valorDeDeposito;
        } finally {
            trava.unlock(); // Desbloqueando acesso à conta
        }
    }

    // Função de transferir um valor para outra conta
    public void transferir(Conta destino, double valor) {
        trava.lock();
        try {
            sacar(valor);
            destino.depositar(valor);
        } finally {
            trava.unlock();
        }
    }
}

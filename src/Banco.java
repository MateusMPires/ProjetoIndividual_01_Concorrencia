
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Instituição financeira que gerencia transferências entre contas
class Banco {
    private Lock trava = new ReentrantLock(); // Instância de Lock

    // Função para transferir dinheiro entre contas
    public synchronized void transferencia(Conta contaOrigem, Conta contaDestino, double valorTransferencia) {
        trava.lock();
        try {

            // Verifica se a conta de origem possui saldo suficiente para a transferência
            if (contaOrigem.getSaldo() >= valorTransferencia) {

                // Se sim, realiza a transferência
                contaOrigem.sacar(valorTransferencia); // Retirar o valor da conta de origem
                contaDestino.depositar(valorTransferencia); // Depositar o valor na conta de destino

                System.out.println("Transferência de R$" + valorTransferencia + " realizada de " + contaOrigem + " para " + contaDestino);
            } else {
                System.out.println("Saldo insuficiente para realizar a transferência de " + contaOrigem + " para " + contaDestino);
            }
        } finally {
            trava.unlock();
        }
    }
}


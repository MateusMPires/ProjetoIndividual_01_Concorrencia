
// Classe Cliente representa um cliente que realiza compras em lojas
class Cliente extends Thread {
    // Valores das compras possíveis
    private static final double[] valoresComprasPossiveis = {100.0, 200.0};

    // Conta do cliente
    final private Conta contaCliente;

    // Array de lojas que o cliente visita
    final private Loja[] lojas;

    // Banco onde o cliente realiza as transações
    final private Banco banco;

    // Construtor para inicializar o cliente com sua conta, lojas e banco
    public Cliente(Conta conta, Loja[] lojas, Banco banco) {
        this.contaCliente = conta;
        this.lojas = lojas;
        this.banco = banco;
    }

    // Função principal que é executado quando a thread do cliente é iniciada
    public void run() {
        // Loop enquanto o cliente tiver saldo na conta
        while (contaCliente.getSaldo() > 0) {
            // Gera um valor aleatório de compra a partir dos valores possíveis
            double valorCompra = valoresComprasPossiveis[(int) (Math.random() * valoresComprasPossiveis.length)];

            synchronized (contaCliente) { // Sincronização para garantir operações seguras
                realizarCompra(valorCompra, lojas[0].getContaLoja());
            }

            // Paga os salários nas lojas visitadas
            lojas[0].pagarSalarios();

            // Troca as lojas de posição no array
            Loja tempLoja = lojas[0];
            lojas[0] = lojas[1];
            lojas[1] = tempLoja;
        }
    }

    // Função que realiza uma compra
    public synchronized void realizarCompra(double valor, Conta contaDestino) {

        // Verifica se o cliente possui saldo suficiente para a compra
        if (contaCliente.getSaldo() >= valor) {

            // Realiza a transferência bancária para pagar a compra
            banco.transferencia(contaCliente, contaDestino, valor);
            System.out.println(Thread.currentThread().getName() + " realizou uma compra de R$" + valor + ".");
        } else {
            // Exibe uma mensagem se o cliente não tiver saldo suficiente para a compra
            System.out.println(Thread.currentThread().getName() + " não possui saldo para fazer a compra de R$" + valor + ".");
        }
    }

}
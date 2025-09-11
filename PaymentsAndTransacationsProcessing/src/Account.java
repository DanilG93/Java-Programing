public class Account {

    private int id;
    private Transaction[] transactions = new Transaction[100];

    public Account(int id) {
        this.id = id;
    }

    public void sendMoneyToAccount(Account accountTo, double moneyAmount) {
        // Validacija ulaznih podataka
        if (accountTo == null || moneyAmount <= 0) {
            return;
        }

        // Dodavanje transakcije za pošiljaoca
        this.addTransaction(new Transaction(this, accountTo, moneyAmount, StandardAccountOperations.MONEY_TRANSFER_SEND));

        // Dodavanje transakcije za primaoca
        accountTo.addTransaction(new Transaction(this, accountTo, moneyAmount, StandardAccountOperations.MONEY_TRANSFER_RECEIVE));
    }

    public void withdrawMoney(double moneyAmount) {
        if (moneyAmount <= 0) {
            return;
        }

        this.addTransaction(new Transaction(this, null, moneyAmount, StandardAccountOperations.WITHDRAW));
    }

    public Transaction[] getTransactions() {
        int count = 0;
        for (Transaction t : transactions) {
            if (t != null) count++;
        }

        Transaction[] result = new Transaction[count];
        for (int i = 0, j = 0; i < transactions.length; i++) {
            if (transactions[i] != null) {
                result[j++] = transactions[i];
            }
        }

        return result;
    }

    private void addTransaction(Transaction transaction) {
        for (int i = 0; i < transactions.length; i++) {
            if (transactions[i] == null) {
                transactions[i] = transaction;
                break;
            }
        }
    }

    public static class Transaction {
        private Account accountFrom;
        private Account accountTo;
        private double moneyAmount;
        private StandardAccountOperations operation;

        public Transaction(Account accountFrom, Account accountTo, double moneyAmount, StandardAccountOperations operation) {
            this.accountFrom = accountFrom;
            this.accountTo = accountTo;
            this.moneyAmount = moneyAmount;
            this.operation = operation;
        }

        public Account getAccountFrom() {
            return accountFrom;
        }

        public Account getAccountTo() {
            return accountTo;
        }

        public double getMoneyAmount() {
            return moneyAmount;
        }

        public StandardAccountOperations getOperation() {
            return operation;
        }
    }
}

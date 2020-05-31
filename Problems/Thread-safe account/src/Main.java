class Account {

    private long balance = 0;

    public synchronized boolean withdraw(long amount) {
        if (getBalance() >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public synchronized void deposit(long amount) {
        balance += amount;
    }

    public synchronized long getBalance() {
        return balance;
    }
}
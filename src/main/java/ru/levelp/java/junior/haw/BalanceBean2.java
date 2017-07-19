package ru.levelp.java.junior.haw;

public class BalanceBean2 {
    private final double delta;
    private final double balance;

    public BalanceBean2(double delta, double balance) {
        this.delta = delta;
        this.balance = balance;
    }

    public double getDelta() {
        return delta;
    }

    public double getValue() {
        return balance;
    }
}

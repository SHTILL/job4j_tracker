package ru.job4j.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class BankService {
    private Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    public void addAccount(String passport, Account account) {
        User u = findByPassport(passport);
        if (u != null) {
            List<Account> accounts = users.get(u);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    public User findByPassport(String passport) {
        return users.keySet().stream()
                            .filter(e -> e.equals(new User(passport, "")))
                            .findFirst()
                            .orElse(null);
    }

    public Account findByRequisite(String passport, String requisite) {
        return users.entrySet().stream()
                                .filter(e -> e.getKey().equals(new User(passport, "")))
                                .flatMap(e -> e.getValue().stream())
                                .filter(e -> e.getRequisite().equals(requisite))
                                .findFirst()
                                .orElse(null);
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        if (srcAccount == null || srcAccount.getBalance() < amount) {
            return false;
        }
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (destAccount == null) {
            return false;
        }
        users.entrySet().stream()
                .filter(e -> e.getKey().equals(new User(srcPassport, "")))
                .flatMap(e -> e.getValue().stream())
                .filter(e -> e.getRequisite().equals(srcRequisite))
                .forEach(e -> e.setBalance(e.getBalance() - amount));
        users.entrySet().stream()
                .filter(e -> e.getKey().equals(new User(destPassport, "")))
                .flatMap(e -> e.getValue().stream())
                .filter(e -> e.getRequisite().equals(destRequisite))
                .forEach(e -> e.setBalance(e.getBalance() + amount));
        return true;
    }
}

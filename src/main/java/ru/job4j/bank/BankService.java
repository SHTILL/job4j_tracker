package ru.job4j.bank;

import java.util.*;
import java.util.stream.Stream;

public class BankService {
    private Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    public void addAccount(String passport, Account account) {
        Optional<User> u = findByPassport(passport);
        if (u.isPresent()) {
            List<Account> accounts = users.get(u.get());
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    public Optional<User> findByPassport(String passport) {
        return users.keySet().stream()
                            .filter(e -> e.equals(new User(passport, "")))
                            .findFirst();
    }

    public Optional<Account> findByRequisite(String passport, String requisite) {
        return users.entrySet().stream()
                                .filter(e -> e.getKey().equals(new User(passport, "")))
                                .flatMap(e -> e.getValue().stream())
                                .filter(e -> e.getRequisite().equals(requisite))
                                .findFirst();
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Optional<Account> srcAccount = findByRequisite(srcPassport, srcRequisite);
        if (srcAccount.isEmpty() || srcAccount.get().getBalance() < amount) {
            return false;
        }
        Optional<Account> destAccount = findByRequisite(destPassport, destRequisite);
        if (destAccount.isEmpty()) {
            return false;
        }
        srcAccount.get().setBalance(srcAccount.get().getBalance() - amount);
        destAccount.get().setBalance(destAccount.get().getBalance() + amount);
        return true;
    }
}

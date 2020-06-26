package ru.job4j.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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
        for (User u: users.keySet()) {
            if (u.getPassport().equals(passport)) {
                return u;
            }
        }
        return null;
    }

    public Account findByRequisite(String passport, String requisite) {
        List<Account> accounts = users.get(new User(passport, ""));
        if (accounts != null) {
            for (Account a: accounts) {
                if (a.getRequisite().equals(requisite)) {
                        return a;
                }
            }
        }
        return null;
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
        srcAccount.setBalance(srcAccount.getBalance() - amount);
        destAccount.setBalance(destAccount.getBalance() + amount);
        return true;
    }
}

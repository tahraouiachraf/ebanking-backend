package org.ebanking.services;

import jakarta.transaction.Transactional;
import org.ebanking.entities.BankAccount;
import org.ebanking.entities.CurrentAccount;
import org.ebanking.entities.SavingAccount;
import org.ebanking.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter() {
        BankAccount bankAccount1 = bankAccountRepository.findById("1a4e4b17-07f9-4b22-a63a-351c43007b2e").orElse(null);
        if (bankAccount1!=null) {
            System.out.println("***********************");
            System.out.println(bankAccount1.getId());
            System.out.println(bankAccount1.getBalance());
            System.out.println(bankAccount1.getStatus());
            System.out.println(bankAccount1.getCreatedAt());
            System.out.println(bankAccount1.getCustomer().getName());
            System.out.println(bankAccount1.getClass().getSimpleName());
            if (bankAccount1 instanceof CurrentAccount) {
                System.out.println("Over Draft=>"+((CurrentAccount) bankAccount1).getOverDraft());
            } else if (bankAccount1 instanceof SavingAccount) {
                System.out.println("Rate=>"+((SavingAccount) bankAccount1).getInterestRate());
            }

            bankAccount1.getAccountOperations().forEach(op -> {
                System.out.println("***************************");
                System.out.println(op.getType()+"\t"+op.getAmount()+"\t"+op.getOperationDate());
            });
        }
    }
}

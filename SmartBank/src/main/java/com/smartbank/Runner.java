package com.smartbank;

import com.smartbank.config.EntityManagerFactorySingleton;
import com.smartbank.models.entities.Credit;
import com.smartbank.models.enums.Civilite;
import com.smartbank.repository.interfaces.CreditRepository;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;

public class Runner {
    public static void main(String[] args) {
        CreditRepository creditRepository = new CreditRepository();

        Credit newCredit = new Credit(null,"Achat Mai","hhh",5000L,20L,455.00,"ayoubsnini@gmail.com","0663311599",Civilite.MADEMOISELLE,"ayoub","snini","HH74718",LocalDate.of(2018,1,1),LocalDate.of(2020,3,3),5000.00,true);
        creditRepository.add(newCredit);
    }
}

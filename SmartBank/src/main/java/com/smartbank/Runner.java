package com.smartbank;

import com.smartbank.config.EntityManagerFactorySingleton;
import com.smartbank.models.entities.Credit;
import com.smartbank.models.entities.CreditStatus;
import com.smartbank.models.entities.Status;
import com.smartbank.repository.implementations.CreditRepository;
import com.smartbank.repository.implementations.CreditStatusRepository;
import com.smartbank.repository.implementations.StatusRepository;
import com.smartbank.repository.interfaces.IStatusRepository;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
//        EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.entityManagerFactory();

//        StatusRepository statusRepository = new StatusRepository();
//        Status newStatus1 = new Status();
//        newStatus1.setNom("En attente");
//        Status newStatus2 = new Status();
//        newStatus2.setNom("Confirmer");
//        Status newStatus3 = new Status();
//        newStatus3.setNom("Rejeter");
//        Status newStatus4 = new Status();
//        newStatus4.setNom("Rejeter par le client");
//        statusRepository.persist(newStatus1);
//        statusRepository.persist(newStatus2);
//        statusRepository.persist(newStatus3);
//        statusRepository.persist(newStatus4);
//        Status statusNew = statusRepository.findById(52L);
//        CreditRepository creditRepository = new CreditRepository();
//        Credit creditNew = creditRepository.findById(1L);
//        CreditStatusRepository creditStatusRepository = new CreditStatusRepository();
//        CreditStatus creditStatus = new CreditStatus();
//        creditStatus.setCredit(creditNew);
//        creditStatus.setExplication("ggh djksndfjlgfsd dfj sojldld flj ljsdfnl");
//        creditStatus.setStatus(statusNew);
//        creditStatus.setDateStatus(LocalDate.now());
//        CreditStatus ayoub = creditStatusRepository.persist(creditStatus);
//        creditNew.getCreditStatus().add(ayoub);
//        System.out.println(ayoub.getCredit().getCivilite());
//        System.out.println(ayoub.getExplication());
//        System.out.println(ayoub.getDateStatus());
//        System.out.println(ayoub.getStatus().getNom());
//        List<CreditStatus> lists = creditNew.getCreditStatus();
//        for (CreditStatus cs : lists) {
//            System.out.println(cs.getId());
//            System.out.println(cs.getExplication());
//            System.out.println(cs.getStatus().getNom());
//        }








    }
}

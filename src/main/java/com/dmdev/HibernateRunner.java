package com.dmdev;

import com.dmdev.converter.BirthdayConverter;
import com.dmdev.entity.*;
import com.dmdev.util.HibernateUtil;
import com.dmdev.util.TestDataImporter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.graph.GraphSemantic;
import org.hibernate.graph.RootGraph;
import org.hibernate.graph.SubGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
public class HibernateRunner {

    @Transactional
    public static void main(String[] args) {

        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
            Session session = sessionFactory.openSession();
            Session session1 = sessionFactory.openSession()){
            TestDataImporter.importData(sessionFactory);
            session.beginTransaction();
            session1.beginTransaction();

            Payment payment = session.find(Payment.class, 1L, LockModeType.OPTIMISTIC);
            payment.setAmount(payment.getAmount() + 10);

            //an error must have occurred. Exception: OptimisticLockException
            Payment theSamePayment = session1.find(Payment.class, 1L, LockModeType.OPTIMISTIC);
            theSamePayment.setAmount(theSamePayment.getAmount() + 20);

            session.getTransaction().commit();
            session1.getTransaction().commit();

        }

    }
}

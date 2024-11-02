package com.dmdev;

import com.dmdev.converter.BirthdayConverter;
import com.dmdev.entity.*;
import com.dmdev.util.HibernateUtil;
import com.dmdev.util.TestDataImporter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.graph.GraphSemantic;
import org.hibernate.graph.RootGraph;
import org.hibernate.graph.SubGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
public class HibernateRunner {

    @Transactional
    public static void main(String[] args) {

        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()){

            try(Session session = sessionFactory.openSession()){
                session.beginTransaction();

                var payment = session.find(Payment.class, 1L);
                payment.setAmount(payment.getAmount() + 10);

                session.getTransaction().commit();
            }

            try(Session session2 = sessionFactory.openSession()){
                session2.beginTransaction();

                AuditReader auditReader = AuditReaderFactory.get(session2);
                Payment oldPayment = auditReader.find(Payment.class, 1L, new Date(1730554471012L));
                System.out.println();

                session2.replicate(oldPayment, ReplicationMode.OVERWRITE);

                auditReader.createQuery()
                        .forEntitiesAtRevision(Payment.class, 400L)
                        .add(AuditEntity.property("amount").ge(450))// критерии для выборки
                        .add(AuditEntity.property("id").ge(6L))
                        .addProjection(AuditEntity.property("amount"))// какие поля выбираем
                        .addProjection(AuditEntity.id())                          // как SELECT amount, id FROM Payment
                        .getResultList();

                session2.getTransaction().commit();
            }
        }

    }
}

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
import org.hibernate.annotations.QueryHints;
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
            User user = null;

            try(Session session = sessionFactory.openSession()){
                session.beginTransaction();

                user = session.find(User.class, 1L);
                user.getCompany().getName();
                user.getUserChats().size();
                User user2 = session.find(User.class, 1L);

                session.createQuery("select p from Payment p where receiver.id = :userId", Payment.class)
                        .setParameter("userId", 1L)
                        .setCacheable(true)
//                        .setCacheRegion("queries")
//                        .setHint(QueryHints.CACHEABLE, true)
                        .getResultList();
                System.out.println(sessionFactory.getStatistics().getCacheRegionStatistics("Users"));

                session.getTransaction().commit();
            }

            try(Session session = sessionFactory.openSession()){
                session.beginTransaction();

                User user3 = session.find(User.class, 1L);
                user3.getCompany().getName();
                user3.getUserChats().size();

                session.createQuery("select p from Payment p where receiver.id = :userId", Payment.class)
                        .setParameter("userId", 1L)
                        .setCacheable(true)
//                        .setCacheRegion("queries")
//                        .setHint(QueryHints.CACHEABLE, true)
                        .getResultList();

                System.out.println(sessionFactory.getStatistics().getCacheRegionStatistics("Users"));
                session.getTransaction().commit();
            }
        }



    }
}

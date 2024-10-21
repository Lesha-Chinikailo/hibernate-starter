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

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {

        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        Session session = sessionFactory.openSession()){
            TestDataImporter.importData(sessionFactory);
            session.beginTransaction();
            RootGraph<User> userGraph = session.createEntityGraph(User.class);
            userGraph.addAttributeNodes("company", "userChats");
            SubGraph<UserChat> userChatsSubgraph = userGraph.addSubgraph("userChats", UserChat.class);
            userChatsSubgraph.addAttributeNodes("chat");

            Map<String, Object> properties = Map.of(
//                    GraphSemantic.LOAD.getJpaHintName(), session.getEntityGraph("WithCompanyAndChat")
                    GraphSemantic.LOAD.getJpaHintName(), userGraph
            );
            User user = session.find(User.class, 1L, properties);
            System.out.println(user.getCompany().getName());
            System.out.println(user.getUserChats().size());

            List<User> users = session.createQuery(
                            "select u from User u", User.class)
                    .setHint(GraphSemantic.LOAD.getJpaHintName(), session.getEntityGraph("WithCompanyAndChat"))
                    .setHint(GraphSemantic.LOAD.getJpaHintName(), userGraph)
                    .list();
            users.forEach(it -> System.out.println(it.getUserChats().size()));
            users.forEach(it -> System.out.println(it.getCompany().getName()));

            session.getTransaction().commit();

        }

    }
}

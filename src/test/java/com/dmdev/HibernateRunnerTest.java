package com.dmdev;

import com.dmdev.entity.*;
import com.dmdev.util.HibernateTestUtil;
import com.dmdev.util.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Set;

public class HibernateRunnerTest {

    @Test
    void checkPostgresDocker(){
        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Company company = Company.builder()
                    .name("Google")
                    .build();
            session.persist(company);

//            Programmer programmer = Programmer.builder()
//                    .username("ivan1@gmail.com")
//                    .language(Language.Ruby)
//                    .company(company)
//                    .build();
//            session.persist(programmer);
//
//            Manager manager = Manager.builder()
//                    .username("sveta@gmail.com")
//                    .projectName("Starter")
//                    .company(company)
//                    .build();
//            session.persist(manager);
//
//            session.flush();
//
//            Programmer programmer1 = session.get(Programmer.class, 1L);
//            User user = session.get(User.class, 2L);

            session.getTransaction().commit();
        }
    }

    @Test
    void checkOrdering(){
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Company company = session.get(Company.class, 1);

            company.getUsers().forEach((k, v) -> System.out.println(v));

            session.getTransaction().commit();
        }
    }

    @Test
    void checkManyToMany(){
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = session.get(User.class, 4L);
            Chat chat = session.get(Chat.class, 1L);

            UserChat userChat = UserChat.builder()
//                    .createdAt(Instant.now())
//                    .createdBy(user.getUsername())
                    .build();
            userChat.setUser(user);
            userChat.setChat(chat);

            session.persist(userChat);


//            Chat chat = Chat.builder()
//                    .name("dmdev")
//                    .build();
//            session.persist(chat);

//

            session.getTransaction().commit();
        }
    }

    @Test
    void checkOneToOne(){
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = User.builder()
                    .username("test4@gmail.com")
                    .build();
            Profile profile = Profile.builder()
                    .language("ru")
                    .street("Kolasa 18")
                    .build();
            profile.setUser(user);

            session.persist(user);


            session.getTransaction().commit();
        }
    }

    @Test
    void checkOrphanRemoval(){
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Company company = session.getReference(Company.class, 3);
//            company.getUsers().removeIf(user -> user.getId().equals(3L));


            session.getTransaction().commit();
        }
    }

    @Test
    void checkLazyInitialization() {
        Company company = null;
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            company = session.get(Company.class, 1);


            session.getTransaction().commit();
        }
//        Set<User> users = company.getUsers();
//        System.out.println(users.size());
    }


    @Test
    void deleteCompany(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Company company = session.get(Company.class, 2);
        session.remove(company);

        session.getTransaction().commit();
    }

    @Test
    void addUserToNewCompany(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Company company = Company.builder()
                .name("FaceBook")
                .build();

        User user = User.builder()
                .username("sveta@gmail.com")
                .build();
        company.addUser(user);

        session.persist(company);

        session.getTransaction().commit();
    }
}

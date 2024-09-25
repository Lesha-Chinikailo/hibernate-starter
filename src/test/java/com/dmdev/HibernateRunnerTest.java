package com.dmdev;

import com.dmdev.entity.Company;
import com.dmdev.entity.Profile;
import com.dmdev.entity.User;
import com.dmdev.util.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class HibernateRunnerTest {

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
            company.getUsers().removeIf(user -> user.getId().equals(3L));


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
        Set<User> users = company.getUsers();
        System.out.println(users.size());
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

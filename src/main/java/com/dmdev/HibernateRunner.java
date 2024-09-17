package com.dmdev;

import com.dmdev.converter.BirthdayConverter;
import com.dmdev.entity.Birthday;
import com.dmdev.entity.Role;
import com.dmdev.entity.User;
import com.dmdev.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {

    public static void main(String[] args) {

        User user = User.builder()
            .username("ivan1@gmail.com")
            .firstname("Ivan")
            .lastname("Ivanov")
            .birthDate(new Birthday(LocalDate.of(2000, 1, 20)))
            .role(Role.ADMIN)
            .build();
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

//            User user1 = session.get(User.class, "ivan1@gmail.com");
            session.merge(user);


            session.getTransaction().commit();
        }
    }
}

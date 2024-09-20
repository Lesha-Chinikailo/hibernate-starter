package com.dmdev;

import com.dmdev.converter.BirthdayConverter;
import com.dmdev.entity.Birthday;
import com.dmdev.entity.PersonalInfo;
import com.dmdev.entity.Role;
import com.dmdev.entity.User;
import com.dmdev.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {

        User user = User.builder()
            .username("ivan2@gmail.com")
            .personalInfo(PersonalInfo.builder()
                    .firstname("Ivan")
                    .lastname("Ivanov")
                    .birthDate(new Birthday(LocalDate.of(2000, 1, 20)))
                    .build())
            .role(Role.ADMIN)
            .build();
        log.info("User entity is in transient state, user: {}", user);
        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()){
            Session session = sessionFactory.openSession();
            try (session) {
                Transaction transaction = session.beginTransaction();
                log.trace("Transaction begin, {}", transaction);

//            User user1 = session.get(User.class, "ivan1@gmail.com");
                session.persist(user);
                log.info("User is in persistent state, user: {}, session: {}", user, session);


                transaction.commit();
            }
            log.info("User is in detached state, {}, session is closed: {}", user, session);
        }
        catch (Exception exception){
            log.error("Exception occurred", exception);
            throw exception;
        }

    }
}

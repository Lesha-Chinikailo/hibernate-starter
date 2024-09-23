package com.dmdev;

import com.dmdev.converter.BirthdayConverter;
import com.dmdev.entity.*;
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
        Company company = Company.builder()
                .name("Google")
                .build();
        User user = User.builder()
            .username("ivan2@gmail.com")
            .personalInfo(PersonalInfo.builder()
                    .firstname("Ivan")
                    .lastname("Ivanov")
                    .birthDate(new Birthday(LocalDate.of(2000, 1, 20)))
                    .build())
            .role(Role.ADMIN)
            .company(company)
            .build();
        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()){
            Session session = sessionFactory.openSession();
            try (session) {
                Transaction transaction = session.beginTransaction();

                User user1 = session.get(User.class, 1L);
//                session.persist(company);
//                session.persist(user);

                transaction.commit();
            }
        }

    }
}

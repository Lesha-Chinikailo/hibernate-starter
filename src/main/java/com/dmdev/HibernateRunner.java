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
            .username("aivan2@gmail.com")
            .personalInfo(PersonalInfo.builder()
                    .firstname("Alesha")
                    .lastname("Ivanov")
                    .birthDate(LocalDate.of(2000, 1, 20))
                    .build())
            .role(Role.ADMIN)
            .company(company)
            .build();
        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()){
            Session session = sessionFactory.openSession();
            try (session) {
                Transaction transaction = session.beginTransaction();

//                User user1 = session.get(User.class, 1L);
                Company company1 = session.get(Company.class, 1);
                company1.addUser(user);
                transaction.commit();
            }
        }

    }
}

package com.dmdev.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record Birthday(LocalDate birthday) {

    public Long getAge(){
        return ChronoUnit.YEARS.between(birthday, LocalDate.now());
    }
}
//@Getter
//@Setter
//public class Birthday {
//    private LocalDate birthday;
//    public Birthday(LocalDate birthday) {
//        this.birthday = birthday;
//    }
//
//    public Long getAge(){
//        return ChronoUnit.YEARS.between(birthday, LocalDate.now());
//    }
//}

package com.dmdev.entity;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PersonalInfo.class)
public abstract class PersonalInfo_ {

	public static volatile SingularAttribute<PersonalInfo, String> firstname;
	public static volatile SingularAttribute<PersonalInfo, LocalDate> birthDate;
	public static volatile SingularAttribute<PersonalInfo, String> lastname;

	public static final String FIRSTNAME = "firstname";
	public static final String BIRTH_DATE = "birthDate";
	public static final String LASTNAME = "lastname";

}


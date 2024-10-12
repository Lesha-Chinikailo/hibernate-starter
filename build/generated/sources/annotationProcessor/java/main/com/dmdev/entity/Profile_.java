package com.dmdev.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Profile.class)
public abstract class Profile_ {

	public static volatile SingularAttribute<Profile, String> street;
	public static volatile SingularAttribute<Profile, String> language;
	public static volatile SingularAttribute<Profile, Long> id;
	public static volatile SingularAttribute<Profile, User> user;

	public static final String STREET = "street";
	public static final String LANGUAGE = "language";
	public static final String ID = "id";
	public static final String USER = "user";

}


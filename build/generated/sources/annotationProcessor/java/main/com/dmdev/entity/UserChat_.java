package com.dmdev.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserChat.class)
public abstract class UserChat_ extends com.dmdev.entity.AuditableEntity_ {

	public static volatile SingularAttribute<UserChat, Chat> chat;
	public static volatile SingularAttribute<UserChat, Long> id;
	public static volatile SingularAttribute<UserChat, User> user;

	public static final String CHAT = "chat";
	public static final String ID = "id";
	public static final String USER = "user";

}


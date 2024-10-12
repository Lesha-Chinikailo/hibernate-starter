package com.dmdev.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Chat.class)
public abstract class Chat_ {

	public static volatile SingularAttribute<Chat, String> name;
	public static volatile SingularAttribute<Chat, Long> id;
	public static volatile ListAttribute<Chat, UserChat> userChats;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String USER_CHATS = "userChats";

}


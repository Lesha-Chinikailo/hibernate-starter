package com.dmdev.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Payment.class)
public abstract class Payment_ {

	public static volatile SingularAttribute<Payment, Integer> amount;
	public static volatile SingularAttribute<Payment, User> receiver;
	public static volatile SingularAttribute<Payment, Long> id;

	public static final String AMOUNT = "amount";
	public static final String RECEIVER = "receiver";
	public static final String ID = "id";

}


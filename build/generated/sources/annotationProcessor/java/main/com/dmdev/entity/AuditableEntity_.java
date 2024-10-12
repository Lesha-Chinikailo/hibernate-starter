package com.dmdev.entity;

import java.time.Instant;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuditableEntity.class)
public abstract class AuditableEntity_ {

	public static volatile SingularAttribute<AuditableEntity, Instant> createdAt;
	public static volatile SingularAttribute<AuditableEntity, String> createdBy;

	public static final String CREATED_AT = "createdAt";
	public static final String CREATED_BY = "createdBy";

}


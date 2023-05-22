package org.hibernate.bugs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void hhh16662Test() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		CustomEntity customEntity = new CustomEntity(UUID.randomUUID(), Map.of("key", "value"));
		entityManager.persist(customEntity);

		entityManager.getTransaction().commit();

		CustomProjection singleResult = (CustomProjection) entityManager.createQuery("SELECT new org.hibernate.bugs.CustomProjection(c.properties) FROM CustomEntity c", CustomProjection.class).getSingleResult();

		assertThat(singleResult.getProperties()).isEqualTo(customEntity.getProperties());
		entityManager.close();
	}
}

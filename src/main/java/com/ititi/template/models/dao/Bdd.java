package com.ititi.template.models.dao;

import com.ititi.template.models.beans.Personne;
import com.ititi.template.models.dto.PersonneDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represente une personne
 */
public class Bdd {
    protected static Bdd instance;
    protected Logger logger = Logger.getLogger(getClass().getName());

    protected static SessionFactory sessionFactory;
    protected static ServiceRegistry serviceRegistry;

    private Bdd() {
        final Configuration configuration = new Configuration();
        configuration.configure();

        configuration.addAnnotatedClass(PersonneDto.class);

        final Properties properties = configuration.getProperties();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(properties).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static Bdd getInstance() {
        if (instance == null) {
            instance = new Bdd();
        }
        return instance;
    }

    public List<Personne> listAll() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            final List<PersonneDto> personnes = session.createQuery("from PersonneDto").list();
            return PersonneDto.toBean(personnes);
        } finally {
            if (session != null) session.close();
        }
    }
    public void save(final Personne personne) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            final PersonneDto dto = new PersonneDto(personne);
            session.saveOrUpdate(dto);
            personne.setId(dto.getId());

            session.flush();
            transaction.commit();
        } catch (final Exception e) {
            logger.log(Level.SEVERE, "Erreur lors de l'ajout de la personne", e);
            if (transaction != null) transaction.rollback();
        } finally {
            if (session != null) session.close();
        }
    }
    public void remove(final Personne personne) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.delete(new PersonneDto(personne));

            session.flush();
            transaction.commit();
        } catch (final Exception e) {
            logger.log(Level.SEVERE, "Erreur lors de la suppression de la personne", e);
            if (transaction != null) transaction.rollback();
        } finally {
            if (session != null) session.close();
        }
    }


}
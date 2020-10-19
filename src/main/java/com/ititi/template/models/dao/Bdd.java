package com.ititi.template.models.dao;

/**
 * DAO de communication avec la BDD SqlLite
 */
public class Bdd {
//    protected static Bdd instance;
//    protected Logger logger = Logger.getLogger(getClass().getName());
//
//    protected static SessionFactory sessionFactory;
//    protected static ServiceRegistry serviceRegistry;
//
//    private Bdd() {
//        final Configuration configuration = new Configuration();
//        configuration.configure();
//
//        configuration.addAnnotatedClass(PersonneDto.class);
//
//        final Properties properties = configuration.getProperties();
//        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(properties).build();
//        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//    }
//
//    public static Bdd getInstance() {
//        if (instance == null) {
//            instance = new Bdd();
//        }
//        return instance;
//    }
//
//    public List<Personne> listAll() {
//        Session session = null;
//        try {
//            session = sessionFactory.openSession();
//            final List<PersonneDto> personnes = session.createQuery("from PersonneDto").list();
//            return PersonneDto.toBean(personnes);
//        } finally {
//            if (session != null) session.close();
//        }
//    }
//    public void save(final Personne personne) {
//        Session session = null;
//        Transaction transaction = null;
//        try {
//            session = sessionFactory.openSession();
//            transaction = session.beginTransaction();
//
//            final PersonneDto dto = new PersonneDto(personne);
//            session.saveOrUpdate(dto);
//            personne.setId(dto.getId());
//
//            session.flush();
//            transaction.commit();
//        } catch (final Exception e) {
//            logger.log(Level.SEVERE, "Erreur lors de l'ajout de la personne", e);
//            if (transaction != null) transaction.rollback();
//        } finally {
//            if (session != null) session.close();
//        }
//    }
//    public void remove(final Personne personne) {
//        Session session = null;
//        Transaction transaction = null;
//        try {
//            session = sessionFactory.openSession();
//            transaction = session.beginTransaction();
//
//            session.delete(new PersonneDto(personne));
//
//            session.flush();
//            transaction.commit();
//        } catch (final Exception e) {
//            logger.log(Level.SEVERE, "Erreur lors de la suppression de la personne", e);
//            if (transaction != null) transaction.rollback();
//        } finally {
//            if (session != null) session.close();
//        }
//    }


}
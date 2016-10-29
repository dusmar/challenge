package com.a2a.common;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


public abstract class AbstractHibernateDao {

    @Autowired
    private SessionFactory sessionFactory;

    private ThreadLocal<Boolean> sessionInitialized = new ThreadLocal<>();

    @Value(value = "${db.oracle.sortMode:BINARY_CI}")
    private String oracleSortMode;

    @Value(value = "${db.oracle.compareMode:LINGUISTIC}")
    private String oracleCompareMode;

    @Value(value = "${db.oracle.explicitOracleSort:false}")
    private boolean setCustomOracleSorting;

    protected final SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    protected final Session createSession() {
        return getSessionFactory().openSession();
    }


    protected final Session getSession() {
        Session session = getSessionFactory().getCurrentSession();
        if (setCustomOracleSorting && (sessionInitialized.get() == null || !sessionInitialized.get())) {
            configureSortingForOracle(session);
            sessionInitialized.set(true);
        }
        return session;
    }

    /**
     * sorting
     * @param session
     */
    private void configureSortingForOracle(Session session) {
        try {
            session.doWork(new Work() {
                @Override
                public void execute(Connection connection) throws SQLException {
                    setSorting(connection);
                }
            });
        } catch (HibernateException he) {
            throw new IllegalStateException(he);
        }
    }

    private void setSorting(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(String.format("alter session set NLS_SORT='%s'", oracleSortMode));
        statement.execute(String.format("alter session set NLS_COMP='%s'", oracleCompareMode));
        statement.close();
    }

    
    /**
     * TODO comment
     *  
     * @param tokens
     * @param fields
     * @return
     */
    protected Conjunction fulltextCriteria(List<String> tokens, String...fields) {
        Objects.requireNonNull(tokens, "Tokens must not be null");
        Objects.requireNonNull(fields, "Fields must not be null");
        Conjunction and = Restrictions.conjunction();
        for (String t : tokens) {
            Disjunction d = Restrictions.disjunction();
            for (String f : fields) {
                d.add(Restrictions.ilike(f, t, MatchMode.ANYWHERE));
            }
            and.add(d);
        }
        return and;
    }

    /**
     * 
     * @param c
     * @param properties
     */
    protected void fields(Criteria c, Class<?> clazz, String...properties) {
        Objects.requireNonNull(c, "Criteria must not be null");
        Objects.requireNonNull(clazz, "Class must not be null");
        
        if (properties == null || properties.length == 0) {
            return;
        }
        
        ProjectionList pl = Projections.projectionList();
        for (String p : properties) {
            pl.add(Projections.alias(Projections.property(p), p));
        }
        c.setProjection(pl);
        c.setResultTransformer(Transformers.aliasToBean(clazz));
    }

}

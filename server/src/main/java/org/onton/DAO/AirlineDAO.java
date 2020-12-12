package org.onton.DAO;

import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.onton.db.SessionFactorySingleton;
import org.onton.entity.Airline;

import java.util.List;

public class AirlineDAO implements DAO<Airline>{

    private final SessionFactory factory = SessionFactorySingleton.getInstance();

    @Override
    public List<Airline> getList() {
        List<Airline> list = null;
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            Query<Airline> query =
                    session.createQuery("FROM Airline", Airline.class);
            list = query.getResultList();

            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void save(Airline object) throws JDBCException {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(object);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public Airline get(int id) {
        Session session = factory.getCurrentSession();
        Airline airline = null;
        try {
            session.beginTransaction();
            airline = session.get(Airline.class, id);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return airline;
    }

    @Override
    public void delete(int id) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("delete from Airline where id=:airlineId");
            query.setParameter("airlineId", id);
            query.executeUpdate();

            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}

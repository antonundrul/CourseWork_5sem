package org.onton.DAO;


import org.onton.db.SessionFactorySingleton;
import org.onton.entity.Airport;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class AirportDAO implements DAO<Airport> {

    private final SessionFactory factory = SessionFactorySingleton.getInstance();

    @Override
    public List<Airport> getList() {
        List<Airport> list = null;
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            Query<Airport> query = session.createQuery("from Airport c" +
                    " join fetch c.city co order by c.name", Airport.class);
            list = query.getResultList();

            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void save(Airport object) {
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
    public Airport get(int id) {
        Session session = factory.getCurrentSession();
        Airport airport = null;
        try {
            session.beginTransaction();

            airport = session.get(Airport.class, id);

            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return airport;
    }

    @Override
    public void delete(int id) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("delete from Airport where id=:airportId");
            query.setParameter("airportId", id);
            query.executeUpdate();

            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}

package org.onton.DAO;


import org.onton.db.SessionFactorySingleton;
import org.onton.entity.Airport;
import org.onton.entity.Flight;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class FlightDAO implements DAO<Flight> {

    private final SessionFactory factory = SessionFactorySingleton.getInstance();

    @Override
    public List<Flight> getList() {
        List<Flight> list = null;
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            Query<Flight> query = session.createQuery("from Flight c inner join fetch c.airportOfDeparture " +
                    "left join fetch c.airportOfDestination " +
                    "order by c.id", Flight.class);
            list = query.getResultList();

            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void save(Flight object) throws JDBCException {
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
    public Flight get(int id) {
        Session session = factory.getCurrentSession();
        Flight flight = null;
        try {
            session.beginTransaction();
            flight = session.get(Flight.class, id);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return flight;
    }

    @Override
    public void delete(int id) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("delete from Flight where id=:flightId");
            query.setParameter("flightId", id);
            query.executeUpdate();

            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}

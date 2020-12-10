package org.onton.DAO;

import org.hibernate.JDBCException;
import org.onton.db.SessionFactorySingleton;
import org.onton.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class TicketDAO implements DAO<Ticket> {

    private final SessionFactory factory = SessionFactorySingleton.getInstance();

    @Override
    public List<Ticket> getList() {
        List<Ticket> list = null;
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            Query<Ticket> query =
                    session.createQuery("from Ticket c inner join fetch c.user " +
                            "inner join fetch c.flight "  +
                            "order by c.id", Ticket.class);
            list = query.getResultList();

            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void save(Ticket object) throws JDBCException {
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
    public Ticket get(int id) {
        Session session = factory.getCurrentSession();
        Ticket ticket = null;
        try {
            session.beginTransaction();
            ticket = session.get(Ticket.class, id);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return ticket;
    }

    @Override
    public void delete(int id) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("delete from Ticket where id=:ticketId");
            query.setParameter("ticketId", id);
            query.executeUpdate();

            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}


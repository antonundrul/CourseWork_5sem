package org.onton.DAO;

import org.hibernate.JDBCException;
import org.onton.db.SessionFactorySingleton;
import org.onton.entity.Review;
import org.onton.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ReviewDAO implements DAO<Review> {

    private final SessionFactory factory = SessionFactorySingleton.getInstance();

    @Override
    public List<Review> getList() {
        List<Review> list = null;
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            Query<Review> query =
                    session.createQuery("from Review c inner join fetch c.user " +
                            "inner join fetch c.airport "  +
                            "order by c.id", Review.class);
            list = query.getResultList();

            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void save(Review object) throws JDBCException {
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
    public Review get(int id) {
        Session session = factory.getCurrentSession();
        Review review = null;
        try {
            session.beginTransaction();
            review = session.get(Review.class, id);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return review;
    }

    @Override
    public void delete(int id) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("delete from Review where id=:reviewId");
            query.setParameter("reviewId", id);
            query.executeUpdate();

            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}


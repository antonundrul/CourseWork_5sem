package org.onton.DAO;

import org.onton.db.SessionFactorySingleton;
import org.onton.entity.User;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAO implements DAO<User> {

    private final SessionFactory factory = SessionFactorySingleton.getInstance();

    @Override
    public List<User> getList() {
        List<User> list = null;
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            Query<User> query =
                    session.createQuery("FROM User", User.class);
            list = query.getResultList();

            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void save(User object) throws JDBCException {
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
    public User get(int id) {
        Session session = factory.getCurrentSession();
        User user = null;
        try {
            session.beginTransaction();
            user = session.get(User.class, id);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public void delete(int id) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("delete from User where id=:userId");
            query.setParameter("userId", id);
            query.executeUpdate();

            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}

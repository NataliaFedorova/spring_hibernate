package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
       sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Transactional
   public User getUserByCar(String model, int series) {
         String hql = "FROM User a WHERE a.car.model = :model AND a.car.series = :series";
         TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
         query.setParameter("model", model);
         query.setParameter("series", series);
         return query.setMaxResults(1).getSingleResult();
   }
}

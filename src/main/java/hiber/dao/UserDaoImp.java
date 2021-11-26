package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
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
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @SuppressWarnings("unchecked")
   public List<User> listUsersWithCars() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User as user where user.car.id != null");
      return query.getResultList();
   }

   @SuppressWarnings("unchecked")
   public List<User> listUsersByCar(String model, int series) {
      String hql = "from User as user where user.car.model = :model and user.car.series = :series";
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.getResultList();
   }

   public void deleteAll() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("delete from User");
      query.executeUpdate();
   }

}

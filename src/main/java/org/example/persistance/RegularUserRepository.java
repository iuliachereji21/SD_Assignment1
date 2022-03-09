package org.example.persistance;

import org.example.business.model.RegularUser;
import org.example.business.model.VacationDestination;
import org.example.business.model.VacationPackage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class RegularUserRepository {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sd_assignment_1");

    public RegularUser findUserByEmailAndPassword(String email, String password){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        try{
            RegularUser user = em.createQuery(
                    "SELECT u from RegularUser u WHERE u.email= :email", RegularUser.class)
                    .setParameter("email",email)
                    .getSingleResult();

            if(user==null || !user.getPassword().equals(password))
                return null;
            return user;
        }
        catch(NoResultException e){
            System.out.println("err");
            return null;
        }
        finally{
            em.getTransaction().commit();
            em.close();
        }
    }

    public RegularUser findUserByEmail(String email){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        try{
            return em.createQuery(
                            "SELECT u from RegularUser u WHERE u.email= :email", RegularUser.class)
                    .setParameter("email",email)
                    .getSingleResult();
        }
        catch(NoResultException e){
            System.out.println("err");
            return null;
        }
        finally{
            em.getTransaction().commit();
            em.close();
        }
    }
    public void addRegularUser(RegularUser user){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public void bookPackage(RegularUser user, VacationPackage vacationPackage){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        try{
            em.merge(user);
            em.merge(vacationPackage);
        }
        catch(NoResultException e){
            System.out.println("err");
        }
        finally{
            em.getTransaction().commit();
            em.close();
        }
    }
}

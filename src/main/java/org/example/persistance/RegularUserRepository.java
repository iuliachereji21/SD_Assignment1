package org.example.persistance;

import org.example.business.model.RegularUser;

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
}
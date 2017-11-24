
package cz.fi.muni.pa165.library.persistance.dao;

import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
/**
 *  Implementation of DAO (data access object) for entity class Loan.
 * @author Jan Tlamicha (xtlamich)
 */
@Repository
public class LoanDaoImpl implements LoanDao {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(Loan loan){
        em.persist(loan);
    }

    @Override
    public void delete(Loan loan){
        em.remove(findById(loan.getId()));
    }

    @Override
    public Loan findById(Long id) {
        return em.find(Loan.class, id);
    }

    @Override
    public List<Loan> allLoansOfMember(Member member) {
       return em.createQuery("select l from Loan l where l.member = :member", Loan.class)
                                            .setParameter("member", member).getResultList();
    }

    @Override
    public List<Loan> findAll() {
        return em.createQuery("select l from Loan l", Loan.class).getResultList();
    }

    @Override
    public void update(Loan loan) {
        em.merge(loan);
    }
    
}

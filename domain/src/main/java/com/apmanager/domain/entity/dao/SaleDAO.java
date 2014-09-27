package com.apmanager.domain.entity.dao;

import com.apmanager.domain.base.BasicSearchDAOImpl;
import com.apmanager.domain.base.Computer;
import com.apmanager.domain.entity.Sale;
import javax.persistence.Query;
import org.eclipse.persistence.config.QueryHints;

/**
 * @author luis
 */
public class SaleDAO extends BasicSearchDAOImpl {

    public Sale getActiveSale(Computer computer) {
        final String jpql = ""
                + "select s "
                + "  from Sale s"
                + " where s.computer = ?1 "
                + "   and s.canceled = false "
                + "   and s.closed = false "
                + "   and s.closeDate is null";

        final Query q = em.createQuery(jpql);
        q.setParameter(1, computer);
        return (Sale) q.getSingleResult();
    }
}

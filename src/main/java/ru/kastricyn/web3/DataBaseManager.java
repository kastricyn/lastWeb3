package ru.kastricyn.web3;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import java.util.List;

@ManagedBean(name="dataBase", eager = true)
@ApplicationScoped
public class DataBaseManager {
    private static final String PERSISTENCE_UNIT_NAME = "postgres";

    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;
//todo: there are pissibly SQL-injection, its very bad
    public List<Point> loadEntries(String sessionId) {
        return entityManager.createQuery("SELECT e FROM ru.kastricyn.web3.Point e WHERE e.sessionId = \'" + sessionId +
                "\'").getResultList();
    }

    public synchronized void addPointToDB(Point point) throws Exception {
        userTransaction.begin();
        entityManager.persist(point);
        userTransaction.commit();
    }

    public synchronized void clearDB(Point point) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        userTransaction.begin();
        entityManager.remove(entityManager.merge(point));
        userTransaction.commit();
    }
}

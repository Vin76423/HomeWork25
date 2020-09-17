package by.tms.services;

import by.tms.dao.OperationsDao;
import by.tms.dao.OperationsDbDao;
import by.tms.entity.UsersOperation;
import java.sql.Connection;
import java.util.List;

public class OperationsServiceImpl implements OperationsService {
    private OperationsDao operationsDao;
    private static OperationsService instance = null;

    private OperationsServiceImpl(Connection connection) {
        this.operationsDao = OperationsDbDao.getInstance(connection);
    }

    public static OperationsService getInstance(Connection connection) {
        if (instance == null) {
            instance = new OperationsServiceImpl(connection);
        }
        return instance;
    }

    @Override
    public void setOperationsList(List<UsersOperation> operations) {
        operationsDao.setOperationsList(operations);
    }

    @Override
    public List<UsersOperation> getOperationsListByUserId(long userId) {
        return operationsDao.getOperationsListByUserId(userId);
        // может выбрасить exception в рантайме
    }

    @Override
    public void clineOperationsListByUserId(long userId) {
        if (!operationsDao.operationsListByUserIdContain(userId)) {
            operationsDao.clineOperationsListByUserId(userId);
        }
    }

    @Override
    public boolean operationsListByUserIdContain(long userId) {
        return operationsDao.operationsListByUserIdContain(userId);
    }
}

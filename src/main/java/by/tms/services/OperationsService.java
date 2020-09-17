package by.tms.services;

import by.tms.entity.UsersOperation;
import java.util.List;

public interface OperationsService {
    void setOperationsList(List<UsersOperation> operations);
    List<UsersOperation> getOperationsListByUserId(long userId);
    void clineOperationsListByUserId(long userId);
    boolean operationsListByUserIdContain(long userId);
}

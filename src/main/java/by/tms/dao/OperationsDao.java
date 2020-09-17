package by.tms.dao;

import by.tms.entity.UsersOperation;
import java.util.List;

public interface OperationsDao {
    String SET_OPERATION = "INSERT INTO `operations` VALUES(null, ?, ?, ?, ?, ?, ?)";
    String GET_OPERATIONS_LIST_BY_ID = "SELECT * FROM `operations` WHERE `us_id` = ?";
    String CLINE_OPERATIONS_LIST_BY_ID = "DELETE FROM `operations` WHERE `us_id` = ?";

    void setOperationsList(List<UsersOperation> operations);
    List<UsersOperation> getOperationsListByUserId(long userId);
    void clineOperationsListByUserId(long userId);
    boolean operationsListByUserIdContain(long userId);
}

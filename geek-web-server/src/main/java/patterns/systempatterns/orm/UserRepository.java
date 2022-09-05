package patterns.systempatterns.orm;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Optional;

public class UserRepository {

    private final Connection conn;

    private final UserMapper mapper;

    private final UnitOfWork unitOfWork;

    public UserRepository(Connection conn) {
        this.conn = conn;
        this.mapper = new UserMapper(conn);
        this.unitOfWork = new UnitOfWork(mapper);
    }

    public Optional<User> findById(long id) {

        return mapper.findById(id);
    }

    public void beginTransaction() {
        try {
            Savepoint savepoint = conn.setSavepoint();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(User user) {
        beginTransaction();
        unitOfWork.registerNew(user);
        commitTransaction();
    }

    public void update(User user) {
        beginTransaction();
        unitOfWork.registerUpdate(user);
        commitTransaction();
    }

    public void delete(User user) {
        beginTransaction();
        unitOfWork.registerDelete(user);
        commitTransaction();
    }

    public void commitTransaction() {
        unitOfWork.commit();
    }

    public void rollbackTransaction() {

    }
}

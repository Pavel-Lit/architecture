package patterns.systempatterns.orm;

import java.sql.*;
import java.util.*;

public class UserMapper {

    private final Connection conn;

    private final Statement statement;

    private final Map<Long, User> identityMap = new HashMap<>();

    public UserMapper(Connection conn) {
        this.conn = conn;
        try {
            this.statement = conn.createStatement();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }

    public Optional<User> findById(long id) {
        String sqlQuery = String.format("select * from users where %d", id);
        User user = identityMap.get(id);
        if (user != null) {
            return Optional.of(user);
        }
        try {
            ResultSet rs = statement.executeQuery(sqlQuery);
            if (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
                identityMap.put(id, user);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }

    public void update(User user)  {
        String sqlQuery = String.format("UPDATE users SET username = '%s', password = '%s' where id = '%d';", user.getLogin(), user.getPassword(), user.getId());
        if (identityMap.get(user.getId()) != null) {
            identityMap.replace((long) user.getId(), user);
        } else {
            identityMap.put((long) user.getId(),user);
        }
        try {
            statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insert(User user) {
        String sqlQuery = String.format("INSERT INTO users ('id','username','password') values ('%d','%s','%s')", user.getId(), user.getLogin(), user.getPassword());
        try {
            statement.executeUpdate(sqlQuery);
            identityMap.put(Long.valueOf(user.getId()), user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(User user) {
        String sqlQuery = String.format("DELETE from users where id = '%d'", user.getId());
        try {
            statement.executeUpdate(sqlQuery);
            identityMap.remove(user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> findAllUsers(){
        List<User> userList = new ArrayList<>();
        try {
            ResultSet resultSet =  statement.executeQuery("select * from users") ;
            while (resultSet.next()){
                userList.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3) ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}

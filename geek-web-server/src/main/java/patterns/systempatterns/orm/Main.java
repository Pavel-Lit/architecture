package patterns.systempatterns.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:D:\\repo\\architecture\\geek-web-server\\src\\main\\resources\\test.db");
        UserMapper userMapper = new UserMapper(connection);
        List<User> userList = userMapper.findAllUsers();
        User user = new User(2, "John1", "1qw2");
        User user1 = new User(3, "John2", "1qw2");
        User user2 = new User(4, "John3", "1qw2");
        User user3 = new User(5, "John4", "1qw2");
        User user4 = new User(6, "John5", "1qw2");
        User user5 = new User(7, "John6", "1qw2");

//        userList.forEach(System.out::println);
//        userMapper.update(user);
//        userList = userMapper.findAllUsers();
//        userList.forEach(System.out::println);
//        User userToInsert = new User(3, "Timon", "22vv33");
//        userMapper.insert(userToInsert);
//        userList = userMapper.findAllUsers();
//        userList.forEach(System.out::println);
//        userMapper.delete(userToInsert);
//        userMapper.findAllUsers().forEach(System.out::println);
        UnitOfWork unitOfWork = new UnitOfWork(userMapper);

//        unitOfWork.registerNew(user1);
//        unitOfWork.registerNew(user2);
//        unitOfWork.registerNew(user3);
//        unitOfWork.registerNew(user4);
//        unitOfWork.registerNew(user5);
//        unitOfWork.registerDelete(user5);
//        unitOfWork.registerDelete(user4);
//        unitOfWork.registerDelete(user3);
//        unitOfWork.commit();
        UserRepository userRepository = new UserRepository(connection);
        userRepository.delete(user1);
        userRepository.delete(user2);

        userMapper.findAllUsers().forEach(System.out::println);
    }
}

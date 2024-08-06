import entites.User;
import orm.EntityManager;
import orm.MyConnector;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Iterator;


public class Main {
    public static void main(String[] args) throws IllegalAccessException, SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        MyConnector.createConnection("root","12345","test");
        Connection connection = MyConnector.getConnection();
        EntityManager<User> entityManager = new EntityManager<>(connection);

//        User user = new User("Pesho",40,LocalDate.of(2021,6,20));
//        //Test One
//        user.setTest("teeeest");
//        entityManager.persist(user);
//
//        //Test Two
//        User found = entityManager.findFirst(User.class,"age > 30");
//        System.out.println(found.getUsername());
//
//        Iterator<User> userIterator = entityManager.find(User.class);
//        while (userIterator.hasNext()){
//            System.out.println(userIterator.next().getUsername());
//        }
//
//        User first = entityManager.findFirst(User.class);
//        System.out.println(first);
//
//        entityManager.doCreate(User.class);
//        entityManager.delete(user,"id = 2");
    }
}
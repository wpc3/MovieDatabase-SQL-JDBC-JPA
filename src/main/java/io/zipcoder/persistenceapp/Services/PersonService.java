package io.zipcoder.persistenceapp.Services;

import io.zipcoder.persistenceapp.Models.Person;
import org.apache.catalina.servlet4preview.http.PushBuilder;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PersonService implements ServiceInterFace<Person> {

    private static final String URL = "jdbc:mysql://localhost:3306/movieTheatre";
    private static final String USER = "root";
    private static final String PASSWORD = "Mightymac18!";

    //made a get connection method
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public Person findByID(int id) {
        String query = "SELECT * FROM PERSON WHERE ID = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1,id);
            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                return new Person(
                resultSet.getInt("ID"),
                resultSet.getString("FIRST_NAME"),
                resultSet.getString("LAST_NAME"),
                resultSet.getString("MOBILE"),
                        resultSet.getDate("BIRTHDAY"),
                resultSet.getInt("HOME_ID")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Person create(Person o) {
        String query = "INSERT INTO PERSON (FIRST_NAME,LAST_NAME,MOBILE,BIRTHDAY,HOME_ID) VALUES (?,?,?,?,?)";
        try {
            Connection connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);


            pstm.setString(1, o.getFirstName());
            pstm.setString(2, o.getLastName());
            pstm.setString(3, o.getMobile());
            pstm.setDate(4,  new java.sql.Date(o.getBirthday().getTime()));
            pstm.setInt(5, o.getHomeID());
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return o;
    }

    @Override
    public Person update(Person o) {
        String query = "UPDATE PERSON SET FIRST_NAME = ?, LAST_NAME = ?, MOBILE = ?, BIRTHDAY = ?, HOME_ID = ? WHERE ID = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);


            pstm.setString(1, o.getFirstName());
            pstm.setString(2, o.getLastName());
            pstm.setString(3, o.getMobile());
            pstm.setDate(4,  new java.sql.Date(o.getBirthday().getTime()));
            pstm.setInt(5, o.getHomeID());
            pstm.setInt(6,o.getId());
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return o;
    }

    @Override
    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();

        String query = "SELECT * FROM PERSON";
        try {
            Connection connection = getConnection();
            PreparedStatement psmt = connection.prepareStatement(query);
            ResultSet resultSet = psmt.executeQuery();

           while (resultSet.next()){
               Person person = new Person(
                       resultSet.getInt("ID"),resultSet.getString("FIRST_NAME"),resultSet.getString("LAST_NAME"),resultSet.getString("MOBILE"),resultSet.getDate("BIRTHDAY"),resultSet.getInt("HOME_ID"));

                        persons.add(person);
           }
            System.out.println(persons);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public void deleteID(int id) {
        String query = "DELETE FROM PERSON WHERE ID = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement psmt = connection.prepareStatement(query);
            psmt.setInt(1,id);
            psmt.executeUpdate();
            System.out.println("Deleted Person");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Person> findAllByLastName(String last){
        List<Person> persons = new ArrayList<>();

        String query = "SELECT * FROM PERSON WHERE LAST_NAME =?";
        try {
            Connection connection = getConnection();
            PreparedStatement psmt = connection.prepareStatement(query);
            psmt.setString(1,last);
            ResultSet resultSet = psmt.executeQuery();

            while (resultSet.next()){
                Person person = new Person(
                        resultSet.getInt("ID"),resultSet.getString("FIRST_NAME"),resultSet.getString("LAST_NAME"),resultSet.getString("MOBILE"),resultSet.getDate("BIRTHDAY"),resultSet.getInt("HOME_ID"));

                persons.add(person);
            }
            System.out.println(persons);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Person> findAllByFirstName(String first){
        List<Person> persons = new ArrayList<>();

        String query = "SELECT * FROM PERSON WHERE FIRST_NAME =?";
        try {
            Connection connection = getConnection();
            PreparedStatement psmt = connection.prepareStatement(query);
            psmt.setString(1,first);
            ResultSet resultSet = psmt.executeQuery();

            while (resultSet.next()){
                Person person = new Person(
                        resultSet.getInt("ID"),resultSet.getString("FIRST_NAME"),resultSet.getString("LAST_NAME"),resultSet.getString("MOBILE"),resultSet.getDate("BIRTHDAY"),resultSet.getInt("HOME_ID"));

                persons.add(person);
            }
            System.out.println(persons);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Person> findAllByBirthday(Date bd){
        List<Person> persons = new ArrayList<>();

        String query = "SELECT * FROM PERSON WHERE BIRTHDAY =?";
        try {
            Connection connection = getConnection();
            PreparedStatement psmt = connection.prepareStatement(query);
            psmt.setDate(1,bd);
            ResultSet resultSet = psmt.executeQuery();

            while (resultSet.next()){
                Person person = new Person(
                        resultSet.getInt("ID"),resultSet.getString("FIRST_NAME"),resultSet.getString("LAST_NAME"),resultSet.getString("MOBILE"),resultSet.getDate("BIRTHDAY"),resultSet.getInt("HOME_ID"));

                persons.add(person);
            }
            System.out.println(persons);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}

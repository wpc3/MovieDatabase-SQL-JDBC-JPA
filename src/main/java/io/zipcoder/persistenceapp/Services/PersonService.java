package io.zipcoder.persistenceapp.Services;

import io.zipcoder.persistenceapp.Models.Person;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.List;

@Service
public class PersonService implements ServiceInterFace {

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
    public Object create(Object o) {

        return null;
    }

    @Override
    public Object update(Object o) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void deleteID(int id) {

    }
}

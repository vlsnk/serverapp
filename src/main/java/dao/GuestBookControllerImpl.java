package dao;

import model.Record;

import javax.sql.DataSource;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class GuestBookControllerImpl implements GuestBookController {

    private final DataSource ds;

    private final String CREATE_TABLE = "CREATE TABLE record(\n" +
            "  recordId IDENTITY,\n" +
            "  postDate TIMESTAMP,\n" +
            "  postText VARCHAR\n" +
            ");";
    private final String INSERT_INTO_TABLE = "INSERT  INTO RECORD (POSTDATE, POSTTEXT) VALUES (?, ?)";
    private final String SELECT_FROM_DB = "select * from record";

    public GuestBookControllerImpl(DataSource ds)  {

        this.ds = ds;
        initDb();
    }

    void initDb(){
        try(Connection connection = ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE_TABLE)){
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addRecord(String message) {

        try (Connection connection = ds.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_INTO_TABLE)){
            statement.setTimestamp(1, Timestamp.from(Instant.now()));
            statement.setString(2, message);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Record> getRecords() {
        List<Record> recordList = new ArrayList<>();
        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement()){
            statement.executeQuery(SELECT_FROM_DB);
            ResultSet msgSet = statement.getResultSet();
            while (msgSet.next()) {
                Record r = new Record(msgSet.getInt(1),
                                      msgSet.getTimestamp(2),
                                      msgSet.getString(3));
                recordList.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recordList;
    }
}

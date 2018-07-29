package dao;

import model.Record;

import java.util.List;

public interface GuestBookController{
    void addRecord(String message);
    List<Record> getRecords(); //Record {id, postDate, message}
}
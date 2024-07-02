package com.herd.API.models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
public class Cow {
    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    private String id;
    private int collarId;
    private int cowNumber;
    private String collarStatus;

    public Cow(){}

    public Cow(String id, int collarId, int cowNumber, String collarStatus) {
        this.id = id;
        this.collarId = collarId;
        this.cowNumber = cowNumber;
        this.collarStatus = collarStatus;
    }

    public Cow(int collarId, int cowNumber, String collarStatus) {
        this.collarId = collarId;
        this.cowNumber = cowNumber;
        this.collarStatus = collarStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCollarId() {
        return collarId;
    }

    public void setCollarId(int collarId) {
        this.collarId = collarId;
    }

    public int getCowNumber() {
        return cowNumber;
    }

    public void setCowNumber(int cowNumber) {
        this.cowNumber = cowNumber;
    }

    public String getCollarStatus() {
        return collarStatus;
    }

    public void setCollarStatus(String collarStatus) {
        this.collarStatus = collarStatus;
    }

    public String responseString() {
        return "{\n" +
                "\"id\"= \"" + id + "\",\n" +
                "\"collarId\"= \"" + collarId + "\",\n" +
                "\"cowNumber\"= \"" + cowNumber + "\",\n" +
                "\"collarStatus\"= \"" + collarStatus + "\"\n" +
                "}\n";
    }

    @Override
    public String toString() {
        String returnString = "{\n" +
                "\"id\"= \"" + id + "\",\n" +
                "\"collarId\"= \"" + collarId + "\",\n" +
                "\"cowNumber\"= \"" + cowNumber + "\",\n" +
                "\"collarStatus\"= \"" + collarStatus + "\"\n" +
                "}\n";
        returnString = returnString.replaceAll("\\n", "<br>");
        return returnString;
    }
}
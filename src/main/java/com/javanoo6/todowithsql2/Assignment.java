package com.javanoo6.todowithsql2;

public class Assignment {
     String assignment;
     int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }
    public Assignment(int id, String assignment) {
        this.id = id;
        this.assignment = assignment;

    }
}

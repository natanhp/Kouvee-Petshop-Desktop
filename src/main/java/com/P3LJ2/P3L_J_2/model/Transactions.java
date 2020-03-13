package com.P3LJ2.P3L_J_2.model;

import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class Transactions {
    private int Id;
    private int createdBy;
    private int updatedBy;
    private boolean isPaid;
    private double total;

    private List<Integer> argument = new LinkedList<>();

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//    LocalDateTime now = LocalDateTime.now(); --> save for use
//    usage dtf.format(now)

    public Transactions(int id, boolean isPaid, double total, int createdBy, int updatedBy, DateTimeFormatter createdAt, DateTimeFormatter updatedAt, DateTimeFormatter deletedAt, Integer ... args) {

        this.Id = id;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.isPaid = isPaid;
        this.total = total;
        this.dtf = createdAt;
        this.dtf = updatedAt;
        this.dtf = deletedAt;

        for(Integer arg : args)
            this.argument.add(arg);

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Integer> getArgument() {
        return argument;
    }

    public void setArgument(List<Integer> argument) {
        this.argument = argument;
    }

    public DateTimeFormatter getDtf() {
        return dtf;
    }

    public void setDtf(DateTimeFormatter dtf) {
        this.dtf = dtf;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "Id=" + Id +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", isPaid=" + isPaid +
                ", total=" + total +
                ", argument=" + argument +
                ", dtf=" + dtf +
                '}';
    }
}

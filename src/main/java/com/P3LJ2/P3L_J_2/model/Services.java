package com.P3LJ2.P3L_J_2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    private String serviceName;

    public Services(int id, String serviceName) {
        Id = id;
        this.serviceName = serviceName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String toString() {
        return "Services{" +
                "Id=" + Id +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }
}

package com.P3LJ2.P3L_J_2;
import org.springframework.scheduling.support.SimpleTriggerContext;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private List<String> argument = new LinkedList<>();


    protected User() {}

    //User constructor
    public User(int Id, String ... args)
    {

        for(String arg : args)
            this.argument.add(arg);
        this.Id = Id;
    }

    //return uniqueId
    public int getId() {
        return Id;
    }

    //return any String arguments
    public static void printList(List<String> argument)
    {
        for(String list : argument)
            System.out.printf("%s", list);
        System.out.println();
    }

    //return data in String format
    @Override
    public String toString()
    {
        return String.format("%s%n%s", Id, argument);
    }

}

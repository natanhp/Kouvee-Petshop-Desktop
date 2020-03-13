package com.P3LJ2.P3L_J_2;

import com.P3LJ2.P3L_J_2.model.Employees;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    Employees employees = new Employees(1, "Jack Bauer", "NewFound St",
            "01/01/1980", "111-1111-11", "Admin", "jackbauer", "jackbauer123");
    public String showBussinesLogic()
    {

        return employees.toString();
    }

}

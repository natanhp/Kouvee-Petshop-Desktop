package com.P3LJ2.P3L_J_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {



    EmployeeRepository employeeRepository = new EmployeeRepository() {
        @Override
        public List<Employee> findByName(String name) {
            return null;
        }

        @Override
        public Employee findById(long Id) {
            return null;
        }

        @Override
        public Employee getEmployeeId() {
            return null;
        }

        @Override
        public <S extends Employee> S save(S entity) {
            return null;
        }

        @Override
        public <S extends Employee> Iterable<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public Optional<Employee> findById(Long aLong) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
            return false;
        }

        @Override
        public Iterable<Employee> findAll() {
            return null;
        }

        @Override
        public Iterable<Employee> findAllById(Iterable<Long> longs) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {

        }

        @Override
        public void delete(Employee entity) {

        }

        @Override
        public void deleteAll(Iterable<? extends Employee> entities) {

        }

        @Override
        public void deleteAll() {

        }
    };
    public String getWeatherForecast()
    {

//        Employee employees = new Employee(1, "Jack Bauer", "", "01/2/1987", "111-1111-11", "Admin", "jackbauer", "jackbauer123");
//        System.out.printf("%s", employees.toString());
        return employeeRepository.toString();
    }



}

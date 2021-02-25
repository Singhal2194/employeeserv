package com.paypal.bfs.test.employeeserv.utils;

import java.util.Random;

public class EmployeeUtils {

    public static Integer generateUniqueEmpId() {
        Random random = new Random();
        return random.nextInt(10000);
    }
}

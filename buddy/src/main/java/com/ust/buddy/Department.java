package com.ust.buddy;

import java.util.ArrayList;
import java.util.List;
 
public class Department {
public int deptId;
public List<Employee> empList = new ArrayList<Employee>();
 
public Department(int deptId) {
this.deptId = deptId;
}
 
}


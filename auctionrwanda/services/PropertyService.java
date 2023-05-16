package com.auctionrwanda.services;


import com.auctionrwanda.model.Property;

import java.util.List;

public interface PropertyService {
    // method signature
    // return value , method name;
    Property registerStudent(Property stud);
    Property updateStudent(Property stud);
    void deleteStudent(String stud);
    List<Property> studentList();
    Property findStudentByStudentId(String stud);
}

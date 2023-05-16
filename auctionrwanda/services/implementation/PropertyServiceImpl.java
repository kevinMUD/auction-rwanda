package com.auctionrwanda.services.implementation;

import com.auctionrwanda.model.Property;
import com.auctionrwanda.repository.PropertyRepository;
import com.auctionrwanda.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    @Override
    public Property registerStudent(Property stud) {
        return propertyRepository.save(stud);
    }

    @Override
    public Property updateStudent(Property stud) {
      return propertyRepository.save(stud);
    }

    @Override
    public void deleteStudent(String stud) {
        propertyRepository.deleteById(stud);
    }


    @Override
    public List<Property> studentList() {
        return propertyRepository.findAll();
    }

    @Override
    public Property findStudentByStudentId(String stud) {
        return propertyRepository.findById(stud).get();
    }
}

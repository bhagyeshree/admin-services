package com.cognizant.goldenretrievers.adminservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AdminService {

    @Autowired
    AdminRepository repository;

    public Iterable<Badge> getBadges(){
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .filter(value->value.getStatus().equals("Issued"))
                .collect(Collectors.toList());
    }

    public String getAvailableBadge(){
        return ""+StreamSupport.stream(repository.findAll().spliterator(), false)
                .filter(value->value.getStatus().equals("Returned"))
                .findFirst()
                .get().getId();
    }

}
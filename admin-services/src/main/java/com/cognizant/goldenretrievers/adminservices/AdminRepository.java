package com.cognizant.goldenretrievers.adminservices;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface AdminRepository extends CrudRepository<Badge,Long> {
}

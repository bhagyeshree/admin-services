package com.cognizant.goldenretrievers.adminservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/adminservice")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping
    public Iterable<Badge> getIssuedBadges(){
        return adminService.getBadges();
    }

    @GetMapping("/id")
    public String getAvailableId(){
        return adminService.getAvailableBadge();
    }


}

package com.cognizant.goldenretrievers.adminservices;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.contains;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class adminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    AdminService adminService;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    AdminRepository repository;

    @After
    public void afterEach(){
        repository.deleteAll();
    }

    @Before
    public void beforeEach(){
        repository.deleteAll();
    }

    @Test
    public void returnEmptyListWhenRequestedForBadges() throws Exception{

        //Setup

        //Exercise
        final String actual = mockMvc.perform(get("/api/adminservice"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //Assert
        assertThat(actual, is("[]"));

    }

    @Test
    public void returnASingleBadgeWhenRequested() throws Exception{

        //Setup
        Badge badge = new Badge(0L,"Issued");
        repository.save(badge);

        //Exercise
        final String content = mockMvc.perform(get("/api/adminservice"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final List<Badge> actual = OBJECT_MAPPER.readValue(content,new TypeReference<List<Badge>>(){});


        //Assert
        assertThat(actual,contains(badge));
    }


    @Test
    public void returnIssuedBadgesWhenGivenRandomSetOfBadges() throws Exception{

        //Setup
        Badge badge1 = new Badge(1L,"Issued");
        Badge badge2 = new Badge(2L,"Returned");
        Badge badge3 = new Badge(3L,"Issued");
        Badge badge4 = new Badge(4L,"Returned");
        Badge badge5 = new Badge(5L,"Issued");
        Badge badge6 = new Badge(6L,"Returned");
        repository.save(badge1);
        repository.save(badge2);
        repository.save(badge3);
        repository.save(badge4);
        repository.save(badge5);
        repository.save(badge6);

        //Exercise
        final String content = mockMvc.perform(get("/api/adminservice"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final List<Badge> actual = OBJECT_MAPPER.readValue(content,new TypeReference<List<Badge>>(){});

        //Assert
        assertThat(actual,contains(badge1, badge3, badge5));

    }


    @Test
    public void returnAvailableBadgeId() throws Exception {

        //Setup
        Badge badge1 = new Badge(7L,"Issued");
        Badge badge2 = new Badge(8L,"Returned");
        Badge badge3 = new Badge(9L,"Issued");
        Badge badge4 = new Badge(10L,"Returned");
        Badge badge5 = new Badge(11L,"Issued");
        Badge badge6 = new Badge(12L,"Returned");
        repository.save(badge1);
        repository.save(badge2);
        repository.save(badge3);
        repository.save(badge4);
        repository.save(badge5);
        repository.save(badge6);

        //Exercise
        final String actual = mockMvc.perform(get("/api/adminservice/id"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //Assert
        assertThat(actual, is("8"));
    }

}

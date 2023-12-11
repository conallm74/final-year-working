package com.project.roku;


import com.project.roku.dao.PharmacyRepoDao;
import com.project.roku.services.pharmacy_services.PharmaRepoServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest
public class PharmacyListTest {


    private static MockHttpServletRequest request;

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    PharmacyRepoDao pharmacyRepoDao;

    @Autowired
    private MockMvc mockMcv;

    @Mock
    private PharmaRepoServiceImpl serviceMock;




    @BeforeAll
    public static void setUp(){
        request = new MockHttpServletRequest();
        request.setParameter("pharmacyId", String.valueOf(80));
        request.setParameter("pharmacyName", "Hollands Pharmacy");
        request.setParameter("pharmacyAddress", "Shaw Street");
    }

    @Test
    public static void testSqlQuery(){
        // assertEquals("sldf","lkjsadf","lkjsdf")
    }

}

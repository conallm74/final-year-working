package com.project.roku;

import com.project.roku.controller.PrescriptionController;
import com.project.roku.dao.PharmacyRepoDao;
import com.project.roku.medical_entities.Prescription;
import com.project.roku.services.pharmacy_services.PharmaRepoServiceImpl;
import com.project.roku.services.prescription_services.PrescriptionServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
public class PrescriptionUpdateTest {


    private static MockHttpServletRequest request;

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    PharmacyRepoDao pharmacyRepoDao;

    @Autowired
    private MockMvc mockMcv;

    @Mock
    private PharmaRepoServiceImpl serviceMock;

    @Mock
    private PrescriptionServiceImpl prescriptionService;

    @InjectMocks
    private PrescriptionController prescriptionController;


    @Test
    public void testPrescriptionUpdate() throws Exception{

        when(prescriptionService.save(Mockito.any(Prescription.class))).thenReturn(new Prescription());



        mockMcv.perform(MockMvcRequestBuilders.post("/prescriptions/prescribePrescription")
                        .param("pharmacyRecipientId", "80")
                        .param("medicationName", "Aspirin")
                        .param("prescriptionDate", "2023-11-10")
                        .param("dosage", "1 pill daily")
                        .param("prescribingDoctor", "Mike"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("redirect:patients/patient-list"));


        // add assertions we expect

    }


}

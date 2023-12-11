package com.project.roku;


import com.project.roku.DTO.PrescriptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PrescriptionDtoTest {

    @Autowired
    public PrescriptionDTO prescriptionDTO;

    // what do I want to test. I want to convert a saved prescription to a DTO. Then I
    // want to test the state of that object, so performing an assertEquals.

}

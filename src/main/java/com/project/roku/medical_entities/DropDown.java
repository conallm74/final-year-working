package com.project.roku.medical_entities;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DropDown extends Pharmacy {

    private List<Pharmacy> pharmacies = new ArrayList<>();

    public DropDown (){}

    public DropDown(List<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
    }

    public List<Pharmacy> getStudents() {
        return pharmacies;
    }

    public void setStudents(List<Pharmacy> students) {
        this.pharmacies = students;
    }

}

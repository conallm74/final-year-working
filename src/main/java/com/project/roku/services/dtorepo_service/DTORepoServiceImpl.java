package com.project.roku.services.dtorepo_service;

import com.project.roku.DTO.PrescriptionDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class DTORepoServiceImpl implements DTORepoService{

    @Autowired
    DTORepoService dtoRepoService;

    @Override
    public PrescriptionDTO save(PrescriptionDTO thePrescriptionDTO){
        return dtoRepoService.save(thePrescriptionDTO);
    }

}

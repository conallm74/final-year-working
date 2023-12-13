package com.project.roku.dao;

import com.project.roku.DTO.PrescriptionDTO;
import org.springframework.data.repository.CrudRepository;

public interface DTORepo extends CrudRepository<PrescriptionDTO, Integer> {
}

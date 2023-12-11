package com.project.roku.services.pharmacy_services;

import com.project.roku.dao.PharmacyRepoDao;
import com.project.roku.medical_entities.Pharmacy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("uniquePharmacyRepoService")
public class PharmaRepoServiceImpl implements PharmaRepoService {

    @Autowired
    private PharmacyRepoDao pharmacyRepo;

    @Autowired
    public PharmaRepoServiceImpl(PharmacyRepoDao thePharmacyRepo){
        pharmacyRepo = thePharmacyRepo;
    }

    @Override
    public List<Pharmacy> findAll() {
        return pharmacyRepo.findAll();
    }

    @Override
    public Pharmacy findById(int theId) {
        Optional<Pharmacy> result = pharmacyRepo.findById(theId);

        Pharmacy theUser = null;
        if (result.isPresent()){
            theUser = result.get();
        }
        else {
            throw new RuntimeException("Couldn't find that Pharmacy by:" + theId + "Id");
        }
        return theUser;
    }

    @Override
    public Pharmacy save(Pharmacy theUser) {
        return pharmacyRepo.save(theUser);
    }

    @Override
    public void deleteById(int theId) {
        pharmacyRepo.deleteById(theId);
    }

    @Override
    public List<Object[]> findAllPharmacies() {
        return pharmacyRepo.findAllPharmacyNames();
    }

    @Override
    public Pharmacy findRecipientId(int recipientId) {
        return pharmacyRepo.findRecipientId(recipientId);
    }


}

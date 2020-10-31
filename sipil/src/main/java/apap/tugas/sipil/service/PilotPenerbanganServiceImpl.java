package apap.tugas.sipil.service;

import apap.tugas.sipil.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import apap.tugas.sipil.repository.PilotPenerbanganDb;

@Service
@Transactional
public class PilotPenerbanganServiceImpl implements PilotPenerbanganService{
    @Autowired
    PilotPenerbanganDb pilotPenerbanganDb;

    @Override
    public void addPilotPenerbangan(PilotPenerbanganModel pilotPenerbanganModel){
        pilotPenerbanganDb.save(pilotPenerbanganModel);
    }
}

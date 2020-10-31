package apap.tugas.sipil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import apap.tugas.sipil.repository.PenerbanganDb;
import apap.tugas.sipil.model.*;
import java.util.List;

@Service
@Transactional
public class PenerbanganServiceImpl implements PenerbanganService{
    @Autowired
    PenerbanganDb penerbanganDb;

    @Override
    public void addPenerbangan(PenerbanganModel penerbanganModel) { 
        penerbanganDb.save(penerbanganModel); 
    }

    @Override
    public List<PenerbanganModel> getAllPenerbangan() { return penerbanganDb.findAll(Sort.by(Sort.Direction.ASC, "kodePenerbangan")); }

    @Override
    public PenerbanganModel getPenerbanganById(Long id){
        return penerbanganDb.findById(id).get();
    }

    @Override
    public PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan) { 
        penerbanganDb.save(penerbangan);

        return penerbangan;
    }

    @Override
    public PenerbanganModel deletePenerbangan(PenerbanganModel penerbangan) {
        penerbanganDb.delete(penerbangan);

        return penerbangan;
    }
}
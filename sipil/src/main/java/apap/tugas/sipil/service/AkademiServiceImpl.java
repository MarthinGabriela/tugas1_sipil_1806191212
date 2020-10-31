package apap.tugas.sipil.service;

import apap.tugas.sipil.model.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import apap.tugas.sipil.repository.AkademiDb;

@Service
@Transactional
public class AkademiServiceImpl implements AkademiService {
    @Autowired
    AkademiDb akademiDb;

    @Override
    public List<AkademiModel> getAllAkademi() { return akademiDb.findAll(Sort.by(Sort.Direction.ASC, "namaAkademi")); }

    @Override
    public AkademiModel getByIdAkademi(Long id) {
        return akademiDb.findByIdAkademi(id).get();
    }
}
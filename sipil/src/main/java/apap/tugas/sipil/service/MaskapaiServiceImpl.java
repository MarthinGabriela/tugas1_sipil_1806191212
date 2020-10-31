package apap.tugas.sipil.service;

import apap.tugas.sipil.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import apap.tugas.sipil.repository.MaskapaiDb;
import java.util.List;

@Service
@Transactional
public class MaskapaiServiceImpl implements MaskapaiService {
    @Autowired
    MaskapaiDb maskapaiDb;

    @Override
    public List<MaskapaiModel> getAllMaskapai() { return maskapaiDb.findAll(Sort.by(Sort.Direction.ASC, "namaMaskapai")); }

    @Override
    public MaskapaiModel getByIdMaskapai(Long id) {
        return maskapaiDb.findByIdMaskapai(id).get();
    }

    @Override
    public MaskapaiModel getByKodeMaskapai(String kode) {
        return maskapaiDb.findByKodeMaskapai(kode).get();
    }
}
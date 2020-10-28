package apap.tugas.sipil.service;

import apap.tugas.sipil.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import apap.tugas.sipil.repository.MaskapaiDb;

@Service
@Transactional
public class MaskapaiServiceImpl implements MaskapaiService {
    @Autowired
    MaskapaiDb maskapaiDb;

    public void addMaskapai(MaskapaiModel maskapaiModel) { maskapaiDb.save(maskapaiModel); }
}
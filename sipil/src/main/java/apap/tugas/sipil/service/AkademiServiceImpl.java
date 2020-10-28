package apap.tugas.sipil.service;

import apap.tugas.sipil.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import apap.tugas.sipil.repository.AkademiDb;

@Service
@Transactional
public class AkademiServiceImpl implements AkademiService {
    @Autowired
    AkademiDb akademiDb;

    public void addAkademi(AkademiModel akademiModel) { akademiDb.save(akademiModel); }
}

package apap.tugas.sipil.service;

import java.util.List;
import apap.tugas.sipil.model.AkademiModel;

public interface AkademiService {
    List<AkademiModel> getAllAkademi();
    AkademiModel getByIdAkademi(Long id);
}
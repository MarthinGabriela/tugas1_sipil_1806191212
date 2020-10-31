package apap.tugas.sipil.service;

import java.util.List;

import apap.tugas.sipil.model.PenerbanganModel;

public interface PenerbanganService {
    void addPenerbangan(PenerbanganModel penerbangan);
    PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan);
    PenerbanganModel deletePenerbangan(PenerbanganModel penerbangan);
    PenerbanganModel getPenerbanganById(Long Id);
    List<PenerbanganModel> getAllPenerbangan();
}

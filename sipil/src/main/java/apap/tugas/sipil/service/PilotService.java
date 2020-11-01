package apap.tugas.sipil.service;

import java.util.List;

import apap.tugas.sipil.model.*;

public interface PilotService {
    void addPilot(PilotModel pilotModel);
    PilotModel updatePilot(PilotModel pilotModel);
    PilotModel deletePilot(PilotModel pilotModel);
    PilotModel getPilotByNip(String nip);
    List<PilotModel> getAllPilot();
    String checkNip(PilotModel pilotModel);
    String randomString();
    List<PilotModel> get3Pilots(MaskapaiModel maskapai);
    List<PilotModel> getPilotMaskapaiModelAndAkademiModel(MaskapaiModel maskapaiModel, AkademiModel akademiModel);
    List<PilotModel> getPilotMaskapaiModel(MaskapaiModel maskapaiModel);
    List<PilotModel> getPilotAkademiModel(AkademiModel akademiModel);
    List<PilotModel> getThisMonthPilot();
}
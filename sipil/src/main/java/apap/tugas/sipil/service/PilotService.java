package apap.tugas.sipil.service;

import apap.tugas.sipil.model.PilotModel;

public interface PilotService {
    void addPilot(PilotModel pilotModel);
    String checkNip(PilotModel pilotModel);
    String randomString();
}
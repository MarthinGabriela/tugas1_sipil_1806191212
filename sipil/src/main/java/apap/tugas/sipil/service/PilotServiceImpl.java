package apap.tugas.sipil.service;

import apap.tugas.sipil.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import apap.tugas.sipil.repository.PilotDb;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PilotServiceImpl implements PilotService {
    @Autowired
    PilotDb pilotDb;

    @Override
    public void addPilot(PilotModel pilotModel) { 
        String nip = checkNip(pilotModel);

        pilotModel.setNip(nip);
        pilotDb.save(pilotModel); 
    }

    @Override
    public String checkNip(PilotModel pilotModel) {
        String first = pilotModel.getJenisKelamin().toString();
        String second = pilotModel.getTempatLahir().substring(0, 2).toUpperCase();
        String third = pilotModel.getNamaPilot().toUpperCase();

        third = third.substring(third.length() - 2, third.length());

        LocalDate tanggalLahir = pilotModel.getTanggalLahir().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = tanggalLahir.getMonthValue();
        int date = tanggalLahir.getDayOfMonth();
        int year = tanggalLahir.getYear();

        String fourth = Integer.toString(date) + Integer.toString(month);
        String fifth = Double.toString(Math.floor(year / 10));
        fifth = fifth.substring(0, 3);
        String sixth = randomString();

        try{
            while(true) {
                getPilotByNip(first + second + third + fourth + fifth + sixth);
                sixth = randomString();
            }
        } catch(NoSuchElementException e) {
            return first + second + third + fourth + fifth + sixth;
        }
    }

    @Override
    public String randomString() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 2;
        Random random = new Random();
    
        String generatedString = random.ints(leftLimit, rightLimit + 1)
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();

        String result = generatedString.toUpperCase();
        return result;
    }

    @Override
    public List<PilotModel> getAllPilot() { return pilotDb.findAll(Sort.by(Sort.Direction.ASC, "namaPilot")); }

    @Override
    public PilotModel getPilotByNip(String nip){
        return pilotDb.findByNip(nip).get();
    }

    @Override
    public PilotModel updatePilot(PilotModel pilotModel){
        String nip = checkNip(pilotModel);

        pilotModel.setNip(nip);
        pilotDb.save(pilotModel);
        return pilotModel;
    }

    @Override
    public PilotModel deletePilot(PilotModel pilotModel) {
        pilotDb.delete(pilotModel);

        return pilotModel;
    }

    @Override
    public List<PilotModel> getPilotMaskapaiModelAndAkademiModel(MaskapaiModel maskapaiModel, AkademiModel akademiModel) {
        return pilotDb.findByMaskapaiModelAndAkademiModel(maskapaiModel, akademiModel);
    }

    @Override
    public List<PilotModel> getPilotMaskapaiModel(MaskapaiModel maskapaiModel) {
        return pilotDb.findByMaskapaiModel(maskapaiModel);
    }

    @Override
    public List<PilotModel> getPilotAkademiModel(AkademiModel akademiModel) {
        return pilotDb.findByAkademiModel(akademiModel);
    }

    @Override
    public List<PilotModel> get3Pilots(MaskapaiModel maskapai){
        return pilotDb.find3Pilots(maskapai);
    }

    @Override
    public List<PilotModel> getThisMonthPilot(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date tanggal = cal.getTime();
        return pilotDb.findByThisMonth(tanggal);
    }
}

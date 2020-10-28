package apap.tugas.sipil.service;

import apap.tugas.sipil.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import apap.tugas.sipil.repository.PilotDb;
import java.time.LocalDate;
import java.time.ZoneId;
import java.nio.charset.*;
import java.util.Random;

@Service
@Transactional
public class PilotServiceImpl implements PilotService{
    @Autowired
    PilotDb pilotDb;

    @Override
    public void addPilot(PilotModel pilotModel) { 
        String nip = checkNip(pilotModel);

        pilotModel.setNip(nip);
        pilotDb.save(pilotModel); 
    }

    public String checkNip(PilotModel pilotModel) {
        String first = pilotModel.getJenisKelamin().toString();
        String second = pilotModel.getTempatLahir().substring(0, 2).toUpperCase();
        String third = pilotModel.getNamaPilot();

        third = third.substring(third.length() - 2, third.length());

        LocalDate tanggalLahir = pilotModel.getTanggalLahir().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = tanggalLahir.getMonthValue();
        int date = tanggalLahir.getDayOfMonth();
        int year = tanggalLahir.getYear();

        String fourth = Integer.toString(date) + Integer.toString(month);
        String fifth = Double.toString(Math.floor(year / 10));
        String sixth = randomString();

        return first + second + third + fourth + fifth + sixth;
    }

    public String randomString() {
        byte[] array = new byte[2];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        return generatedString.toUpperCase();
    }
}

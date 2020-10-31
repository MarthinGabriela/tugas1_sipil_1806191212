package apap.tugas.sipil.controller;

import apap.tugas.sipil.model.*;
import apap.tugas.sipil.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class PilotController {
    @Qualifier("pilotServiceImpl")
    @Autowired
    private PilotService pilotService;

    @Autowired
    private MaskapaiService maskapaiService;

    @Autowired
    private AkademiService akademiService;

    @GetMapping("/pilot/add")
    public String addPilotFormPage(Model model) {
        List<MaskapaiModel> listMaskapai = maskapaiService.getAllMaskapai();
        List<AkademiModel> listAkademi = akademiService.getAllAkademi();

        model.addAttribute("pilot", new PilotModel());
        model.addAttribute("listAkademi", listAkademi);
        model.addAttribute("listMaskapai", listMaskapai);
        return "form-add-pilot";
    }

    @PostMapping("/pilot/add")
    public String addPilotSubmit(
            @ModelAttribute PilotModel pilot,
            Model model
    ) {
        pilotService.addPilot(pilot);
        model.addAttribute("nip_pilot", pilot.getNip());

        return "add-pilot";
    }

    @GetMapping("/pilot")
    private String viewAllPilot(Model model) {
        List<PilotModel> listPilot = pilotService.getAllPilot();
        model.addAttribute("listPilot", listPilot);
        return "pilot";
    }

    @GetMapping("/pilot/{nip}")
    public String viewDetailPilot(
        @PathVariable(value = "nip") String nip,
        Model model
    ) {
        try{
            PilotModel pilot = pilotService.getPilotByNip(nip);
            model.addAttribute("pilot", pilot);
            model.addAttribute("listPilotPenerbangan", pilot.getListPilotPenerbanganModel());
            return "view-pilot";
        } catch(NoSuchElementException e) {
            String message = "Proses view tidak dapat dilakukan karena NIP yang diberikan tidak valid";
            model.addAttribute("message", message);
            return "error";
        }
    }

    @GetMapping("/pilot/ubah/{nip}")
    public String ubahPilot(
        @PathVariable(value = "nip") String nip,
        Model model
    ) {
        try {
            PilotModel pilot = pilotService.getPilotByNip(nip);

            List<MaskapaiModel> listMaskapai = maskapaiService.getAllMaskapai();
            List<AkademiModel> listAkademi = akademiService.getAllAkademi();

            model.addAttribute(("pilot"), pilot);
            model.addAttribute("listAkademi", listAkademi);
            model.addAttribute("listMaskapai", listMaskapai);
            return "form-ubah-pilot";
        } catch(NoSuchElementException e) {
            String message = "Proses ubah tidak dapat dilakukan karena NIP yang diberikan tidak valid";
            model.addAttribute("message", message);
            return "error";
        }
    }

    @PostMapping(value = "/pilot/ubah")
    public String ubahPilotSubmit(
        @ModelAttribute PilotModel pilot,
        Long idPilot,
        Model model
    ) {
        pilot.setIdPilot(idPilot);
        PilotModel updatedPilot = pilotService.updatePilot(pilot);
        model.addAttribute("idPilot", updatedPilot.getIdPilot());
        return "ubah-pilot";
    }

    @GetMapping(value = "/pilot/delete/{nip}")
    public String deletePilot(
        @PathVariable(value = "nip") String nip,
        Model model
    ) {
        String menu = "pilot";
        model.addAttribute("menu", menu);

        try {
            PilotModel pilot = pilotService.getPilotByNip(nip);

            model.addAttribute("idPilot", pilot.getIdPilot());
            if(pilot.getListPilotPenerbanganModel().size() != 0) {
                String message = "Pilot " + pilot.getNip() + " gagal dihapus karena memiliki tugas penerbangan";
                model.addAttribute("message", message);
            }
            
            pilot = pilotService.deletePilot(pilot);
            return "delete-pilot";
        } catch(NoSuchElementException e) {
            String message = "Proses hapus tidak dapat dilakukan karena NIP yang diberikan tidak valid";
            model.addAttribute("menu", menu);
            model.addAttribute("message", message);
            return "error";
        }
    }
}
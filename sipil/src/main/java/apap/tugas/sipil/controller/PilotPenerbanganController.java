package apap.tugas.sipil.controller;

import apap.tugas.sipil.model.*;
import apap.tugas.sipil.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@Controller
public class PilotPenerbanganController {
    @Qualifier("pilotPenerbanganServiceImpl")
    @Autowired
    private PilotPenerbanganService pilotPenerbanganService;

    @PostMapping(value = "/penerbangan/{idPenerbangan}/pilot/tambah")
    public String postNamaPilot(
        @PathVariable Long idPenerbangan,
        @ModelAttribute PilotPenerbanganModel pilotPenerbangan,
        Model model
    ) {
        String message = "penerbangan";
        Date tanggalPenugasan = new Date();

        pilotPenerbangan.setTanggalPenugasanPilotPenerbangan(tanggalPenugasan);
        pilotPenerbanganService.addPilotPenerbangan(pilotPenerbangan);

        model.addAttribute("kodePenerbangan", pilotPenerbangan.getPenerbanganModel().getKodePenerbangan());
        model.addAttribute("namaPilot", pilotPenerbangan.getPilotModel().getIdPilot());
        model.addAttribute("message", message);
        return "add-pilotPenerbangan";
    }
}
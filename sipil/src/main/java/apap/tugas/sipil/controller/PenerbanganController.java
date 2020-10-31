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
public class PenerbanganController {
    @Qualifier("penerbanganServiceImpl")
    @Autowired
    private PenerbanganService penerbanganService;

    @Autowired
    private PilotService pilotService;

    @GetMapping("/penerbangan/add")
    public String addpenerbanganFormPage(Model model) {
        model.addAttribute("penerbangan", new PenerbanganModel());
        return "form-add-penerbangan";
    }

    @PostMapping("/penerbangan/add")
    public String addpenerbanganSubmit(
            @ModelAttribute PenerbanganModel penerbangan,
            Model model
    ) {
        penerbanganService.addPenerbangan(penerbangan);
        model.addAttribute("kodePenerbangan", penerbangan.getKodePenerbangan());

        return "add-penerbangan";
    }

    @GetMapping("/penerbangan")
    private String viewAllpenerbangan(Model model) {
        List<PenerbanganModel> listPenerbangan = penerbanganService.getAllPenerbangan();
        model.addAttribute("listPenerbangan", listPenerbangan);
        return "penerbangan";
    }

    @GetMapping("/penerbangan/detail/{idPenerbangan}")
    public String viewDetailpenerbangan(
        @PathVariable(value = "idPenerbangan") Long idPenerbangan,
        Model model
    ) {
        try{
            PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idPenerbangan);

            List<PilotModel> listPilot = pilotService.getAllPilot();
            model.addAttribute("listPilot", listPilot);
            model.addAttribute("pilotPenerbangan", new PilotPenerbanganModel());
            model.addAttribute("penerbangan", penerbangan);
            model.addAttribute("listPilotPenerbangan", penerbangan.getListPilotPenerbanganModel());
            return "view-penerbangan";
        } catch(NoSuchElementException e) {
            String message = "Proses view tidak dapat dilakukan karena id yang diberikan tidak valid";
            model.addAttribute("message", message);
            return "error";
        }
    }

    @GetMapping("/penerbangan/ubah/{idPenerbangan}")
    public String ubahPenerbangan(
        @PathVariable(value = "idPenerbangan") Long idPenerbangan,
        Model model
    ) {
        try{
            PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
            model.addAttribute("penerbangan", penerbangan);
            return "form-ubah-penerbangan";
        } catch(NoSuchElementException e) {
            String message = "Proses ubah tidak dapat dilakukan karena id yang diberikan tidak valid";
            model.addAttribute("message", message);
            return "error";
        }
    }

    @PostMapping(value = "/penerbangan/ubah")
    public String ubahPenerbanganSubmit(
        @ModelAttribute PenerbanganModel penerbangan,
        Model model
    ) {
        PenerbanganModel updatedPenerbangan = penerbanganService.updatePenerbangan(penerbangan);
        model.addAttribute("idPenerbangan", updatedPenerbangan.getIdPenerbangan());
        return "ubah-penerbangan";
    }

    @GetMapping(value = "/penerbangan/delete/{id}")
    public String deletePenerbangan(
        @PathVariable(value = "id") Long id,
        Model model
    ) {
        String menu = "penerbangan";
        model.addAttribute("menu", menu);

        try {
            PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(id);

            model.addAttribute("idPenerbangan", penerbangan.getIdPenerbangan());
            if(penerbangan.getListPilotPenerbanganModel().size() != 0) {
                String message = "Penerbangan " + penerbangan.getKodePenerbangan() + " gagal dihapus karena terdapat pilot yang bertugas";
                model.addAttribute("message", message);
                return "error";
            }
            penerbangan = penerbanganService.deletePenerbangan(penerbangan);
            return "delete-penerbangan";
        } catch(NoSuchElementException e) {
            String message = "Proses hapus tidak dapat dilakukan karena kode yang diberikan tidak valid";
            model.addAttribute("message", message);
            return "error";
        }
    }
}

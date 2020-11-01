package apap.tugas.sipil.controller;

import apap.tugas.sipil.model.*;
import apap.tugas.sipil.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Controller
public class MainController {

    @Autowired
    private PilotService pilotService;

    @Autowired
    private MaskapaiService maskapaiService;

    @Autowired
    private AkademiService akademiService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        return "home";
    }

    @GetMapping("/cari")
    public String getCariPage(Model model) {
        return "cari";
    }

    @RequestMapping(value="/cari/pilot")
    public String seleksiPilot(
        @RequestParam(value = "idAkademi", required=false) Long idAkademi,
        @RequestParam(value = "kodeMaskapai", required=false) String kodeMaskapai,
        Model model
        ) {
            List<MaskapaiModel> listMaskapai = maskapaiService.getAllMaskapai();
            List<AkademiModel> listAkademi = akademiService.getAllAkademi();

            List<PilotModel> listPilot = null;
            MaskapaiModel maskapaiModel =new MaskapaiModel();
            AkademiModel akademiModel =new AkademiModel();

            try {
                maskapaiModel = maskapaiService.getByKodeMaskapai(kodeMaskapai);
                akademiModel = akademiService.getByIdAkademi(idAkademi);

                listPilot = pilotService.getPilotMaskapaiModelAndAkademiModel(maskapaiModel, akademiModel);
            } catch (Exception e) {
                try {
                    maskapaiModel = maskapaiService.getByKodeMaskapai(kodeMaskapai);
                    listPilot = pilotService.getPilotMaskapaiModel(maskapaiModel);
                } catch (Exception i) {
                    try {
                        akademiModel = akademiService.getByIdAkademi(idAkademi);
                        listPilot = pilotService.getPilotAkademiModel(akademiModel);
                    } catch (Exception a) {
                        
                    }
                }
            }

            model.addAttribute("listPilot", listPilot);
            model.addAttribute("listMaskapai", listMaskapai);
            model.addAttribute("listAkademi", listAkademi);
            model.addAttribute("maskapaiModel", maskapaiModel);
            model.addAttribute("akademiModel", akademiModel);
            return "seleksi-pilot";
    }
    
    @RequestMapping(value = "/cari/pilot/penerbangan-terbanyak")
    public String cariTigaPilot(
        @RequestParam (value = "kodeMaskapai", required=false) String kodeMaskapai,
        Model model
    ) {
        List<MaskapaiModel> listMaskapai = maskapaiService.getAllMaskapai();

        List<PilotModel> listPilot = null;
        MaskapaiModel maskapaiModel =new MaskapaiModel();

        try {
            maskapaiModel = maskapaiService.getByKodeMaskapai(kodeMaskapai);
            listPilot = pilotService.get3Pilots(maskapaiModel);
        } catch (Exception e) {
            
        }

        model.addAttribute("listPilot", listPilot);
        model.addAttribute("listMaskapai", listMaskapai);
        model.addAttribute("maskapaiModel", maskapaiModel);

        return "topTigaPilot";
    }

    @GetMapping(value = "/cari/pilot/penerbanganterbanyak")
    public String cariTigaPilotAwal(
        Model model
    ) {
        List<MaskapaiModel> listMaskapai = maskapaiService.getAllMaskapai();

        List<PilotModel> listPilot = null;
        MaskapaiModel maskapaiModel =new MaskapaiModel();

        try {
            maskapaiModel = maskapaiService.getAllMaskapai().get(0);
            listPilot = pilotService.get3Pilots(maskapaiModel);
        } catch (Exception e) {
            
        }

        model.addAttribute("listPilot", listPilot);
        model.addAttribute("listMaskapai", listMaskapai);
        model.addAttribute("maskapaiModel", maskapaiModel);

        return "topTigaPilot";
    }

    @GetMapping("/cari/pilot/bulan/ini")
    public String cariPilotBulanIni(Model model) {

        List<PilotModel> listPilot = null;
        try{
            listPilot = pilotService.getThisMonthPilot();
        } catch (Exception e) {

        }

        model.addAttribute("listPilot", listPilot);

        return "bonus";
    }
}
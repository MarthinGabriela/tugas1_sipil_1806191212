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
    private MaskapaiService maskapaiService;

    @Autowired
    private AkademiService akademiService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        return "home";
    }

    @RequestMapping(value="/cari/pilot")
    public String seleksiPilot(
        @RequestParam(value = "idAkademi", required=false) Long idAkademi,
        @RequestParam(value = "idMaskapai", required=false) String kodeMaskapai,
        Model model
        ) {
            List<MaskapaiModel> listMaskapai = maskapaiService.getAllMaskapai();
            List<AkademiModel> listAkademi = akademiService.getAllAkademi();

            List<PilotModel> listPilot = null;
            MaskapaiModel maskapaiModel =new MaskapaiModel();
            AkademiModel akademiModel =new AkademiModel();

            try {
                maskapaiModel = maskapaiService.getByKodeMaskapai(kodeMaskapai);
            } catch (Exception e) {
                try {
                    
                } catch (Exception e) {
                    try {
                        
                    } catch (Exception e) {
                        
                    }
                }
            }
            return"";
    }
    
}
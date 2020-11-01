package apap.tugas.sipil.repository;

import apap.tugas.sipil.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;
import java.util.Optional;


@Repository
public interface PilotDb extends JpaRepository<PilotModel, Long>{
    Optional<PilotModel> findByIdPilot(Long idPilot);
    Optional<PilotModel> findByNip(String nip);
    List<PilotModel> findByMaskapaiModelAndAkademiModel(MaskapaiModel maskapaiModel, AkademiModel akademiModel);
    List<PilotModel> findByMaskapaiModel(MaskapaiModel maskapaiModel);
    List<PilotModel> findByAkademiModel(AkademiModel akademiModel);

    @Query(
        value = 
        "SELECT p, COUNT(p.idPilot) as x " +
        "FROM PilotModel p, PilotPenerbanganModel q " +
        "WHERE q.pilotModel.idPilot = p.idPilot " +
        "AND p.maskapaiModel = ?1 " +
        "GROUP BY p.idPilot"
        ) 
    List<PilotModel> find3Pilots(MaskapaiModel maskapai);

    @Query(
        value = 
        "SELECT distinct p.pilotModel " +
        "from PilotPenerbanganModel p " +
        "where p.penerbanganModel.waktuPenerbangan >= ?1"
        )
    List<PilotModel> findByThisMonth(Date date);
}
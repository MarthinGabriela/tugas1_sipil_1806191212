package apap.tugas.sipil.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "penerbangan")
public class PenerbanganModel implements Serializable { 
    @Id
    @Column(name = "id_penerbangan")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPenerbangan;

    @NotNull
    @Size(max = 16)
    @Column(name = "kode_penerbangan")
    private String kodePenerbangan;

    @NotNull
    @Column(name = "kode_penerbangan")
    private String kotaAsalPenerbangan;

    @NotNull
    @Column(name = "kode_penerbangan")
    private String kotaTujuanPenerbangan;

    @NotNull
    @Column(name = "waktu_penerbangan")
    private Date waktuPenerbangan;

    @OneToMany(mappedBy = "PilotPenerbanganModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PilotPenerbanganModel> listPilotPenerbanganModel;

    public Long getIdPenerbangan() {
        return this.idPenerbangan;
    }

    public void setIdPenerbangan(Long idPenerbangan) {
        this.idPenerbangan = idPenerbangan;
    }

    public String getKodePenerbangan() {
        return this.kodePenerbangan;
    }

    public void setKodePenerbangan(String kodePenerbangan) {
        this.kodePenerbangan = kodePenerbangan;
    }

    public String getKotaAsalPenerbangan() {
        return this.kotaAsalPenerbangan;
    }

    public void setKotaAsalPenerbangan(String kodeAsalPenerbangan) {
        this.kotaAsalPenerbangan = kodeAsalPenerbangan;
    }

    public String getKotaTujuanPenerbangan() {
        return this.kotaTujuanPenerbangan;
    }

    public void setKotaTujuanPenerbangan(String kodeTujuanPenerbangan) {
        this.kotaTujuanPenerbangan = kodeTujuanPenerbangan;
    }

    public Date getWaktuPenerbangan() {
        return this.waktuPenerbangan;
    }

    public void setWaktuPenerbangan(Date waktuPenerbangan) {
        this.waktuPenerbangan = waktuPenerbangan;
    }

    public List<PilotPenerbanganModel> getListPilotPenerbanganModel() {
        return this.listPilotPenerbanganModel;
    }

    public void addListPilotPenerbanganModel(PilotPenerbanganModel pilotPenerbanganModel) {
        this.listPilotPenerbanganModel.add(pilotPenerbanganModel);
    }
}

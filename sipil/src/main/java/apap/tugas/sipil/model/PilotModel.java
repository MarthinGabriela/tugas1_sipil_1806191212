package apap.tugas.sipil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pilot")
public class PilotModel implements Serializable {
    @Id
    @Column(name = "id_pilot")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPilot;

    @NotNull
    @Column(name = "nama_pilot")
    private String namaPilot;

    @NotNull
    @Size(min=13, max = 13)
    @Column(name = "nip_pilot", unique=true)
    private String nip;

    @NotNull
    @Column(name = "nik_pilot")
    private String nik;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tanggal_lahir_pilot")
    private Date tanggalLahir;

    @NotNull
    @Column(name = "tempat_lahir_pilot")
    private String tempatLahir;

    @NotNull
    @Column(name = "jenis_kelamin_pilot")
    private Integer jenisKelamin;

    @OneToMany(mappedBy = "pilotModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PilotPenerbanganModel> listPilotPenerbanganModel;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "maskapai_id", referencedColumnName = "id_maskapai", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private MaskapaiModel maskapaiModel;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "akademi_id", referencedColumnName = "id_akademi", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private AkademiModel akademiModel;

    public void setIdPilot(Long id) {
        this.idPilot = id;
    }
    
    public Long getIdPilot() {
        return this.idPilot;
    }

    public String getNamaPilot() {
        return this.namaPilot;
    }

    public void setNamaPilot(String namaPilot) {
        this.namaPilot = namaPilot;
    }

    public String getNik() {
        return this.nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNip() {
        return this.nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public Date getTanggalLahir() {
        return this.tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public Integer getJenisKelamin() {
        return this.jenisKelamin;
    }

    public void setJenisKelamin(Integer jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public MaskapaiModel getMaskapaiModel() {
        return this.maskapaiModel;
    }

    public void setMaskapaiModel(MaskapaiModel maskapaiModel) {
        this.maskapaiModel = maskapaiModel;
    }
    public AkademiModel getAkademiModel() {
        return this.akademiModel;
    }

    public void setAkademiModel(AkademiModel akademiModel) {
        this.akademiModel = akademiModel;
    }
    
    public List<PilotPenerbanganModel> getListPilotPenerbanganModel() {
        return this.listPilotPenerbanganModel;
    }

    public void addListPilotPenerbanganModel(PilotPenerbanganModel pilotPenerbanganModel) {
        this.listPilotPenerbanganModel.add(pilotPenerbanganModel);
    }

    public String getTempatLahir() {
        return this.tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

}
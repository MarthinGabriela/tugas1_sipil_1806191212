package apap.tugas.sipil.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "maskapai")
public class MaskapaiModel implements Serializable {
    @Id
    @Column(name = "id_maskapai")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMaskapai;

    @NotNull
    @Column(name = "nama_maskapai")
    private String namaMaskapai;

    @NotNull
    @Column(name = "lokasi_maskapai")
    private String lokasiMaskapai;

    @OneToMany(mappedBy = "PilotModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PilotModel> listPilotModels;

    public Long getIdMaskapai() {
        return this.idMaskapai;
    }

    public String getNamaMaskapai() {
        return this.namaMaskapai;
    }

    public void setNamaMaskapai(String namaMaskapai) {
        this.namaMaskapai = namaMaskapai;
    }

    public String getLokasiMaskapai() {
        return this.lokasiMaskapai;
    }

    public void setLokasiMaskapai(String lokasiMaskapai) {
        this.lokasiMaskapai = lokasiMaskapai;
    }

    public List<PilotModel> getListPilotModels() {
        return this.listPilotModels;
    }

    public void addListPilotModels(PilotModel pilotModel){
        this.listPilotModels.add(pilotModel);
    }
}

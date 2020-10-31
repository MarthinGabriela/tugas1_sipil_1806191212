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
    @Column(name = "kode_maskapai")
    private String kodeMaskapai;

    @OneToMany(mappedBy = "maskapaiModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PilotModel> listPilotModel;

    public Long getIdMaskapai() {
        return this.idMaskapai;
    }

    public String getNamaMaskapai() {
        return this.namaMaskapai;
    }

    public void setNamaMaskapai(String namaMaskapai) {
        this.namaMaskapai = namaMaskapai;
    }

    public String getKodeMaskapai() {
        return this.kodeMaskapai;
    }

    public void setKodeMaskapai(String kodeMaskapai) {
        this.kodeMaskapai = kodeMaskapai;
    }

    public List<PilotModel> getListPilotModel() {
        return this.listPilotModel;
    }

    public void addListPilotModel(PilotModel pilotModel){
        this.listPilotModel.add(pilotModel);
    }
}

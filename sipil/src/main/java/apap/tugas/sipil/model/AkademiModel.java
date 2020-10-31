package apap.tugas.sipil.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "akademi")
public class AkademiModel implements Serializable {
    @Id
    @Column(name = "id_akademi")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAkademi;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama_akademi")
    private String namaAkademi;

    @NotNull
    @Size(max = 255)
    @Column(name = "lokasi_akademi")
    private String lokasiAkademi;

    @OneToMany(mappedBy = "akademiModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PilotModel> listPilotModel;

    public Long getIdAkademi() {
        return this.idAkademi;
    }

    public String getNamaAkademi() {
        return this.namaAkademi;
    }

    public void setNamaAkademi(String namaAkademi) {
        this.namaAkademi = namaAkademi;
    }
    
    public String getLokasiAkademi() {
        return this.lokasiAkademi;
    }

    public void setLokasiAkademi(String lokasiAkademi) {
        this.lokasiAkademi = lokasiAkademi;
    }

    public List<PilotModel> getListPilotModel(){
        return this.listPilotModel;
    }

    public void addListPilotModel(PilotModel pilotModel){
        this.listPilotModel.add(pilotModel);
    }
}

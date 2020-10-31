package apap.tugas.sipil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pilot_penerbangan")
public class PilotPenerbanganModel implements Serializable {

    public PilotPenerbanganModel() {
        
    }

    @Id
    @Column(name = "id_pilot_penerbangan")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPilotPenerbangan;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tanggal_penugasan_pilot_penerbangan")
    private Date tanggalPenugasanPilotPenerbangan;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pilot_id", referencedColumnName = "id_pilot", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PilotModel pilotModel;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "penerbangan_id", referencedColumnName = "id_penerbangan", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PenerbanganModel penerbanganModel;

    public Long getIdPilotPenerbangan() {
        return this.idPilotPenerbangan;
    }

    public Date getTanggalPenugasanPilotPenerbangan() {
        return this.tanggalPenugasanPilotPenerbangan;
    }

    public void setTanggalPenugasanPilotPenerbangan(Date tanggalPenugasanPilotPenerbangan) {
        this.tanggalPenugasanPilotPenerbangan = tanggalPenugasanPilotPenerbangan;
    }
    
    public PilotModel getPilotModel() {
        return this.pilotModel;
    }

    public void setPilotModel(PilotModel pilotModel) {
        this.pilotModel = pilotModel;
    }

    public PenerbanganModel getPenerbanganModel() {
        return this.penerbanganModel;
    }

    public void setPenerbanganModel(PenerbanganModel penerbanganModel) {
        this.penerbanganModel = penerbanganModel;
    }
}

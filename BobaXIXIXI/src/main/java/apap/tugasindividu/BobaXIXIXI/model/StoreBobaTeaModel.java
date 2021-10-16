package apap.tugasindividu.BobaXIXIXI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.Store;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity

@Table(name = "storebobatea")
public class StoreBobaTeaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStoreBobaTea;

    @NotNull
    @Size(max = 255)
    @Column(name = "production_code", nullable = false)
    private String productionCode;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idStore", referencedColumnName = "idStore", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StoreModel store;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idBobaTea", referencedColumnName = "idBobaTea", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BobaTeaModel bobaTea;
}

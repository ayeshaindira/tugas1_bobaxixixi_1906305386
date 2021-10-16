package apap.tugasindividu.BobaXIXIXI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity

@Table(name = "store")
public class StoreModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStore;

    @NotNull
    @Size(max=30)
    @Column(name="name", nullable = false)
    private String storeName;

    @NotNull
    @Size(max=255)
    @Column(name="address", nullable = false)
    private String storeAddress;

    @NotNull
    @Size(max=10)
    @Column(name="store_code", nullable = false, unique = true)
    private String storePhone;

    @NotNull
    @Column(name="open_hour", nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime openHour;

    @NotNull
    @Column(name="close_hour", nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime closeHour;

    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(
            name ="store_bobatea",
            joinColumns = @JoinColumn(name="id_store"),
            inverseJoinColumns = @JoinColumn(name = "id_bobatea")
    )
    List<BobaTeaModel> listBobaTea;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="idManager", referencedColumnName = "idManager", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ManagerModel manager;
}

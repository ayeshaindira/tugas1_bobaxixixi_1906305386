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

@Table(name = "boba_tea")
public class BobaTeaModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBobaTea;

    @NotNull
    @Size(max=255)
    @Column(name="name", nullable = false)
    private String bobaTeaName;

    @NotNull
    @Column(name="price", nullable = false)
    private Long bobaTeaPrice;

    @NotNull
    @Column(name="size", nullable = false)
    private Long bobaTeaSize;

    @NotNull
    @Column(name="ice_level", nullable = false)
    private Long bobaTeaIceLevel;

    @NotNull
    @Column(name="sugar_level", nullable = false)
    private Long bobaTeaSugarLevel;


    //Relasi dengan StoreModel
    @ManyToMany
    @JoinTable(
            name = "bobatea_store",
            joinColumns = @JoinColumn(name = "id_bobatea"),
            inverseJoinColumns = @JoinColumn(name = "id_store"))
    List<StoreModel> listStore;

    //Relasi dengan ToppingModel
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idTopping", referencedColumnName = "idTopping", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ToppingModel topping;

}

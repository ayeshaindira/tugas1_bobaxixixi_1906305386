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
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity

@Table(name = "topping")
public class ToppingModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTopping;

    @NotNull
    @Size(max=255)
    @Column(name="name", nullable = false)
    private String toppingName;

    @NotNull
    @Column(name="price", nullable = false)
    private Long toppingPrice;

    //Relasi dengan BobaTeaModel
    @OneToMany(mappedBy = "topping", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BobaTeaModel> listBobaTea;
}

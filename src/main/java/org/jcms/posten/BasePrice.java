package org.jcms.posten;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "baseprice")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BasePrice {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "value", nullable = false)
    private Integer value;
}

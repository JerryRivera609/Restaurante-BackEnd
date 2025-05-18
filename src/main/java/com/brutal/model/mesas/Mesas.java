package com.brutal.model.mesas;

import com.brutal.generics.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mesas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Mesas extends BaseEntity {
    private Integer numero;
    private String estado;
}

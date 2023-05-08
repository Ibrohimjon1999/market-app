package uz.market.yuksalish.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
@Data
@Entity
public class Role implements Serializable {
    @Id
    private String name;
}

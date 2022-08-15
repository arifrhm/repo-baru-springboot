package com.dibimbing.dibimbing.model;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "rekening")
@Where(clause = "deleted_date is null")
public class Rekening extends AbstractDate implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama", length = 80, nullable = false)
    private String nama;

    @Column(name = "jenis", length = 100, nullable = false)
    private String jenis;

    @Column(name = "nomor", length = 100, nullable = false)
    private String nomor;

    @JsonIgnore
    @ManyToOne(targetEntity = Karyawan.class, cascade = CascadeType.ALL)
    private Karyawan karyawan;

}

package com.dibimbing.dibimbing.model;


import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Where;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "karyawan")
@Where(clause = "deleted_date is null")
public class Karyawan extends AbstractDate implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama", nullable = false, length = 100)
    private String nama;

    @Column(name = "jk", nullable = false, length = 45)
    private String jk;

    @Column(name = "dob", nullable = false)
    private Date dob;

    @Column(name = "alamat", nullable = false)
    private String alamat;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @JsonIgnore
    @OneToMany(targetEntity = Rekening.class, cascade = CascadeType.ALL)
    private List<Rekening> rekening;

    @OneToMany(targetEntity = KaryawanTraining.class, cascade = CascadeType.ALL)
    private List<KaryawanTraining> karyawanTraining;


}

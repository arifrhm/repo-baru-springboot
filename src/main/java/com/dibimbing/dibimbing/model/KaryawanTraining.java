package com.dibimbing.dibimbing.model;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "karyawan_training")
@Where(clause = "deleted_date is null")
public class KaryawanTraining extends AbstractDate implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "training_date")
    private Date training_date;

    @ManyToOne(targetEntity = Karyawan.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "karyawan_id", referencedColumnName = "id")
    private Karyawan karyawan;

    @ManyToOne(targetEntity = Training.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "training_id", referencedColumnName = "id")
    private Training training;
}

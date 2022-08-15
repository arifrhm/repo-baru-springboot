package com.dibimbing.dibimbing.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class KaryawanMybatis extends AbstractDate implements Serializable {
    Long resid;
    String resnama;
    String resjk;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date resdob;
    String resalamat;
    String resstatus;
    String resnik;
    String resnpwp;
    String reserordesc;
    Integer reserorcode;
}

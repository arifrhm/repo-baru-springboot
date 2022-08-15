package com.dibimbing.dibimbing.utils;

import org.springframework.stereotype.Component;

@Component
public class QuerySP {

    public String save = "CREATE OR REPLACE PROCEDURE public.savekaryawan(" +
            "INOUT resid bigint, " +
            "INOUT resnama character varying, " +
            "INOUT resjk character varying, " +
            "INOUT resdob date, " +
            "INOUT resalamat text, " +
            "INOUT resstatus character varying, " +
            "INOUT reserordesc character varying, " +
            "INOUT reserorcode integer)\n" +
            " LANGUAGE plpgsql\n" +
            "AS $procedure$\n" +
            "\tBEGIN\n" +
            "\t\tif resnama is null Then \n" +
            "\t\t  raise notice 'nama kosong';\n" +
            "\t\t reserordesc = 'nama wajib diisi';\n" +
            "\t\t reserorcode = 404;\n" +
            "\t\t return;\n" +
            "\t\telse \n" +
            "\t\t raise notice 'nama  ada';\n" +
            "\t\tend if; \n" +
            "\t\n" +
            "\t\tinsert into public.karyawan \n" +
            "\t\t(id,nama,jk,dob,alamat,status,created_date,updated_date)\n" +
            "\t\tselect nextval('karyawan_id_seq'),\n" +
            "\t\t\tresnama,\n" +
            "\t\t\tresjk,\n" +
            "\t\t\tresdob,\n" +
            "\t\t\tresalamat,\n" +
            "\t\t\tresstatus,\n" +
            "\t\t\tnow(),\n" +
            "\t\t\tnow() \n" +
            "\t\treturning id into resid;\n" +
            "\t\n" +
            "    reserordesc = 'sukses';\n" +
            "\treserorcode = 200;\n" +
            "\t\tcommit;\n" +
            "\tEND;\n" +
            "$procedure$\n" +
            ";";

    public String updatekaryawan ="CREATE OR REPLACE PROCEDURE public.updatekaryawan(" +
            "INOUT resid bigint, " +
            "INOUT resnama character varying, " +
            "INOUT resjk character varying, " +
            "INOUT resdob date, " +
            "INOUT resalamat text, " +
            "INOUT resstatus character varying, " +
            "INOUT reserordesc character varying, " +
            "INOUT reserorcode integer)\n" +
            " LANGUAGE plpgsql\n" +
            "AS $procedure$\n" +
            "\tBEGIN\n" +
            "\t\tif not exists (select id from karyawan k where id = resid) then\n" +
            "\t\t\t\traise notice 'id tidak ada';\n" +
            "\t\t\treturn;\n" +
            "\t\telse\n" +
            "\t\t\t\traise notice 'id ada';\n" +
            "\t\tend if;\n" +
            "\t\n" +
            "\t\tupdate public.karyawan\n" +
            "\t\t\tset \n" +
            "\t\t\tnama = resnama,\n" +
            "\t\t\tjk = resjk,\n" +
            "\t\t\tdob = resdob,\n" +
            "\t\t\talamat = resalamat,\n" +
            "\t\t\tstatus = resstatus\n" +
            "\t\t\twhere id = resid returning id into resid;\n" +
            "\t\tcommit;\n" +
            "\tEND;\n" +
            "$procedure$\n" +
            ";\n";

    public String getkaryawan = "CREATE OR REPLACE FUNCTION public.getkaryawan(rqid bigint)\n" +
            " RETURNS TABLE(resid bigint, resnama character varying, resjk character varying, resdob timestamp, resalamat character varying, resstatus character varying)\n" +
            " LANGUAGE plpgsql\n" +
            "AS $function$\n" +
            "\tBEGIN\n" +
            "\t\treturn query\n" +
            "\t\tselect e.id ,e.nama, e.jk, e.dob, e.alamat, e.status\n" +
            "\t\tfrom public.karyawan as e\n" +
            "\t\twhere id = rqid;\n" +
            "\tEND;\n" +
            "$function$\n" +
            ";";

    public String getlistkaryawanbynama = "CREATE OR REPLACE FUNCTION public.getlistkaryawanbynama(rqnama character varying)\n" +
            " RETURNS TABLE(" +
            "resid bigint, " +
            "resnama character varying, " +
            "resjk character varying, " +
            "resdob date, " +
            "resalamat text, " +
            "resstatus character varying)" +
            " LANGUAGE plpgsql\n" +
            "AS $function$\n" +
            "DECLARE \n" +
            "    var_r record;\n" +
            "BEGIN\n" +
            "    FOR var_r IN(SELECT \n" +
            "                id,\n" +
            "                nama,\n" +
            "                jk,\n" +
            "                dob,\n" +
            "                alamat,\n" +
            "                status\n" +
            "                FROM karyawan\n" +
            "                WHERE nama ILIKE  rqnama)  \n" +
            "    LOOP\n" +
            "        resid :=  var_r.id  ; \n" +
            "        resnama := var_r.nama ; \n" +
            "        resjk :=  var_r.jk ; \n" +
            "        resdob := var_r.dob  ; \n" +
            "        resalamat :=  var_r.alamat  ; \n" +
            "        resstatus := var_r.status ;\n" +
            "        RETURN NEXT;\n" +
            "    END LOOP;\n" +
            "\n" +
            "\tEND;\n" +
            "$function$\n" +
            ";\n";

    public String deletekaryawan = "CREATE OR REPLACE PROCEDURE public.deletekaryawan(INOUT resid bigint, INOUT reserordesc character varying, INOUT reserorcode integer)\n" +
            " LANGUAGE plpgsql\n" +
            "AS $procedure$\n" +
            "\tBEGIN\n" +
            "\t\tif not exists (select id from karyawan k where id = resid) then\n" +
            "\t\t\t\traise notice 'id tidak ada';\n" +
            "\t\t\treturn;\n" +
            "\t\telse\n" +
            "\t\t\t\traise notice 'id ada';\n" +
            "\t\tend if;\n" +
            "\t\n" +
            "\t\tupdate public.karyawan\n" +
            "\t\t\tset \n" +
            "\t\t\tdeleted_date = now()\n" +
            "\t\t\twhere id = resid returning id into resid;\n" +
            "\t\tcommit;\n" +
            "\tEND;\n" +
            "$procedure$\n" +
            ";\n";

}

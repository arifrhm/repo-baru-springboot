package com.dibimbing.assignment3.sp.testing;

import com.dibimbing.assignment3.utils.QuerySP;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KaryawanJunitRestTemplateMybatis {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Autowired
    public QuerySP querySP;

    @Test
    public void callAllSP(){
        jdbcTemplate.execute(querySP.save);
        jdbcTemplate.execute(querySP.updatekaryawan);
        jdbcTemplate.execute(querySP.getkaryawan);
        jdbcTemplate.execute(querySP.getlistkaryawan);
        jdbcTemplate.execute(querySP.deletekaryawan);
    }

    @Test
    public void  saveSukses() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        String bodyTesting = "{\n" +
                "    \"id\":\"0\",\n" +
                "    \"nama\":\"Rahman\",\n" +
                "    \"jk\":\"laki-laki\",\n" +
                "    \"dob\":\"2022-10-03\",\n" +
                "    \"alamat\":\"Jakarta\",\n" +
                "    \"status\":\"Belum Menikah\",\n" +
                "    \"nik\":\"654654564\",\n" +
                "    \"npwp\":\"654654654\",\n" +
                "    \"eror_desc\":\"OK\",\n" +
                "    \"eror_code\":\"200\"\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<>(bodyTesting, headers);
        System.out.println("bodyTesting  =" + bodyTesting);
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:9098/api/v1/sp/karyawan/save", HttpMethod.POST, entity, String.class);
        System.out.println("response  =" + exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());

    }
    @Test
    public void  updateSukses() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        String bodyTesting = "{\n" +
                "    \"id\":\"1\",\n" +
                "    \"nama\":\"Indah\",\n" +
                "    \"jk\":\"Wanita\",\n" +
                "    \"dob\":\"1990-10-03\",\n" +
                "    \"alamat\":\"Bandung\",\n" +
                "    \"status\":\"Belum Menikah\",\n" +
                "    \"nik\":\"5489754545\",\n" +
                "    \"npwp\":\"548918321\",\n" +
                "    \"eror_desc\":\"OK\",\n" +
                "    \"eror_code\":\"200\"\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<>(bodyTesting, headers);
        System.out.println("bodyTesting  =" + bodyTesting);
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:9098/api/v1/sp/karyawan/update", HttpMethod.PUT, entity, String.class);
        System.out.println("response  =" + exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
    }
    @Test
    public void getIdSukses() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        long id = 1L;
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:9098/api/v1/sp/karyawan/"+id, HttpMethod.GET, null, String.class);
        System.out.println("response  =" + exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
    }
    @Test
    public void listSukses(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");

        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:9098/api/v1/sp/karyawan/list?nama=Indah", HttpMethod.GET, null, String.class);
        System.out.println("response  =" + exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
    }

    @Test
    public void deleteIdSukses() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        long id = 1L;
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:9098/api/v1/sp/karyawan/delete/"+id, HttpMethod.DELETE, null, String.class);
        System.out.println("response  =" + exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
    }
}

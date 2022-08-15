package com.dibimbing.dibimbing.sp.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.dibimbing.dibimbing.utils.QuerySP;

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
        jdbcTemplate.execute(querySP.getlistkaryawanbynama);
        jdbcTemplate.execute(querySP.deletekaryawan);
    }

    @Test
    public void  saveSukses() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        String bodyTesting = "{\n" +
                "    \"id\":null,\n" +
                "    \"nama\":\"Rahman\",\n" +
                "    \"jk\":\"laki-laki\",\n" +
                "    \"dob\":\"1997-03-12\",\n" +
                "    \"alamat\":\"Jakarta\",\n" +
                "    \"status\":\"Belum Menikah\",\n" +
                "    \"eror_desc\":null,\n" +
                "    \"eror_code\":null\n" +
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
                "    \"id\":\"2\",\n" +
                "    \"nama\":\"Indah\",\n" +
                "    \"jk\":\"Wanita\",\n" +
                "    \"dob\":\"1990-10-03\",\n" +
                "    \"alamat\":\"Bandung\",\n" +
                "    \"status\":\"Belum Menikah\",\n" +
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

        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:9098/api/v1/sp/karyawan/list?nama=ar", HttpMethod.GET, null, String.class);
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

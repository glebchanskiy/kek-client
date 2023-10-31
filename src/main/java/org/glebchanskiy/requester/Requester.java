package org.glebchanskiy.requester;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Requester<T> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String restRoute;
    private final Class<T> clazz;
    private final RestTemplate restTemplate = new RestTemplate();

    public Requester(String restRoute, Class<T> clazz) {
        this.restRoute = "http://localhost:8080/api" + restRoute;
        this.clazz = clazz;
    }

    public void insert(T entity) {
//        try {
//            System.out.println("START");
//            System.out.println("URL " + restRoute);
//            System.out.println("BODY " + objectMapper.writeValueAsString(entity));
            restTemplate.postForEntity(restRoute, entity, clazz);
//            Unirest.post(restRoute )
//                    .header("Content-Type", "application/json")
//                    .body(objectMapper.writeValueAsString(entity))
//                    .asString();

//            System.out.println("YEP");
//        } catch ( JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
    }


    public T findById(int id) {
        try {
//            return restTemplate.getForObject(restRoute + '/' + id, clazz);
            HttpResponse<String> stringHttpResponse = Unirest.get(restRoute + '/' + id)
                    .header("accept", "application/json")
                    .asString();
            return objectMapper.readValue(stringHttpResponse.getBody(), clazz);
        } catch (UnirestException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public T findByName(String name) {
        try {
//            return restTemplate.getForObject(restRoute + '/' + name, clazz);
            HttpResponse<String> stringHttpResponse = Unirest.get(restRoute + '/' + name)
                    .header("accept", "application/json")
                    .asString();
            System.out.println("URL " + restRoute + '/' + name);
            System.out.println("kek: " + stringHttpResponse.getBody());
            return objectMapper.readValue(stringHttpResponse.getBody(), clazz);
        } catch (UnirestException | JsonProcessingException e) {
            System.out.println("ERRRRRROR");

            throw new RuntimeException(e);
        }
    }

    public List<T> findAll() {
        try {
            HttpResponse<String> stringHttpResponse = Unirest.get(restRoute)
                    .header("accept", "application/json")
                    .asString();
            return objectMapper.readerForListOf(clazz).readValue(stringHttpResponse.getBody());
        } catch (UnirestException | JsonProcessingException e) {

            throw new RuntimeException(e);
        }
    }

    public void update(int id, T entity) {
//        try {
            restTemplate.postForEntity(restRoute + '/' + id, entity, clazz);
//            Unirest.post(restRoute + '/' + id)
//                    .body(objectMapper.writeValueAsString(entity))
//                    .asString();
//        } catch (UnirestException | JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void deleteById(int id) {
        try {
            Unirest.delete(restRoute + '/' + id).asString();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteByName(String name) {
        try {
            Unirest.delete(restRoute + '/' + name).asString();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }


}

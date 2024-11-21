package com.rocketseat.redirectUrlShortener;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.io.InputStream;
import java.util.Map;

public class Main implements RequestHandler<Map<String, Object>, Map<String, String>> {

    private final S3Client s3Client = S3Client.builder().build();

    @Override
    public Map<String, String> handleRequest(Map<String, Object> input, Context context) {
        String pathParameters = (String) input.get("rawPath");
        // raw path nos retorna tudo que vem depois do dominio
        // se fosse google.com/teste, retornaria /teste

        String shortUrlCode = pathParameters.replace("/", "");
        // aqui estamos dando um replace no / por "", fazendo com que suma a barra
        // e fique apenas o path param. logo, de /teste fica teste.

        if (shortUrlCode == null || shortUrlCode.isEmpty()) {
            throw  new IllegalArgumentException("Invalid input: 'shortUrlCode' is required.");
        }
        // valida se o url esta vazio ou é nulo(se nao possui nenhum path param)

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket("raul-url-shortener-storage")
                .key(shortUrlCode + ".json")
                .build();

        InputStream s3ObjectStream;
        // o input, ou seja, a maneira que estamos recuperando nosso arquivo do s3
        // é uma stream, pois é um pacotinho que vamos pegando aos poucos(vamos captando
        // as informaçoes do s3 aos poucos). por isso, o tipo é InputStream

        try {
            s3ObjectStream = s3Client.getObject(getObjectRequest);
        } catch (Exception e ) {
            throw new RuntimeException("Error fetching URL data from S3: " + e.getMessage(), e);
        }

        UrlData urlData;

        return Map.of();
    }
}
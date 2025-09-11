package org.example.config.feign;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.example.exception.BadRequestException;
import org.example.exception.NotFoundException;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorEncoder implements ErrorDecoder {
    private final ObjectMapper objectMapper = new ObjectMapper();

   @Override
    public Exception decode(String methodKey, Response response){
       try{
           if(response.body()!=null){
               JsonNode node = objectMapper.readTree(response.body().asInputStream());
               String detail = node.has("detail")?node.get("detail").asText():"Unknown error";
               return new NotFoundException(detail);
           }else{
               return new RuntimeException("No error body.");
           }
       }catch (Exception e){
            return new BadRequestException("Failed to parse error.");
       }
   }
}

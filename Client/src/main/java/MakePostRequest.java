import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

class MakePostRequest {
    public void makeRequestWithJsonData(String url, Map<String, Object> json){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(json, headers);

        try{
            restTemplate.postForObject(url, request, String.class);
        } catch (HttpClientErrorException e){
            System.out.println("Error!");
        }
    }

}

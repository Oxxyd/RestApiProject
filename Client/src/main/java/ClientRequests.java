import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ClientRequests {
    public static void main(String[] args) {
        String sensorName = "Sensor_119";

        Sensor sensor = new Sensor(sensorName);
        sensor.registerANewSensor();

        Measurements measurements = new Measurements(sensorName);
        measurements.addMeasurements();
    }

}

class Sensor{
    private String sensorName;

    public Sensor(String sensorName){
        this.sensorName = sensorName;
    }

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    String url = "http://localhost:8080/sensors/registration";

    Map<String, String> json = new HashMap<>();

    public void registerANewSensor(){
        json.put("name", sensorName);

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(json, headers);

        try{
            restTemplate.postForObject(url, request, String.class);
        } catch (HttpClientErrorException e){
            System.out.println("Error!");
        }
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }
}

class Measurements{
    String sensorName;
    String url = "http://localhost:8080/measurements/add";
    MakePostRequest request = new MakePostRequest();

    public Measurements(String sensorName){
        this.sensorName = sensorName;
    }

    Map<String, Object> json;

    public void addMeasurements(){
        Random random = new Random();
        float maxTemperature = 45.0F;

        for(int i = 0; i <= 500; i ++){
            sendMeasurements(random.nextFloat() * maxTemperature, random.nextBoolean(), sensorName);
            System.out.println(i + "'s measurements is added!");
        }
    }

    public void sendMeasurements(float value, boolean raining, String sensorName){

        json = new HashMap<>();
        json.put("value", value);
        json.put("raining", raining);
        json.put("sensorName", sensorName);

        request.makeRequestWithJsonData(url, json);
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }
}
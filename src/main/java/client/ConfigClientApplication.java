package client;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }
}

@RefreshScope
@RestController
class MessageRestController {

    @Value("${message:Hello test}")
    private String message;

    @RequestMapping("/message")
    String getMessage() {
        return this.message;
    }

    @RequestMapping(value = "/available")
    public String available() {
        JSONObject json = new JSONObject();
        try {
            json = new JSONObject();
            json.put("UserName", "Test User");
            json.put("ClientName", "test client");
            JSONArray jArray = new JSONArray();
            jArray.put("https://zeppelin.apache.org/");
            jArray.put("https://mattermost.com/");
            jArray.put("https://www.mysql.com/");
            json.put("ClientURLs", jArray);
        }catch (Exception e){

        }
        return json.toString();
    }

    @PostMapping("/auth")
    public String newEmployee(@RequestBody String jsonStr) {
        JSONObject client  = new JSONObject();
        JSONObject error = new JSONObject();
        boolean login = false;
        try {
            client = new JSONObject();
            client.put("UserName", "Test User");
            client.put("ClientName", "test client");
            JSONArray jArray = new JSONArray();
            jArray.put("https://zeppelin.apache.org/");
            jArray.put("https://mattermost.com/");
            jArray.put("https://www.mysql.com/");
            client.put("ClientURLs", jArray);

            JSONObject json = new JSONObject(jsonStr);
            String name = json.getString("user_id");
            String password = json.getString("password");
            System.out.print("user"+name+"passw"+password);
            if(name.equalsIgnoreCase("1001") && password.equalsIgnoreCase("123456")){

                login = true;
            }else {
                error = new JSONObject();

                error.put("error", "Invalid user id or password");

                login = false;
            }


        } catch (Exception e) {

        }
        if (login)
            return client.toString();
        else
            return error.toString();
    }
}
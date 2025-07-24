package micro.planner.utils.resttemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import micro.planner.entity.User;

//@Component
//public class UserRestBuilder {   // класс для вызова через RestTemplate
//
//    private static final String baseUrl = "http://localhost:8765/planner-users/user";
//
//    public boolean userExist(Long userId) {
//        RestTemplate restTemplate = new RestTemplate();  // создали объект RestTemplate
//        HttpEntity<Long> request = new HttpEntity(userId);  // создали HttpEntity, чтобы поместить внутрь запроса нужный параметр
//
//        ResponseEntity<User> response = null;  // создали ResponseEntity (спец объект-контейнер), в кот. указывается статус ответа, служебная инф-ция или сам объект
//        // если нужно получить объект, вызываем response.getBody() и автоматически произойдет конвертация из JSON в POJO
//        // в текущем вызове нам не нужен объект, тк мы просто проверяем, есть ли такой пользователь
//
//        try {
//            // вызов сервиса с помощью метода exchange(), передаем туда параметры (url, метод, сам запрос и тип объекта, который будет возвращен)
//            response = restTemplate.exchange(baseUrl + "/id", HttpMethod.POST, request, User.class);
//
//            if (response.getStatusCode() == HttpStatus.OK) return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
//}

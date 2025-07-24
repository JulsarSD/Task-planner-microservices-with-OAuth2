package micro.planner.utils.webclient;

import micro.planner.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

//@Component
//public class UserWebClientBuilder {    // класс для вызова через WebClient
//
//    private static final String baseUrl = "http://localhost:8765/planner-users/user";
//    private static final String baseUrlData = "http://localhost:8765/planner-todo/data/";
//
//
//    public boolean userExist(Long userId) {
//        try {
//            User user = WebClient.create(baseUrl) // создать WebClient с помощью baseUrl
//                    .post()  // тип метода
//                    .uri("/id")
//                    .bodyValue(userId)
//                    .retrieve() // метод вызывает микросервис
//                    .bodyToFlux(User.class)  // результат будет упакован в объект-обёртку Флакс
//                    .blockFirst(); // блокирует запрос до получения записи (пишем эту строку, чтобы убрать ассинхронность, в результате чего вернётся именно объект User, а не Flux)
//
//            if (user != null) return true;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
//
//    public Flux<User> userExistsAsync(Long userId){
//
//        Flux<User> fluxUser = WebClient.create(baseUrl)
//                .post()
//                .uri("/id")
//                .bodyValue(userId)
//                .retrieve()
//                .bodyToFlux(User.class);
//
//        return fluxUser;
//    }
//
//    public Flux<User> initUserData(Long userId){
//
//        Flux<User> fluxUser = WebClient.create(baseUrlData)
//                .post()
//                .uri("/init")
//                .bodyValue(userId)
//                .retrieve()
//                .bodyToFlux(User.class);
//
//        return fluxUser;
//    }
//}

package micro.planner.todo.mq;

import micro.planner.todo.service.TestDataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import org.springframework.messaging.Message;

//@Configuration
//public class MessageFunc {    // класс для работы с RabbitMQ
//
//    private TestDataService testDataService;
//
//    public MessageFunc(TestDataService testDataService) {
//        this.testDataService = testDataService;
//    }
//
//    @Bean
//    public Consumer<Message<Long>> newUserActionConsume(){
//        return message -> testDataService.initTestData(message.getPayload());
//    }
//}

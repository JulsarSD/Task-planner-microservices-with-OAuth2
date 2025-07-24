package micro.planner.users.mq;

import lombok.Getter;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

//@Service
//@Getter
//public class MessageFunkAction {  // // класс для работы с RabbitMQ
//
//    private MessageFunk messageFunk;
//
//    public MessageFunkAction(MessageFunk messageFunk) {
//        this.messageFunk = messageFunk;
//    }
//
//    public void sendNewUserMessage(Long id){
//        messageFunk.getInnerBus().emitNext(MessageBuilder.withPayload(id).build(), Sinks.EmitFailureHandler.FAIL_FAST);
//        System.out.println("Message sent: " + id);
//    }
//}

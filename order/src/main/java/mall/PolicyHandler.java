package mall;

import mall.config.kafka.KafkaProcessor;

// import java.util.Optional;

// import com.fasterxml.jackson.databind.DeserializationFeature;
// import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDeliveryStarted_ChangeStatus(@Payload DeliveryStarted deliveryStarted){

        // deliveryStarted에 대한 유효성 검사
        if(!deliveryStarted.validate()) return;

        System.out.println("\n\n##### listener ChangeStatus : " + deliveryStarted.toJson() + "\n\n");

        // NULL 값을 담을 수 있는 객체로 orderRepository에서 deliveryStarted에서 확인한 orderId로 optionalOrder 객체를 생성
        java.util.Optional<Order> optionalOrder = orderRepository.findById(deliveryStarted.getOrderId());
        // order에 모든 속성을 담음
        Order order = optionalOrder.get();
        // delivery에 관련된 속성 변경
        order.setDeliveryId(deliveryStarted.getId());
        order.setDeliveryStatus(deliveryStarted.getDeliveryStatus());

        orderRepository.save(order);
            
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}

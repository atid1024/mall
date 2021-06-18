package mall;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import java.util.List;

 @RestController
 public class DeliveryController {
     @Autowired DeliveryRepository deliveryRepository;
     @RequestMapping(value = "/deliveries/cancelDelivery",
                     method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public Boolean cancelDelivery(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
            String delivery_Id = request.getParameter("id");
            java.util.Optional<Delivery> optionalDelivery = deliveryRepository.findById(Long.valueOf(delivery_Id));
            Delivery delivery = optionalDelivery.get();
            deliveryRepository.delete(delivery);
            
            return true;
            }

 }

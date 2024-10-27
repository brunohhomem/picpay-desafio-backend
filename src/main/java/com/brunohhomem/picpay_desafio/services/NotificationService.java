package com.brunohhomem.picpay_desafio.services;

import com.brunohhomem.picpay_desafio.domain.user.User;
import com.brunohhomem.picpay_desafio.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

//        ResponseEntity<String> notificationReponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequest, String.class);
//
//        if (!(notificationReponse.getStatusCode() == HttpStatus.OK)){
//            System.out.println("Erro ao enviar notificação");
//
//            throw new Exception("Serviço de notificação está fora do ar.");
//        }
        System.out.println("Notificado para o usuário");
    }

}

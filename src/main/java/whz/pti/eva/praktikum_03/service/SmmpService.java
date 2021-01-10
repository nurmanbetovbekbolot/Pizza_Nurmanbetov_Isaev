package whz.pti.eva.praktikum_03.service;


import whz.pti.eva.praktikum_03.dto.PayActionResponseDTO;

public interface SmmpService {
    PayActionResponseDTO doPayAction(String from, String to, String pcontent);
}

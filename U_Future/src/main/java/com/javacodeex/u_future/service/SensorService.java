package com.javacodeex.u_future.service;

import com.javacodeex.u_future.domain.converter.SensorConverter;
import com.javacodeex.u_future.domain.dto.SensorDTO;
import com.javacodeex.u_future.domain.entity.Sensor;
import com.javacodeex.u_future.exception.SensorNotFoundException;
import com.javacodeex.u_future.repository.SensorRepository;
import com.javacodeex.u_future.utils.BindCheckerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.Optional;

/**
 * Сервис для работы с сенсорами.
 * <p>Предоставляет методы для создания сенсоров, поиска сенсора по имени и проверки его существования в базе данных.</p>
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorsRepository;
    private final SensorConverter sensorConverter;

    /**
     * Создает новый сенсор.
     * <p>Метод выполняет проверку на ошибки валидации, затем создает объект {@link Sensor}
     * и сохраняет его в базе данных. Если сенсор с таким именем уже существует, генерируется исключение {@link SensorNotFoundException}.</p>
     *
     * @param sensorDTO объект {@link SensorDTO}, содержащий данные сенсора.
     * @param bindingResult объект {@link BindingResult}, который содержит информацию о возможных ошибках валидации.
     * @throws SensorNotFoundException если сенсор с таким именем уже существует в базе данных.
     */
    @Transactional
    public void create(SensorDTO sensorDTO, BindingResult bindingResult) {
        BindCheckerUtil.checkBind(bindingResult);
        Sensor sensor = sensorConverter.toEntity(sensorDTO);
        if(sensorsRepository.findSensorByName(sensor.getName()).isPresent()){
            throw new RuntimeException("Sensor already exists");
        }
        sensorsRepository.save(sensor);
    }

    /**
     * Находит сенсор по имени.
     * <p>Ищет сенсор в базе данных по имени. Если сенсор не найден, генерируется исключение {@link SensorNotFoundException}.</p>
     *
     * @param name имя сенсора.
     * @return объект {@link Sensor}, если сенсор с таким именем найден.
     * @throws SensorNotFoundException если сенсор с таким именем не найден в базе данных.
     */
    public Sensor findOneByNameOrElseThrowException(String name) {
        Optional<Sensor> sensorOptional = sensorsRepository.findSensorByName(name);
        if(!sensorOptional.isPresent()){
            throw new SensorNotFoundException();
        }
        return sensorOptional.get();
    }

}

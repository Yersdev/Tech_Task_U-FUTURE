package com.javacodeex.u_future.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурационный класс, содержащий настройки для приложения.
 * В этом классе создается бин ModelMapper, используемый для маппинга объектов.
 */
@Configuration
public class AppConfig {

    /**
     * Создает и возвращает бин ModelMapper, который используется для преобразования
     * между различными типами объектов, например, для маппинга DTO в сущности и наоборот.
     *
     * @return объект ModelMapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

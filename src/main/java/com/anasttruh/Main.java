package com.anasttruh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Главный класс приложения Laba1
 * Демонстрация: логирование + работа с JSON
 */
public class Main {

    // Инициализация логгера (вместо System.out)
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("🚀 Запуск приложения Laba1...");

        // Создаём объект User
        User user = new User(1, "Anastasia", "anasttruh@example.com");
        logger.info("📦 Создан объект: {}", user);

        // Сериализация в JSON
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(user);
            logger.info("✅ Сериализация в JSON: {}", json);

            // Десериализация из JSON
            User restoredUser = mapper.readValue(json, User.class);
            logger.info("✅ Десериализация из JSON: {}", restoredUser);

            // Проверка целостности данных
            if (user.getId() == restoredUser.getId() &&
                    user.getName().equals(restoredUser.getName())) {
                logger.info("✔️ Данные совпадают после сериализации/десериализации");
            } else {
                logger.warn("⚠️ Данные не совпадают!");
            }

        } catch (JsonProcessingException e) {
            logger.error("❌ Ошибка работы с JSON: {}", e.getMessage(), e);
        }

        logger.info("🏁 Приложение завершено успешно.");
    }
}
package ru.mirea.lilkhalil.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Utils {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = Utils.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            log.error("Ошибка загрузки файла конфигурации: ", ex);
        }
    }

    /**
     * Размер оперативной памяти
     */
    public static final int MEMORY_SIZE = Integer.parseInt(properties.getProperty("memory.size", "1024"));
    /**
     * Смещение данных в оперативной памяти
     */
    public static final int DATA_OFFSET = Integer.parseInt(properties.getProperty("data.offset", "512"));

    /**
     * Период вызова инструкций процессора
     */
    public static final long CYCLE_PERIOD = Long.parseLong(properties.getProperty("cycle.period", "500"));
}

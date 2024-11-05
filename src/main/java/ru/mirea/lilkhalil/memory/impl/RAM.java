package ru.mirea.lilkhalil.memory.impl;

import lombok.extern.slf4j.Slf4j;
import ru.mirea.lilkhalil.memory.Memory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static ru.mirea.lilkhalil.utils.Utils.DATA_OFFSET;

/**
 * Класс {@code RAM} реализует интерфейс {@link Memory} и представляет
 * простую модель оперативной памяти, основанную на массиве целых чисел.
 * <p>
 * Класс предоставляет методы для чтения и записи данных по определенным
 * адресам, которые представляют индексы в массиве.
 * </p>
 */
@Slf4j
public class RAM implements Memory {

    /** Массив, представляющий память, где каждый элемент является ячейкой памяти. */
    private final int[] memory;

    /**
     * Создает экземпляр класса {@code RAM} с указанным размером памяти.
     *
     * @param size размер массива памяти в ячейках
     */
    public RAM(int size) {
        memory = new int[size];
    }

    /**
     * Читает значение из указанной ячейки памяти.
     *
     * @param address адрес ячейки памяти для чтения (индекс в массиве)
     * @return значение, хранящееся по указанному адресу
     */
    @Override
    public int read(int address) {
        return memory[address];
    }

    /**
     * Записывает значение в указанную ячейку памяти.
     *
     * @param address адрес ячейки памяти для записи (индекс в массиве)
     * @param value   значение, которое необходимо записать в указанную ячейку
     */
    @Override
    public void write(int address, int value) {
        memory[address] = value;
    }

    /**
     * Загружает данные из указанного файла в память. Файл должен содержать
     * адрес и значение в двоичном формате, разделенные двоеточием.
     * Разделяет секции данных и команд на основе пустой строки:
     * - До пустой строки загружает данные в секцию данных с учетом смещения.
     * - После пустой строки загружает команды непосредственно по указанным адресам.
     *
     * @param fileName имя файла, из которого следует загрузить данные в память
     */
    @Override
    public void load(String fileName) {
        boolean isDataSection = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {

                if (line.isBlank()) {
                    isDataSection = false;
                    continue;
                }

                String[] parts = line.split(":");
                int address = Integer.parseInt(parts[0], 2);
                int value = Integer.parseUnsignedInt(parts[1], 2);

                if (isDataSection) {
                    memory[address + DATA_OFFSET] = value;
                } else {
                    memory[address] = value;
                }
            }
        } catch (IOException ex) {
            log.error("Ошибка загрузки файла исходного кода программы", ex);
        }
    }
}

package ru.mirea.lilkhalil.memory.impl;

import ru.mirea.lilkhalil.memory.Memory;

/**
 * Класс {@code RAM} реализует интерфейс {@link Memory} и представляет
 * простую модель оперативной памяти, основанную на массиве целых чисел.
 * <p>
 * Класс предоставляет методы для чтения и записи данных по определенным
 * адресам, которые представляют индексы в массиве.
 * </p>
 */
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
}

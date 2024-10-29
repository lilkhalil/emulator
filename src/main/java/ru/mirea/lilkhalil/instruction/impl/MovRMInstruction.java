package ru.mirea.lilkhalil.instruction.impl;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.memory.Memory;

import static ru.mirea.lilkhalil.utils.Utils.DATA_OFFSET;

/**
 * Класс {@code MovRMInstruction} реализует инструкцию перемещения
 * значения из памяти в регистр для процессора.
 * <p>
 * Инструкция считывает значение из памяти по указанному адресу и
 * загружает его в указанный регистр, затем обновляет счетчик команд.
 * </p>
 */
public class MovRMInstruction implements Instruction {

    /**
     * Выполняет инструкцию перемещения, считывая значение из памяти
     * и загружая его в указанный регистр.
     *
     * @param cpu       объект {@link CPU}, представляющий центральный процессор
     * @param memory    объект {@link Memory}, представляющий доступ к памяти
     * @param registers массив регистров процессора
     * @param command   целочисленный код команды, содержащий индекс регистра и адрес в памяти
     * @param pc        текущее значение счетчика команд (Program Counter)
     */
    @Override
    public void execute(CPU cpu, Memory memory, int[] registers, int command, int pc) {

        int registerIdx = (command >> 4) & 0xF;
        int value = memory.read((command & 0xF) + DATA_OFFSET);

        registers[registerIdx] = value;

        cpu.setPc(++pc);
    }
}

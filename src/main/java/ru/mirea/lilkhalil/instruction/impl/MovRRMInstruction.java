package ru.mirea.lilkhalil.instruction.impl;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.memory.Memory;

/**
 * Класс {@code MovRRMInstruction} реализует инструкцию перемещения
 * значения из памяти, адресуемой через регистр, в другой регистр для процессора.
 * <p>
 * Инструкция считывает значение из памяти по адресу, указанному в
 * исходном регистре, и загружает его в целевой регистр, затем обновляет
 * счетчик команд.
 * </p>
 */
public class MovRRMInstruction implements Instruction {

    /**
     * Выполняет инструкцию перемещения, считывая значение из памяти
     * по адресу, заданному в исходном регистре, и загружая его в
     * целевой регистр.
     *
     * @param cpu       объект {@link CPU}, представляющий центральный процессор
     * @param memory    объект {@link Memory}, представляющий доступ к памяти
     * @param registers массив регистров процессора
     * @param command   целочисленный код команды, содержащий индексы регистров
     * @param pc        текущее значение счетчика команд (Program Counter)
     */
    @Override
    public void execute(CPU cpu, Memory memory, int[] registers, int command, int pc) {

        int destIdx = (command >> 4) & 0xF;
        int sourceIdx = command & 0xF;
        int value = memory.read(registers[sourceIdx]);

        registers[destIdx] = value;

        cpu.setPc(++pc);
    }
}

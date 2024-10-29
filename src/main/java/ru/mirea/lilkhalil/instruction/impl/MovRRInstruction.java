package ru.mirea.lilkhalil.instruction.impl;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.memory.Memory;

/**
 * Класс {@code MovRRInstruction} реализует инструкцию перемещения
 * значения из одного регистра в другой для процессора.
 * <p>
 * Инструкция копирует значение из исходного регистра в целевой регистр
 * и обновляет счетчик команд.
 * </p>
 */
public class MovRRInstruction implements Instruction {

    /**
     * Выполняет инструкцию перемещения, копируя значение из указанного
     * регистра в другой регистр.
     *
     * @param cpu       объект {@link CPU}, представляющий центральный процессор
     * @param memory    объект {@link Memory}, представляющий доступ к памяти (не используется)
     * @param registers массив регистров процессора
     * @param command   целочисленный код команды, содержащий индексы регистров
     * @param pc        текущее значение счетчика команд (Program Counter)
     */
    @Override
    public void execute(CPU cpu, Memory memory, int[] registers, int command, int pc) {

        int destIdx = (command >> 4) & 0xF;
        int sourceIdx = command & 0xF;
        registers[destIdx] = registers[sourceIdx];

        cpu.setPc(++pc);
    }
}

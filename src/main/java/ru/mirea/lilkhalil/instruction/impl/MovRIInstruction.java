package ru.mirea.lilkhalil.instruction.impl;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.memory.Memory;

/**
 * Класс {@code MovRIInstruction} реализует инструкцию перемещения
 * непосредственного значения в регистр для процессора.
 * <p>
 * Инструкция загружает непосредственное значение в указанный регистр
 * и обновляет счетчик команд.
 * </p>
 */
public class MovRIInstruction implements Instruction {

    /**
     * Выполняет инструкцию перемещения, загружая непосредственное значение
     * в указанный регистр.
     *
     * @param cpu       объект {@link CPU}, представляющий центральный процессор
     * @param memory    объект {@link Memory}, представляющий доступ к памяти (не используется)
     * @param registers массив регистров процессора
     * @param command   целочисленный код команды, содержащий регистр и непосредственное значение
     * @param pc        текущее значение счетчика команд (Program Counter)
     */
    @Override
    public void execute(CPU cpu, Memory memory, int[] registers, int command, int pc) {

        int indirect = (command >> 8) & 0xFFFFF;

        int registerIdx = (command >> 4) & 0xF;

        registers[registerIdx] = indirect;

        cpu.setPc(++pc);
    }
}

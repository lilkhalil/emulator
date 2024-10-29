package ru.mirea.lilkhalil.instruction.impl;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.memory.Memory;

/**
 * Класс {@code JgeInstruction} реализует инструкцию перехода (jump) при
 * условии "больше или равно нулю" для процессора.
 * <p>
 * Инструкция проверяет значения флагов и изменяет счетчик команд (PC)
 * в зависимости от результата сравнения.
 * </p>
 */
public class JgeInstruction implements Instruction {

    /**
     * Выполняет инструкцию перехода, изменяя счетчик команд на заданное
     * значение, если выполняется условие "больше или равно нулю".
     * Условие проверяется на основе флагов нуля и знака.
     *
     * @param cpu       объект {@link CPU}, представляющий центральный процессор
     * @param memory    объект {@link Memory}, представляющий доступ к памяти (не используется)
     * @param registers массив регистров процессора (не используется)
     * @param command   целочисленный код команды, содержащий адрес перехода
     * @param pc        текущее значение счетчика команд (Program Counter)
     */
    @Override
    public void execute(CPU cpu, Memory memory, int[] registers, int command, int pc) {

        int indirect = (command >> 8) & 0xFFFFF;

        boolean zeroFlag = cpu.isZeroFlag();
        boolean signFlag = cpu.isSignFlag();

        if (zeroFlag || !signFlag) {
            cpu.setPc(indirect);
        } else {
            cpu.setPc(++pc);
        }
    }
}

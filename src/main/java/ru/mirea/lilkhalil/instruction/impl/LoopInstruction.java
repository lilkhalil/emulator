package ru.mirea.lilkhalil.instruction.impl;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.memory.Memory;

/**
 * Класс {@code LoopInstruction} реализует инструкцию цикла для процессора.
 * <p>
 * Инструкция уменьшает значение счетчика в регистре и, если счетчик
 * больше нуля, выполняет переход по указанному адресу.
 * </p>
 */
public class LoopInstruction implements Instruction {

    /**
     * Выполняет инструкцию цикла, уменьшая значение счетчика в регистре
     * и выполняя переход на заданный адрес, если счетчик еще не достиг нуля.
     *
     * @param cpu       объект {@link CPU}, представляющий центральный процессор
     * @param memory    объект {@link Memory}, представляющий доступ к памяти (не используется)
     * @param registers массив регистров процессора
     * @param command   целочисленный код команды, содержащий адрес перехода
     * @param pc        текущее значение счетчика команд (Program Counter)
     */
    @Override
    public void execute(CPU cpu, Memory memory, int[] registers, int command, int pc) {

        int counter = registers[2];
        counter--;
        registers[2] = counter;

        int indirect = (command >> 8) & 0xFFFFF;

        if (counter > 0)
            cpu.setPc(indirect);
        else
            cpu.setPc(++pc);
    }
}

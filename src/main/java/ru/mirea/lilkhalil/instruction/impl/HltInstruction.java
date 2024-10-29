package ru.mirea.lilkhalil.instruction.impl;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.memory.Memory;

/**
 * Класс {@code HltInstruction} реализует команду остановки процессора.
 * <p>
 * При выполнении этой команды процессор прекращает выполнение всех дальнейших команд
 * путем завершения работы планировщика команд.
 * </p>
 */
public class HltInstruction implements Instruction {

    /**
     * Выполняет команду остановки, завершая работу процессора.
     * Остановка достигается за счет завершения службы исполнения команд {@link CPU#getExecutorService()}.
     *
     * @param cpu       объект {@link CPU}, представляющий центральный процессор
     * @param memory    объект {@link Memory}, представляющий доступ к памяти (не используется)
     * @param registers массив регистров процессора (не используется)
     * @param command   целочисленный код команды (не используется)
     * @param pc        текущее значение счетчика команд (не используется)
     */
    @Override
    public void execute(CPU cpu, Memory memory, int[] registers, int command, int pc) {
        cpu.getExecutorService().shutdown();
    }
}

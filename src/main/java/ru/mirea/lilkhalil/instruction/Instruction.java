package ru.mirea.lilkhalil.instruction;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.memory.Memory;

/**
 * Интерфейс {@code Instruction} представляет собой команду, которую
 * процессор может выполнять, используя состояние центрального процессора (CPU).
 * <p>
 * Интерфейс предоставляет метод по умолчанию {@code execute(CPU cpu)}, который
 * определяет общий процесс выполнения инструкции, включая получение значения
 * текущего счетчика команд (PC), памяти и регистров. Реализация конкретных
 * инструкций должна быть предоставлена в перегруженном методе {@code execute(CPU, Memory, int[], int, int)}.
 * </p>
 */
public interface Instruction {

    /**
     * Выполняет инструкцию, используя состояние процессора. Этот метод определяет
     * общий процесс для выполнения команд, включая доступ к счетчику команд, памяти и регистрам.
     * После выполнения конкретной инструкции обновленное состояние регистров выводится на консоль.
     *
     * @param cpu объект {@link CPU}, представляющий центральный процессор, на котором выполняется команда
     */
    default void execute(CPU cpu) {
        int pc = cpu.getPc();
        Memory memory = cpu.getMemory();
        int[] registers = cpu.getRegisters();
        int command = memory.read(pc);

        execute(cpu, memory, registers, command, pc);

        cpu.printRegisters();
    }

    /**
     * Определяет логику выполнения конкретной инструкции.
     *
     * @param cpu       объект {@link CPU}, представляющий центральный процессор, на котором выполняется команда
     * @param memory    объект {@link Memory}, представляющий доступ к памяти
     * @param registers массив регистров, хранящий текущее состояние регистров процессора
     * @param command   код команды, который должен быть выполнен
     * @param pc        текущее значение счетчика команд (program counter, PC)
     */
    void execute(CPU cpu, Memory memory, int[] registers, int command, int pc);
}

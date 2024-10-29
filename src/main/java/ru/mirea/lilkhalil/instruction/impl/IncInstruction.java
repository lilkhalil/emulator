package ru.mirea.lilkhalil.instruction.impl;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.memory.Memory;

/**
 * Класс {@code IncInstruction} реализует команду инкремента регистра.
 * <p>
 * Команда увеличивает значение указанного регистра на единицу и обновляет
 * соответствующие флаги процессора (ноль, знак и переполнение).
 * </p>
 */
public class IncInstruction implements Instruction {

    /**
     * Выполняет команду инкремента, увеличивая значение регистра, указанного в команде.
     * После инкремента обновляет флаги процессора и увеличивает счетчик команд.
     *
     * @param cpu       объект {@link CPU}, представляющий центральный процессор
     * @param memory    объект {@link Memory}, представляющий доступ к памяти (не используется)
     * @param registers массив регистров процессора
     * @param command   целочисленный код команды, содержащий индекс регистра для инкрементации
     * @param pc        текущее значение счетчика команд (Program Counter)
     */
    @Override
    public void execute(CPU cpu, Memory memory, int[] registers, int command, int pc) {

        int registerIdx = (command >> 4) & 0xF;

        registers[registerIdx]++;

        int result = registers[registerIdx];

        updateFlags(cpu, result);

        cpu.setPc(++pc);
    }

    /**
     * Обновляет флаги процессора в зависимости от результата инкремента.
     * Устанавливает флаги нуля, знака и переполнения.
     *
     * @param cpu    объект {@link CPU}, представляющий центральный процессор
     * @param result результат инкремента регистра
     */
    private void updateFlags(CPU cpu, int result) {
        cpu.setZeroFlag(result == 0);
        cpu.setSignFlag(result < 0);
        cpu.setOverflowFlag(result == Integer.MIN_VALUE);
    }
}

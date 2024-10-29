package ru.mirea.lilkhalil.instruction.impl;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.memory.Memory;

/**
 * Класс {@code CmpInstruction} реализует инструкцию сравнения для процессора.
 * <p>
 * Инструкция сравнивает значение регистра с данными из памяти и обновляет
 * флаги процессора (ноль, знак, перенос и переполнение) в зависимости от результата сравнения.
 * </p>
 */
public class CmpInstruction implements Instruction {

    /**
     * Выполняет команду сравнения, извлекая два операнда: значение из регистра и
     * значение из памяти по адресу, указанному другим регистром. После вычисления
     * разности обновляет флаги процессора и увеличивает счетчик команд.
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

        int op1 = registers[destIdx];
        int op2 = memory.read(registers[sourceIdx]);

        int result = op1 - op2;

        updateFlags(cpu, result, op1, op2);

        cpu.setPc(++pc);
    }

    /**
     * Обновляет флаги процессора в зависимости от результата сравнения.
     * Устанавливает флаги нуля, знака, переноса и переполнения.
     *
     * @param cpu    объект {@link CPU}, представляющий центральный процессор
     * @param result результат вычитания второго операнда из первого
     * @param op1    значение первого операнда
     * @param op2    значение второго операнда
     */
    private void updateFlags(CPU cpu, int result, int op1, int op2) {
        cpu.setZeroFlag(result == 0);
        cpu.setSignFlag(result < 0);
        cpu.setCarryFlag(Integer.compareUnsigned(op1, op2) < 0);
        cpu.setOverflowFlag(((op1 ^ op2) < 0) && ((op1 ^ result) < 0));
    }
}

package ru.mirea.lilkhalil.instruction.registry;

import ru.mirea.lilkhalil.instruction.Instruction;

/**
 * Интерфейс {@code InstructionSet} определяет метод для получения инструкции
 * на основе ее кода операции.
 * <p>
 * Реализация этого интерфейса должна предоставлять набор инструкций,
 * доступных для выполнения процессором, и возвращать соответствующую
 * инструкцию в зависимости от переданного кода операции.
 * </p>
 */
public interface InstructionSet {
    /**
     * Возвращает инструкцию, соответствующую заданному коду операции.
     *
     * @param operationCode целочисленный код операции, определяющий инструкцию
     * @return объект {@link Instruction}, соответствующий указанному коду операции
     */
    Instruction getInstruction(int operationCode);
}

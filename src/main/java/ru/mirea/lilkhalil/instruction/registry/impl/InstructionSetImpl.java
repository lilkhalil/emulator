package ru.mirea.lilkhalil.instruction.registry.impl;

import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.instruction.registry.InstructionSet;
import ru.mirea.lilkhalil.instruction.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс {@code InstructionSetImpl} реализует интерфейс {@link InstructionSet} и
 * предоставляет доступ к набору инструкций, которые процессор может выполнять.
 * <p>
 * Класс хранит инструкции в виде сопоставлений между кодами операций и соответствующими
 * объектами инструкций. Если код операции не поддерживается, метод {@code getInstruction}
 * выбрасывает исключение {@link IllegalArgumentException}.
 * </p>
 */
public class InstructionSetImpl implements InstructionSet {

    /** Карта, сопоставляющая коды операций с объектами инструкций. */
    private static final Map<Integer, Instruction> instructions = new HashMap<>();

    static {
        instructions.put(0x0, new HltInstruction());
        instructions.put(0x1, new MovRIInstruction());
        instructions.put(0x2, new MovRRInstruction());
        instructions.put(0x3, new MovRMInstruction());
        instructions.put(0x4, new MovRRMInstruction());
        instructions.put(0x5, new CmpInstruction());
        instructions.put(0x6, new JgeInstruction());
        instructions.put(0x7, new IncInstruction());
        instructions.put(0x8, new LoopInstruction());
    }

    /**
     * Возвращает инструкцию, соответствующую заданному коду операции.
     * Если код операции не найден, выбрасывается исключение.
     *
     * @param operationCode целочисленный код операции, определяющий инструкцию
     * @return объект {@link Instruction}, соответствующий указанному коду операции
     * @throws IllegalArgumentException если код операции не поддерживается
     */
    @Override
    public Instruction getInstruction(int operationCode) {
        return instructions.computeIfAbsent(operationCode, code -> {
            throw new IllegalArgumentException("Operation with code=%s is not supported".formatted(Integer.toHexString(code)));
        });
    }
}

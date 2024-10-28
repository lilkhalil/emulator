package ru.mirea.lilkhalil.instruction.registry.impl;

import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.instruction.registry.InstructionSet;
import ru.mirea.lilkhalil.instruction.impl.*;

import java.util.HashMap;
import java.util.Map;

public class InstructionSetImpl implements InstructionSet {

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

    @Override
    public Instruction getInstruction(int operationCode) {
        return instructions.computeIfAbsent(operationCode, code -> {
            throw new IllegalArgumentException("Operation with code=%s is not supported".formatted(Integer.toHexString(code)));
        });
    }
}

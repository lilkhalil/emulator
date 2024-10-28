package ru.mirea.lilkhalil.instruction.registry;

import ru.mirea.lilkhalil.instruction.Instruction;

public interface InstructionSet {
    Instruction getInstruction(int operationCode);
}

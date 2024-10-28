package ru.mirea.lilkhalil.instruction.impl;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.instruction.Instruction;

public class HltInstruction implements Instruction {

    @Override
    public void execute(CPU cpu) {
        cpu.getExecutorService().shutdown();
    }
}

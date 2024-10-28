package ru.mirea.lilkhalil.instruction.impl;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.memory.Memory;

public class HltInstruction implements Instruction {

    @Override
    public void execute(CPU cpu, Memory memory, int[] registers, int command, int pc) {
        cpu.getExecutorService().shutdown();
    }
}

package ru.mirea.lilkhalil.instruction.impl;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.memory.Memory;

public class MovRIInstruction implements Instruction {

    @Override
    public void execute(CPU cpu) {
        int PC = cpu.getPC();
        Memory memory = cpu.getMemory();
        int[] registers = cpu.getRegisters();
        int command = memory.read(PC);

        int indirect = (command >> 8) & 0xFFFFF;

        int registerIdx = (command >> 4) & 0xF;

        registers[registerIdx] = indirect;

        cpu.setPC(++PC);
        cpu.printRegisters();
    }
}

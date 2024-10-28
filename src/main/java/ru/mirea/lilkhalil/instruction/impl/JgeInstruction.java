package ru.mirea.lilkhalil.instruction.impl;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.memory.Memory;

public class JgeInstruction implements Instruction {
    @Override
    public void execute(CPU cpu) {
        int PC = cpu.getPC();
        Memory memory = cpu.getMemory();
        int command = memory.read(PC);

        int indirect = (command >> 8) & 0xFFFFF;

        boolean zeroFlag = cpu.isZeroFlag();
        boolean signFlag = cpu.isSignFlag();

        if (zeroFlag || !signFlag) {
            cpu.setPC(indirect);
        } else {
            cpu.setPC(++PC);
        }

        cpu.printRegisters();
    }
}

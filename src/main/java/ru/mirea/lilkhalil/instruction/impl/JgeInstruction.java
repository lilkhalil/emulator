package ru.mirea.lilkhalil.instruction.impl;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.memory.Memory;

public class JgeInstruction implements Instruction {
    @Override
    public void execute(CPU cpu, Memory memory, int[] registers, int command, int pc) {

        int indirect = (command >> 8) & 0xFFFFF;

        boolean zeroFlag = cpu.isZeroFlag();
        boolean signFlag = cpu.isSignFlag();

        if (zeroFlag || !signFlag) {
            cpu.setPc(indirect);
        } else {
            cpu.setPc(++pc);
        }
    }
}

package ru.mirea.lilkhalil.instruction.impl;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.memory.Memory;

public class MovRIInstruction implements Instruction {

    @Override
    public void execute(CPU cpu, Memory memory, int[] registers, int command, int pc) {

        int indirect = (command >> 8) & 0xFFFFF;

        int registerIdx = (command >> 4) & 0xF;

        registers[registerIdx] = indirect;

        cpu.setPc(++pc);
    }
}

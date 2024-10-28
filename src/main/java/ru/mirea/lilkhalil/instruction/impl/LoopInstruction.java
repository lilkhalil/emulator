package ru.mirea.lilkhalil.instruction.impl;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.memory.Memory;

public class LoopInstruction implements Instruction {
    @Override
    public void execute(CPU cpu, Memory memory, int[] registers, int command, int pc) {

        int counter = registers[2];
        counter--;
        registers[2] = counter;

        int indirect = (command >> 8) & 0xFFFFF;

        if (counter > 0)
            cpu.setPc(indirect);
        else
            cpu.setPc(++pc);
    }
}

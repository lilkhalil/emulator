package ru.mirea.lilkhalil.instruction.impl;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.memory.Memory;

public class LoopInstruction implements Instruction {
    @Override
    public void execute(CPU cpu) {
        int PC = cpu.getPC();
        Memory memory = cpu.getMemory();
        int[] registers = cpu.getRegisters();
        int command = memory.read(PC);

        int counter = registers[2];
        counter--;
        registers[2] = counter;

        int indirect = (command >> 8) & 0xFFFFF;

        if (counter > 0)
            cpu.setPC(indirect);
        else
            cpu.setPC(++PC);

        cpu.printRegisters();
    }
}

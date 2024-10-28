package ru.mirea.lilkhalil.instruction.impl;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.memory.Memory;

public class MovRRMInstruction implements Instruction {

    @Override
    public void execute(CPU cpu) {
        int PC = cpu.getPC();
        Memory memory = cpu.getMemory();
        int[] registers = cpu.getRegisters();
        int command = memory.read(PC);

        int destIdx = (command >> 4) & 0xF;
        int sourceIdx = command & 0xF;
        int value = memory.read(registers[sourceIdx]);

        registers[destIdx] = value;

        cpu.setPC(++PC);
        cpu.printRegisters();
    }
}

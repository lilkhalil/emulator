package ru.mirea.lilkhalil.instruction.impl;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.memory.Memory;

import static ru.mirea.lilkhalil.utils.Utils.DATA_OFFSET;

public class MovRMInstruction implements Instruction {

    @Override
    public void execute(CPU cpu) {
        int PC = cpu.getPC();
        Memory memory = cpu.getMemory();
        int[] registers = cpu.getRegisters();
        int command = memory.read(PC);

        int registerIdx = (command >> 4) & 0xF;
        int value = memory.read((command & 0xF) + DATA_OFFSET);

        registers[registerIdx] = value;

        cpu.setPC(++PC);
        cpu.printRegisters();
    }
}

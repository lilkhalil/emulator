package ru.mirea.lilkhalil.instruction.impl;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.memory.Memory;

public class IncInstruction implements Instruction {
    @Override
    public void execute(CPU cpu, Memory memory, int[] registers, int command, int pc) {

        int registerIdx = (command >> 4) & 0xF;

        registers[registerIdx]++;

        int result = registers[registerIdx];

        updateFlags(cpu, result);

        cpu.setPc(++pc);
    }

    private void updateFlags(CPU cpu, int result) {
        cpu.setZeroFlag(result == 0);
        cpu.setSignFlag(result < 0);
        cpu.setOverflowFlag(result == Integer.MIN_VALUE);
    }
}

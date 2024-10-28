package ru.mirea.lilkhalil.instruction.impl;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.memory.Memory;

public class CmpInstruction implements Instruction {
    @Override
    public void execute(CPU cpu) {
        int PC = cpu.getPC();
        Memory memory = cpu.getMemory();
        int[] registers = cpu.getRegisters();
        int command = memory.read(PC);

        int destIdx = (command >> 4) & 0xF;
        int sourceIdx = command & 0xF;

        int op1 = registers[destIdx];
        int op2 = memory.read(registers[sourceIdx]);

        int result = op1 - op2;

        updateFlags(cpu, result, op1, op2);
        cpu.setPC(++PC);
        cpu.printRegisters();
    }

    private void updateFlags(CPU cpu, int result, int op1, int op2) {
        cpu.setZeroFlag(result == 0);
        cpu.setSignFlag(result < 0);
        cpu.setCarryFlag(Integer.compareUnsigned(op1, op2) < 0);
        cpu.setOverflowFlag(((op1 ^ op2) < 0) && ((op1 ^ result) < 0));
    }
}

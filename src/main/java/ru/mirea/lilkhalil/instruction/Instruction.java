package ru.mirea.lilkhalil.instruction;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.memory.Memory;

public interface Instruction {
    default void execute(CPU cpu) {
        int pc = cpu.getPc();
        Memory memory = cpu.getMemory();
        int[] registers = cpu.getRegisters();
        int command = memory.read(pc);

        execute(cpu, memory, registers, command, pc);

        cpu.printRegisters();
    }

    void execute(CPU cpu, Memory memory, int[] registers, int command, int pc);
}

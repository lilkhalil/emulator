package ru.mirea.lilkhalil.instruction;

import ru.mirea.lilkhalil.cpu.CPU;

public interface Instruction {
    void execute(CPU cpu);
}

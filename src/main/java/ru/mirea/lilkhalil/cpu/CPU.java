package ru.mirea.lilkhalil.cpu;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.Data;
import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.instruction.registry.InstructionSet;
import ru.mirea.lilkhalil.instruction.registry.impl.InstructionSetImpl;
import ru.mirea.lilkhalil.memory.Memory;

@Data
public class CPU {

    public CPU(int PC, Memory memory, long period) {
        this.PC = PC;
        this.memory = memory;
        this.period = period;
    }

    /**
     * EAX, EBX, ECX, EDX, ESI, EDI, EBP, ESP
     */
    private int[] registers = new int[8];

    /**
     * Program counter
     */
    private int PC;

    /**
     * RAM
     */
    private Memory memory;

    /**
     * Flags
     */
    private boolean zeroFlag;
    private boolean signFlag;
    private boolean overflowFlag;
    private boolean carryFlag;

    private final InstructionSet instructionSet = new InstructionSetImpl();

    private final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);
    private final long period;

    public void run() {
        executorService.scheduleAtFixedRate(() -> {
            int command = memory.read(PC);
            int operationCode = (command >> 28) & 0xF;
            Instruction instruction = instructionSet.getInstruction(operationCode);
            instruction.execute(this);
        }, 0, period, TimeUnit.MILLISECONDS);
    }

    public void printRegisters() {
        for (int i = 0; i < registers.length; ++i)
            System.out.printf("R[%d]=%d ", i, registers[i]);
        System.out.println();
    }
}

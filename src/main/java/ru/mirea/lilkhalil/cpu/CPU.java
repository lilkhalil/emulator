package ru.mirea.lilkhalil.cpu;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.mirea.lilkhalil.instruction.Instruction;
import ru.mirea.lilkhalil.instruction.registry.InstructionSet;
import ru.mirea.lilkhalil.instruction.registry.impl.InstructionSetImpl;
import ru.mirea.lilkhalil.memory.Memory;

@Data
@Slf4j
public class CPU {

    public CPU(int pc, Memory memory, long period) {
        this.pc = pc;
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
    private int pc;

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
            int command = memory.read(pc);
            int operationCode = (command >> 28) & 0xF;
            Instruction instruction = instructionSet.getInstruction(operationCode);
            instruction.execute(this);
        }, 0, period, TimeUnit.MILLISECONDS);
    }

    public void printRegisters() {
        log.info(IntStream.range(0, registers.length)
                .mapToObj(i -> String.format("R[%d]=%d", i, registers[i]))
                .collect(Collectors.joining(", ")));
    }
}

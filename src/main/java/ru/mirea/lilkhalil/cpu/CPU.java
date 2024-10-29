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

/**
 * Класс {@code CPU} представляет собой модель центрального процессора, который может
 * выполнять инструкции, используя заданную память и систему регистров.
 * <p>
 * Основная функция класса — метод {@code run()}, который периодически
 * считывает команды из памяти и выполняет их в соответствии с заданным набором инструкций.
 * </p>
 */
@Data
@Slf4j
public class CPU {

    /**
     * Создает экземпляр {@code CPU} с заданным значением счетчика команд,
     * подключенной памятью и периодом для выполнения команд.
     *
     * @param pc     начальное значение счетчика команд (Program Counter, PC)
     * @param memory объект {@link Memory}, представляющий подключенную память
     * @param period период в миллисекундах, с которым процессор выполняет команды
     */
    public CPU(int pc, Memory memory, long period) {
        this.pc = pc;
        this.memory = memory;
        this.period = period;
    }

    /** Массив регистров, представляющий регистры процессора */
    private int[] registers = new int[8];

    /** Счетчик команд (Program Counter, PC), указывающий на текущую команду. */
    private int pc;

    /** Подключенная память, из которой считываются команды и данные. */
    private Memory memory;

    /** Флаг нуля, указывающий на результат операции равный нулю. */
    private boolean zeroFlag;

    /** Флаг знака, указывающий на положительный или отрицательный результат операции. */
    private boolean signFlag;

    /** Флаг переполнения, указывающий на возникновение арифметического переполнения. */
    private boolean overflowFlag;

    /** Флаг переноса, указывающий на возникновение переноса при выполнении операции. */
    private boolean carryFlag;

    /** Набор инструкций, которые процессор может выполнять. */
    private final InstructionSet instructionSet = new InstructionSetImpl();

    /** Служба для планирования выполнения команд через фиксированные интервалы времени. */
    private final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);

    /** Период в миллисекундах, через который выполняется каждая инструкция. */
    private final long period;

    /**
     * Запускает процессор, который будет периодически считывать команды из памяти и выполнять их.
     * Команды считываются на основе значения счетчика команд {@code pc}, и выполняются в соответствии
     * с набором инструкций.
     */
    public void run() {
        executorService.scheduleAtFixedRate(() -> {
            int command = memory.read(pc);
            int operationCode = (command >> 28) & 0xF;
            Instruction instruction = instructionSet.getInstruction(operationCode);
            instruction.execute(this);
        }, 0, period, TimeUnit.MILLISECONDS);
    }

    /**
     * Выводит текущее состояние регистров процессора в лог.
     * Каждый регистр отображается в формате {@code R[index]=value}.
     */
    public void printRegisters() {
        log.info(IntStream.range(0, registers.length)
                .mapToObj(i -> String.format("R[%d]=%d", i, registers[i]))
                .collect(Collectors.joining(", ")));
    }
}

package ru.mirea.lilkhalil;

import lombok.extern.slf4j.Slf4j;
import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.memory.Memory;
import ru.mirea.lilkhalil.memory.impl.RAM;

import static ru.mirea.lilkhalil.utils.Utils.CYCLE_PERIOD;
import static ru.mirea.lilkhalil.utils.Utils.MEMORY_SIZE;

@Slf4j
public class Main
{
    public static void main(String ... args)
    {
        if (args.length == 0) {
            log.error("Укажите путь к файлу в качестве аргумента командной строки.");
            System.exit(1);
        }

        String fileName = args[0];

        Memory memory = new RAM(MEMORY_SIZE);

        memory.load(fileName);

        CPU cpu = new CPU(0, memory, CYCLE_PERIOD);

        cpu.run();
    }
}

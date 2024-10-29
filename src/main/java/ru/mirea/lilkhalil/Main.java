package ru.mirea.lilkhalil;

import ru.mirea.lilkhalil.cpu.CPU;
import ru.mirea.lilkhalil.memory.Memory;
import ru.mirea.lilkhalil.memory.impl.RAM;

import static ru.mirea.lilkhalil.utils.Utils.CYCLE_PERIOD;
import static ru.mirea.lilkhalil.utils.Utils.DATA_OFFSET;
import static ru.mirea.lilkhalil.utils.Utils.MEMORY_SIZE;

public class Main
{
    public static void main(String ... args)
    {
        Memory memory = new RAM(MEMORY_SIZE);

        initializeData(memory, args);
        initializeCommands(memory);

        CPU cpu = new CPU(0, memory, CYCLE_PERIOD);

        cpu.run();
    }

    public static void initializeData(Memory memory, String ... args) {

        if (args.length == 0)
            throw new IllegalArgumentException("Массив данных не передан." +
                    "Укажите данные в аргументах командной строки.");

        if (args.length < 2)
            throw new IllegalArgumentException("В массиве должно быть минимум два элемента." +
                    "Укажите данные в формате [размер массива] [элемент1] [элемент2] ... [элементN]");

        for (int i = 0; i < args.length; ++i)
            memory.write(i + DATA_OFFSET, Integer.parseInt(args[i]));
    }

    public static void initializeCommands(Memory memory) {
        /*
        MOV ecx [Vec_1]
         */
        memory.write(0, 0b0011_00000000000000000000_0010_0000);

        /*
        MOV edi Vec_1 + 1
         */
        memory.write(1, 0b0001_00000000001000000001_0101_0000);

        /*
        MOV eax [edi]
         */
        memory.write(2, 0b0100_00000000000000000000_0000_0101);

        /*
        CMP eax [edi] - МЕТКА find_max адрес 0x3
         */
        memory.write(3, 0b0101_00000000000000000000_0000_0101);

        /*
        JGE skip - переход к адресу метки greaterOrEqual иначе PC++
         */
        memory.write(4, 0b0110_00000000000000000110_0000_0000);

        /*
        MOV eax, [edi]
         */
        memory.write(5, 0b0100_00000000000000000000_0000_0101);

        /*
        INC rdi - МЕТКА skip адрес 0x6
         */
        memory.write(6, 0b0111_00000000000000000000_0101_0000);

        /*
        LOOP find_max - переход к адресу метки [0x3] если ecx > 0
         */
        memory.write(7, 0b1000_00000000000000000011_0000_0000);

        /*
        HALT
         */
        memory.write(8, 0x10);
    }
}

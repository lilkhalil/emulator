package ru.mirea.lilkhalil.memory.impl;

import ru.mirea.lilkhalil.memory.Memory;

public class RAM implements Memory {

    private final int[] memory;

    public RAM(int size) {
        memory = new int[size];
    }

    @Override
    public int read(int address) {
        return memory[address];
    }

    @Override
    public void write(int address, int value) {
        memory[address] = value;
    }
}

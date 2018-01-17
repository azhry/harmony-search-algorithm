/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsa;

/**
 *
 * @author Azhary Arliansyah
 */
public class HarmonySearch {
    
    public final int maxTries = 100;
    public final int targetQuality = Integer.MAX_VALUE;
    public final double harmonyMemoryConsiderationRate = 0.95;
    public final double pitchAdjustmentRate = 0.1;
    public final int instruments = 10;
    public final int[] notes = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
    public final int harmonyMemorySize = 10;
    
    public Harmony[] harmonyMemory = new Harmony[harmonyMemorySize];
    private boolean running;
    
    public HarmonySearch() {
        running = false;
    }
    
    // Harmony search algorithm
    public Harmony search() {
        running = true;
        for (int i = 0; i < harmonyMemorySize; i++) {
            harmonyMemory[i] = getRandomHarmony();
        }
        return harmonyMemory[0];
    }
    
    // Generate a random harmony
    public Harmony getRandomHarmony() {
        int[][] chord = new int[instruments][2];
        for (int i = 0; i < instruments; i++) {
            int idx = (int)Math.floor(Math.random() * notes.length);
            chord[i][0] = notes[idx];
            chord[i][1] = idx;
        }
        return new Harmony(chord);
    }
    
    // Generate a new harmony based on the HMCR and the PAR
    public Harmony getNextHarmony() {
        int[][] chord = new int[instruments][2];
        for (int i = 0; i < instruments; i++) {
            int note, noteIndex;
            if (Math.random() < harmonyMemoryConsiderationRate) {
                // Consider HM. Pick a random harmony, and sample the note at this position in the chord
                int harmonyMemoryIndex = (int)Math.floor(Math.random() * harmonyMemorySize);
                note = harmonyMemory[harmonyMemoryIndex].notes[i]; // Grab note for this instrument
                noteIndex = harmonyMemory[harmonyMemoryIndex].noteIndices[i];
            
                // Do pitch adjustment
                if (Math.random() < pitchAdjustmentRate) {
                    // Adjust the pitch up or down one
                    int adjustment = Math.random() > 0.5 ? 1 : -1;
                    noteIndex = (noteIndex + adjustment + notes.length) % notes.length;
                    note = notes[noteIndex];
                }
            } else {
                // Don't consider the HM. Pick a random note from all possible values.
                noteIndex = (int)Math.floor(Math.random() * notes.length);
                note = notes[noteIndex];
            }
            
            chord[i][0] = note;
            chord[i][1] = noteIndex;
        }
        return new Harmony(chord);
    }
}

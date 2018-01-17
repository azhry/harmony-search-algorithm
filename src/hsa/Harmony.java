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
public class Harmony {
    
    public int[] notes;
    public int[] noteIndices;
    
    // Pull out the note and index of the note from the chord, passed in
    // in the [[note_1, index_1], [note_2, index_2], ...] format
    public Harmony(int[][] chord) {
        notes = new int[chord.length];
        noteIndices = new int[chord.length];
        
        for (int i = 0; i < chord.length; i++) {
            notes[i] = chord[i][0];
            noteIndices[i] = chord[i][1];
        }
    }
    
    // Cache the quality calculation
    public void quality() {
        calculateQuality();
    }
    
    public void calculateQuality() {
        System.out.println("Extend this class to define how a harmony's quality is evaluated");
    }
    
}

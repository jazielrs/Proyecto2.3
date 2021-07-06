/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.stack;

/**
 *
 * @author Jaziel
 */
public class StackException extends Exception {
    public StackException(String message) {
        super("Array is full");
    }
}

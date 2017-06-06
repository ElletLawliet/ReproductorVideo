/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package errors;

import java.io.IOException;

/**
 *
 * @author serbr
 */
public class MusicPathError extends IOException {

    public MusicPathError(String message) {
        super(message);
    }

    public MusicPathError(String message, Throwable cause) {
        super(message, cause);
    }

    public MusicPathError(Throwable cause) {
        super(cause);
    }
    
}

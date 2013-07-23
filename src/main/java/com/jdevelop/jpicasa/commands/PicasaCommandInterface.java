package com.jdevelop.jpicasa.commands;

/**
 * Declares methods used to operate with picasa
 */
public interface PicasaCommandInterface<CommandResult> {

    public CommandResult execute() throws PicasaCommandException;

}

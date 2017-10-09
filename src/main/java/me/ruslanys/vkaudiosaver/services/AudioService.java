package me.ruslanys.vkaudiosaver.services;

import me.ruslanys.vkaudiosaver.domain.Audio;

import java.util.List;

/**
 * @author Ruslan Molchanov (ruslanys@gmail.com)
 */
public interface AudioService {

    List<Audio> getAll();

    void save(Audio audio);

}

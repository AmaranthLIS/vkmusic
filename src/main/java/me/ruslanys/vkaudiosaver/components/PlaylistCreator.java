package me.ruslanys.vkaudiosaver.components;

import me.ruslanys.vkaudiosaver.domain.Audio;

import java.util.Collection;

/**
 * @author Ruslan Molchanov (ruslanys@gmail.com)
 */
public interface PlaylistCreator {

    void playlist(String destination, Collection<Audio> audios);

}

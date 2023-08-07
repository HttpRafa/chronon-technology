package de.rafael.mods.chronon.technology.values;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

@Getter
@AllArgsConstructor
public enum NbtKeys {

    STORED_CHRONONS("storedChronons");

    private final String key;

}

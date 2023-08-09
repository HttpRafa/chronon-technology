package de.rafael.mods.chronon.technology.utils.values;

import de.rafael.mods.chronon.technology.ChrononTech;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Rafael K.
 * @since 07/08/2023
 */

@Getter
public enum NbtKeys {

    STORED_CHRONONS("storedChronons");

    private final String key;

    NbtKeys(String key) {
        this.key = ChrononTech.MOD_ID + "_" + key;
    }

}

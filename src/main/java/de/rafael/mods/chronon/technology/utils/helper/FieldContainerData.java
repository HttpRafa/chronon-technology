package de.rafael.mods.chronon.technology.utils.helper;

import de.rafael.mods.chronon.technology.ChrononTech;
import lombok.Getter;
import net.minecraft.world.inventory.ContainerData;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafael K.
 * @since 09/08/2023
 */

@Getter
@Deprecated(forRemoval = true)
public class FieldContainerData implements ContainerData {

    private final Object instance;
    private final Field[] fields;

    public FieldContainerData(@NotNull Object instance) {
        this.instance = instance;
        List<Field> fields = new ArrayList<>();
        for (Field declaredField : instance.getClass().getDeclaredFields()) {
            if(declaredField.isAnnotationPresent(Sync.class)) {
                fields.add(declaredField);
            }
        }
        this.fields = fields.toArray(Field[]::new);
    }

    @Override
    public int get(int i) {
        try {
            if (fields.length <= i) {
                return 0;
            }
            return fields[i].getInt(instance);
        } catch (IllegalAccessException accessException) {
            ChrononTech.LOGGER.error("Unable to sync data because of missing access to fields", accessException);
            return 0;
        }
    }

    @Override
    public void set(int i, int value) {
        try {
            if (fields.length <= i) return;
            fields[i].set(instance, value);
        } catch (IllegalAccessException accessException) {
            ChrononTech.LOGGER.error("Unable to sync data because of missing access to fields", accessException);
        }
    }

    @Override
    public int getCount() {
        return this.fields.length;
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Sync {}

}

/*
 * MIT License
 *
 * Copyright (c) 2023 Rafael
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package de.rafael.mods.chronon.technology.util.helper;

import de.rafael.mods.chronon.technology.ChrononTech;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import net.minecraft.world.inventory.ContainerData;
import org.jetbrains.annotations.NotNull;

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

import { FieldConfig } from 'app/dynamic-form/models/field-config.interface';

export class Spawner {

    public static spawnInputField(group: string, config: FieldConfig[]): FieldConfig {
        let max: FieldConfig = null;
        for (const c of config) {
            if (c !== null && c.group === group) {
                if (max === null || c.index > max.index) {
                    max = c;
                }
            }
        }
        if (max !== null) {
            const spawn: FieldConfig = {
                group: max.group,
                label: max.label,
                name: (max.group + (max.index + 1)),
                type: max.type,
                order: max.order + 1,
                placeholder: max.placeholder,
                validation: max.validation,
                inputType: max.inputType,
                class: max.class,
                index: max.index + 1
            };

            console.log('Spawner: spawnInputField: Spawned: ', spawn);

            return spawn;
        }

        console.log('Spawner: spawnInputField: Spawned nothing.');

        return null;
    }

}

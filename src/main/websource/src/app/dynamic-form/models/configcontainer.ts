import { FieldConfig } from 'app/dynamic-form/models/field-config.interface';

export class ConfigurationContainer {

    static spawnInputField(name: string, config: FieldConfig[]): FieldConfig {
        let max: FieldConfig = null;
        for (const c of config) {
            if (name === c.name) {
                if (max === null || c.index > max.index) {
                    max = c;
                }
            }
        }
        if (max !== null) {
            const spawn: FieldConfig = {
                id: (max.name + (max.index + 1)),
                label: max.label,
                name: max.name,
                type: max.type,
                order: max.order + 1,
                placeholder: max.placeholder,
                validation: max.validation,
                inputType: max.inputType,
                class: max.class,
                index: max.index + 1
            };

            console.log('ConfigurationContainer: spawnInputField: Spawned: ', spawn);

            return spawn;
        }

        console.log('ConfigurationContainer: spawnInputField: Spawned nothing.');

        return null;
    }

}

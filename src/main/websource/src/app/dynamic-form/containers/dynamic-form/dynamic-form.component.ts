import { Component, OnInit, OnChanges, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { FieldConfig } from 'app/dynamic-form/models/field-config.interface';
import { Spawner } from 'app/dynamic-form/models/spawner';

@Component({
  exportAs: 'dynamicForm',
  selector: 'app-dynamic-form',
  templateUrl: './dynamic-form.component.html',
  styleUrls: ['./dynamic-form.component.css']
})
export class DynamicFormComponent implements OnChanges, OnInit {
  @Input() config: FieldConfig[] = [];
  @Output() submit: EventEmitter<any> = new EventEmitter<any>();

  form: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    console.log('DynamicFormComponent: constructor');
  }

  get controls() {
    return this.config.filter(({ type }) => type !== 'button');
  }

  get changes() {
    return this.form.valueChanges;
  }

  get valid() {
    return this.form.valid;
  }

  get value() {
    return this.form.value;
  }

  ngOnInit() {
    console.log('DynamicFormComponent: ngOnInit');
    this.form = this.createGroup();
  }

  ngOnChanges() {
    console.log('DynamicFormComponent: ngOnChanges');
    if (this.form) {
      const controls = Object.keys(this.form.controls);
      const configControls = this.controls.map((item) => item.name);

      controls
        .filter((control) => configControls.find(x => x === control) !== undefined)
        .map((control) => this.form.removeControl(control));

      configControls
        .filter((control) => controls.find(x => x === control) !== undefined)
        .forEach((name) => {
          const config = this.config.find((control) => control.name === name);
          this.form.addControl(name, this.createControl(config));
        });
    }
  }

  createGroup() {
    const group = this.formBuilder.group({});
    this.config.forEach(control =>
      group.addControl(control.name, this.createControl(control))
    );
    return group;
  }

  addControl(config: FieldConfig) {
    this.form.addControl(config.name, this.createControl(config));
  }

  createControl(config: FieldConfig) {
    const { disabled, validation, value } = config;
    return this.formBuilder.control({disabled, value}, validation);
  }

  handleSubmit(event: Event) {
    event.preventDefault();
    event.stopPropagation();
    this.submit.emit(this.value);
  }

  setDisabled(name: string, disable: boolean) {
    if (this.form.controls[name]) {
      const method = disable ? 'disable' : 'enable';
      this.form.controls[name][method]();
      return;
    }

    this.config = this.config.map((item) => {
      if (item.name === name) {
        item.disabled = disable;
      }
      return item;
    });
  }

  setValue(name: string, value: any) {
    this.form.controls[name].setValue(value, { emitEvent: true});
  }

  reset() {
    this.form.reset();
  }

}

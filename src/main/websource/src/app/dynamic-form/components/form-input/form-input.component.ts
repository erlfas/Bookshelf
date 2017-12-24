import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FieldConfig } from 'app/dynamic-form/models/field-config.interface';
import { Field } from 'app/dynamic-form/models/field.interface';

@Component({
  selector: 'app-form-input',
  templateUrl: './form-input.component.html',
  styleUrls: ['./form-input.component.css']
})
export class FormInputComponent implements Field {

  config: FieldConfig;
  group: FormGroup;

}

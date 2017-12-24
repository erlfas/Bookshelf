import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Field } from 'app/dynamic-form/models/field.interface';
import { FieldConfig } from 'app/dynamic-form/models/field-config.interface';

@Component({
  selector: 'app-form-select',
  templateUrl: './form-select.component.html',
  styleUrls: ['./form-select.component.css']
})
export class FormSelectComponent implements Field {

  config: FieldConfig;
  group: FormGroup;

}

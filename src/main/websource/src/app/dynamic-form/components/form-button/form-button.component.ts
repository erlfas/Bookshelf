import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Field } from 'app/dynamic-form/models/field.interface';
import { FieldConfig } from 'app/dynamic-form/models/field-config.interface';

@Component({
  selector: 'app-form-button',
  templateUrl: './form-button.component.html',
  styleUrls: ['./form-button.component.css']
})
export class FormButtonComponent implements Field {

  config: FieldConfig;
  group: FormGroup;

}

import { ValidatorFn } from '@angular/forms';
import { AddInputField } from 'app/dynamic-form/models/addinput.interface';

export interface FieldConfig {
  id: string;
  name: string;
  type: string;
  order: number;
  options?: string[];
  placeholder?: string;
  disabled?: boolean;
  label?: string;
  validation?: ValidatorFn[];
  value?: any;
  inputType?: string;
  class?: string;
  clickFunc?: AddInputField;
  index?: number;
}


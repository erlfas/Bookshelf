import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { DynamicFormComponent } from 'app/dynamic-form/containers/dynamic-form/dynamic-form.component';
import { DynamicFieldDirective } from 'app/dynamic-form/components/dynamic-field/dynamic-field.directive';
import { FormButtonComponent} from 'app/dynamic-form/components/form-button/form-button.component';
import { FormInputComponent } from 'app/dynamic-form/components/form-input/form-input.component';
import { FormSelectComponent } from 'app/dynamic-form/components/form-select/form-select.component';

// Credits to: https://toddmotto.com/angular-dynamic-components-forms
@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  declarations: [
    DynamicFieldDirective,
    DynamicFormComponent,
    FormButtonComponent,
    FormInputComponent,
    FormSelectComponent
  ],
  exports: [
    DynamicFormComponent
  ],
  entryComponents: [
    FormButtonComponent,
    FormInputComponent,
    FormSelectComponent
  ]
})
export class DynamicFormModule { }
